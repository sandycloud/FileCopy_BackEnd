Requires java 11 and later. 
It's tested on openJdk 11. It's not tested on older java versions.

If you want to monitor or profile this service, first start Jconsole /jvisualvm. 
Then start this REST service.

Build using maven targets "clean package".

For monitoring, start REST API using below command.
command to start the app: 
java -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9990 -Dcom.sun.management.jmxremote.authenticate=false -Xms768m -Xmx1600m -XX:+UseG1GC -XX:MaxGCPauseMillis=8000 -Xlog:gc*:file=./logs/gc.log:time,uptime,pid,tid:filecount=5,filesize=10m -jar target\file-upload-download-service-1.0-SNAPSHOT.jar

else if you simply want to run the service without additional arguments, use below command.

java -Xms768m -Xmx1600m -jar target\file-upload-download-service-1.0-SNAPSHOT.jar

Obviously, you can also run the service from your IDE.

upload service can be accessed by: http://localhost:8080/apiv1/files/upload

download service can be accessed by: http://localhost:8080/apiv1/files/download/<filename> 

Also you can use along with the UI code to upload and download files. UI code is at:
<git_url>
