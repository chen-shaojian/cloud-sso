FROM java:8
VOLUME /tmp
ADD web-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone
EXPOSE 80
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]