package samples.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.HashMap;
import java.util.Map;

public class EnvConfig {

    private EnvironmentVariables environmentVariables;

    /**
     * Get Environment Specific Property Value.
     * @param propName
     * @return
     */

    public String getApiProperty(String propName) {
        String propertyValue = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty(propName);
        return propertyValue;
    }

    /**
     * Appends Common Headers to Api Request.
     * @return defaultHeaders.
     */
    public Map<String, Object> appendHeaders()
    {
        Map<String,Object> defaultHeaders = new HashMap<>();
        defaultHeaders.put(Constants.CONTENT_TYPE,Constants.JSON_CONTENT_TYPE);
        defaultHeaders.put(Constants.ACCEPT_ENCODING,Constants.GZIP_ENCODING);

        return defaultHeaders;
    }
}
