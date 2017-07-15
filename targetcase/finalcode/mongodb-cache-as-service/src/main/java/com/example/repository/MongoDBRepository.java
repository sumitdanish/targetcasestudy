package com.example.repository;

import com.example.mongoentity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */
@Repository
public interface MongoDBRepository extends MongoRepository<Product,String> {
    List<Product> findByProductNameLike(String productName);
}
