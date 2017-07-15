package com.example.repository;

import com.example.entity.CancelPaymentOfItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by summit on 12/7/17.
 */

@Repository
public interface CancelRepository extends MongoRepository<CancelPaymentOfItems,String> {
    @Query("{'_id.userId' : ?0}")
    public List<CancelPaymentOfItems> findByUserId(String userId);
}
