
package com.tencent.oauth.infrastructure.oauth;


import com.tencent.commons.utils.UUIDGenerator;
import com.tencent.oauth.domain.security.Privilege;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

/**
 * 2015/11/10
 *
 * @author bobzbfeng
 */
public abstract class OAuthUtils {

    public static enum GrantType {
        PASSWORD("password"),
        REFRESH_TOKEN("refresh_token"),
        CLIENT_CREDENTIALS("client_credentials");

        private String type;

        GrantType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return name();
        }


    }


//  ------------------------------------  application Server client constants ------------------------------------


    //Multi grant_type split by ','
    public static final String APPLICATION_GRANT_TYPES = GrantType.CLIENT_CREDENTIALS.getType();


    //See  security.xml configuration
    public static final String APPLICATION_RESOURCE_IDS = "app-server-resource";


    //Spring security role
    public static final String APPLICATION_CLIENT_PRIVILEGES = "ROLE_" + Privilege.APPLICATION_CLIENT.name();


    //Available values:   read, read write
    public static final String APPLICATION_CLIENT_SCOPE = "read";


    /*
    * Generate client_secret
    * */
    private static RandomValueStringGenerator clientSecretGenerator = new RandomValueStringGenerator(40);


    //singleton
    private OAuthUtils() {
    }


    public static String tokenURI(String host) {
        if (host.endsWith("/")) {
            return host + "oauth/token";
        }
        return host + "/oauth/token";
    }


    /**
     * Generate a new clientId
     *
     * @return clientId
     */
    public static String generateClientId() {
        return UUIDGenerator.generate();
    }


    /**
     * Generate a new clientSecret
     *
     * @return clientSecret
     */
    public static String generateClientSecret() {
        return clientSecretGenerator.generate();
    }

    /*accessTokenValidity*/
    public final static Integer DEFAULT_SECONDS = 43200;
    /*默认单位小时（3600秒）*/
    public final static Integer DIVISOR_SECONDS = 3600;


}
