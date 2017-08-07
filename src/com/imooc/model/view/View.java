package com.imooc.model.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.imooc.action.GoddessAction;
import com.imooc.model.Goddess;
//��ͼ�㣬�������
public class View {
	GoddessAction action = new GoddessAction();
	Scanner sc = new Scanner(System.in);
	private static final String CONTEXT = "========��ӭ����Ů�����========\n" 
				+ "������Ů������Ĺ����б�\n" + 
				"[MAIN/M]:���˵�\n" + 
				"[QUERY/Q]:�鿴ȫ��Ů�����Ϣ\n" + 
				"[GET/G]:�鿴ĳλŮ�����ϸ��Ϣ\n" + 
				"[ADD/A]:���Ů����Ϣ\n" + 
				"[UPDATE/U]:����Ů����Ϣ\n" + 
				"[DELETE/D]:ɾ��Ů����Ϣ\n"+ 
				"[SEARCH/S]:��ѯŮ����Ϣ(��������ģ����ѯ)\n" + 
				"[EXIT/E]:�˳�Ů�����";

	public static void main(String[] args) throws Exception {
		View v = new View();
		v.mainMenu();
		v.opt();
		
	}

	public void opt() throws Exception {
		while(true) {
				System.out.println("�������Ӧ����:");
				String choice = sc.next().trim().toUpperCase();// ��ȥǰ��ո�
				switch (choice) {
				case "MAIN":
				case "M":
					mainMenu();
					break;
				case "QUERY":
				case "Q":
					query();
					break;
				case "GET":
				case "G":
					get();
					break;
				case "ADD":
				case "A":
					add();
					break;
				case "UPDATE":
				case "U":
					update();
					break;
				case "DELETE":
				case "D":
					delect();
					break;
				case "SEARCH":
				case "S":
					search();
					break;
				case "EXIT":
				case "E":
					System.out.println("���ѳɹ��˳�Ů�����");
					System.exit(0);
				default:
					System.out.println("û�иò���������");
			}
		}
	}
	/**
	 * ���˵�
	 */
	public void mainMenu() {
		System.out.println(CONTEXT);
	}
	/**
	 * �鿴ȫ��Ů�����Ϣ
	 */
	public void query() {
		try {
			List<Goddess> goddesses = action.query();
			if (goddesses != null) {
				for (Goddess g : goddesses) {
					System.out.println("���:" + g.getId() + ", ����:" + g.getUser_name() + ", ����:" + g.getAge());
				}
			}
			else {
				System.out.println("û�в�ѯ��Ů����Ϣ������");
			}
		} catch (Exception e) {
			System.out.println("��ѯʧ��!");
			e.printStackTrace();
		}
	}
	
