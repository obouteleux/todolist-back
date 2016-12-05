package com.oscar.todolist.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Formation
 * @version 1.0
 * @created 08-nov.-2016 12:50:31
 */

@NamedQueries({
	@NamedQuery(name="findTasksById", query="SELECT entity FROM Task entity WHERE entity.user.id = :idUser"),
})

@Entity 
@Table(catalog="todolist")
public class Task {

	private User user;
	private String description;
	private Date endDate;
	private int id;
	private Date startDate;
	private String title;
	public Status status;

	public Task(){
		
	}

	public Task(String title, String description, User user) {
		this.title = title;
		this.description = description;
		this.user = user;
		this.startDate = new LocalDate().toDate();
		this.status = Status.ACTIVE;
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
}//end Task
