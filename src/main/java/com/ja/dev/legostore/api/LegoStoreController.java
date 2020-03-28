package com.ja.dev.legostore.api;

import java.util.Collection;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.dev.legostore.model.LegoSet;

@RestController
@RequestMapping("/api")
public class LegoStoreController {
	
	private MongoTemplate mongoTemplate;
	
	public LegoStoreController(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@PostMapping
	public void insert(@RequestBody LegoSet legoSet) {
		// If object has NO Id, new Id created
		// If object has Id, and present in collection
		// throws Duplicate Key Exception, else insert
		this.mongoTemplate.insert(legoSet);
	}
	
	@PutMapping
	public void update(@RequestBody LegoSet legoSet) {
		// If object has no Id, new Id generated
		// if object has Id, update document if present
		// else save
		this.mongoTemplate.save(legoSet);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.mongoTemplate.remove(new Query(Criteria.where("id").is(id)), LegoSet.class);
	}
	
	
	@GetMapping("/all")
	public Collection<LegoSet> findAll() {
		Collection<LegoSet> legoSets = this.mongoTemplate.findAll(LegoSet.class);
		return legoSets;
	}
	

}
