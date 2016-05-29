package com.chenzhida.dao;

import javax.servlet.http.HttpServletResponse;

import com.chenzhida.vo.BBSUser;

public interface IUserDAO {
	BBSUser login(BBSUser user);
	void getHead(int id,HttpServletResponse response);
	public void register(BBSUser user);
}
