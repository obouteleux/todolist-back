package com.oscar.todolist.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author Formation
 * @version 1.0
 * @created 08-nov.-2016 12:50:31
 */

@NamedQueries({
	@NamedQuery(name="findByStatus", query="SELECT entity FROM Task entity WHERE entity.user.id = :idUser AND entity.status = :status"),
	@NamedQuery(name="findAllUsers",  query="SELECT entity FROM User entity"),
	@NamedQuery(name="findUserByEmail",  query="SELECT entity FROM User entity WHERE entity.email = :emailUser")
	
}) 

@Entity 
@Table(catalog="todolist")
public class User {

	private String email;
	private String firstname;
	private int id;
	private String lastname;
	private String password;
	public  List<Task> tasks;

	public User(){

	}

	public User(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	@OneToMany(mappedBy="user")
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	
}//end User
