# camunda-app

This is a workflow management application which integrated with Camunda platform.

---

## Update Submodule

```shell
git submodule update --remote
```

### or (with rebase)

```shell
git submodule update --rebase --remote
```

## Local Development

### Gradle

#### Clean & Build

```shell
gradle clean build
```

#### Run

```shell
java -jar build/libs/camunda-app-1.0-SNAPSHOT.jar
```

## Docker

### Docker Build

```shell
docker build -t mangoket/camunda-app .
```

### Docker Run

#### Use `docker-compose.yml`

```shell
docker-compose up -d
```

#### Or

```shell
docker run -d -p 9004:9004 -t mangoket/camunda-app
```

## Deploy

### Stage

1. `ssh ubuntu@stage.mangoket.us`
2. `cd /srv/source/`
3. `./buildCamundaApp.sh`
4. `./deployCamundaApp.sh`
5. check docker status: `docker ps`
