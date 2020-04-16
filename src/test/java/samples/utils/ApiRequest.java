package samples.utils;

import net.serenitybdd.rest.SerenityRest;

import java.util.Map;


public class ApiRequest extends EnvConfig {
    /**
     * Method to Construct Api Request and Return Rest Assured Response.
     * @param baseUrl
     * @param basePath
     * @param requestBody
     * @param headers
     */

    public void withDetails(String baseUrl, String basePath, String requestBody, Map<String, Object> headers) {
        SerenityRest.given()
                .headers(headers)
                .baseUri(baseUrl)
                .basePath(basePath)
                .body(requestBody)
                .post()
                .thenReturn();
    }

}
