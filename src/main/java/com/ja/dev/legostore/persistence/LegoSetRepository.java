package com.ja.dev.legostore.persistence;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ja.dev.legostore.model.LegoSet;
import com.ja.dev.legostore.model.LegoSetDifficulty;

/**
 * 
 * @author jacheampong
 *
 */
public interface LegoSetRepository extends MongoRepository<LegoSet,String> {
	
	Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);
	
	Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
	
	@Query("{'deliveryInfo.deliveryFee' : {$lt : ?0} }")
	Collection<LegoSet> findByDeliveryPriceLessThan(int price);
	
	@Query("{'reviews.rating' : {$eq : 5} }")
	Collection<LegoSet> findAllByGreatReviews();
	
	@Query("{'deliveryInfo.inStock' : true }")
	Collection<LegoSet> findAllInStock();
	
	Collection<LegoSet> findAllByThemeIsNot(String theme);

}
