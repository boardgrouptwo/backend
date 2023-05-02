package com.khcare.spring.Service;


import com.khcare.spring.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class GoogleService {
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(GoogleService.class);


    private static final String CLIENT_ID = "559072792143-366521ue3tu5jpmc1721ptke087c4ct0.apps.googleusercontent.com";

    public String getKey() {
        String key = null;
        try {
            Properties p = new Properties();
            p.load(new FileReader(".env"));
            key = "KakaoAK " + p.getProperty("KAKAO_PAY_KEY");
        } catch(IOException e) {

        }

        return key;
    }

    public String getUserInfo(String accessToken) throws IOException{
        URL url = new URL("https://www.googleapis.com/oauth2/v3/userinfo");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);
        con.setRequestProperty("Accept", "application/json");
        int status = con.getResponseCode();
        if (status == 200) {
            Scanner scanner = new Scanner(con.getInputStream());
            String responseBody = scanner.useDelimiter("\\A").next();
            scanner.close();

            // Parse the response JSON to extract user information
            String name = extractName(responseBody);
            String email = extractEmail(responseBody);

            return jwtTokenProvider.createToken(email, Collections.singletonList("USER"), name);
        } else {
            throw new IOException("Failed to fetch user information: " + con.getResponseMessage());
        }
    }
    private String extractName(String responseBody) {
        // Parse the "name" field from the JSON response
        // This assumes that the response is in the following format:
        // {
        //   "name": "John Smith",
        //   "email": "john.smith@example.com",
        //   ...
        // }
        return responseBody.split("\"name\"\\s*:\\s*\"")[1].split("\"")[0];
    }

    private String extractEmail(String responseBody) {
        // Parse the "email" field from the JSON response
        // This assumes that the response is in the following format:
        // {
        //   "name": "John Smith",
        //   "email": "john.smith@example.com",
        //   ...
        // }
        return responseBody.split("\"email\"\\s*:\\s*\"")[1].split("\"")[0];
    }


}
