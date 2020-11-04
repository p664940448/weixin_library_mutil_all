package com.netmax.library.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.netmax.library.model.AppUser;
import com.netmax.library.model.BReader;

public class MylibService {
    
	//获取个人信息
	public static HashMap userInfo(String userCode){
		HashMap result=new HashMap();
		BReader reader= BReader.dao.findFirst("select * from b_reader where read_card=?",userCode);
		if(reader!=null){
			result.put("userCode", reader.getReadCard());
			result.put("userName", reader.getReadName());
			result.put("email", reader.getReadEmail());
			result.put("idCard", reader.getReadId());
			result.put("mphone", reader.getReadMobile());
			
		}		
		
		//所在馆
		String sql_sid="select s_name from web_school where s_id=?";
		Record rs_sid=Db.findFirst(sql_sid,reader.getSId());
		if(rs_sid!=null){
			result.put("sname", rs_sid.getStr("s_name"));
		}else{
			result.put("sname", "无");
		}
		
		//全部借书
		String sql="select count(*) as shu from b_lend where read_card=? and back_date is null";
		Record record=Db.findFirst(sql,userCode);
		result.put("lendCount", record.getInt("shu"));
		
		//逾期图书
		sql="select count(*) as shu from b_lend where (back_date IS NULL) and must_date<getdate() and read_card=?";
		record=Db.findFirst(sql,userCode);
		result.put("lendExCount", record.getInt("shu"));
		
		//历史借阅
		sql="select count(*) as shu from b_lend where read_card=?";
		record=Db.findFirst(sql,userCode);
		result.put("lendHisCount", record.getInt("shu"));
		
		//收藏
		sql="select count(*) as shu from app_myStore where reader_code=?";
		int shu=Db.queryInt(sql,userCode);
		result.put("myStoreCount", shu);
		
		//预借图书
		sql="select COUNT(*) as shu from b_prelend a,b_book b,b_status c where a.tag =1 and a.b_bar = c.b_barid and c.b_no = b.b_no and a.lend_card=? and state='预借'";
		int shu1=Db.queryInt(sql,userCode);
		result.put("preLendCount", shu1);
		
		//委托预约
		String tasksql=" select count(*) as shu "
                      +" from b_prelend a "  
				      +" left join b_book b on  a.b_no = b.b_no "   
                      +"where a.tag =1 and  a.lend_card=? and a.state='预约' ";
		int shu2=Db.queryInt(tasksql,userCode);
		result.put("yueLendCount", shu2);
				
		return result;
	}
	
	//返回逾期图书数量
	public static int getLendExCount(String userCode){
		String sql="select count(*) as shu from b_lend where (back_date IS NULL) and must_date<getdate() and read_card=?";
		Record record=Db.findFirst(sql,userCode);
		return  record.getInt("shu");
	}
	
	
	//我的二维码
	public static String myQR(String userCode){
		return userCode;
	}
	
	//修改密码
	@Before(Tx.class)
	public static String changePwd(String userCode,String password){
		String sql="update b_reader set read_pw=? where read_card=?";
		Db.update(sql,password,userCode);
		return "ok";
	}
	
