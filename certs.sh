cd ./src/main/resources/keys

keytool -genkeypair -alias algafood -keyalg RSA -keypass 123456 -keystore algafood.jks -storepass 123456 -validity 3650 -dname "CN=Test, OU=Development, O=Development,
             L=Anytown, S=Rio de janeiro, C=RJ"

keytool -export -rfc -alias algafood -keystore algafood.jks -file algafood-cert.pem

openssl x509 -pubkey -noout -in algafood-cert.pem > algafood-pkey.pem
