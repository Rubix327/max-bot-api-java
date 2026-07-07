package ru.max.botapi.client;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public final class TrustStoreManager {

    private static final String[] CERTIFICATES = {
            "certs/rootca_ssl_rsa2022.crt",
            "certs/subca_ssl_rsa2024.crt"
    };

    private static final SSLContext SSL_CONTEXT;

    static {
        try {
            SSL_CONTEXT = createSslContext();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to initialize SSL context", e);
        }
    }

    private TrustStoreManager() {
    }

    public static SSLContext getSslContext(){
        return SSL_CONTEXT;
    }

    private static SSLContext createSslContext() {
        try {
            KeyStore trustStore = loadDefaultTrustStore();
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            ClassLoader classLoader = TrustStoreManager.class.getClassLoader();

            for (String certificatePath : CERTIFICATES) {
                try (InputStream inputStream = classLoader.getResourceAsStream(certificatePath)) {

                    if (inputStream == null) {
                        throw new IllegalStateException("Certificate not found: " + certificatePath);
                    }

                    Certificate certificate = certificateFactory.generateCertificate(inputStream);

                    trustStore.setCertificateEntry(
                            certificatePath,
                            certificate);
                }
            }

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(
                            TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(trustStore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            return sslContext;
        }
        catch (Exception e) {
            throw new MaxSslInitializationException("Unable to initialize SSL context", e);
        }
    }

    private static KeyStore loadDefaultTrustStore() throws Exception {
        Path javaHome = Path.of(System.getProperty("java.home"));

        Path trustStorePath = Files.exists(javaHome.resolve("lib/security/cacerts"))
                ? javaHome.resolve("lib/security/cacerts")
                : javaHome.resolve("jre/lib/security/cacerts");

        if (!Files.exists(trustStorePath)) {
            throw new IllegalStateException("Default Java trust store not found.");
        }

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (InputStream inputStream = Files.newInputStream(trustStorePath)) {
            keyStore.load(inputStream, "changeit".toCharArray());
        }

        return keyStore;
    }

}