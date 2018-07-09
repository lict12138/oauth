package com.tencent.commons.utils.httpclient;

/**
 * @author bobzbfeng
 */

public interface HttpResponseHandler<T> {


    public T handleResponse(IDSHttpResponse response);

}