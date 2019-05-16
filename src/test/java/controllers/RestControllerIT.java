package controllers;

import com.Application;
import com.Database.ProductDatabase;
import com.models.Coin;
import com.models.Product;
import com.models.RequestProduct;
import com.models.VendProduct;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ProductDatabase database;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<String> getHealthCheck() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                createURLWithPort("/healthcheck"),
                HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<VendProduct> postPurchaseProduct(String productLocation, List<Coin> coins) {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setProductLocation(productLocation);
        requestProduct.setInsertedCoins(coins);

        return restTemplate.postForEntity(
                createURLWithPort("/purchaseProduct"), requestProduct, VendProduct.class);
    }

    public void stockProducts(List<Product> products) {
        database.batchInsertProducts(products);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}

