package com.example.repository;

import com.example.entity.Product;
import com.example.entity.UserProductIdetifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by summit on 12/7/17.
 */

@Repository
public interface KartRepository extends MongoRepository<Product,String> {

    @Query("{'_id.userId' : ?0}")
    public List<Product> findByUserId(String userId);
}
