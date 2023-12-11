package Persistence.ApiHelper;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Helper class with the responsibility of reading and posting Strings to an HTTPS API. Due to a misconfiguration
 * in the SimpleRPG API, it's set up to ignore SSL certificates, connecting to any server.
 * Be aware that this should NOT be used in real production environments, as verifying certificates is a
 * key part of ensuring security in the context of Internet communications
 */
public final class ApiHelper {
    private final HttpClient client;

    /**
     * Default constructor, where the client used for HTTPS communication is set up
     *
     * @throws IOException If your computer doesn't support SSL at all. If you get this exception when calling the
     *                     constructor, contact the OOPD teachers.
     */
    public ApiHelper() throws IOException {
        try {
            client = HttpClient.newBuilder().sslContext(insecureContext()).build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IOException(e);
        }
    }

    /**
     * Method that reads the contents from a URL using the HTTPS protocol. Specifically, a GET request is sent.
     * Any parameters should be included in the URL.
     *
     * @param url A String representation of the URL to read from, which will be assumed to use HTTP/HTTPS.
     * @return The contents of the URL represented as text.
     * @throws IOException If the URL is malformed or the server can't be reached.
     */
    public String getFromUrl(String url) throws IOException {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new IOException(e);
        }
    }

    /**
     * Method that posts contents to a URL using the HTTPS protocol. Specifically, a POST request is sent.
     * The request body is set to the corresponding parameter, and the response body is returned just in case.
     *
     * @param url  A String representation of the URL to post to, which will be assumed to use HTTP/HTTPS.
     * @param body The content to post, which will be sent to the server in the request body.
     * @return The contents of the response, in case the server sends anything back after posting the content.
     * @throws IOException If the URL is malformed or the server can't be reached.
     */
    public String postToUrl(String url, String body) throws IOException {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).headers("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new IOException(e);
        }
    }

    /**
     * Method that removes the contents from a URL using the HTTPS protocol. Specifically, a DELETE request is sent.
     * Any parameters should be included in the URL.
     *
     * @param url A String representation of the URL to delete from, which will be assumed to use HTTP/HTTPS.
     * @return The contents of the response, in case the server sends anything back after deleting the content.
     * @throws IOException If the URL is malformed or the server can't be reached.
     */
    public String deleteFromUrl(String url) throws IOException {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).DELETE().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new IOException(e);
        }
    }

    /**
     * Helper function that sets up a SSLContext designed to ignore certificates, accepting anything by default
     * NOT TO BE USED IN REAL PRODUCTION ENVIRONMENTS
     *
     * @return An instance of the SSLContext class, which manages SSL verifications, configured to accept even misconfigured certificates
     */
    private SSLContext insecureContext() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] insecureTrustManager = new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        SSLContext sc = SSLContext.getInstance("ssl");
        sc.init(null, insecureTrustManager, null);
        return sc;
    }
}

