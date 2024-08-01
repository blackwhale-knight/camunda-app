export JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto
export PROJECT_NAME=camunda_app
export LOG_FOLDER=camunda_app
cd /srv/source/camunda-app
docker-compose -p $PROJECT_NAME down
docker-compose -p $LOG_FOLDER logs -f
docker-compose -p $PROJECT_NAME up -d
#sudo tail -f /srv/docker-logs/camunda_app/catalina.out