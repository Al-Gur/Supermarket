package supermarket.main;

import supermarket.dao.Supermarket;
import supermarket.dao.SupermarketImpl;
import supermarket.model.Product;

import java.time.LocalDate;

public class SupermarketMain {
    public static void main(String[] args) {
        Supermarket market = new SupermarketImpl();
        Product[] products=new Product[4];
        products[0]= new Product(1, "milk", "milk products", "OEU", 10, LocalDate.parse("2024-12-05"));
        products[1]= new Product(2, "cheese", "milk products", "OEU", 40, LocalDate.parse("2024-11-30"));
        products[2]= new Product(3, "Coca Cola", "drinks", "Coca Cola", 12, LocalDate.parse("2025-06-30"));
        products[3]= new Product(4, "bread", "bread", "YYY", 5, LocalDate.parse("2024-12-03"));
        for(Product p: products){
            market.addProduct(p);
        }
        for (Product p:market){
            System.out.println(p);
        }
        System.out.println("======= Expired products ==========");
        Iterable<Product> expiredProducts = market.findProductsWithExpiredDate();
        for (Product p:expiredProducts){
            System.out.println(p);
        }
    }
}
