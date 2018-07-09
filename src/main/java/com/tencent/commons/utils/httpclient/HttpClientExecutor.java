package com.tencent.commons.utils.httpclient;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Wrapper HttpClient operations
 * Default request method: GET
 *
 * @author bobzbfeng
 */
public class HttpClientExecutor {

    /*
    * Available content Types
    * */
    public static final List<ContentType> CONTENT_TYPES = Arrays.asList(
            ContentType.TEXT_PLAIN, ContentType.TEXT_HTML,
            ContentType.TEXT_XML, ContentType.APPLICATION_XML,
            ContentType.APPLICATION_SVG_XML, ContentType.APPLICATION_XHTML_XML,
            ContentType.APPLICATION_ATOM_XML,
            ContentType.APPLICATION_JSON);


    protected static final Logger LOGGER = LoggerFactory.getLogger(HttpClientExecutor.class);
    //Convert mill seconds to second unit
    //protected static final int MS_TO_S_UNIT = 1000;

    //https prefix
    protected static final String HTTPS = "https";

    protected static HttpsTrustManager httpsTrustManager = new HttpsTrustManager();

    protected String url;

    //毫秒值
    protected int maxConnectionMillisecond = 1000;

    protected String contentType;

    protected HttpEntity httpEntity;

    protected Map<String, String> requestParams = new HashMap<>();
    protected Map<String, String> headers = new HashMap<>();


    public HttpClientExecutor(String url) {
        this.url = url;
    }


    @SuppressWarnings("unchecked")
    public <T extends HttpClientExecutor> T maxConnectionMillisecond(int maxConnectionMillisecond) {
        this.maxConnectionMillisecond = maxConnectionMillisecond;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends HttpClientExecutor> T addRequestParam(String key, String value) {
        this.requestParams.put(key, value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends HttpClientExecutor> T addHeader(String key, String value) {
        this.headers.put(key, value);
        return (T) this;
    }


    @SuppressWarnings("unchecked")
    public <T extends HttpClientExecutor> T contentType(String contentType) {
        this.contentType = contentType;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends HttpClientExecutor> T httpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
        return (T) this;
    }


    public <T>T execute(HttpResponseHandler<T> responseHandler) {
        T result = null;
        try {
            final CloseableHttpResponse response = sendRequest();
            result =  responseHandler.handleResponse(new IDSHttpResponse(response));
            response.close();
        } catch (Exception e) {
            LOGGER.error("Send request to url[" + url + "] failed", e);
        }
        return result;
    }

    /*
    * Execute and handle exception by yourself
    * */
    public <T>T executeWithException(HttpResponseHandler<T> responseHandler) throws Exception {
        final CloseableHttpResponse response = sendRequest();
        final T result = responseHandler.handleResponse(new IDSHttpResponse(response));
        response.close();
        return result;
    }


    protected CloseableHttpResponse sendRequest() throws Exception {
        HttpUriRequest request = retrieveHttpRequest();
        setContentType(request);
        setHeaders(request);

        CloseableHttpClient client = retrieveHttpClient();
        return client.execute(request);
    }


    protected void setHeaders(HttpUriRequest request) {
        for (String key : headers.keySet()) {
            request.addHeader(key, headers.get(key));
        }
    }


    private void setContentType(HttpUriRequest request) {
        if (StringUtils.isNotEmpty(this.contentType)) {
            request.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
            LOGGER.debug("Set HttpUriRequest[{}] contentType: {}", request, contentType);
        }
    }

    protected CloseableHttpClient retrieveHttpClient() {
        final RequestConfig requestConfig = requestConfig();
        if (isHttps()) {
            //Support SSL
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(createSSLContext());
            return HttpClients.custom().setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslConnectionSocketFactory).build();
        } else {
            return HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        }
    }

    private RequestConfig requestConfig() {
        final int maxConnMillSeconds = this.maxConnectionMillisecond;
        return RequestConfig.custom()
                .setSocketTimeout(maxConnMillSeconds)
                .setConnectTimeout(maxConnMillSeconds).build();
    }


    private SSLContext createSSLContext() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new HttpsTrustManager[]{httpsTrustManager}, null);
            return sslContext;
        } catch (Exception e) {
            throw new IllegalStateException("Create SSLContext error", e);
        }
    }

    protected boolean isHttps() {
        return url.toLowerCase().startsWith(HTTPS);
    }

    private HttpUriRequest retrieveHttpRequest() {
        final RequestBuilder builder = createRequestBuilder();
        addRequestParams(builder);
        setHttpEntity(builder);
        return builder.setUri(url).build();
    }

    protected void setHttpEntity(RequestBuilder builder) {
        if (this.httpEntity != null) {
            builder.setEntity(this.httpEntity);
        }
    }

    protected void addRequestParams(RequestBuilder builder) {
        final Set<String> keySet = requestParams.keySet();
        for (String key : keySet) {
            builder.addParameter(key, requestParams.get(key));
        }
    }

    protected RequestBuilder createRequestBuilder() {
        return RequestBuilder.get();
    }


    /**
     * Default X509TrustManager implement
     */
    private static class HttpsTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            //ignore
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            //ignore
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}