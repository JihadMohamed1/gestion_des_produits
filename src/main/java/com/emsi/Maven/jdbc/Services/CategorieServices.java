package com.emsi.Maven.jdbc.Services;

import java.util.List;

import com.emsi.Maven.jdbc.dao.CategorieDao;
import com.emsi.Maven.jdbc.dao.Imp.CategorieDaoImp;
import com.emsi.Maven.jdbc.Entites.Categorie;

public class CategorieServices {
	
	private CategorieDao categorieDao= new CategorieDaoImp();

	public List<Categorie> findAll() {
		return categorieDao.findAll();
		
	}
	public Categorie findById(Integer id) {
		return categorieDao.findById(id);
		
	}
	public Categorie findByName(String nom) {
		return categorieDao.findByName(nom);
		
	}
	public void save(Categorie categorie) {
		
		categorieDao.insert(categorie);
		
	}
	public void update(Categorie categorie) {
		
		categorieDao.update(categorie);
		
	}
	public void remove(Categorie categorie) {
		categorieDao.deleteById(categorie.getId());
	}

}
