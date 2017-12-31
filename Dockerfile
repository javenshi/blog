FROM freedomkk/tomcat-maven

# 更改文件上传大小限制
RUN  sed -i '/<Connector port="8080"/a   maxThreads="800" acceptCount="1000" maxPostSize="0" maxHttpHeaderSize="65535" URIEncoding="UTF-8"' /usr/local/tomcat/conf/server.xml
RUN  sed -i 's/port="8009"/port="8009"  maxThreads="800" acceptCount="1000" maxPostSize="0" maxHttpHeaderSize="65535" URIEncoding="UTF-8"/' /usr/local/tomcat/conf/server.xml
RUN  sed -i '/<\/Service>/i \
    <Connector \
       protocol="org.apache.coyote.http11.Http11NioProtocol" \
       port="8443" maxThreads="200" \
       scheme="https" secure="true" SSLEnabled="true" \
       keystoreFile="conf/tomcat.keystore" keystorePass="newsys" \
       clientAuth="false" sslProtocol="TLS" />  ' /usr/local/tomcat/conf/server.xml

# 更改maven settings: central 指向公司内部nexus
RUN  sed -i '/<mirrors>/ a \
<mirror> \
  <id>nexus-centling</id>  \
  <mirrorOf>central</mirrorOf> \
  <name>Nexus centling</name> \
  <url>http://192.168.206.92/content/groups/public/</url> \
</mirror>  ' /usr/share/maven/conf/settings.xml
# change to Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENV JAVA_OPTS=-Xmx768m TZ=Asia/Shanghai
ENV JPDA_ADDRESS=8000 JPDA_TRANSPORT=dt_socket TZ=Asia/Shanghai

CMD ["catalina.sh", "jpda", "run"]

ADD . /app
WORKDIR /app

RUN cp /app/tomcat.keystore /usr/local/tomcat/conf/

RUN mvn clean package -Dmaven.test.skip=true &&  cd /app/target/ && unzip *.war -d /usr/local/tomcat/webapps/$(basename $(ls *.war) .war) &&   rm -rf /root/.m2 && rm -rf /app
