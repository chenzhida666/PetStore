package com.chenzhida.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenzhida.service.IUserService;
import com.chenzhida.service.UserServiceImpl;
import com.chenzhida.vo.BBSUser;
@WebServlet(name="/usercontrol",urlPatterns={"/user"})
public class UserControl extends HttpServlet {
	private IUserService service=new UserServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=req.getParameter("action")==null?"reg":req.getParameter("action");
		if(action.equals("login")){
			login(req, resp);
		}else if(action.equals("head")){
			String id=req.getParameter("id");
			service.getHead(Integer.parseInt(id), resp);
		}else if(action.equals("reg")){//直接是注册
			register(req, resp);
		}



	}
	private void register(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		BBSUser user=service.uploadPic(req);
		if(user!=null){//上传成功，在存储数据库
			
			
			service.register(user);
		}
		RequestDispatcher dispatcher=null;
		dispatcher=req.getRequestDispatcher("show.jsp");
		dispatcher.forward(req, resp);
		
	}

	private void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher=null;

	

//		String id=req.getParameter("id");
		String name=req.getParameter("name");
//		String sex=req.getParameter("sex");
//		String age=req.getParameter("age");
//		String phone_number=req.getParameter("phone_number");
//		String score=req.getParameter("score");
//		String high_School=req.getParameter("high_School");
//		String exa_number=req.getParameter("exa_number");
//		String art_science=req.getParameter("art_science");
//		String parents_name=req.getParameter("parents_name");
//		String parents_Pnumber=req.getParameter("parents_Pnumber");
		
		String on=req.getParameter("sun");


		

		BBSUser user=new BBSUser();
		
//		user.setId(id);
		user.setName(name);
//		user.setSex(sex);
//		user.setAge(age);
//		user.setPhone_number(phone_number);
//		user.setScore(score);
//		user.setHigh_School(high_School);
//		user.setExa_number(exa_number);
//		user.setArt_science(art_science);
//		user.setParents_name(parents_name);
//		user.setParents_Pnumber(parents_Pnumber);
		BBSUser u=service.login(user);
		if(u==null){

			req.setAttribute("linvalid", "用户名密码错误，请重新登录");

		}else{
			if(on!=null){


				Cookie uc=new Cookie("0722u",username);
				resp.addCookie(uc);
				uc.setMaxAge(3600*7);


				Cookie up=new Cookie("0722p",password);
				resp.addCookie(up);
				up.setMaxAge(3600*7);
			}








			req.getSession().setAttribute("lsuccess", u);
			req.setAttribute("lok", "登录成功，欢迎回来");
		}
		dispatcher=req.getRequestDispatcher("show.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
