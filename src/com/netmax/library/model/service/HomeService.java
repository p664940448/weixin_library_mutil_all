package com.netmax.library.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.netmax.library.model.BBook;

public class HomeService {
	//最新图书
		public List getNewBooks(){
			List<BBook> list= BBook.dao.find("select top 10 * from b_book order by r_date desc");
			List result=new ArrayList();
			for(BBook book : list){
				HashMap map=new HashMap();
				map.put("bookId", book.getBNo());
				map.put("bookName", book.getBTitle());
				map.put("author", book.getBDutyer());
				map.put("publisher", book.getPubMan());
				map.put("theDate", book.getPubDate());
				if(book.getBPicture()==null || "".equals(book.getBPicture())){
					map.put("pic", "img/book.png");
				}else{
					map.put("pic", book.getBPicture());
				}
				
				map.put("isbn", book.getBIsbn());
				result.add(map);
			}
			return result;
		}
		
		//推荐图书
		public List tuijianBooks(){
			List<Record> list=Db.find("select top 10 b.* from s_tj s left join b_book b on b.b_no=s.b_no order by s.createdate desc");
			List result=new ArrayList();
			for(Record book : list){
				HashMap map=new HashMap();
				map.put("bookId", book.getBigDecimal("b_no"));
				map.put("bookName", book.getStr("b_title"));
				map.put("author", book.getStr("b_dutyer"));
				map.put("publisher", book.getStr("pub_man"));
				map.put("theDate", book.getStr("pub_date"));
				if(book.getStr("b_picture")==null || "".equals(book.getStr("b_picture"))){
					map.put("pic", "img/book.png");
				}else{
					map.put("pic", book.getStr("b_picture"));
				}
				
				map.put("isbn", book.getStr("b_isbn"));
				result.add(map);
			}
			return result;
		}
		
		//图书详情
		public HashMap getBookDetail(String bookId){
			BBook book= BBook.dao.findById(bookId);
			HashMap map=new HashMap();
			if(book!=null){
				map.put("bookName", book.getBTitle());
				map.put("publisher", book.getPubMan());
				map.put("pubDate", book.getPubDate());
				map.put("author", book.getBDutyer());
				map.put("price", book.getBPrice());
				map.put("note", book.getBNote());
				map.put("bookId", bookId);
				if(book.getBPicture()==null || "".equals(book.getBPicture())){
					map.put("pic", "img/book.png");
				}else{
					map.put("pic", book.getBPicture());
				}
			}
			
			return map;
		}
		
		//按书名查询，分页
		public List<HashMap> searchByBookName(String keyWord,int thePage){
			Page<BBook> page=BBook.dao.paginate(thePage, 10, "select * ", "from b_book where b_title like ?","%"+keyWord+"%");
			List<HashMap> resultList=new ArrayList();
			
			List<BBook> books=page.getList();
			for(BBook book : books){
				HashMap map=new HashMap();
				map.put("bookId", book.getBNo());
				map.put("bookName", book.getBTitle());
				map.put("author", book.getBDutyer());
				map.put("publisher", book.getPubMan());
				map.put("theDate", book.getPubDate());
				if(book.getBPicture()==null || "".equals(book.getBPicture())){
					map.put("pic", "img/book.png");
				}else{
					map.put("pic", book.getBPicture());
				}
				
				map.put("isbn", book.getBIsbn());
				resultList.add(map);
			}
			return resultList;
		}
		
		
		//按作者检索，分页
		public List<HashMap> searchByAuthor(String keyWord,int thePage){
			Page<BBook> page=BBook.dao.paginate(thePage, 10, "select * ", "from b_book where b_dutyer like ? or b_dutyerPy like ?","%"+keyWord+"%","%"+keyWord+"%");
			List<HashMap> resultList=new ArrayList();
			
			List<BBook> books=page.getList();
			for(BBook book : books){
				HashMap map=new HashMap();
				map.put("bookId", book.getBNo());
				map.put("bookName", book.getBTitle());
				map.put("author", book.getBDutyer());
				map.put("publisher", book.getPubMan());
				map.put("theDate", book.getPubDate());
				if(book.getBPicture()==null || "".equals(book.getBPicture())){
					map.put("pic", "img/book.png");
				}else{
					map.put("pic", book.getBPicture());
				}
				
				map.put("isbn", book.getBIsbn());
				resultList.add(map);
			}
			return resultList;
		}
		
		//按出版社检索，分页
			public List<HashMap> searchByPublisher(String keyWord,int thePage){
				Page<BBook> page=BBook.dao.paginate(thePage, 10, "select * ", "from b_book where pub_man like ?","%"+keyWord+"%");
				List<HashMap> resultList=new ArrayList();
				
				List<BBook> books=page.getList();
				for(BBook book : books){
					HashMap map=new HashMap();
					map.put("bookId", book.getBNo());
					map.put("bookName", book.getBTitle());
					map.put("author", book.getBDutyer());
					map.put("publisher", book.getPubMan());
					map.put("theDate", book.getPubDate());
					if(book.getBPicture()==null || "".equals(book.getBPicture())){
						map.put("pic", "img/book.png");
					}else{
						map.put("pic", book.getBPicture());
					}
					
					map.put("isbn", book.getBIsbn());
					resultList.add(map);
				}
				return resultList;
			}
			
			//按出版社检索，分页
			public List<HashMap> searchByIsbn(String keyWord,int thePage){
				Page<BBook> page=BBook.dao.paginate(thePage, 10, "select * ", "from b_book where b_isbn like ?","%"+keyWord+"%");
				List<HashMap> resultList=new ArrayList();
				
				List<BBook> books=page.getList();
				for(BBook book : books){
					HashMap map=new HashMap();
					map.put("bookId", book.getBNo());
					map.put("bookName", book.getBTitle());
					map.put("author", book.getBDutyer());
					map.put("publisher", book.getPubMan());
					map.put("theDate", book.getPubDate());
					if(book.getBPicture()==null || "".equals(book.getBPicture())){
						map.put("pic", "img/book.png");
					}else{
						map.put("pic", book.getBPicture());
					}
					
					map.put("isbn", book.getBIsbn());
					resultList.add(map);
				}
				return resultList;
			}
}
