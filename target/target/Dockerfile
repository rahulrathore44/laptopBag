# Use the official Tomcat 8 image with JDK 1.8
FROM tomcat:8-jre8

ARG JAR_FILE=target/*.war

# Copy the WAR file to the webapps directory of Tomcat
COPY ${JAR_FILE} /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
