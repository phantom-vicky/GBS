package com.isl.gbm.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by abhay.kumar-v on 10/23/2017.
 */

@Component
public class HttpPostClient {
    private static final Logger log = LoggerFactory.getLogger(HttpPostClient.class);

    @Autowired
    private PropertiesValue propertiesValue;

    public static HttpResponse doPost(String url, String obj, String msg) {
        HttpResponse httpResponse = null;
        try {
            long startTime = System.nanoTime();
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            log.info("Instance of HttpPost created");
            //add header
            //post.setHeader("User-Agent", USER_AGENT);
            log.info("Setting post parameters here");
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            //         urlParameters.add(new BasicNameValuePair("obj",obj));
            urlParameters.add(new BasicNameValuePair("msg", msg));
            log.info("Post parameters set");
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            //     post.setHeader("HASH",obj);

            //===========================


            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {


                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };

            //==============


            String string = client.execute(post, responseHandler);
            System.out.print(string);
            //   log.info("HttpResponse is "+response.getStatusLine().getStatusCode());
            String timeTaken = (System.nanoTime() - startTime) / 1000000 + " ms";
            log.info("Exiting public static void doPost(String url, String obj, String msg) throws ClientProtocolException, IOException - time taken " + timeTaken);
        } catch (ClientProtocolException cpe) {
            log.error(" ClientProtocolException  " + cpe);
        } catch (IOException ioe) {
            log.error(" IOException  " + ioe);
        }


        return httpResponse;

    }


    public static String callHttpPost(String url, ConcurrentMap<String, String> headersMap, ConcurrentMap<String, String> params, String jsonRequest) {
        String strResponse = "";
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);

