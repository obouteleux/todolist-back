package com.oscar.todolist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oscar.todolist.entities.User;
import com.oscar.todolist.persistence.UserDAO;

/**
 * @author Formation
 * @version 1.0
 * @created 09-nov.-2016 09:45:04
 */
@Service
public class UserService {
	@Autowired private UserDAO userDAO;

	public Object getTasks(int saveUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDAO.getUser();
	}

	@Transactional
	public User connectionUser(String username, String password) {
		List<User> users = userDAO.getPassword(username);
		System.out.println(users.size());
		
		if(users.size() == 1 && users.get(0).getPassword().equals(password)){
			return users.get(0);
		}
		return null;
	}

	public int newUser(User user) {
		User newUser = userDAO.newUser(user);
		if (newUser == null)
			return -1;
		
		return newUser.getId();
	}

}

