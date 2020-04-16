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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Employee extends Assert {

    @Steps
    ApiRequest recordNewEmployee;

    @Steps
    ApiResponse createEmployee;

    @Steps
    EnvConfig envConfig;


    String baseURI, basePath, createEmployeeRequestBody;


    @Before
    public void init() {
        RestAssured.useRelaxedHTTPSValidation();
        baseURI = envConfig.getApiProperty(Constants.API_DNS_EMPLOYEE);
        basePath = envConfig.getApiProperty(Constants.EMPLOYEE_BASE_PATH);
    }

    @Given("the following employee:")
    public void the_folowing_book(List<Map<String, String>> employeeDetails) throws IOException {

        createEmployeeRequestBody = MergeFrom.template(Constants.EMPLOYEE_TEMPLATE)
                .withFieldsFrom(employeeDetails.get(0));
    }

    @When("I create an employee")
    public void i_create_the_employee_record() {
        recordNewEmployee.withDetails(baseURI,
                basePath,
                createEmployeeRequestBody,
                envConfig.appendHeaders());
    }


    @Then("Api should Return Statuscode <{int}>")
    public void apiShouldReturnStatuscode(int statusCode) {
        assertStatusCode(statusCode);
    }


    @And("Response should have status as Success")
    public void responseShouldHaveStatusAsSuccess() {
        assertResponseBody(SerenityRest.lastResponse());
    }


    @Override
    public void assertResponseBody(Response response) {
        HashMap<String, Object> responseMap = new HashMap<>(response.getBody().as(Map.class));
        assertThat("status does not match", responseMap.get("status"), is(equalTo("success")));

    }

    @After
    public void cleanup() {
        RestAssured.reset();
    }


}
