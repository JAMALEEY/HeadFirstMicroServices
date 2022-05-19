package ma.youcode.msinaction.dao;

import ma.youcode.msinaction.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{
    public static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1L, "Ordinateur portable", 350L));
        products.add(new Product(2L, "Aspirateur Robot", 500L));
        products.add(new Product(3L, "Table de Ping Pong", 750L));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long productId) {
        for (Product product : products){
            if (product.getProductId() == productId){
                return product;
            }
        }
        return null;
    }

        @Override
        public Product save(Product product) {
            products.add(product);
            return product;
        }
}
