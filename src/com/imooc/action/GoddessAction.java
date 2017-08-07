package com.imooc.action;

import java.sql.SQLException;
import java.util.List;

import com.imooc.dao.GoddessDao;
import com.imooc.model.Goddess;

public class GoddessAction {
	GoddessDao dao=new GoddessDao();
	/**
	 * 添加女神
	 * @param goddess 传入goddess对象
	 * @throws Exception
	 */
	public void add(Goddess goddess) throws Exception{
		goddess.setCreate_user("admin");
		dao.addGoddess(goddess);
	}
	/**
	 * 更新女神
	 * @param goddess
	 * @throws Exception
	 */
	public void edit(Goddess goddess) throws Exception{
		dao.updateGoddess(goddess);
	}
	
	/**
	 * 根据id删除女神
	 * @param id 要删除的女神的id
	 * @throws SQLException
	 */
	public void del(Integer id) throws SQLException{
		dao.delGoddess(id);
	}
		
	/**
	 * 根据id查询女神
	 * @param id 要查询的女神的id
	 * @return goddess
	 * @throws SQLException
	 */
	public Goddess get(Integer id) throws SQLException{
		return dao.get(id);
	}
	/**
	 * 根据name模糊查询女神
	 * @param name 要查询的女神的名字(可以只记得一部分)
	 * @return goddess或者List<Goddess>
	 * @throws Exception
	 */
	public List<Goddess> query(String name) throws Exception{
		return dao.query(name);
	}
	/**
	 * 查询全部女神
	 * @return List<Goddess>
	 * @throws Exception
	 */
	public List<Goddess>  query() throws Exception{
		return dao.query();
	}
	
}
