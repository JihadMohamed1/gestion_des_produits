package com.emsi.Maven.jdbc.dao;

import com.emsi.Maven.jdbc.Entites.Categorie;

import java.util.List;



public interface CategorieDao {
	void insert(Categorie categorie);

	void update(Categorie categorie);

	void deleteById(Integer id);

	Categorie findById(Integer id);
	Categorie findByName(String Nom);

	List<Categorie> findAll();
}
