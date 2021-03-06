package com.example.spring.boot.projekt.Efremov.service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
@Data
public class UserGoogleOAuthServiceImpl implements UserGoogleOAuthService {

    final OAuth20Service service;
    private String secretState;
    private final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v3/userinfo";


    @Override
    public RedirectView getRedirectView() {
        secretState = "secret" + new Random().nextInt(999_999);
        String authorizationUrl = service.createAuthorizationUrlBuilder()
                .state(secretState)
                .build();
        return new RedirectView(authorizationUrl);
    }

    @Override
    public String getJsonGoogleUser(String code, String state) throws InterruptedException, ExecutionException, IOException {
        if (state.equals(secretState)) {
            OAuth2AccessToken accessToken = service.getAccessToken(code);
            final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, request);
            Response response = service.execute(request);
            return response.getBody();
        }
        return "error";
    }
}
