package com.emsi.Maven.jdbc.dao.Imp;

import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;
import com.emsi.Maven.jdbc.Entites.User;
import com.emsi.Maven.jdbc.dao.UserDao;

import java.sql.*;
import java.util.List;

public class UserDaoImp implements UserDao {
    private Connection conn= DB.getConnection();

    @Override
    public void insert(User user) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO categorie (login,password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    user.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un User"+e);;
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void update(User user) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE categorie SET login = ?,password=? WHERE id = ?");

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de mise à jour d'un user");;
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de suppression d'un Categorie");;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public User findByLogin(String st) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM users WHERE login like ?");

            ps.setString(1, st);

            rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                return user;
            }

            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver un user");;
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }


}
