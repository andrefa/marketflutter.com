#!/bin/bash -e

# install eb (elasticbeanstalk) cli
pip install awsebcli --upgrade

# init the eb application
eb init # shouldn't be needed

# create the database
eb create --single --database

eb setenv SERVER_PORT=5000 # eb listens to this port
eb setenv EB_DATABASE_URL_AND_PORT=awseb-e-8pi6g2qqmm-stack-awsebrdsdatabase-zxanjqppyptu.cwntno3xv1kd.us-east-1.rds.amazonaws.com:3306 # the url returned on the create database step (may be present on the eb dashboard as well)
eb setenv SPRING_PROFILES_ACTIVE=prod # the profile to use

# package the application in a single jar
mvn clean package spring-boot:repackage

# deploy the packaged jar
eb deploy
