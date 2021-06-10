import Classes.Product;
import ServiceClasses.ClientServices;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class OrderTest {
    private static final double DELTA = 1e-15;

    @Test
    public void orderPriceCorrect(){
        Product product = new Product(1,1,"salad",25,"salad");
        Product product1 = new Product(2,1,"salad2",30,"salad2");
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        ClientServices clientServices = new ClientServices();
        Assert.assertEquals(55,clientServices.orderPrice(products),DELTA);
    }
}
