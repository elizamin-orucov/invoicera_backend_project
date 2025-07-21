#!/bin/bash

JAR_NAME="api-gateway.jar"
TARGET_PATH="target/$JAR_NAME"
DEST_PATH="./$JAR_NAME"

echo "🧹 Maven clean install başlayır..."
mvn clean install -DskipTests || { echo "❌ Maven build uğursuz oldu"; exit 1; }

echo "📦 JAR faylı kopyalanır..."
cp $TARGET_PATH $DEST_PATH || { echo "❌ JAR faylı tapılmadı: $TARGET_PATH"; exit 1; }

echo "🧼 Əvvəlki prosesi dayandırırıq (əgər varsa)..."
PID=$(lsof -t -i:8000)
if [ -n "$PID" ]; then
  kill -9 $PID
  echo "✅ Port 8080 boşaldıldı (PID $PID öldürüldü)"
fi

echo "🚀 JAR faylı detach modda işə salınır..."
nohup java -jar $DEST_PATH > log.txt 2>&1 &

echo "✅ Proses işə düşdü (detach modda). Log: log.txt"
