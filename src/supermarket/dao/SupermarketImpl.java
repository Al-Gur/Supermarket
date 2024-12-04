package supermarket.dao;

import supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class SupermarketImpl implements Supermarket {
    private Collection<Product> products;

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

    private Iterable<Product> findByPredicate(Predicate<Product> p) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product product : products) {
            if (p.test(product)) {
                res.add(product);
            }
        }
        return res;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        return findByPredicate(product -> product.getCategory().equals(category));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        return findByPredicate(product -> product.getBrand().equals(brand));
    }

    @Override
    public Iterable<Product> findProductsWithExpiredDate() {
        LocalDate date = LocalDate.now();
        return findByPredicate(product -> product.getExpDate().isBefore(date));
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
