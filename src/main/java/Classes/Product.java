package Classes;

import Database.DatabaseConnection;

import java.sql.SQLException;

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
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try {
            return  /*"Product id: " + productId + '\n' +*/
                    "Product name: " + productName + '\n' +
                    "Category: " + databaseConnection.getCategoryById(categoryId).getCategoryName() + '\n' +
                    "Price: " + price + "RON\n" +
                    "Description: " + description + '\n';
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
