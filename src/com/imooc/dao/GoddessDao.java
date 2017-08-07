package com.imooc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.imooc.db.DBUtil;
import com.imooc.model.Goddess;

//Dao层
public class GoddessDao {

	//新增女神
	public void addGoddess(Goddess g) throws Exception{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				"insert into goddess" +
				"(user_name,sex,age,birthday,email,mobile," +
				"create_user,create_date,update_date)" +
				"values(" +
				"?,?,?,?,?,?,?,current_date(),current_date())";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1, g.getUser_name());
		ptmt.setString(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getCreate_user());
		ptmt.execute();
	}
	
	//更新女神（根据id）
	public void updateGoddess(Goddess g) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" update goddess " +
				" set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?, " +
				" update_date=current_date()" + " where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1, g.getUser_name());
		ptmt.setString(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setInt(7, g.getId());
		ptmt.execute();
	}
	
	//根据id删除女神
	public void delGoddess(Integer id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" delete from goddess " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	//查询全部女神
	public List<Goddess> query() throws Exception{
		List<Goddess> result=new ArrayList<Goddess>();
		
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from goddess  ");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		ResultSet rs=ptmt.executeQuery();
		
		Goddess g=null;
		while(rs.next()){
			g=new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			result.add(g);
		}
		return result;
	}
	
	//根据name模糊查询女神
	public List<Goddess> query(String name) throws Exception{
		List<Goddess> result=new ArrayList<Goddess>();
		
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from goddess  ");
		
		sb.append(" where user_name like ?");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%"+name+"%");
		System.out.println(sb.toString());
		ResultSet rs=ptmt.executeQuery();
		
		Goddess g=null;
		while(rs.next()){
			g=new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getString("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			result.add(g);
		}
		return result;
	}
	
	//根据id查询女神
	public Goddess get(Integer id) throws SQLException{
		Goddess g=null;
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" select * from goddess " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			g=new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getString("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
		}
		return g;
	}
	
//	public List<Goddess> query(List<Map<String, Object>> params) throws Exception{
//	List<Goddess> result=new ArrayList<Goddess>();
//	
//	Connection conn=DBUtil.getConnection();
//	StringBuilder sb=new StringBuilder();
//	sb.append("select * from goddess where 1=1 ");
//	
//	if(params!=null&&params.size()>0){
//		for (int i = 0; i < params.size(); i++) {
//			Map<String, Object> map=params.get(i);
//			sb.append(" and  "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
//		}
//	}
//	
//	PreparedStatement ptmt=conn.prepareStatement(sb.toString());
//	
//	System.out.println(sb.toString());
//	ResultSet rs=ptmt.executeQuery();
//	
//	Goddess g=null;
//	while(rs.next()){
//		g=new Goddess();
//		g.setId(rs.getInt("id"));
//		g.setUser_name(rs.getString("user_name"));
//		g.setAge(rs.getInt("age"));
//		g.setSex(rs.getInt("sex"));
//		g.setBirthday(rs.getDate("birthday"));
//		g.setEmail(rs.getString("email"));
//		g.setMobile(rs.getString("mobile"));
//		g.setCreate_date(rs.getDate("create_date"));
//		g.setCreate_user(rs.getString("create_user"));
//		g.setUpdate_date(rs.getDate("update_date"));
//		g.setUpdate_user(rs.getString("update_user"));
//		g.setIsdel(rs.getInt("isdel"));
//		
//		result.add(g);
//	}
//	return result;
//}

}
