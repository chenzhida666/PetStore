package com.chenzhida.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.chenzhida.db.DB;
import com.chenzhida.vo.BBSUser;

public class UserDAOImpl implements IUserDAO {
	private DruidPooledConnection conn;
	public UserDAOImpl(){
		
		
		conn=DB.getconnection();
		
	}
	
	
	
	
	
	
	
	@Override
	public BBSUser login(BBSUser user) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		BBSUser u=null;
		try {
			String sql="select * from user_info where id=? and name=? and sex=? and age=? and phone_number=? "
					+ "and score=? and High_School=? and exa_number=? and art_science=? and parents_name=? and parents_Pnumber=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,user.getId());
			pstmt.setString(2,user.getName());
			pstmt.setString(3,user.getSex());
			pstmt.setInt(4,user.getAge());
			pstmt.setInt(5,user.getPhone_number());
			pstmt.setInt(6,user.getScore());
			pstmt.setString(7,user.getHigh_School());
			pstmt.setInt(8,user.getExa_number());
			pstmt.setString(9,user.getArt_science());
			pstmt.setString(10,user.getParents_name());
			pstmt.setInt(11,user.getParents_Pnumber());
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				
				u=new BBSUser();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setPhone_number(rs.getInt("phone_number"));
				u.setScore(rs.getInt("score"));
				u.setHigh_School(rs.getString("high_School"));
				u.setExa_number(rs.getInt("exa_number"));
				u.setArt_science(rs.getString("art_science"));
				u.setParents_name(rs.getString("parents_name"));
				u.setParents_Pnumber(rs.getInt("parents_Pnumber"));
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return u;
	}







	@Override
	public void getHead(int id,HttpServletResponse response) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		InputStream is=null;
		
		try {
			String sql="select pic from bbsuser where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				
				is=rs.getBinaryStream("pic");
				ServletOutputStream sos=response.getOutputStream();
				int n=-1;
				byte[] buffer=new byte[1024];
				while((n=is.read(buffer))!=-1){
					sos.write(buffer, 0, n);
				}
				sos.flush();
				sos.close();
				is.close();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}


	private int getMaxid(){
		int count=0;
		CallableStatement cs=null;
		
		try {
			
			cs=conn.prepareCall(" {?= call f_max_id() }");
			cs.registerOutParameter(1,java.sql.Types.NUMERIC);
			cs.execute();
			count=cs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			
			try {
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		return count+1;
	}
	public static void main(String[] args) {
		System.out.println(new UserDAOImpl().getMaxid());
	}



	@Override
	public void register(BBSUser user) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql="insert into bbsuser(id,username,password,pic,pagenum) "
					+"values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,this.getMaxid());
			pstmt.setString(2,user.getName());
//			pstmt.setString(3,user.getPassword());
//			FileInputStream fis=new FileInputStream(user.getPpath());
			
			pstmt.setBinaryStream(4,fis,fis.available());
			pstmt.setInt(5,5);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			try {
				
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
	}

}
