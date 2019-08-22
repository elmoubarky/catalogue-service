package org.sid;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.sid.entitie.Category;
import org.sid.entitie.Product;
import org.sid.repository.CategoryRepository;
import org.sid.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class CatalogueServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CatalogueServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start (CategoryRepository categoryRepository, ProductRepository productRepository) {
		return args->{
			Stream.of("C1 Ordinateur", "C2 Imprimante", "C3 Ecran").forEach(c->{
				categoryRepository.save(new Category(c.split(" ")[0],c.split(" ")[1], new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			
			productRepository.deleteAll();
			Category C1 = categoryRepository.findById("C1").get();
			Stream.of("P1", "P2","P3", "P4").forEach(name->{
				Product p =productRepository.save(new Product(null,name, Math.random()*1000,C1));
				C1.getProducts().add(p);
				categoryRepository.save(C1);
			});
			
			Category C2 = categoryRepository.findById("C2").get();
			Stream.of("P5", "P6", "P7", "P8").forEach(name->{
				Product p =productRepository.save(new Product(null,name, Math.random()*1000,C2));
				C2.getProducts().add(p);
				categoryRepository.save(C2);
				
			});
			
			Category C3 = categoryRepository.findById("C3").get();
			Stream.of("P9", "P10","P11").forEach(name->{
				Product p =productRepository.save(new Product(null,name, Math.random()*1000,C3));
				C2.getProducts().add(p);
				categoryRepository.save(C2);
				
			});
			
			
			productRepository.findAll().forEach(p->{
				System.out.println(p.toString());
			});
			
		};
	}

}