	/**
	 * ����id��ѯĳλŮ����ϸ��Ϣ
	 */
	public void get() {
		System.out.println("������Ů��ı��,���鿴Ů�����ϸ��Ϣ:");
		int id;
		Goddess g;
		try{
			id = sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("�����ʽ����");
			sc.next();
			get();
			return;
		}
		try {
			g = action.get(id);
			if(g != null) {
				System.out.println(g);
			}
		} catch (SQLException e) {
			System.out.println("��ѯʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	/**
	 * ���Ů��
	 * @throws ParseException
	 */
	public void add() throws ParseException {
		Date birthday = new Date();
		System.out.println("��ֱ���дŮ�����Ϣ,���������Ϣ��֪��,����null����");
		System.out.println("����:");
		String user_name = sc.next();
		System.out.println("����:");
		Integer age = Integer.valueOf(sc.next());
		System.out.println("�Ա�:");
		String sex = sc.next();
		System.out.println("����(�� 1980-05-12)");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String in = sc.next();
		birthday = sf.parse(in);
		System.out.println("����:");
		String email = sc.next();
		System.out.println("�ֻ���:");
		String mobile = sc.next();
		Goddess g = new Goddess();
		g.setUser_name(user_name);
		g.setAge(age);
		g.setSex(sex);
		g.setBirthday(birthday);
		g.setEmail(email);
		g.setMobile(mobile);
		try {
			action.add(g);
			System.out.println("��ӳɹ�!");
		} catch (Exception e) {
			System.out.println("���ʧ��!");
			e.printStackTrace();
		}
	}
	/**
	 * ����Ů��
	 * @throws SQLException
	 */
	public void update() throws SQLException {
		Integer id;
		Goddess gs = new Goddess();
		Date birthday = new Date();
		String user_name;
		String sex;
		Integer age;
		String email;
		String mobile;
		System.out.println("������Ҫ���µ�Ů��ı��:");
		id = Integer.valueOf(sc.next());
		gs.setId(id);
		Goddess gf = action.get(id);
			if(gf != null) {
				
				System.out.println("����(��������¸��ֶ�,������null):");
				user_name = sc.next();
				if(user_name.equals("null")) {
					gs.setUser_name(gf.getUser_name());
				} else {
					gs.setUser_name(user_name);
				}
				
				System.out.println("����(��������¸��ֶ�,������0):");
				try{
					age = sc.nextInt();
					if(age == 0) {
						gs.setAge(gf.getAge());
					}else{
						gs.setAge(age);
					}
				} catch(InputMismatchException e) {
					System.out.println("�����ʽ����");
					sc.next();
					update();
					return;
				}
				
				System.out.println("�Ա�(������\"��\"��\"Ů\"):");
				sex = sc.next();
				gs.setSex(sex);
				
				//���յĸ���
				System.out.println("����(��������¸��ֶ�,������null;������£���ʽ��:1980-05-12):");
				String in = sc.next();
				if(in.equals("null")) {
					gs.setBirthday(gf.getBirthday());
				}else {
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					try {
						birthday = sf.parse(in);
						gs.setBirthday(birthday);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("����(��������¸��ֶ�,������null):");
				email = sc.next();
				if(email.equals("null")) {
					gs.setEmail(gf.getEmail());
				}else {
					gs.setEmail(email);
				}
				
				System.out.println("�ֻ���(��������¸��ֶ�,������null):");
				mobile = sc.next();
				if(mobile.equals("null")) {
					gs.setMobile(gf.getMobile());
				}else {
					gs.setMobile(mobile);
				}
				try {
					action.edit(gs);
					System.out.println("Ů����Ϣ���³ɹ�!");
				} catch (Exception e) {
					System.out.println("����ʧ��!");
					e.printStackTrace();
				}
			}else {
				System.out.println("û�иñ�ŵ�Ů�񡣡�����");
			}
	}
	/**
	 * ɾ��Ů��
	 */
	public void delect() {
		System.out.println("������Ҫɾ����Ů��ı��:");
		Integer id = Integer.valueOf(sc.next());
		try {
			action.del(id);
			System.out.println("ɾ��Ů�����!");
		} catch (SQLException e) {
			System.out.println("ɾ��ʧ��!");
			e.printStackTrace();
		}
	}
	
	public void search() {
		System.out.println("������Ҫ��ѯ������:");
		String name = sc.next();
		try {
			List<Goddess> goddesses = action.query(name);
			if(goddesses != null) {
				for(Goddess g:goddesses) {
					System.out.println(g);
				}
			}else {
				System.out.println("û�и�Ů�񡣡�����");
			}
		} catch (Exception e) {
			System.out.println("��ѯʧ��!");
			e.printStackTrace();
		}
	}
	// Scanner in = new Scanner(System.in);
	// System.out.println("������Ů�����Ϣ[���գ���ʽ:2014-12-12]��");
	// SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	// Date birthday=null;
	// birthday = sf.parse(in);
	// go.setBirthday(birthday);
	
	// System.out.println("������Ҫ��ѯ��Ů����Ϣ��֧���������ֻ��Ų�ѯ����������������������ö��ŷָ�[user_name=xx,mobile=xx]��");
	// if(in!=null&&in!=""){
	// List<Map<String, Object>> params=new ArrayList<Map<String,Object>>();
	// Map<String, Object> param=null;
	// String[] strs=in.split(",");
	// for (int i = 0; i < strs.length; i++) {
	// String[] strs_s=strs[i].split("=");
	// param=new HashMap<String, Object>();
	// param.put("name", strs_s[0]);
	// param.put("rela", "=");
	// param.put("value", "'"+strs_s[1]+"'");
	// params.add(param);
	// }
	// List<Goddess> list=action.query(params);
	// if(list!=null&&list.size()>0){
	// for (Goddess goddess : list) {
	// System.out.println(goddess.toString());
}
