package com.example.demo.Provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.DTO.GoogleIDTokenDTO;
import com.example.demo.DTO.GoogleTokenDTO;
import com.example.demo.DTO.GoogleUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
public class GoogleProvider {

    public GoogleIDTokenDTO getGoogleIDToken(GoogleTokenDTO googleTokenDTO){
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String str = JSON.toJSONString(googleTokenDTO);
        RequestBody body = RequestBody.create(mediaType, str);
        Request request = new Request.Builder()
                .url("https://oauth2.googleapis.com/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            GoogleIDTokenDTO googleIDTokenDTO = JSON.parseObject(res,GoogleIDTokenDTO.class);
            return googleIDTokenDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GoogleUser parseJWT(String jwt) {
        String[] code = jwt.split("\\.");
        String str = new String(Base64.getDecoder().decode(code[1]));
        GoogleUser googleUser = JSON.parseObject(str, GoogleUser.class);
        return googleUser;
    }

}
