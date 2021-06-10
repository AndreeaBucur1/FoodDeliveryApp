package Classes;

public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private float price;
    private String description;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Product(int productId, int categoryId, String productName, float price, String description) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return  "ID: " + productId + '\n' +
                "Category id: " + categoryId + '\n' +
                "Product name: " + productName + '\n' +
                "Price: " + price + "RON\n" +
                "Description: " + description + '\n';
    }
}
