cd ./src/main/resources/keys

keytool -genkeypair -alias algafood -keyalg RSA -keypass 123456 -keystore algafood.jks -storepass 123456 -validity 3650 -dname "CN=Test, OU=Development, O=Development,
             L=Anytown, S=Rio de janeiro, C=RJ"