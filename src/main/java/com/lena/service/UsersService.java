package com.lena.service;

import java.util.List;


import com.lena.pojo.Users;

public interface UsersService {
	
	void addUser(Users user);
	List<Users> findUserAll();
	Users findUserById(Integer id);
	Users findUserByName(String username);
	void updateUser(Users users);
	
	void delUsers(Integer id);

}
