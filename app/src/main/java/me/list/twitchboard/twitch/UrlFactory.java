package me.list.twitchboard.twitch;

import android.support.annotation.Nullable;

/**
 * Created by masterjefferson on 7/4/2016.
 */
public class UrlFactory {

    static final String USER_AUTH_FORMAT = "https://api.twitch.tv/kraken/oauth2/authorize?" +
            "response_type=token" +
            "&client_id=%s" +
            "&redirect_uri=%s" +
            "&scope=%s";

    public static String userAuthURL(String clientID, String redirectURI, String... scopes) {
        String scopesStr = getScopesString(scopes);
        return String.format(
                USER_AUTH_FORMAT,
                clientID,
                redirectURI,
                scopesStr
        );
    }

    private static String getScopesString(@Nullable String[] scopes) {
        StringBuilder scopesStr = new StringBuilder();
        if (scopes != null) {
            for (int i = 0; i < scopes.length; i++) {
                scopesStr.append(scopes[i]);
                if (i < scopes.length - 1) {
                    scopesStr.append('+');
                }
            }
        }
        return scopesStr.toString();
    }

}
