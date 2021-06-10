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
        Assert.assertEquals(55, clientServices.orderPrice(products),DELTA);
    }

    @Test
    public void discountIsCorrect(){
        double orderPrice1 = 130;
        double orderPrice2 = 170;
        double orderPrice3 = 250;
        double orderPrice4 = 400;
        double orderPrice5 = 50;
        ClientServices clientServices = new ClientServices();
        Assert.assertEquals(117,clientServices.priceAfterDiscount(orderPrice1),DELTA);
        Assert.assertEquals(144.5,clientServices.priceAfterDiscount(orderPrice2),DELTA);
        Assert.assertEquals(200,clientServices.priceAfterDiscount(orderPrice3),DELTA);
        Assert.assertEquals(300,clientServices.priceAfterDiscount(orderPrice4),DELTA);
        Assert.assertEquals(50.,clientServices.priceAfterDiscount(orderPrice5),DELTA);
    }

}
