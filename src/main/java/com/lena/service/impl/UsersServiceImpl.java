package com.lena.service.impl;

import java.util.List;

import com.lena.mapper.UsersMapper;
import com.lena.pojo.Users;
import com.lena.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper usersMapper;
	public void addUser(Users user) {
		System.out.println(user.getUsername());
	this.usersMapper.insert(user);


	}
	@Override
	public List<Users> findUserAll() {
		return usersMapper.selectList(null);
		
	}
	@Override
	public Users findUserById(Integer id) {
		// TODO Auto-generated method stub
		return this.usersMapper.selectById(id);
	}
	@Override
	public void updateUser(Users users) {
		System.out.println(users.getId()+",,,"+users.getUsername());

		this.usersMapper.updateById(users);
		
	}
	@Override
	public void delUsers(Integer id) {
		// TODO Auto-generated method stub
		this.usersMapper.deleteById(id);
	}

}
