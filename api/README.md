# Market Flutter

## Config

You'll need Mysql 8 installed.

```
docker run --name mysql-8 -p33060:3306 -e MYSQL_ROOT_PASSWORD="super-senha" -d mysql:latest
```

To log into the database:
```
docker exec -it mysql-8 /bin/sh
```