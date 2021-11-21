package com.blazemeter;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SharedfolderUtil {

    public final String authHeader;

    public SharedfolderUtil(String apiKey, String apiSecret) {
        String auth = apiKey + ":" + apiSecret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        this.authHeader = "Basic " + new String(encodedAuth);
    }

    public final String uploadFile(String url, String filePath) throws IOException {

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File file = new File(filePath);
        builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public final String getFolderContent(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpclient.execute(httpget);
        return EntityUtils.toString(response.getEntity());
    }
}
