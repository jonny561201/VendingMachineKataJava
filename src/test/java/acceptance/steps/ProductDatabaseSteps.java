package acceptance.steps;

import controllers.RestControllerIT;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ProductDatabaseSteps extends RestControllerIT {

    private ResponseEntity<String> healthCheck;

    @Given("^When I query the healthcheck endpoint$")
    public void whenIQueryTheHealthCheckEndpoint() {
        healthCheck = getHealthCheck();
    }

    @Then("^I get a success status code$")
    public void iGetASuccessStatusCode() {
        assertEquals(HttpStatus.OK, healthCheck.getStatusCode());
    }
}
