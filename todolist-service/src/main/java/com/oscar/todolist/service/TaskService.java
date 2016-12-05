package com.oscar.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscar.todolist.entities.Status;
import com.oscar.todolist.entities.Task;
import com.oscar.todolist.entities.User;
import com.oscar.todolist.persistence.TaskDAO;

/**
 * @author Formation
 * @version 1.0
 * @created 09-nov.-2016 09:45:04
 */
@Service
public class TaskService {
	@Autowired private TaskDAO taskDAO;

	public Task saveTask(Task task) {
		System.out.println("service");
		return taskDAO.saveTask(task);
	}

	public List<Task> getTasks(int idUser) {
		return taskDAO.getTasks(idUser);
	}

	public List<Task> addTask(Task task, int idUser) {
		return taskDAO.addTasks(task, idUser);
	}

	public List<Task> removeTask(int idTask) {
		return taskDAO.removeTasks(idTask);
	}

	public List<Task> modifyStatus(int idTask) {
		return taskDAO.modifyStatus(idTask);
	}

	public List<Task> updateTask(Task task, int idUser) {
		return taskDAO.updateTask(task, idUser);
	}	
	
}

