#!/bin/sh

# check if the function name was provided
if [ -z "$1" ]; then
  echo "Function name not provided"
  exit 1
fi

login ()
{
  curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"user": "almeida.andref@gmail.com", "password": "f1a4821f-b53e-42a8-a1dc-451ace9f6e5b"}' \
    http://localhost:8080/api/v1/auth/login
}

list_user_assets ()
{
  curl \
    -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbG1laWRhLmFuZHJlZkBnbWFpbC5jb20iLCJpYXQiOjE2Nzc0NTAyNjksImV4cCI6MTY3NzQ2ODI2OX0.oFXiMRYRwCjMkGN7VoGMTlUo_eB5at5EmDECBLOjwG3HwvTlO_-YJBkZxhZRCU1NL5QQ9OT8AxmTDEW7S7cO4g" \
    http://localhost:8080/api/v1/assets/available
  #
}

gen_password ()
{
  generated_password=$(uuidgen)
  echo "${generated_password}"
  curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbG1laWRhLmFuZHJlZkBnbWFpbC5jb20iLCJpYXQiOjE2Nzc0NTAyNjksImV4cCI6MTY3NzQ2ODI2OX0.oFXiMRYRwCjMkGN7VoGMTlUo_eB5at5EmDECBLOjwG3HwvTlO_-YJBkZxhZRCU1NL5QQ9OT8AxmTDEW7S7cO4g" \
    -d " { \"user\": \"almeida.andref@gmail.com\", \"password\": \"${generated_password}\" } " \
    http://localhost:8080/api/v1/auth/encode_password
}

"$1"
