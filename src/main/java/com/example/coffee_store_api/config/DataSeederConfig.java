package com.example.coffee_store_api.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.coffee_store_api.models.ProductCategory;
import com.example.coffee_store_api.models.Role;
import com.example.coffee_store_api.models.Status;

@Configuration
public class DataSeederConfig {

    private static final List<String> STATUSES = List.of("active", "inactive", "blocked", "cancelled", "expired");
    private static final List<String> ROLES = List.of("admin", "collab", "customer");
    private static final List<String> PRODUCT_CATEGORIES = List.of("Café en grano", "Café molido");

    @Bean
    CommandLineRunner seedData(MongoTemplate mongoTemplate) {
        return args -> {
            for (String description : STATUSES) {
                if (!mongoTemplate.exists(Query.query(Criteria.where("description").is(description)), Status.class)) {
                    mongoTemplate.save(new Status(description));
                }
            }

            for (String description : ROLES) {
                if (!mongoTemplate.exists(Query.query(Criteria.where("description").is(description)), Role.class)) {
                    mongoTemplate.save(new Role(description));
                }
            }

            for (String name : PRODUCT_CATEGORIES) {
                if (!mongoTemplate.exists(Query.query(Criteria.where("name").is(name)), ProductCategory.class)) {
                    mongoTemplate.save(new ProductCategory(name));
                }
            }
        };
    }
}
