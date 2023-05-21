package com.emsi.Maven.jdbc.Services;

import java.util.List;

import com.emsi.Maven.jdbc.dao.ProductDao;
import com.emsi.Maven.jdbc.dao.Imp.ProductDaoImp;
import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;

public class ProductServices {
	private ProductDao productDao=new ProductDaoImp();
	
	public List<Product> findAll() {
		return productDao.findAll();
	}
	public Product findById(Integer id) {
		return productDao.findById(id);
				}
	public List<Product> findByCategorie(Categorie categorie) {
		return productDao.findByCategrie(categorie);
				}
	public void save(Product product) {
	
		productDao.insert(product);
		
	}
	public void update(Product product) {
		
		productDao.update(product);
		
	}
	public void remove(Product product) {
		productDao.deleteById(product.getId());
	}

}
