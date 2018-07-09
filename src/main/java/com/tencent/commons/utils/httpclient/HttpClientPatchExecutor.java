package com.tencent.commons.utils.httpclient;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 15-3-27
 *
 * @author bobzbfeng
 */
public class HttpClientPatchExecutor extends HttpClientExecutor {

    public HttpClientPatchExecutor(String url) {
        super(url);
    }


    protected RequestBuilder createRequestBuilder() {
        return RequestBuilder.patch();
    }
}
