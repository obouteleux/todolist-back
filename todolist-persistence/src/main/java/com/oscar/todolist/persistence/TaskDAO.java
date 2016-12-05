package com.oscar.todolist.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oscar.todolist.entities.Status;
import com.oscar.todolist.entities.Task;
import com.oscar.todolist.entities.User;

@Repository
public class TaskDAO {
	@PersistenceContext 
	private EntityManager entityManager;
	
	@Autowired UserDAO userDao;

	@Transactional
	public Task saveTask(Task task) {
		System.out.println("DAO");
		entityManager.persist(task);
		
		return task;
	}
	
	public List<Task> findAll(int idUser) {

		Query query = entityManager.createNamedQuery("findAll");
		query.setParameter("idUser", idUser);
		
		return query.getResultList();
	}

	public List<Task> getTasks(int idUser) {
		Query query = entityManager.createNamedQuery("findTasksById");
		query.setParameter("idUser", idUser);
		return  query.getResultList();
	}

	@Transactional
	public List<Task> addTasks(Task task, int idUser) {
		User user = userDao.getUser(idUser);
		task.setUser(user);
		task.setStatus(Status.ACTIVE);
		entityManager.persist(task);
		return getTasks(idUser);
	}

	@Transactional
	public List<Task> removeTasks(int idTask) {
		Task task = entityManager.find(Task.class, idTask);
		int idUser = task.getUser().getId();
		entityManager.remove(task);
		return getTasks(idUser);
	}

	@Transactional
	public List<Task> modifyStatus(int idTask) {
		Task task = entityManager.find(Task.class, idTask);
		int idUser = task.getUser().getId();
		if (task.getStatus() == Status.ACTIVE)
			task.setStatus(Status.COMPLETED);
		else
			task.setStatus(Status.ACTIVE);
		return getTasks(idUser);
	}

	@Transactional
	public List<Task> updateTask(Task task, int idUser) {
		Task taskTemp = entityManager.find(Task.class, task.getId());
		taskTemp.setTitle(task.getTitle());
		taskTemp.setDescription(task.getDescription());
		taskTemp.setStartDate(task.getStartDate());
		taskTemp.setEndDate(task.getEndDate());
		
		return getTasks(idUser);
	}

}

