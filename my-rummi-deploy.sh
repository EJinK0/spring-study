#!/bin/bash

REPOSITORY=/home/ubuntu/myReactProject/spring-study
cd $REPOSITORY

APP_NAME=wepin-dev
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

git fetch && git pull && git status

./gradlew clean build

# =====================================
# 현재 구동 중인 application pid 확인
# =====================================
pid=$(ps aux | grep "active=dev" | grep -v grep | awk '{print $2}')

if [ -z "$pid" ]; then
    echo "NOT RUNNING"
else
    echo "> kill -15 $pid"
    kill -15 $pid
    sleep 5
fi

echo "> $JAR_PATH 배포"
java -jar -Dspring.profiles.active=dev $JAR_PATH &
