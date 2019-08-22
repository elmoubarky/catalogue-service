package org.sid.repository;

import org.sid.entitie.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository  extends MongoRepository<Product, String>{

}
