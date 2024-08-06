package com.mangoket.camunda.clients.jwt;

import com.mangoket.camunda.CamundaAppConfiguration;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JwtClient {
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String GRANT_TYPE = "grant_type";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String HOST = "host";
    private static final String OAUTH_ACCESS_TOKEN_URL = "/oauth/access_token";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String POST = "POST";

    public String getAccessToken(String service) {
        CamundaAppConfiguration config = new CamundaAppConfiguration().getConfig(service);
        String refreshToken = config.getString(REFRESH_TOKEN);
        String host = config.getString(HOST);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse(APPLICATION_FORM_URLENCODED);
        RequestBody requestBody = RequestBody.create(
                String.format("%s=%s&%s=%s", GRANT_TYPE, REFRESH_TOKEN, REFRESH_TOKEN, refreshToken)
                , mediaType
        );

        Request request = new Request.Builder()
                .url(String.format("%s%s", host, OAUTH_ACCESS_TOKEN_URL))
                .method(POST, requestBody)
                .addHeader(CONTENT_TYPE, APPLICATION_FORM_URLENCODED)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            JSONObject jsonObject = new JSONObject(response.body().string());
            return jsonObject.get(ACCESS_TOKEN).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
