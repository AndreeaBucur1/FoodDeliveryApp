package Classes;

import java.util.ArrayList;
import java.util.Objects;

public class Cart {
    private int cartId;
    private int clientId;
    private ArrayList<Product> products;
    private double totalPrice;

    public Cart(int clientId, ArrayList<Product> products, double totalPrice) {
        this.products = new ArrayList<>();
        this.clientId = clientId;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Cart(int clientId, double totalPrice) {
        this.clientId = clientId;
        this.totalPrice = totalPrice;
    }

    public Cart(int cartId, int clientId, double totalPrice) {
        this.cartId = cartId;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProducts (ArrayList<Product> products){
        this.products.addAll(products);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId && clientId == cart.clientId && Double.compare(cart.totalPrice, totalPrice) == 0 && Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, clientId, products, totalPrice);
    }

    @Override
    public String toString() {
        return  "cartId=" + cartId +
                ", clientId=" + clientId +
                ", products=" + products +
                ", totalPrice=" + totalPrice;
    }
}