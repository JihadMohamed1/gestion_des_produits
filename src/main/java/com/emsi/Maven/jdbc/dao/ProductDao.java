package com.emsi.Maven.jdbc.dao;

import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;

import java.util.List;



public interface ProductDao {
	void insert(Product product);

	void update(Product product);

	void deleteById(Integer id);

	Product findById(Integer id);

	List<Product> findAll();

	List<Product> findByCategrie(Categorie categorie);

}
