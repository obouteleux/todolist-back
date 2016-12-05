package com.oscar.todolist.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.oscar.todolist.entities.User;

/**
 * @author Formation
 * @version 1.0
 * @created 02-nov.-2016 15:45:04
 */
@Repository
public class UserDAO {
	@PersistenceContext 
	private EntityManager entityManager;

	public UserDAO(){

	}

	@Transactional
	public User newUser(User user) {
		System.out.println("DAO");
		if(getPassword(user.getEmail()) != null){
			entityManager.persist(user);
			return user;
		}
			
		return null;
		
	}
	
	public User getUser(int idUser) {
		System.out.println("DAO");
		return entityManager.find(User.class, idUser);
		
	}

	public List<User> getUser() {
		Query query = entityManager.createNamedQuery("findAllUsers");
		
		return query.getResultList();
	}

	public List<User> getPassword(String username) {
		Query query = entityManager.createNamedQuery("findUserByEmail");
		query.setParameter("emailUser", username);
		
		return query.getResultList();		
	}

}//end EmployeDAO