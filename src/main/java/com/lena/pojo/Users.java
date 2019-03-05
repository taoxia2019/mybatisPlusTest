package com.lena.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.NotBlank;

public class Users {
	/*
	* @TableId中的 value用来指定数据库表中主键的 名称
	* IdType用来指定主键策略，以下用自增。
	* 校验规则
		@NotNull	限制必须不为null
		@AssertFalse	限制必须为false
		@AssertTrue	限制必须为true
		@DecimalMax(value)	限制必须为一个不大于指定值的数字
		@DecimalMin(value)	限制必须为一个不小于指定值的数字
		@Digits(integer,fraction)	限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
		@Future	限制必须是一个将来的日期
		@Max(value)	限制必须为一个不大于指定值的数字
		@Min(value)	限制必须为一个不小于指定值的数字
		@Past	限制必须是一个过去的日期
		@Pattern(value)	限制必须符合指定的正则表达式
		@Size(max,min)	限制字符长度必须在min到max之间
		@Past	验证注解的元素值（日期类型）比当前时间早
		@NotEmpty	验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
		@NotBlank	验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
		@Email	验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式
	* */
	@TableId(value="id",type = IdType.AUTO)
	private Integer id;
	//@NotBlank(message = "用户名不能为空")//非空校验 message看重新设置页面呈现的值
	private String username;
	//@NotBlank
	private String password;
	private String privilege;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
}
