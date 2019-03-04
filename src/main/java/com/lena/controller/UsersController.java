package com.lena.controller;

import java.util.List;

import com.lena.mapper.UsersMapper;
import com.lena.pojo.Users;
import com.lena.service.UsersService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;


@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private UsersMapper usersMapper;
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	@RequestMapping("/add")
	public String addUser(Users user){
		this.usersService.addUser(user);
		System.out.println(user.getUsername());
		return "ok";
	}
	
	@RequestMapping("/findUserAll")
	public String findUserAll(Model model){
		List<Users> list = this.usersService.findUserAll();
		model.addAttribute("list", list);
		
		return "userslist";
	}
	@RequestMapping("/findUserById")
	public String findUserById(Integer id,Model model){
		Users user = this.usersService.findUserById(id);
		model.addAttribute("user", user);
		return "updateuser";
	}
	
	@RequestMapping("/editUser")
	public String editUser(Users users){
		
		this.usersService.updateUser(users);
		System.out.println(users.getId());
		return "redirect:/users/findUserAll";
	}
	@RequestMapping("/delUser")
	public String delUser(Integer id){
		this.usersService.delUsers(id);
		return "redirect:/users/findUserAll";
	}
	
	
	//http://localhost:8080/users/findByPage?page=2&size=2
	//分页查询
	@RequestMapping("findByPage")
	public String findByPage(Integer page,Integer size,Model model){
		Wrapper<Users> wr=new EntityWrapper<>();
		
		RowBounds rb =new RowBounds((page-1)*size,size);
		List<Users> selectPage = usersMapper.selectPage(rb, wr);
		
		model.addAttribute("list", selectPage);
		return "userslist";
	}
	

}
