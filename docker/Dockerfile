FROM java:8
VOLUME /tmp
ADD discovery-1.0-SNAPSHOT.jar discovery.jar
RUN bash -c 'touch /discovery.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/discovery.jar"]
