echo use java-21
export JAVA_HOME="/usr/lib/jvm/java-21-amazon-corretto"
source ~/.bashrc
./updateCamundaApp.sh
cd /srv/source/camunda-app
./gradlew clean build
echo building camunda-app docker image...
docker build -t mangoket/camunda-app .