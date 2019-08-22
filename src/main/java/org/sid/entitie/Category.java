package org.sid.entitie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class Category implements Serializable{
	
	@Id
	private String id;
	private String name;
	
	@DBRef
	private Collection<Product> products = new ArrayList<>();

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	

}
