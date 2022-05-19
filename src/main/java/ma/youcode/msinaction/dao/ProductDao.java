package ma.youcode.msinaction.dao;

import ma.youcode.msinaction.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
}
