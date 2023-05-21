package com.emsi.Maven.jdbc.dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emsi.Maven.jdbc.dao.CategorieDao;
import com.emsi.Maven.jdbc.Entites.Categorie;

public class CategorieDaoImp implements CategorieDao {
		private Connection conn= DB.getConnection();
	@Override
	public void insert(Categorie categorie) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO categorie (nom) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, categorie.getNom());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					categorie.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un Categorie"+e);;
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Categorie categorie) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE categorie SET nom = ? WHERE id = ?");

			ps.setString(1, categorie.getNom());
			ps.setInt(2, categorie.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un Categorie");;
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM categorie WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un Categorie");;
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Categorie findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM categorie WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Categorie categorie = new Categorie();

				categorie.setId(rs.getInt("id"));
				categorie.setNom(rs.getString("nom"));

				return categorie;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver un Categorie");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Categorie> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM categorie");
			rs = ps.executeQuery();

			List<Categorie> listCategorie = new ArrayList<>();

			while (rs.next()) {
				Categorie categorie = new Categorie();

				categorie.setId(rs.getInt("id"));
				categorie.setNom(rs.getString("nom"));

				listCategorie.add(categorie);
			}

			return listCategorie;
		} catch (SQLException e) {
			System.err.println("problème de requête pour sélectionner un Categorie");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	@Override
	public Categorie findByName(String Nom) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM categorie WHERE nom like ? ");

			ps.setString(1, Nom);

			rs = ps.executeQuery();

			if (rs.next()) {
				Categorie categorie = new Categorie();

				categorie.setId(rs.getInt("id"));
				categorie.setNom(rs.getString("nom"));

				return categorie;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver un Categorie");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
	}


