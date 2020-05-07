package com.gitmuts.openbanking.service;

import com.gitmuts.openbanking.model.GetTokenResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Slf4j
@Service
public class GetToken {

    Gson gson = new Gson();

    @Value("${coop.consumer.key}")
    private String consumerKey;

    @Value("${coop.consumer.secret}")
    private String consumerSecret;

    public GetTokenResponse getToken() {
        try{

            String appKeySecret = consumerKey + ":" + consumerSecret;

            byte[] bytes = appKeySecret.getBytes("ISO-8859-1");


            byte[] encodedBytes = Base64.getEncoder().encode(bytes);

            String auth = new String(encodedBytes);

            log.info("{}", auth);

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("grant_type", "client_credentials")
                    .build();

            Request request = new Request.Builder()

                    .url("https://developer.co-opbank.co.ke:8243/token")

                    .post(formBody)

                    .addHeader("authorization", "Basic " + auth)

                    .addHeader("cache-control", "no-cache")

                    .build();

            log.info("{}", request.headers());

            Response response = client.newCall(request).execute();

            log.info("{}", response.code());

            String responseBody = response.body().string();

            log.info("{}", responseBody);

            GetTokenResponse getTokenResponse = gson.fromJson(responseBody, GetTokenResponse.class);

            return getTokenResponse;
        }catch (Exception e){
            log.error("Error occurred while calling {} Ex{}", "", e);
            return null;
        }
    }
}
