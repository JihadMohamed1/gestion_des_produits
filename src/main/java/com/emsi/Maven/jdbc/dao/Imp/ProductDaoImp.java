package com.emsi.Maven.jdbc.dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.emsi.Maven.jdbc.dao.ProductDao;
import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;

public class ProductDaoImp implements ProductDao {
	private Connection conn= DB.getConnection();

	@Override
	public void insert(Product product) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"INSERT INTO product (nom, prix, description, quantite, CategorieId) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, product.getNom());
			ps.setDouble(2, product.getPrix());
			ps.setString(3, product.getDescription());
			ps.setInt(4,product.getQuantite());
			ps.setInt(5, product.getCategorie().getId());
			

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					product.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyé");;
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un product"+e);;
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Product product) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"UPDATE product SET nom = ?, prix = ?, description = ?, quantite = ?,CategorieId = ? WHERE id = ?");

			ps.setString(1, product.getNom());
			ps.setDouble(2, product.getPrix());
			ps.setString(3, product.getDescription());
			ps.setInt(4,product.getQuantite());
			ps.setInt(5, product.getCategorie().getId());
			ps.setInt(6, product.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un product");;
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM product WHERE id = ?");

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un product");;
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT p.*, c.nom AS CatNom FROM product p INNER JOIN categorie c ON p.CategorieId = c.id WHERE p.id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Categorie categorie = instantiateCategroie(rs);
				Product product = instantiateProduct(rs, categorie);

				return product;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver le product");
			return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Product> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT p.*, c.nom as CatNom FROM product p INNER JOIN categorie c ON p.CategorieId = c.id ORDER BY p.nom");
			rs = ps.executeQuery();
			List<Product> list = new ArrayList<>();
			Map<Integer, Categorie> map = new HashMap<>();

			while (rs.next()) {
				Categorie cat = map.get(rs.getInt("CategorieId"));

				if (cat == null) {
					cat = instantiateCategroie(rs);

					map.put(rs.getInt("CategorieId"), cat);
				}

				Product product = instantiateProduct(rs, cat);

				list.add(product);
			}

			return list;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner les vendeurs");
		return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Product> findByCategrie(Categorie categorie) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT p.*, c.nom as CatNom FROM product p INNER JOIN categorie c ON p.CategorieId = c.id WHERE p.CategorieId = ? ORDER BY p.nom");

			ps.setInt(1, categorie.getId());

			rs = ps.executeQuery();
			List<Product> list = new ArrayList<>();
			Map<Integer, Categorie> map = new HashMap<>();

			while (rs.next()) {
				Categorie cat = map.get(rs.getInt("CategorieId"));

				if (cat == null) {
					cat = instantiateCategroie(rs);

					map.put(rs.getInt("CategorieId"), cat);
				}

				Product product = instantiateProduct(rs, cat);

				list.add(product);
			}

			return list;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner les products d'un categorie donné"+e);
		return null;
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	private Product instantiateProduct(ResultSet rs, Categorie categorie) throws SQLException {
		Product product = new Product();

		product.setId(rs.getInt("id"));
		product.setNom(rs.getString("nom"));
		product.setPrix(rs.getDouble("prix"));
		product.setDescription(rs.getString("description"));
		product.setQuantite(rs.getInt("quantite"));
		product.setCategorie(categorie);

		return product;
	}

	private Categorie instantiateCategroie(ResultSet rs) throws SQLException {
		Categorie categorie = new Categorie();

		categorie.setId(rs.getInt("CategorieId"));
		categorie.setNom(rs.getString("CatNom"));

		return categorie;
	}

}
