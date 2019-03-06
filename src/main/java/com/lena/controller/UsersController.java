package com.lena.controller;

import java.util.List;

import com.lena.mapper.UsersMapper;
import com.lena.pojo.Users;
import com.lena.service.UsersService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	//@Autowired
	//private UsersMapper usersMapper;
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}

	@RequestMapping("/test")
	public Object test(){
		return "test";
	}

	@RequestMapping("/add")
	public Object add(){
		return "user/add";
	}
    /*非空校验
    @Valid开启Users类的数据校验
    BindingResult封装了校验结果
    在页面中 的对应表单input 框后 <font color="red" th:errors="${users.user}"/>可获得错误信息
    如跳转发生异常，可在跳转的controller方法中注入Users
    springmvc默认将users注入到model当中
    参数的变量名需要和对象名称相同，result使用的是驼峰命名规则
    */
    @RequestMapping("/save")
    public String save(Users users){

		System.out.println(users.getPassword());
		SimpleHash newPassword= new SimpleHash("MD5",users.getPassword(),"lena",2);
		users.setPassword(newPassword.toString());
		System.out.println(users.getUsername());
		usersService.addUser(users);

	    return "redirect:/users/findUserAll";
    }
	@RequestMapping("/update")
	public Object update(){
		return "user/update";
	}

	@RequestMapping("/tologin")
	public Object tologin(){
		return "login";
	}

	@RequestMapping("/noauth")
	public Object noauth(){
		return "noauth";
	}

	@RequestMapping("/login")
	public Object login(String username,String password,Model model){
		//1、获得主体
		Subject subject = SecurityUtils.getSubject();
		//2、封装用户数据
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		System.out.println(username+"controller");
		System.out.println(password+"controller");
		//3、执行登录方法
		try {
			subject.login(token);
			return "redirect:test";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg","用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg","密码不正确。");
			return "login";
		}
	}
	
	@RequestMapping("/findUserAll")
	public String findUserAll(Model model){
		List<Users> list = this.usersService.findUserAll();
		model.addAttribute("list", list);
		
		return "userslist";
	}
	/*@RequestMapping("/findUserById")
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
	}*/
	

}
