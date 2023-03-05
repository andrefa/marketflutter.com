#!/bin/sh

# check if the function name was provided
if [ -z "$1" ]; then
  echo "Function name not provided"
  exit 1
fi

env_url=http://localhost:8080
#env_url=http://market-flutter-prod.ca-central-1.elasticbeanstalk.com
token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbG1laWRhLmFuZHJlZkBnbWFpbC5jb20iLCJpYXQiOjE2NzgwNDYxNjEsImV4cCI6MTY3ODA2NDE2MX0.16_dAVZVhicHQez0rkP6_JQglA9sfjTBHgb4QFDm6WbqHnDc6ulqjmgEVrxVhsWz8rBLfxKKCq8qGheTTWjmnQ

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

check ()
{
  curl "$env_url/api/v1/healthcheck"
}

me ()
{
  curl \
    -H "Authorization: Bearer $token" \
    "$env_url/api/v1/user/me"
}

x ()
{
  curl -I \
    -H "Authorization: Bearer $token" \
    "$env_url/x"
}

"$1"
