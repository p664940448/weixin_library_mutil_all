package com.netmax.library.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.netmax.library.model.BBook;

public class SearchController extends Controller {
	
    public void search(){
       String keyWord=getPara("keyWord");
       String kind=getPara("kind");
       String sid=getPara("sid");
       String thePage=getPara("thePage");
       if(thePage==null || "".equals(thePage)){
    	   thePage="1";
       }else{
    	   //不是数字
    	   try{
    		   Integer.parseInt(thePage);
    	   }catch(Exception e){
    		   thePage="1";
    	   }
       }
       
       if(sid==null){
    	   sid="";
       }
       
       //按书名查询
       if("bookName".equals(kind)){
    	   renderJson(BBook.dao.searchByBookName(keyWord,Integer.parseInt(thePage),sid));
       }
       
       //按作者检索
       if("author".equals(kind)){
    	   renderJson(BBook.dao.searchByAuthor(keyWord,Integer.parseInt(thePage),sid));
       }
       
       //按出版社检索
       if("publisher".equals(kind)){
    	   renderJson(BBook.dao.searchByPublisher(keyWord,Integer.parseInt(thePage),sid));
       }
       
       //isbn检索
       if("isbn".equals(kind)){
    	   renderJson(BBook.dao.searchByIsbn(keyWord,Integer.parseInt(thePage),sid));
       }
    }
    
    
    //分馆列表
    public void getSchs(){
    	String sql="select s_id as sid,s_name as sname from web_school order by s_name";
    	List<Record> rs=Db.find(sql);
    	renderJson(rs);
    }
    
    public void searchNew(){
    	String thePage=getPara("thePage");
        String title=getPara("title");
        String dutyer=getPara("dutyer");
        String sid=getPara("sid");
        String kind=getPara("kind");
        String isbn=getPara("isbn");
        String pub_man=getPara("pub_man");
        String pub_date=getPara("pub_date");
        String pub_zhaiyao=getPara("pub_zhaiyao");
        
        int pageNum = 1;
        if(thePage==null || "".equals(thePage)){
        	pageNum = 1;
        }else{
     	   //不是数字
     	   try{
     		  pageNum = Integer.parseInt(thePage);
     	   }catch(Exception e){
     		  pageNum = 1;
     	   }
        }
        
        if(sid==null){
     	   sid="";
        }
        
        renderJson(BBook.dao.searchNew(pageNum,
        		            title, 
        		            dutyer, 
        		            sid, 
        		            kind,
        		            isbn,
        		            pub_man,
        		            pub_date,
        		            pub_zhaiyao));
        
     }
}
