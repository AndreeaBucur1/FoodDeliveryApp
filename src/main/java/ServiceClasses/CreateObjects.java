package ServiceClasses;
import Database.DatabaseConnection;
import Classes.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class CreateObjects {
    DisplayObjects displayObjects = new DisplayObjects();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    Scanner scanner = new Scanner(System.in);

    public void createAccount() throws SQLException {
        System.out.print("Enter your email adress: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        System.out.println();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into account (email,password) values (?,?)");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.execute();


    }

    public void addCategory() throws SQLException {
        System.out.print("Enter the name of the category you want to add: ");
        String categoryName = scanner.next();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into category (categoryname) values (?)");
        preparedStatement.setString(1, categoryName);
        preparedStatement.execute();

    }

    public void addProduct() throws SQLException {
        System.out.print("Enter the name of the product you want to add: ");
        String productName = scanner.nextLine().toString();
        System.out.println("This are all the categories:");
        displayObjects.displayCategories();
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        boolean validCategory = false;
        int categoryId;
        do {
            System.out.println("Enter -1 to abort");
            System.out.print("Enter the number of the category: ");
            categoryId = scanner.nextInt();
            System.out.println(categoryId);
            if(categoryId == -1){
                break;
            }
            resultSet = statement.executeQuery("select * from category where categoryId = " + categoryId);
            if (resultSet.next()) {
                validCategory = true;
            } else {
                System.out.println("Category does not exist");
            }
        } while (!validCategory);

        if(categoryId == -1){
            System.out.println("Canceled");
        }
        else{
            System.out.print("Enter the price: ");
            float price = scanner.nextFloat();
            System.out.print("Enter product description: ");
            scanner.nextLine();
            String description = scanner.nextLine().toString();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("insert into product (categoryId,productName,price,description) values (?,?,?,?)");
            preparedStatement.setInt(1,categoryId);
            preparedStatement.setString(2,productName);
            preparedStatement.setFloat(3,price);
            preparedStatement.setString(4,description);
            preparedStatement.execute();
        }
    }

    public void add(Order order) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into orders (clientId,totalPrice,address,orderDate) values (?,?,?,?)");
        preparedStatement.setInt(1,order.getClientId());
        preparedStatement.setFloat(2,order.getTotalPrice());
        preparedStatement.setString(3,order.getAddress());
        preparedStatement.setDate(4, Date.valueOf(order.getOrderDate()));
        preparedStatement.execute();


        System.out.println(order.getProducts());
        addProductsToOrder(order);

    }
    public void addOrder(Order order) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into orders (clientId,totalPrice,address,orderDate) values (?,?,?,?)");
        preparedStatement.setInt(1,order.getClientId());
        preparedStatement.setFloat(2,order.getTotalPrice());
        preparedStatement.setString(3,order.getAddress());
        preparedStatement.setDate(4, Date.valueOf(order.getOrderDate()));
        preparedStatement.execute();


        System.out.println(order.getProducts());
        addProductsToOrder(order);

    }

    public void addProductsToOrder(Order order) throws SQLException {
        PreparedStatement preparedStatement;
        ArrayList<Order> orders = databaseConnection.getAllOrders();
        orders.sort(Comparator.comparing(Order::getOrderId));
        System.out.println(orders);
        int lastId = orders.get(orders.size() - 1).getOrderId();
        order.setOrderId(lastId);
        ArrayList<Integer> checked = new ArrayList<>();
        for (Product product : order.getProducts()) {
            preparedStatement = null;
            if(checked.contains(product.getProductId()) == false) {
                int quantity = 0;
                for(Product product1 : order.getProducts()){
                    if(product1.getProductId() == product.getProductId()){
                        quantity++;
                    }
                }
                checked.add(product.getProductId());

                preparedStatement = connection.prepareStatement("insert into orderHasProducts values (?,?,?)");
                preparedStatement.setInt(1, order.getOrderId());
                preparedStatement.setInt(2, product.getProductId());
                preparedStatement.setInt(3, quantity);
                preparedStatement.execute();
            }


        }
    }
}