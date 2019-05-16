package Database;

import com.Application;
import com.Database.ProductDatabase;
import com.models.Product;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductDatabaseTest {

    @Autowired
    private ProductDatabase database;
    private static final String PRODUCT_LOCATION = "A69";

    @After
    public void tearDown() {
        database.deleteProductsByLocation(PRODUCT_LOCATION);
    }

    @Test
    public void getProductsByLocation_ShouldReturnProductsFromTable() {
        String productLocation = "C3";
        List<Product> actualProducts = database.getProductsByLocation(productLocation);

        Product actualProduct = actualProducts.get(0);
        assertEquals("Twix", actualProduct.getName());
    }

    @Test
    public void batchInsertProducts_ShouldInsertProductsIntoTable() {
        Product product1 = new Product("test", PRODUCT_LOCATION, BigDecimal.ZERO);
        Product product2 = new Product("fake", PRODUCT_LOCATION, BigDecimal.ZERO);

        database.batchInsertProducts(Arrays.asList(product1, product2));

        List<Product> products = database.getProductsByLocation(PRODUCT_LOCATION);

        assertEquals(2, products.size());
        assertEquals(product1.getName(), products.stream().filter(x -> x.getName().equals(product1.getName())).findFirst().get().getName());
        assertEquals(product2.getName(), products.stream().filter(x -> x.getName().equals(product2.getName())).findFirst().get().getName());
    }
}
