package com.tencent.commons.utils.httpclient;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 15-3-27
 *
 * @author bobzbfeng
 */
public class HttpClientPutExecutor extends HttpClientExecutor {

    public HttpClientPutExecutor(String url) {
        super(url);
    }


    protected RequestBuilder createRequestBuilder() {
        return RequestBuilder.put();
    }
}
