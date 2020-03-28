package com.ja.dev.legostore.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.dev.legostore.model.LegoSet;
import com.ja.dev.legostore.persistence.LegoSetRepository;

@RestController
@RequestMapping("/api")
public class LegoStoreController {
	
	@Autowired
	private LegoSetRepository legoSetRepository;

	@PostMapping
	public void insert(@RequestBody LegoSet legoSet) {
		// If object has NO Id, new Id created
		// If object has Id, and present in collection
		// throws Duplicate Key Exception, else insert
		this.legoSetRepository.insert(legoSet);
	}
	
	@PutMapping
	public void update(@RequestBody LegoSet legoSet) {
		// If object has no Id, new Id generated
		// if object has Id, update document if present
		// else save
		this.legoSetRepository.save(legoSet);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.legoSetRepository.deleteById(id);
	}
	
	
	@GetMapping("/all")
	public Collection<LegoSet> findAll() {
		Collection<LegoSet> legoSets = this.legoSetRepository.findAll();
		return legoSets;
	}
	

}
