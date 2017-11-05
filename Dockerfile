FROM openjdk:8

ADD target/mobileUsageModified-0.0.1-SNAPSHOT.jar /mobileusageapp.jar

RUN sh -c 'touch /mobileusageapp.jar'

#EXPOSE 8140
EXPOSE 80

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mobileusageapp.jar"]