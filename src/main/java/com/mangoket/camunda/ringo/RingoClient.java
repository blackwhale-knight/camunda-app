package com.mangoket.camunda.ringo;

import com.mangoket.camunda.CamundaAppConfiguration;
import com.mangoket.camunda.jwt.JwtClient;
import okhttp3.*;

import java.io.IOException;

public class RingoClient {
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String HOST = "host";
    private static final String URL = "/shipment/getAllJITCustomerDemand";
    private static final String POST = "POST";

    public static void main(String[] args) throws IOException {
        CamundaAppConfiguration config = new CamundaAppConfiguration().getConfig("ringo");
        String host = config.getString(HOST);

        JwtClient jwtClient = new JwtClient();
        String accessToken = jwtClient.getAccessToken("ringo");
        System.out.println(accessToken);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse(APPLICATION_FORM_URLENCODED);
        RequestBody body = RequestBody.create("ym=202501", mediaType);
        Request request = new Request.Builder()
                .url(String.format("%s%s", host, URL))
                .method(POST, body)
                .addHeader(CONTENT_TYPE, APPLICATION_FORM_URLENCODED)
                .addHeader(AUTHORIZATION, String.format("Bearer %s", accessToken))
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseStr = response.body().string();
            System.out.println(responseStr);
        }

    }
}
