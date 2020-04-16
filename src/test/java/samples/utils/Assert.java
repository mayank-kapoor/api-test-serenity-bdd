package samples.utils;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class Assert {

    public void assertStatusCode(int statusCode) {
        assertThat("Status code does not match", SerenityRest.lastResponse().statusCode(),
                is(equalTo(statusCode)));
    }

    public void assertResponseHeaders(Response response) {

    }

    public abstract void assertResponseBody(Response response);


}
