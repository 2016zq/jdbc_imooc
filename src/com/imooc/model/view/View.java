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
//视图层，程序入口
public class View {
	GoddessAction action = new GoddessAction();
	Scanner sc = new Scanner(System.in);
	private static final String CONTEXT = "========欢迎来到女神禁区========\n" 
				+ "下面是女神禁区的功能列表：\n" + 
				"[MAIN/M]:主菜单\n" + 
				"[QUERY/Q]:查看全部女神的信息\n" + 
				"[GET/G]:查看某位女神的详细信息\n" + 
				"[ADD/A]:添加女神信息\n" + 
				"[UPDATE/U]:更新女神信息\n" + 
				"[DELETE/D]:删除女神信息\n"+ 
				"[SEARCH/S]:查询女神信息(根据姓名模糊查询)\n" + 
				"[EXIT/E]:退出女神禁区";

	public static void main(String[] args) throws Exception {
		View v = new View();
		v.mainMenu();
		v.opt();
		
	}

	public void opt() throws Exception {
		while(true) {
				System.out.println("请输入对应操作:");
				String choice = sc.next().trim().toUpperCase();// 除去前后空格
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
					System.out.println("您已成功退出女神禁区");
					System.exit(0);
				default:
					System.out.println("没有该操作。。。");
			}
		}
	}
	/**
	 * 主菜单
	 */
	public void mainMenu() {
		System.out.println(CONTEXT);
	}
	/**
	 * 查看全部女神的信息
	 */
	public void query() {
		try {
			List<Goddess> goddesses = action.query();
			if (goddesses != null) {
				for (Goddess g : goddesses) {
					System.out.println("编号:" + g.getId() + ", 姓名:" + g.getUser_name() + ", 年龄:" + g.getAge());
				}
			}
			else {
				System.out.println("没有查询到女神信息。。。");
			}
		} catch (Exception e) {
			System.out.println("查询失败!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id查询某位女神详细信息
	 */
	public void get() {
		System.out.println("请输入女神的编号,来查看女神的详细信息:");
		int id;
		Goddess g;
		try{
			id = sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("输入格式有误！");
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
			System.out.println("查询失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加女神
	 * @throws ParseException
	 */
	public void add() throws ParseException {
		Date birthday = new Date();
		System.out.println("请分别填写女神的信息,如果部分信息部知道,则用null代替");
		System.out.println("姓名:");
		String user_name = sc.next();
		System.out.println("年龄:");
		Integer age = Integer.valueOf(sc.next());
		System.out.println("性别:");
		String sex = sc.next();
		System.out.println("生日(如 1980-05-12)");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String in = sc.next();
		birthday = sf.parse(in);
		System.out.println("邮箱:");
		String email = sc.next();
		System.out.println("手机号:");
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
			System.out.println("添加成功!");
		} catch (Exception e) {
			System.out.println("添加失败!");
			e.printStackTrace();
		}
	}
	/**
	 * 更新女神
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
		System.out.println("请输入要跟新的女神的编号:");
		id = Integer.valueOf(sc.next());
		gs.setId(id);
		Goddess gf = action.get(id);
			if(gf != null) {
				
				System.out.println("姓名(如果不跟新该字段,则输入null):");
				user_name = sc.next();
				if(user_name.equals("null")) {
					gs.setUser_name(gf.getUser_name());
				} else {
					gs.setUser_name(user_name);
				}
				
				System.out.println("年龄(如果不跟新该字段,则输入0):");
				try{
					age = sc.nextInt();
					if(age == 0) {
						gs.setAge(gf.getAge());
					}else{
						gs.setAge(age);
					}
				} catch(InputMismatchException e) {
					System.out.println("输入格式有误！");
					sc.next();
					update();
					return;
				}
				
				System.out.println("性别(请输入\"男\"或\"女\"):");
				sex = sc.next();
				gs.setSex(sex);
				
				//生日的更新
				System.out.println("生日(如果不跟新该字段,则输入null;如果更新，格式如:1980-05-12):");
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
				
				System.out.println("邮箱(如果不跟新该字段,则输入null):");
				email = sc.next();
				if(email.equals("null")) {
					gs.setEmail(gf.getEmail());
				}else {
					gs.setEmail(email);
				}
				
				System.out.println("手机号(如果不跟新该字段,则输入null):");
				mobile = sc.next();
				if(mobile.equals("null")) {
					gs.setMobile(gf.getMobile());
				}else {
					gs.setMobile(mobile);
				}
				try {
					action.edit(gs);
					System.out.println("女神信息更新成功!");
				} catch (Exception e) {
					System.out.println("更新失败!");
					e.printStackTrace();
				}
			}else {
				System.out.println("没有该编号的女神。。。。");
			}
	}
	/**
	 * 删除女神
	 */
	public void delect() {
		System.out.println("请输入要删除的女神的编号:");
		Integer id = Integer.valueOf(sc.next());
		try {
			action.del(id);
			System.out.println("删除女神完毕!");
		} catch (SQLException e) {
			System.out.println("删除失败!");
			e.printStackTrace();
		}
	}
	
	public void search() {
		System.out.println("请输入要查询的姓名:");
		String name = sc.next();
		try {
			List<Goddess> goddesses = action.query(name);
			if(goddesses != null) {
				for(Goddess g:goddesses) {
					System.out.println(g);
				}
			}else {
				System.out.println("没有该女神。。。。");
			}
		} catch (Exception e) {
			System.out.println("查询失败!");
			e.printStackTrace();
		}
	}
	// Scanner in = new Scanner(System.in);
	// System.out.println("请输入女神的信息[生日，格式:2014-12-12]：");
	// SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	// Date birthday=null;
	// birthday = sf.parse(in);
	// go.setBirthday(birthday);
	
	// System.out.println("请输入要查询的女神信息，支持姓名、手机号查询，如果两个参数都输入则用逗号分隔[user_name=xx,mobile=xx]：");
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
