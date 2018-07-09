package com.tencent.commons.utils.httpclient;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HttpClientExecutorTest {


    /**
     * JSON HTTP
     */
    @Test(enabled = false)
    public void testHttp(){
        String url = "http://192.168.1.1/test";
        HttpClientExecutor clientExecutor = new HttpClientPostExecutor(url);

        //http header
        clientExecutor.addHeader("contentType","application/json");

        //http ody
        JSONObject json = new JSONObject();
        json.put("username","张三");
        HttpEntity entity = new StringEntity(json.toString(),"UTF-8");
        clientExecutor.httpEntity(entity);


        //设置超时连接毫秒值
        clientExecutor.maxConnectionMillisecond(400);
        JSONObject result =clientExecutor.execute(new HttpResponseHandler<JSONObject>() {
            @Override
            public JSONObject handleResponse(IDSHttpResponse response) {
                if (response.isResponse200()) {
                    String responseAsString = response.responseAsString();
                    return JSONObject.fromObject(responseAsString);

                } else {
                    //todo error return
                    return null;
                }
            }
        });


    }

    @Test(enabled = false)
    public void testFormHttp(){
        String url = "http://192.168.1.1/test";
        HttpClientExecutor clientExecutor = new HttpClientPostExecutor(url);

        clientExecutor.addRequestParam("username","张三");
        clientExecutor.addRequestParam("age","11");

        clientExecutor.execute(new HttpResponseHandler<JSONObject>() {
            @Override
            public JSONObject handleResponse(IDSHttpResponse response) {
                if(response.isResponse200()){
                    String responseAsString = response.responseAsString();
                    return JSONObject.fromObject(responseAsString);

                }else{
                    //todo error return
                    return null;
                }
            }
        });
    }
}