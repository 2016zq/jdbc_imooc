package com.imooc.action;

import java.sql.SQLException;
import java.util.List;

import com.imooc.dao.GoddessDao;
import com.imooc.model.Goddess;

public class GoddessAction {
	GoddessDao dao=new GoddessDao();
	/**
	 * ���Ů��
	 * @param goddess ����goddess����
	 * @throws Exception
	 */
	public void add(Goddess goddess) throws Exception{
		goddess.setCreate_user("admin");
		dao.addGoddess(goddess);
	}
	/**
	 * ����Ů��
	 * @param goddess
	 * @throws Exception
	 */
	public void edit(Goddess goddess) throws Exception{
		dao.updateGoddess(goddess);
	}
	
	/**
	 * ����idɾ��Ů��
	 * @param id Ҫɾ����Ů���id
	 * @throws SQLException
	 */
	public void del(Integer id) throws SQLException{
		dao.delGoddess(id);
	}
		
	/**
	 * ����id��ѯŮ��
	 * @param id Ҫ��ѯ��Ů���id
	 * @return goddess
	 * @throws SQLException
	 */
	public Goddess get(Integer id) throws SQLException{
		return dao.get(id);
	}
	/**
	 * ����nameģ����ѯŮ��
	 * @param name Ҫ��ѯ��Ů�������(����ֻ�ǵ�һ����)
	 * @return goddess����List<Goddess>
	 * @throws Exception
	 */
	public List<Goddess> query(String name) throws Exception{
		return dao.query(name);
	}
	/**
	 * ��ѯȫ��Ů��
	 * @return List<Goddess>
	 * @throws Exception
	 */
	public List<Goddess>  query() throws Exception{
		return dao.query();
	}
	
}
