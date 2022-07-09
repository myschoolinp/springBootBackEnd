package com.test.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.demo.dao.RegisterUserIntf;
import com.test.demo.entities.AppResponse;
import com.test.demo.entities.LoginUser;
import com.test.demo.entities.UserRegister;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("coaching/api/v1/")
public class UserController {

	@Autowired
	private RegisterUserIntf RegisterUserIntf;
	
	@GetMapping("home")
	public ResponseEntity<AppResponse<UserRegister>> home() {
		AppResponse<?> response =new AppResponse<>();
		response.setStatus("SUCCESS");
		response.setMessage("hello from controller");
		List list =new ArrayList<UserRegister>();
		response.setData(list);
		return new ResponseEntity(response,HttpStatus.OK);
	}
	
	@PostMapping("loginUser")
	public ResponseEntity<AppResponse<UserRegister>> loginUser(@RequestBody LoginUser luser){
		AppResponse<UserRegister> response = new AppResponse<UserRegister>();
		List<UserRegister> list =new ArrayList<UserRegister>();
		HttpStatus statusCode;
		try {
	   
	     if(this.RegisterUserIntf.existsById(luser.getUserName())) {
	    	  Optional<UserRegister> optional = this.RegisterUserIntf.findById(luser.getUserName());
	    	  UserRegister currentUser=optional.get();
	    	  if(currentUser.getPassword().equals(luser.getPassword())){
	    	  response.setMessage("Login Successfully");
	    	   statusCode =HttpStatus.OK;
			   response.setStatus("SUCCESS");
			   
			   list.add(currentUser);
			   response.setData(list);
	    	  }else {
	    		  statusCode =HttpStatus.OK;
	    		  response.setMessage("Username or password not match");
				   response.setStatus("FAILED");
				   response.setData(list);
	    	  }
	     }else {
	    	    statusCode =HttpStatus.NOT_FOUND;
	    	   response.setMessage("User Not Found");
			   response.setStatus("FAILED");
			   response.setData(list);
	     }
		
		}
		catch (Exception e) {
			   statusCode =HttpStatus.INTERNAL_SERVER_ERROR;
			   response.setMessage("Something went wrong");
			   response.setStatus("FAILED");
			   response.setData(list);
		}
		return new ResponseEntity(response,statusCode);
	}
	@PostMapping("addUser")
	public ResponseEntity<AppResponse<UserRegister>> addNewUser(@RequestBody UserRegister user){
		AppResponse<UserRegister> response = new AppResponse<UserRegister>();
		List<UserRegister> list =new ArrayList<UserRegister>();
		HttpStatus statusCode;
		try {
		if(this.RegisterUserIntf.existsById(user.getUserName())) {
			  statusCode =HttpStatus.OK;
			  response.setMessage("User Exist with this UserName");
			   response.setStatus("FAILER");
			response.setData(list);
		}else {
		
	    this.RegisterUserIntf.save(user);
	     statusCode =HttpStatus.OK;
	     response.setMessage("Added User Successfully");
		   response.setStatus("SUCCESS");
		   response.setData(list);
		}
		}
		catch (Exception e) {
		   statusCode =HttpStatus.INTERNAL_SERVER_ERROR;
		   response.setMessage("Not Able to add Data");
		   response.setStatus("FAILED");
		   response.setData(list);
		}
		return new ResponseEntity(response,statusCode);
		
	}
	
}
