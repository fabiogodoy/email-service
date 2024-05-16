#/bin/sh

rm -rf target
mvn package
scp -i ~/Documents/certificates/pilatespro.pem target/email-service-1.0.war ubuntu@server1.connectas.com.br:~/
