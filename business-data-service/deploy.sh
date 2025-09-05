#!/bin/bash

#cd /home/alizamin/invoicera/invoicera_backend_project || exit 1

echo ">>> Maven build başlayır..."
mvn clean install -DskipTests

echo ">>> Köhnə business-service.jar prosesi dayandırılır..."
PID=$(lsof -t -i:8085)

if [ -n "$PID" ]; then
    kill -9 $PID
    echo "Prosess $PID uğurla dayandırıldı."
else
    echo "8085 portunda işləyən hər hansı proses tapılmadı."
fi

# Yeni JAR faylını işə sal
echo ">>> Yeni business-service.jar detach modda işə salınır..."
#nohup java -jar business-data-service/target/business-service.jar > business-service.log 2>&1 &
nohup java -jar target/business-service.jar > business-service.log 2>&1 &

echo ">>> Yeni business-service.jar uğurla işə salındı."
