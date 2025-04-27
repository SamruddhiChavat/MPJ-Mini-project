# Use official Tomcat image with JDK 17
FROM tomcat:9.0-jdk17-temurin

# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file to Tomcat (renamed to ROOT.war for root context)
COPY target/JAVASearchEngine.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
