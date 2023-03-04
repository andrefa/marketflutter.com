#!/bin/sh

# check if the function name was provided
if [ -z "$1" ]; then
  echo "Function name not provided"
  exit 1
fi

env_url=http://localhost:8080
#env_url=http://market-flutter-prod.us-east-1.elasticbeanstalk.com
token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbG1laWRhLmFuZHJlZkBnbWFpbC5jb20iLCJpYXQiOjE2Nzc5Njc2NjIsImV4cCI6MTY3Nzk4NTY2Mn0.cC5OmZPDSyDtumCRBOhWJNg7I_WB5yeHDEVHKdo3jIK1K0jo5tEkd65U-voTYoEW00R0B408mXxkL6mmcefPUw

login ()
{
  curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"user": "almeida.andref@gmail.com", "password": "f1a4821f-b53e-42a8-a1dc-451ace9f6e5b"}' \
   "$env_url/api/v1/auth/login"
}

list_user_assets ()
{
  curl \
    -H "Authorization: Bearer $token" \
    "$env_url/api/v1/assets/available"
  #
}

gen_password ()
{
  generated_password=$(uuidgen)
  echo "${generated_password}"
  curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $token" \
    -d " { \"user\": \"almeida.andref@gmail.com\", \"password\": \"${generated_password}\" } " \
    "$env_url/api/v1/auth/encode_password"
}

start_jobs ()
{
  curl -X PUT \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $token" \
    -d "{}" \
    "$env_url/api/v1/jobs/binance-prices-listener/start"
}

stop_jobs ()
{
    curl -X PUT \
      -H "Content-Type: application/json" \
      -H "Authorization: Bearer $token" \
      -d "{}" \
      "$env_url/api/v1/jobs/binance-prices-listener/stop"
}

reset_price ()
{
      curl -X PUT \
        -H "Content-Type: application/json" \
        -H "Authorization: Bearer $token" \
        -d "{}" \
        "$env_url/api/v1/assets/reset-base-price"
}

"$1"
