package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}