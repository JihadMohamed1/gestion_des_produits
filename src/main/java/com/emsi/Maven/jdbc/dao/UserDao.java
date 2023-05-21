package com.emsi.Maven.jdbc.dao;

import com.emsi.Maven.jdbc.Entites.Categorie;
import com.emsi.Maven.jdbc.Entites.Product;
import com.emsi.Maven.jdbc.Entites.User;

import java.util.List;

public interface UserDao {

        void insert(User user);

        void update(User user);

        void deleteById(Integer id);

       public User findByLogin(String st);



}
