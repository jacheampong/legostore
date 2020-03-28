package com.ja.dev.legostore.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ja.dev.legostore.model.LegoSet;

public interface LegoSetRepository extends MongoRepository<LegoSet,String> {

}
