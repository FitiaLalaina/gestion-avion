# Compile all java file
javac -cp /home/tendry/Katsaka/WEB-INF/lib/connection.jar:/home/tendry/Katsaka/WEB-INF/lib/formulaire.jar:/home/tendry/Katsaka/WEB-INF/lib/agregation.jar -d /home/tendry/Katsaka/WEB-INF/classes/ /home/tendry/Katsaka/WEB-INF/classes/*.java

# # Copy project into server
echo "remove file"
rm /home/tendry/apache-tomcat-10.1.7/webapps/katsaka.war
echo "convert to jar"
jar -cf /home/tendry/apache-tomcat-10.1.7/webapps/katsaka.war .

echo "restart server"
# Restart tomcat
/home/tendry/apache-tomcat-10.1.7/bin/shutdown.sh
/home/tendry/apache-tomcat-10.1.7/bin/startup.sh
