package com.emsi.Maven.jdbc.Services;

import com.emsi.Maven.jdbc.Entites.User;
import com.emsi.Maven.jdbc.dao.Imp.UserDaoImp;
import com.emsi.Maven.jdbc.dao.UserDao;

public class UserServices {
    private UserDao userDao=new UserDaoImp();

    void insert(User user)
    {
        userDao.insert(user);
    }

    void update(User user)
    {
        userDao.update(user);
    }

    void deleteById(Integer id)
    {
        userDao.deleteById(id);
    }

    public User findByLogin(String st)
    {
        return  userDao.findByLogin(st);
    }
}
