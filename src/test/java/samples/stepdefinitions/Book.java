package samples.stepdefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import samples.templates.MergeFrom;
import samples.utils.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Book extends Assert {


    @Steps
    ApiRequest createBook;

    @Steps
    ApiResponse book;

    @Steps
    EnvConfig envConfig;

    String baseURI, basePath, createBookRequestBody;

    List<Map<String, String>> bookDetails;

    @Before
    public void init() {
        baseURI = envConfig.getApiProperty(Constants.API_DNS_BOOK);
        basePath = envConfig.getApiProperty(Constants.BOOKS_BASE_PATH);
        RestAssured.useRelaxedHTTPSValidation();
    }

    /**
     * Method to construct Request Body w.r.t Data provided in feature file.
     * TBD: Need to Enhance for multiple datasets.
     * @param bookDetails
     * @throws IOException
     */
    @Given("the following book:")
    public void the_folowing_book(List<Map<String, String>> bookDetails) throws IOException {
        createBookRequestBody = MergeFrom.template("templates/book.json")
                .withFieldsFrom(bookDetails.get(0));
    }

    @When("I create the book record")
    public void i_create_the_book_record() {
        createBook.withDetails(baseURI,
                basePath,
                createBookRequestBody,
                envConfig.appendHeaders());
    }


    @Then("Book Api should Return Statuscode <{int}>")
    public void apiShouldReturnStatuscode(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("Recorded book should include the following details:")
    public void the_recorded_book_should_include_the_following_details(List<Map<String, String>> bookDetails) {
        this.bookDetails = bookDetails;
        assertResponseBody(SerenityRest.lastResponse());
    }

    @Override
    public void assertResponseBody(Response response) {
        Map<String, String> expectedResponse = bookDetails.get(0);
        Map<String, String> actualResponse = book.returned();
        assertThat("Book Details do not match", actualResponse.entrySet(),
                both(everyItem(isIn(expectedResponse.entrySet()))).and(containsInAnyOrder(expectedResponse.entrySet().toArray())));

    }

    @After
    public void cleanup() {
        RestAssured.reset();
    }


}
