package acceptance.steps;

import com.models.Coin;
import com.models.Product;
import com.models.VendProduct;
import controllers.RestControllerIT;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static com.models.Coin.QUARTER;
import static org.junit.Assert.assertEquals;

public class VendingMachineSteps extends RestControllerIT {

    private ResponseEntity<String> healthCheck;
    private ResponseEntity<VendProduct> purchase;
    private String expectedProduct;

    @Given("^When I query the healthCheck endpoint$")
    public void whenIQueryTheHealthCheckEndpoint() {
        healthCheck = getHealthCheck();
    }

    @Then("^I get a success status code$")
    public void iGetASuccessStatusCode() {
        assertEquals(HttpStatus.OK, healthCheck.getStatusCode());
    }

    @Given("^The database is stocked with (.*) at (.*) for \\$(\\d+.\\d+)$")
    public void theDatabaseIsStockedWithItems(String productName, String location, double cost) {
        expectedProduct = productName;
        BigDecimal expectedCost = new BigDecimal(cost);
        Product product = new Product(expectedProduct, location, expectedCost);
        stockProducts(Collections.singletonList(product));
    }

    @When("^I purchase a product at location (.*)$")
    public void iQueryForProductLocationA(String productLocation) {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER);
        purchase = postPurchaseProduct(productLocation, coins);
    }

    @Then("^I should return only matching items$")
    public void iShouldReturnOnlyMatchingItems() {
        assertEquals(expectedProduct, purchase.getBody().getProduct().getName());
    }
}
