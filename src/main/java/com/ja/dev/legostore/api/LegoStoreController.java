package com.ja.dev.legostore.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.dev.legostore.model.LegoSet;
import com.ja.dev.legostore.model.LegoSetDifficulty;
import com.ja.dev.legostore.persistence.LegoSetRepository;

/**
 * 
 * @author jacheampong
 *
 */

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
	
	/**
	 * findAll() - find all sorted by theme
	 * @return Collection<LegoSet>
	 */
	@GetMapping("/all")
	public Collection<LegoSet> findAll() {
		Sort sortByThemeASC = Sort.by("them").ascending();
		Collection<LegoSet> legoSets = this.legoSetRepository.findAll(sortByThemeASC);
		return legoSets;
	}
	
	@GetMapping("/{id}")
	public LegoSet findLegoSetById(@PathVariable String id) {
		LegoSet legoSet = this.legoSetRepository.findById(id).orElse(null);
		return legoSet;
	}
	
	/**
	 * findLegoSetByTheme - Find Document by field name, sorted
	 * @param theme
	 * @return
	 */
	@GetMapping("/byTheme/{theme}")
	public Collection<LegoSet> findLegoSetByTheme(@PathVariable String theme) {
		Sort sortByThemeASC = Sort.by("them").ascending();
		Collection<LegoSet> legoSets = this.legoSetRepository.findAllByThemeContains(theme, sortByThemeASC);
		return legoSets;
	}
	
	/**
	 * findByDifficultyAndName - Find Documents by multiple fields
	 * @param letter
	 * @return Collection<LegoSet>
	 */
	@GetMapping("/hardThatStartWith/{letter}")
	public Collection<LegoSet> byDifficultyAndName(@PathVariable String letter) {
		return this.legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, letter);
		
	}
	
	/**
	 * byDeliveryFeeLessThan - Find Documents By a field in Sub-document. Used @Query
	 * @param price
	 * @return Collection<LegoSet>
	 */
	@GetMapping("/deliveryFee/{price}")
	public Collection<LegoSet> byDeliveryFeeLessThan(@PathVariable int price) {
		return this.legoSetRepository.findByDeliveryPriceLessThan(price);
		
	}
	
	/**
	 * byGreatReviews - Find Documents by a field value in an array. Used @Query
	 * @return Collection<LegoSet>
	 */
	@GetMapping("/greatreviews")
	public Collection<LegoSet> byGreatReviews() {
		return this.legoSetRepository.findAllByGreatReviews();
	}
	
	/**
	 * findAllInStock - Find all LegoSet in stock
	 * @return
	 */
	@GetMapping("/instock")
	public Collection<LegoSet> findAllInStock() {
		return this.legoSetRepository.findAllInStock();
	}
	
	/**
	 * findAllNotStarWars
	 * @return
	 */
	@GetMapping("/notStarWarsTheme")
	public Collection<LegoSet> findAllNotStarWars() {
	    return this.legoSetRepository.findAllByThemeIsNot("Star Wars");
	}
	

}
