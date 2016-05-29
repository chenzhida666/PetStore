package com.chenzhida.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.chenzhida.vo.BBSUser;
import com.chenzhida.dao.*;

public class UserServiceImpl implements IUserService {

	private IUserDAO dao=new UserDAOImpl();

	private String tmpDir=""+File.separator+"tmpDir";
	private String savDir=""+File.separator+"savDir";
	private Map<String,String> types=new HashMap<String,String>();
	private File ftmpDir=new File(tmpDir);
	private File fsavDir=new File(savDir);

	public UserServiceImpl(){

		types.put("image/jpeg", ".jpg");
		types.put("image/gif", ".gif");
		types.put("image/x-ms-bmp", ".bmp");
		types.put("image/png", ".png");

	}
	@Override
	public BBSUser login(BBSUser user) {
		// TODO Auto-generated method stub
		return dao.login(user);
	}


	@Override
	public void getHead(int id, HttpServletResponse response) {
		// TODO Auto-generated method stub
		dao.getHead(id, response);
	}


	@Override
	public BBSUser uploadPic(HttpServletRequest request) {
		// TODO Auto-generated method stub
		BBSUser user=new BBSUser();;
		String ttpath=request.getServletContext().getRealPath("/");
		try {

			request.setCharacterEncoding("utf-8");
			if(ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory df=new DiskFileItemFactory();
				df.setRepository(ftmpDir);
				df.setSizeThreshold(1024000*10);

				ServletFileUpload su=new ServletFileUpload(df);
				su.setFileSizeMax(1024*1024*10);
				su.setSizeMax(1024*1024*100);

				FileItemIterator fi=su.getItemIterator(request);

				//file input->text 
				while(fi.hasNext()){
					FileItemStream fs=fi.next();
					//找到文件类型，文件名称，上传的头像的文件流
					InputStream is=fs.openStream();



					//分开是file域还是input-text
					if(fs.isFormField()){//input-text
						String fieldname=fs.getFieldName();

						switch(fieldname){
						case "reusername":{
							user.setUsername(Streams.asString(is, "utf-8"));

							break;

						}
						case "repassword":{
							user.setPassword(Streams.asString(is, "utf-8"));

							break;

						}



						}


					}else if(fs.getName().length()>0){//有file域,并且上传了文件

						String fileType=fs.getContentType();
						String fileName=fs.getName();


						if(this.types.get(fileType)!=null){//是我们需要上传的文件类型
							//读取上传的文件
							BufferedInputStream bis=new BufferedInputStream(is);

							//设置文件名称
							String fname=request.getSession().getId();//全球唯一
							String ftype=this.types.get(fileType);//文件类型

							String ff=ttpath+"\\upload\\"+fname+ftype;//真正存储到硬盘上的肖像文件

							BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(ff));

							Streams.copy(bis, bos, true);
							user.setPpath(ff);
							bis.close();
							bos.flush();
							bos.close();

						}else{
							request.setAttribute("rerror", "文件类型错误，不能注册！");
						}



					}

				}


			}






		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;


	}
	
	@Override
	public void register(BBSUser user) {
		// TODO Auto-generated method stub
		dao.register(user);
	}



}
