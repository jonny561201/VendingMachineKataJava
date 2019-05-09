package Database;

import com.Application;
import com.Database.ProductDatabase;
import com.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductDatabaseTest {

    @Autowired
    private ProductDatabase database;

    @Test
    public void getProductsByLocation_ShouldReturnProductsFromTable() {
        String productLocation = "C3";
        List<Product> actualProducts = database.getProductsByLocation(productLocation);

        Product actualProduct = actualProducts.get(0);
        assertEquals("Pepsi", actualProduct.getName());
    }
}
