package com.gitmuts.openbanking.service;

import com.gitmuts.openbanking.model.PesalinkRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
@Slf4j
public class PesalinkService {

    OkHttpClient okHttpClient = new OkHttpClient();

    Gson gson = new Gson();

    public String sendRequest(PesalinkRequest pesalinkRequest, String token) {
        try{

            MediaType mediaType = MediaType.parse("application/json");

            RequestBody body = RequestBody.create(mediaType, gson.toJson(pesalinkRequest));


            log.info("{}", gson.toJson(pesalinkRequest));

            Request request = new Request.Builder()

                    .url("https://developer.co-opbank.co.ke:8243/FundsTransfer/External/A2A/PesaLink/1.0.0")

                    .post(body)

                    .addHeader("authorization", "Bearer "+ token)

                    .addHeader("content-type", "application/json")

                    .build();


            Response response = okHttpClient.newCall(request).execute();


            log.info("Response {}", response.body().string());

            return response.code()+"";

        }catch (Exception e){
            log.error("Error occurred while calling {} ", Method.DECLARED, e);
            return e.getMessage();
        }
    }
}
