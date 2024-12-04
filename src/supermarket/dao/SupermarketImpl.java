package supermarket.dao;

import supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SupermarketImpl implements Supermarket {
    private ArrayList<Product> products;

    public SupermarketImpl() {
        products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(Product product) {
        if (findByBarCode(product.getBarCode()) != null) {
            return false;
        } else {
            products.add(product);
            return true;
        }
    }

    @Override
    public Product removeProduct(long barCode) {
        Product victim = findByBarCode(barCode);
        if (victim != null) {
            products.remove(victim);
        }
        return victim;
    }

    @Override
    public Product findByBarCode(long barCode) {
        for (Product product : products) {
            if (product.getBarCode() == barCode) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                res.add(product);
            }
        }
        return res;
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equals(brand)) {
                res.add(product);
            }
        }
        return res;
    }

    @Override
    public Iterable<Product> findProductsWithExpiredDate() {
        ArrayList<Product> res = new ArrayList<>();
        for (Product product : products) {
            if (product.getExpDate().isBefore(LocalDate.now())) {
                res.add(product);
            }
        }
        return res;
    }

    @Override
    public int skuQuantity() {
        return products.size();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
