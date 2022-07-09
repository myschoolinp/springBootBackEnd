package com.test.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.test.demo.entities.UserRegister;

public interface RegisterUserIntf extends CrudRepository<UserRegister, String>{

}
