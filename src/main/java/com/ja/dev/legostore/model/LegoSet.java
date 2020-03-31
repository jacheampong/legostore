package com.ja.dev.legostore.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author jacheampong
 *
 */

@Document(collection = "legosets")
public class LegoSet {
	
	@Id
	private String id;
	@TextIndexed
	private String name;
	
	// frequently accessed field
	@Indexed(direction = IndexDirection.ASCENDING)
	private String theme;
	private LegoSetDifficulty difficulty;
	
	private DeliveryInfo deliveryInfo;
	private Collection<ProductReview> reviews = new ArrayList<>();
	
	@DBRef
	private PaymentOption paymentOption;
	
	public LegoSet() {}
	
	public LegoSet(String name, String theme, LegoSetDifficulty difficulty, DeliveryInfo deliveryInfo,
			Collection<ProductReview> reviews, PaymentOption paymentOption) {
		this.name = name;
		this.theme = theme;
		this.difficulty = difficulty;
		this.deliveryInfo = deliveryInfo;
		this.paymentOption = paymentOption;
		if (reviews != null) {
			this.reviews = reviews;
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public LegoSetDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(LegoSetDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public Collection<ProductReview> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<ProductReview> reviews) {
		this.reviews = reviews;
	}

	public PaymentOption getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
	}
	
		

}
