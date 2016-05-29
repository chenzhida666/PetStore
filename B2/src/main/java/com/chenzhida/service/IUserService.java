package com.chenzhida.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenzhida.vo.BBSUser;

public interface IUserService {
	BBSUser login(BBSUser user);
	void getHead(int id,HttpServletResponse response);
	public BBSUser uploadPic(HttpServletRequest request); 
	public void register(BBSUser user) ;
}
