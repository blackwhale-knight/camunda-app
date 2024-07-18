# camunda-app

This is a workflow management application which integrated with Camunda platform.

---

## Local Development

### Maven Build & Package

```shell
./mvnw clean package
```

### Run

```shell
java -jar target/camunda-app-1.0-SNAPSHOT.jar
```

## Docker

### Docker Build

```shell
docker build -t mangoket/camunda-app .
```

### Maven Docker Build

```shell
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=mangoket/camunda-app
```

### Docker Run

```shell
docker run -p -d 8080:8080 -t mangoket/camunda-app
```
