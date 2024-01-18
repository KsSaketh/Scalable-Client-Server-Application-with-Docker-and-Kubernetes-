FROM ubuntu:latest
WORKDIR /app
RUN apt-get update && \ 
    apt-get install -y default-jdk
COPY  Chat/*  /app/
Run javac  Server.java 
CMD java Server
