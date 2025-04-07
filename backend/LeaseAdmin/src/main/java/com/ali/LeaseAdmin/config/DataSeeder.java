package com.ali.LeaseAdmin.config;

import com.ali.LeaseAdmin.model.Lease;
import com.ali.LeaseAdmin.repository.LeaseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner loadData(LeaseRepository leaseRepository) {
        return args -> {
            // Only seed if there are no leases yet
            if (leaseRepository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                TypeReference<List<Lease>> typeReference = new TypeReference<List<Lease>>() {};
                InputStream inputStream = DataSeeder.class.getResourceAsStream("/seeder.json");
                if (inputStream == null) {
                    System.out.println("Seeder file not found!");
                    return;
                }
                List<Lease> leases = mapper.readValue(inputStream, typeReference);
                leaseRepository.saveAll(leases);
                System.out.println("Leases seeded to database!");
            } else {
                System.out.println("Leases already seeded. Skipping seeding.");
            }
        };
    }
}