            // logic for param
            if (!StringUtils.isEmpty(params)) {
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                Set<String> set = params.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    urlParameters.add(new BasicNameValuePair(key, params.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(urlParameters));
            }
            // logic for headers
            if (!StringUtils.isEmpty(headersMap)) {
                Set<String> headerKeySet = headersMap.keySet();
                Iterator<String> headerKeyIterator = headerKeySet.iterator();
                while (headerKeyIterator.hasNext()) {
                    String headerKey = headerKeyIterator.next();
                    post.setHeader(headerKey, headersMap.get(headerKey));
                }
            }

            // logic for binary data in json format
            if (!StringUtils.isEmpty(jsonRequest)) {
                post.setEntity(new StringEntity(jsonRequest, ContentType.create("application/json")));
            }
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            strResponse = client.execute(post, responseHandler);
        } catch (ClientProtocolException cpe) {
            log.error(" ClientProtocolException  " + cpe);
        } catch (IOException ioe) {
            log.error(" IOException  " + ioe);
        }
        return strResponse;

    }

    public String callHttpPostSSL(String url, ConcurrentMap<String, String> headersMap, ConcurrentMap<String, String> params) {
        String strResponse = "";
        try {

            //final String KEY_STORE_PATH = "C:\\Projects\\middleware\\src\\main\\resources\\keystore.jks";//"/path/to/pkcs12file.p12";


            // Load the key store, containing the client-side certificate.
            KeyStore keyStore = KeyStore.getInstance("jks");
            InputStream keyStoreInput = new FileInputStream(propertiesValue.getKeyStore());
            keyStore.load(keyStoreInput, propertiesValue.getKeyStorePassword().toCharArray());
            System.out.println("Key store has " + keyStore.size() + " keys");
            SSLContext sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, propertiesValue.getKeyStorePassword().toCharArray())
                    .build();

            // Prepare the HTTPClient.
            HttpClientBuilder builder = HttpClientBuilder.create();
            SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(
                    sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            builder.setSSLSocketFactory(sslConnectionFactory);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslConnectionFactory)
                    .build();
            HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);
            builder.setConnectionManager(ccm);
            // HttpClient client=HttpClientBuilder.create().build();
            HttpClient client = builder.build();
            HttpPost post = new HttpPost(url);
            // logic for param
            if (!StringUtils.isEmpty(params)) {
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                Set<String> set = params.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    urlParameters.add(new BasicNameValuePair(key, params.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(urlParameters));
            }
            // logic for headers
            if (!StringUtils.isEmpty(headersMap)) {
                Set<String> headerKeySet = headersMap.keySet();
                Iterator<String> headerKeyIterator = headerKeySet.iterator();
                while (headerKeyIterator.hasNext()) {
                    String headerKey = headerKeyIterator.next();
                    post.setHeader(headerKey, headersMap.get(headerKey));
                }
            }
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            strResponse = client.execute(post, responseHandler);

        } catch (ClientProtocolException cpe) {
            log.error(" ClientProtocolException  " + cpe);
        } catch (IOException ioe) {
            log.error(" IOException  " + ioe);
        } catch (KeyStoreException kse) {
            log.error(" KeyStoreException  " + kse);
        } catch (CertificateException ce) {
            log.error(" CertificateException  " + ce);
        } catch (NoSuchAlgorithmException nsae) {
            log.error(" NoSuchAlgorithmException  " + nsae);
        } catch (KeyManagementException kme) {
            log.error(" KeyManagementException  " + kme);
        } catch (UnrecoverableKeyException e) {
            log.error(" UnrecoverableKeyException  " + e);
        }
        return strResponse;

    }

    public static HttpResponse callHttpPostCollection(String url, ConcurrentMap<String, String> headersMap, ConcurrentMap<String, String> params, String jsonRequest) {
        HttpResponse httpResponse = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);

            // logic for param
            if (!StringUtils.isEmpty(params)) {
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                Set<String> set = params.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    urlParameters.add(new BasicNameValuePair(key, params.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(urlParameters));
            }
            // logic for headers
            if (!StringUtils.isEmpty(headersMap)) {
                Set<String> headerKeySet = headersMap.keySet();
                Iterator<String> headerKeyIterator = headerKeySet.iterator();
                while (headerKeyIterator.hasNext()) {
                    String headerKey = headerKeyIterator.next();
                    post.setHeader(headerKey, headersMap.get(headerKey));
                }
            }

            // logic for binary data in json format
            if (!StringUtils.isEmpty(jsonRequest)) {
                post.setEntity(new StringEntity(jsonRequest, ContentType.create("application/json")));
            }
/*            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };*/
            httpResponse = client.execute(post);
        } catch (ClientProtocolException cpe) {
            log.error(" ClientProtocolException  " + cpe);
        } catch (IOException ioe) {
            log.error(" IOException  " + ioe);
        }
        return httpResponse;

    }


    public static String callHttpPost(String url, String object, String message) {
        StringEntity inputEntity = null;
        String output = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            inputEntity = new StringEntity(object);
            inputEntity.setContentType("application/json");
            httpPost.setEntity(inputEntity);
            httpPost.setHeader("HASH", object);
            RequestConfig config = RequestConfig.custom().
                    setConnectTimeout(3 * 1000).
                    setConnectionRequestTimeout(3 * 1000).
                    setSocketTimeout(3 * 1000).build();
            CloseableHttpClient client = HttpClientBuilder.create()
                    .setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("Failed response"
                        + httpResponse.getStatusLine().getStatusCode());
            }
            log.debug("Output from Server .... \n");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            (httpResponse.getEntity().getContent())));
            while ((output = bufferedReader.readLine()) != null) {
                log.error(output);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException :: " + e);
        } catch (MalformedURLException e) {
            log.error("MalformedURLException :: " + e);
        } catch (IOException e) {
            log.error("IOException :: " + e);
        }

        return output;
    }

    public static String callHttpPost(String url, String contentType, String headerKey, String headerValue, String data) {
        StringEntity inputEntity = null;
        String output = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            inputEntity = new StringEntity(data);
            inputEntity.setContentType(contentType);
            httpPost.setEntity(inputEntity);
            httpPost.setHeader(headerKey, headerValue);//HASH
            RequestConfig config = RequestConfig.custom().
                    setConnectTimeout(3 * 1000).
                    setConnectionRequestTimeout(3 * 1000).
                    setSocketTimeout(3 * 1000).build();
            CloseableHttpClient client = HttpClientBuilder.create()
                    .setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("Failed response"
                        + httpResponse.getStatusLine().getStatusCode());
            }
            log.debug("Output from Server .... \n");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            (httpResponse.getEntity().getContent())));
            while ((output = bufferedReader.readLine()) != null) {
                log.error(output);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException :: " + e);
        } catch (MalformedURLException e) {
            log.error("MalformedURLException :: " + e);
        } catch (IOException e) {
            log.error("IOException :: " + e);
        }

        return output;
    }
}
