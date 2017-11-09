package com.ingesup.restcontroller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.ingesup.controller.utils.ControllerUtils;
import com.ingesup.hibernate.EntityManager;
import com.ingesup.hibernate.UserManager;
import com.ingesup.model.User;

@RestController
public class UserRestController {

	/**
	 * Return all users as a List
	 * @return
	 */
	@RequestMapping(value="/rest/User", method=RequestMethod.GET)
	public ResponseEntity<?> userGet(HttpServletRequest request){
		
		// 0. Validating current user
		if(!ControllerUtils.isValidUser(request))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		// 1. Loading all the User as a List
		List<User> userList = UserManager.getAll();
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	/**
	 * Create the requested user
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rest/UserCreate", method = RequestMethod.POST)
	public ResponseEntity<?> userCreate(@RequestParam String userEmail, @RequestParam String userPassword, HttpServletRequest request){
		
		// 0. Validating current user
		if(!ControllerUtils.isValidUser(request))
			return new ResponseEntity<>("Vous n'êtes pas autorisé à faire cette action.",HttpStatus.UNAUTHORIZED);
		
		// 1. Trying to load the request user
		User currentUser = new User(null, userEmail, Hashing.sha1().hashString(userPassword, Charsets.UTF_8 ).toString());
		
		// 2. Create the user
		try {
			EntityManager.create(currentUser);
		} catch(Exception e){
			return new ResponseEntity<>("Erreur lors de la création de l'utilisateur.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		// 3. Reload the list of user
		currentUser = UserManager.get(currentUser);
		
		return new ResponseEntity<>(currentUser, HttpStatus.CREATED);
	}
	
	/**
	 * Delete the requested user
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rest/UserDelete", method = RequestMethod.POST)
	public ResponseEntity<?> userDelete(@RequestParam Integer userId, HttpServletRequest request){
		
		// 0. Validating current user
		if(!ControllerUtils.isValidUser(request))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		// 1. Trying to load the request user
		User currentUser = UserManager.get(userId);
		
		if(currentUser == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		// 2. Delete the user
		EntityManager.delete(currentUser);
		
		// 3. Reload the list of user
		List<User> userList = UserManager.getAll();
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
}
