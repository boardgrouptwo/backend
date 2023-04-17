package com.khcare.spring.Service;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleService {

    private final Logger logger = LoggerFactory.getLogger(GoogleService.class);
    private static final String CLIENT_ID = "559072792143-366521ue3tu5jpmc1721ptke087c4ct0.apps.googleusercontent.com";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public String verifyAccessToken(String accessToken) throws IOException, GeneralSecurityException, TokenResponseException {
        logger.info(accessToken);

        GoogleIdToken idToken = GoogleIdToken.parse(JSON_FACTORY, accessToken); // 수정된 부분
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        logger.info(verifier+"");

        try {
            if (verifier.verify(idToken)) {
                String name = extractUserInfoFromToken(idToken);
                logger.info(name);
                return name;
            } else {
                logger.error("Failed to verify access token: Invalid token");
                return  null;
            }
        } catch (GeneralSecurityException | IOException e) {
            logger.error("Failed to verify access token", e);
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error occurred while verifying access token", e);
            throw e;
        }

    }

    // 추가된 메서드
    protected JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }

    public String extractUserInfoFromToken(GoogleIdToken idToken) throws GeneralSecurityException, IOException {

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String locale = (String) payload.get("locale");

        logger.info(email);
        logger.info(name);

        return name;

    }

}
