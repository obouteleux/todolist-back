package com.oscar.todolist.control;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.todolist.authent.Authentication;
import com.oscar.todolist.authent.LoginDTO;
import com.oscar.todolist.authent.LoginResponseDTO;
import com.oscar.todolist.entities.Task;
import com.oscar.todolist.entities.User;
import com.oscar.todolist.service.TaskService;
import com.oscar.todolist.service.UserService;

/**
 * @author Formation
 * @version 1.0
 * @created 02-nov.-2016 15:45:04
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainControl {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private Authentication authentication;
	
	@RequestMapping(value="/users", method = RequestMethod.POST)
	public User getUsers(String username, String password){
		System.out.println("Access : " + username + " " + password);
		return userService.connectionUser(username, password);
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public LoginResponseDTO login(@RequestBody LoginDTO login){
		
		//authentication
		System.out.println("Access : " + login.getEmail() + " " + login.getPassword());
		User user = userService.connectionUser(login.getEmail(), login.getPassword());
		
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		
		if(user != null){
			loginResponseDTO.setToken(authentication.createJWT(String.valueOf(user.getId()), "todolist", "login", 1800));
			loginResponseDTO.setId(user.getId());
			loginResponseDTO.setEmail(login.getEmail());
			loginResponseDTO.setFirstName(user.getFirstname());
			loginResponseDTO.setLastNname(user.getLastname());
			
			return loginResponseDTO;
		}
		else 
			return loginResponseDTO;
	}
	
	@RequestMapping(value="/user/{idUser}", method = RequestMethod.GET)
	public List<Task> getTasks(@PathVariable int idUser){
		
		return taskService.getTasks(idUser);
	}
	
	@RequestMapping(value="/task/{idUser}", method = RequestMethod.POST)
	public List<Task> addTask(@RequestBody Task task,@PathVariable int idUser){
		
		System.out.println(task + " | " + idUser);
		return taskService.addTask(task, idUser);
	}
	
	
	@RequestMapping(value="/task/{idTask}", method = RequestMethod.DELETE)
	public List<Task> removeTask(@PathVariable int idTask){
		System.out.println(idTask);
		return taskService.removeTask(idTask);
	}

	@RequestMapping(value="/updateTask/{idUser}", method = RequestMethod.POST)
	public List<Task> updateTask(@RequestBody Task task,@PathVariable int idUser){
		
		System.out.println(task + " | " + idUser);
		return taskService.updateTask(task, idUser);
	}

	@RequestMapping(value="/modifyStatus/{idTask}", method = RequestMethod.GET)
	public List<Task> modifyStatus(@PathVariable int idTask){
		System.out.println(idTask);
		return taskService.modifyStatus(idTask);
	}

	@RequestMapping(value="/newUser", method = RequestMethod.POST)
	public int newUser(@RequestBody User user){
		
		System.out.println(user);
		return userService.newUser(user);
	}
	
}//end ConsulterHistoriqueControl