	//借阅列表
	public static List<HashMap> lendList(String userCode){
		String tasksql=         "SELECT a.b_name,a.b_bar,c.b_barid,b.b_no, b.b_frame, b.b_title,b.b_volumn, b.b_price, a.lend_date, a.must_date,a.lend_no AS lend_no, a.b_relend,a.lend_no,b.b_picture "
				              + " FROM b_lend a LEFT OUTER JOIN b_status c ON a.b_bar = c.b_barid "
				              + " LEFT OUTER JOIN b_book b ON c.b_no = b.b_no WHERE (a.back_date IS NULL) and a.read_card=?";
		List<Record> records=Db.find(tasksql,userCode);
		
		List result=new ArrayList();
		for(Record book : records){
			HashMap map=new HashMap();
			map.put("bookId", book.getBigDecimal("b_no"));
			map.put("bookName", book.getStr("b_name"));
			map.put("author", book.getStr("b_dutyer"));
			map.put("publisher", book.getStr("pub_man"));
			map.put("lend_no",book.getBigDecimal("lend_no"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("mustDate", formatter.format(book.getDate("must_date")));
			map.put("lendDate", formatter.format(book.getDate("lend_date")));
			
			map.put("barid", book.getStr("b_bar"));
			if(book.getStr("b_picture")==null || "".equals(book.getStr("b_picture"))){
				map.put("pic", "img/book.png");
			}else{
				map.put("pic", book.getStr("b_picture"));
			}
			
			java.util.Date today=new java.util.Date();
			if(book.getDate("must_date").getTime()>today.getTime()){
				if("R".equals(book.getStr("b_relend"))){
					map.put("state", "已续借|未到期");
				}else{
					map.put("state", "未到期");
				}
				
			}else{
				if("R".equals(book.getStr("b_relend"))){
					map.put("state", "已续借|逾期");
				}else{
					map.put("state", "逾期");
				}
			}
			map.put("relend", book.getStr("b_relend"));
			map.put("isbn", book.getStr("b_isbn"));
			result.add(map);
		}
		return result;
	}
	
	//借阅列表
	public static List<HashMap> lendOverList(String userCode){
		String tasksql=         "SELECT a.b_name,a.b_bar,c.b_barid,b.b_no, b.b_frame, b.b_title,b.b_volumn, b.b_price, a.lend_date, a.must_date,a.lend_no AS lend_no, a.b_relend,a.lend_no,b.b_picture "
				              + " FROM b_lend a LEFT OUTER JOIN b_status c ON a.b_bar = c.b_barid "
				              + " LEFT OUTER JOIN b_book b ON c.b_no = b.b_no WHERE (a.back_date IS NULL) and a.read_card=? and a.must_date<getdate()";
		System.out.print(tasksql);
		List<Record> records=Db.find(tasksql,userCode);
		
		List result=new ArrayList();
		for(Record book : records){
			HashMap map=new HashMap();
			map.put("bookId", book.getBigDecimal("b_no"));
			map.put("bookName", book.getStr("b_name"));
			map.put("author", book.getStr("b_dutyer"));
			map.put("publisher", book.getStr("pub_man"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("mustDate", formatter.format(book.getDate("must_date")));
			map.put("lendDate", formatter.format(book.getDate("lend_date")));
			
			map.put("barid", book.getStr("b_bar"));
			if(book.getStr("b_picture")==null || "".equals(book.getStr("b_picture"))){
				map.put("pic", "img/book.png");
			}else{
				map.put("pic", book.getStr("b_picture"));
			}
			
			
			java.util.Date today=new java.util.Date();			
			java.util.Date mustDate=book.getDate("must_date");
			
			try{
				today=formatter.parse(formatter.format(today));
				mustDate=formatter.parse(formatter.format(mustDate));
			}catch(Exception e){
				
			}
			
			long n=(today.getTime() - mustDate.getTime() )/(3600*24*1000);
			map.put("days", n);
			
			map.put("isbn", book.getStr("b_isbn"));
			result.add(map);
		}
		return result;
	}
    
	
	//预借列表
	public static List<HashMap> prelendList(String userCode){
		String sql="select a.eff_date, b.b_volumn,b.b_no ,c.b_barid,a.b_id,b.b_picture,"
				              + "b.b_frame,b.b_title,b.b_price,a.prelend_date "
				              + "from b_prelend a,b_book b,b_status c where a.tag =1 and a.b_bar = c.b_barid and c.b_no = b.b_no and a.lend_card=? and state='预借'";
		//System.out.print(sql);
		List<Record> list=Db.find(sql,userCode);
		List result=new ArrayList();
		for(Record book : list){
			HashMap map=new HashMap();
			map.put("bookId", book.getBigDecimal("b_no"));
			map.put("bookName", book.getStr("b_title"));
			map.put("barid", book.getStr("b_barid"));
			map.put("bid", book.getInt("b_id"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("mustDate", formatter.format(book.getDate("eff_date")));
			map.put("lendDate", formatter.format(book.getDate("prelend_date")));
			

			if(book.getStr("b_picture")==null || "".equals(book.getStr("b_picture"))){
				map.put("pic", "img/book.png");
			}else{
				map.put("pic", book.getStr("b_picture"));
			}
			
			
			java.util.Date today=new java.util.Date();			
			java.util.Date mustDate=book.getDate("eff_date");
			
			System.out.println(mustDate);
			try{
				today=formatter.parse(formatter.format(today));
				mustDate=formatter.parse(formatter.format(mustDate));
			}catch(Exception e){
				
			}
			System.out.println(mustDate);
			long n=(today.getTime() - mustDate.getTime() )/(3600*24*1000);
			if(n>0){
				map.put("state", "已失效");
			}else{
				map.put("state", "有效");
			}

			result.add(map);
		}
		return result;
	}
	
	//委托预约列表
	public static List<HashMap> yuelendList(String userCode){
		String sql="select a.eff_date, b.b_volumn,b.b_no ,a.b_id,b.b_frame,b.b_title,b.b_price, a.prelend_date,b.b_picture  "
	                   +" from b_prelend a "
                       +" left join b_book b on  a.b_no = b.b_no "
                       +"where a.tag =1 and  a.lend_card=? and a.state='预约' ";
		
		System.out.print(sql);
		List<Record> list=Db.find(sql,userCode);
		List result=new ArrayList();
		for(Record book : list){
			HashMap map=new HashMap();
			map.put("bookId", book.getBigDecimal("b_no"));
			map.put("bookName", book.getStr("b_title"));
			map.put("bid", book.getInt("b_id"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("mustDate", formatter.format(book.getDate("eff_date")));
			map.put("lendDate", formatter.format(book.getDate("prelend_date")));
			

			if(book.getStr("b_picture")==null || "".equals(book.getStr("b_picture"))){
				map.put("pic", "img/book.png");
			}else{
				map.put("pic", book.getStr("b_picture"));
			}
			
			
			java.util.Date today=new java.util.Date();			
			java.util.Date mustDate=book.getDate("eff_date");
			
			try{
				today=formatter.parse(formatter.format(today));
				mustDate=formatter.parse(formatter.format(mustDate));
			}catch(Exception e){
				
			}

			long n=(today.getTime() - mustDate.getTime() )/(3600*24*1000);
			if(n>0){
				map.put("state", "已失效");
			}else{
				map.put("state", "有效");
			}

			result.add(map);
		}
		return result;
	}
	
	//历史借阅，分页
	public static List<HashMap> lendHisList(String userCode,String thePage){
		
		int pageNum=Integer.parseInt(thePage);
		
		String sqlSelect="select a.b_name,a.b_bar,c.b_barid,b.b_no, a.back_date,b.b_frame, b.b_title,b.b_volumn,b.b_picture,b.pub_man,b.b_dutyer, "
				   + "   b.b_price, a.lend_date, a.must_date,a.lend_no AS lend_no, a.b_relend ";
		
		String sqlFrom=" from b_lend a LEFT OUTER JOIN b_status c ON a.b_bar = c.b_barid "
				      + "LEFT OUTER JOIN b_book b ON c.b_no = b.b_no"
				     + " where a.read_card=? order by a.lend_date desc ";
		Page page=Db.paginate(pageNum, 10, sqlSelect, sqlFrom,userCode);		
		List<Record> list=page.getList();
		List result=new ArrayList();
		for(Record book : list){
			HashMap map=new HashMap();
			map.put("bookId", book.getBigDecimal("b_no"));
			map.put("bookName", book.getStr("b_name"));
			map.put("author", book.getStr("b_dutyer"));
			map.put("publisher", book.getStr("pub_man"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("mustDate", formatter.format(book.getDate("must_date")));
			map.put("lendDate", formatter.format(book.getDate("lend_date")));
			
			map.put("barid", book.getStr("b_bar"));
			if(book.getStr("b_picture")==null || "".equals(book.getStr("b_picture"))){
				map.put("pic", "img/book.png");
			}else{
				map.put("pic", book.getStr("b_picture"));
			}
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 注册用户
	 * @param mphone
	 * @param username
	 * @param idcard
	 * @param email
	 * @param address
	 * @param com
	 */
	public static String regUser(String mphone,String userName,
			                   String idcard,String email, String address,String com,String password){
		AppUser appUser=new AppUser();
		appUser.setMphone(mphone);
		appUser.setUserName(userName);
		appUser.setIdcard(idcard);
		appUser.setEmail(email);
		appUser.setAddress(address);
		appUser.setCom(com);
		appUser.setPassword(password);
		
		/*
		String sql="select count(*) as shu from app_user where mphone=?";
		Integer shu=Db.queryInt(sql,mphone);
		if(shu>0){
			return "手机号码："+mphone+"已经注册，请直接登录";
		}

        sql="select count(*) as shu from app_user where idcard=?";
		int shu1=Db.queryInt(sql,idcard);
		if(shu1>0){
			return "身份证号："+idcard+"已经注册！";
		}
		*/
		String checkEmail="F";
		String checkPhone="F";
		String checkId="F";
		
		String sql="select thevalue from app_config where thekind='app' and thekey='checkEmail' and flag='T' ";
		Record rs=Db.findFirst(sql);
		if(rs!=null && rs.getStr("thevalue").equals("T")){
			checkEmail="T";
		}
		
		String sql1="select thevalue from app_config where thekind='app' and thekey='checkPhone' and flag='T' ";
		Record rs1=Db.findFirst(sql1);
		if(rs1!=null && rs1.getStr("thevalue").equals("T")){
			checkPhone="T";
		}
		
		String sql2="select thevalue from app_config where thekind='app' and thekey='checkId' and flag='T' ";
		Record rs2=Db.findFirst(sql2);
		if(rs2!=null && rs2.getStr("thevalue").equals("T")){
			checkId="T";
		}
		
		
		if(checkEmail.equals("T")){
			sql="select count(*) as shu from b_reader where read_email=?";
			int shu= Db.queryInt(sql,email);
			if(shu>0){
				return "邮箱已经被注册，请换用其它邮箱或者直接登录";
			}
		}
		
		if(checkPhone.equals("T")){
			sql="select count(*) as shu from b_reader where read_mobile=?";
			int shu= Db.queryInt(sql,mphone);
			if(shu>0){
				return "手机号已经被注册，请换用其它手机号或者直接登录";
			}
		}
		
		if(checkId.equals("T")){
			sql="select count(*) as shu from b_reader where read_id=?";
			int shu= Db.queryInt(sql,idcard);
			if(shu>0){
				return "身份证号已经被注册，请换用其它身份证号或者直接登录";
			}
		}
		
		boolean flag=appUser.save();
		return "";
	}
	
	
}
