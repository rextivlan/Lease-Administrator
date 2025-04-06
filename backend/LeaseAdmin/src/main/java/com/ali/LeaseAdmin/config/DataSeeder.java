package com.ali.LeaseAdmin.config;

import com.ali.LeaseAdmin.model.Lease;
import com.ali.LeaseAdmin.service.LeaseService;
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
    public CommandLineRunner loadData(LeaseService leaseService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            // Register the JavaTimeModule to support Java 8 Date/Time API
            mapper.registerModule(new JavaTimeModule());
            // Optionally, disable writing dates as timestamps
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            TypeReference<List<Lease>> typeReference = new TypeReference<List<Lease>>() {};
            InputStream inputStream = DataSeeder.class.getResourceAsStream("/seeder.json");
            if (inputStream == null) {
                System.out.println("Seeder file not found!");
                return;
            }
            List<Lease> leases = mapper.readValue(inputStream, typeReference);
            for (Lease lease : leases) {
                leaseService.createLease(lease);
            }
            System.out.println("Leases saved to database!");
        };
    }
}
