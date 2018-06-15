#! /bin/bash

mvn clean package
docker build -t webnary_img .
docker run -p 8080:8080 -d --name webnary webnary_img 
