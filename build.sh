./gradlew shadowJar
docker build --platform linux/amd64 -t romahn/appointmentalert-backend:latest .
docker push romahn/appointmentalert-backend:latest