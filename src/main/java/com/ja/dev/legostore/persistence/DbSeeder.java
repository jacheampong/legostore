package com.ja.dev.legostore.persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ja.dev.legostore.model.DeliveryInfo;
import com.ja.dev.legostore.model.LegoSet;
import com.ja.dev.legostore.model.LegoSetDifficulty;
import com.ja.dev.legostore.model.ProductReview;

@Service
public class DbSeeder implements CommandLineRunner {
	
	private MongoTemplate mongoTemplate;

    public DbSeeder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        // drop
        this.mongoTemplate.dropCollection(LegoSet.class);

        LegoSet milleniumFlacon = new LegoSet(
                "Millenium Falcon",
                "Star wars",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
                Arrays.asList(
                        new ProductReview("Dan", 4),
                        new ProductReview("Audrey", 5),
                        new ProductReview("Zoe", 4),
                        new ProductReview("Sam", 4)
                )
        );

        LegoSet skyPolice = new LegoSet(
                "Sky Police Air Base",
                "City",
                LegoSetDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
                Arrays.asList(
                        new ProductReview("Dan", 4),
                        new ProductReview("Jon", 5),
                        new ProductReview("Anna", 4)
                )
        );

        LegoSet mcLarenSenna = new LegoSet(
                "McLaren Senna",
                "Speed Champions",
                LegoSetDifficulty.EASY,
                new DeliveryInfo(LocalDate.now().plusDays(7), 70, false),
                Arrays.asList(
                        new ProductReview("Dan", 4),
                        new ProductReview("Jon", 5),
                        new ProductReview("Anna", 4),
                        new ProductReview("Zoe", 4),
                        new ProductReview("Brooks", 5)
                )
        );

        Collection<LegoSet> initialProducts = Arrays.asList(milleniumFlacon, skyPolice, mcLarenSenna);

        this.mongoTemplate.insertAll(initialProducts);

    }

}
