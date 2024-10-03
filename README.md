# laptopBag

* A simple application built using the Jersey framework. Deploy a REST API for performing CRUD operations.
* Java - 1.8

# Udemy

https://www.udemy.com/user/rahulrathore3/

# YouTube 

https://www.youtube.com/fluxay44

## Docker image

Run the following command to build the image and tags it as `jersey/laptop-bag`.

`mvn package`

`docker build -t jersey/laptop-bag .`

Run the container using the image 

`docker run -d -p <application-port>:<expose-port> jersey/laptop-bag`

Example: `docker run -d -p 8080:8080 jersey/laptop-bag`

You can access the application on port **8080**

