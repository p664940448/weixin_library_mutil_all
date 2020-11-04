package com.netmax.library.controller;

import java.util.HashMap;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.netmax.library.model.AppConfig;
import com.netmax.library.model.BAuth;
import com.netmax.library.model.BBmarcgs;
import com.netmax.library.model.BBook;
import com.netmax.library.model.BBookBz;
import com.netmax.library.model.BBookCg;
import com.netmax.library.model.BBookFrame;
import com.netmax.library.model.BBookkind;
import com.netmax.library.model.BBookpic;
import com.netmax.library.model.BCf;
import com.netmax.library.model.BCheck;
import com.netmax.library.model.BCsh;
import com.netmax.library.model.BEBook;
import com.netmax.library.model.BEStatus;
import com.netmax.library.model.BEditer;
import com.netmax.library.model.BIssue;
import com.netmax.library.model.BLend;
import com.netmax.library.model.BLendLog;
import com.netmax.library.model.BLendpara;
import com.netmax.library.model.BMaintain;
import com.netmax.library.model.BNews;
import com.netmax.library.model.BPrelend;
import com.netmax.library.model.BPublisher;
import com.netmax.library.model.BQk;
import com.netmax.library.model.BQkBz;
import com.netmax.library.model.BQkCf;
import com.netmax.library.model.BQkDetail;
import com.netmax.library.model.BQkGhs;
import com.netmax.library.model.BQkHd;
import com.netmax.library.model.BQkHdDetail;
import com.netmax.library.model.BQkLend;
import com.netmax.library.model.BQkStatus;
import com.netmax.library.model.BQkType;
import com.netmax.library.model.BQkZch;
import com.netmax.library.model.BQkZchOrder;
import com.netmax.library.model.BQkZd;
import com.netmax.library.model.BQkZdDetail;
import com.netmax.library.model.BReadDd;
import com.netmax.library.model.BReadLog;
import com.netmax.library.model.BReadSf;
import com.netmax.library.model.BReadaddr;
import com.netmax.library.model.BReadbm;
import com.netmax.library.model.BReadcash;
import com.netmax.library.model.BReader;
import com.netmax.library.model.BReaderpic;
import com.netmax.library.model.BReadhols;
import com.netmax.library.model.BReadtype;
import com.netmax.library.model.BRecord;
import com.netmax.library.model.BReserve;
import com.netmax.library.model.BSbgsbz;
import com.netmax.library.model.BStatus;
import com.netmax.library.model.BStatusBz;
import com.netmax.library.model.BStatusCg;
import com.netmax.library.model.BSysset;
import com.netmax.library.model.BXz;
import com.netmax.library.model.BZch;
import com.netmax.library.model.BZchOrder;
import com.netmax.library.model.S39map;
import com.netmax.library.model.SCharge;
import com.netmax.library.model.SClient;
import com.netmax.library.model.SContent;
import com.netmax.library.model.SDomain;
import com.netmax.library.model.SEmail;
import com.netmax.library.model.SEmailLog;
import com.netmax.library.model.SIpbind;
import com.netmax.library.model.SKind;
import com.netmax.library.model.SLevel;
import com.netmax.library.model.SLog;
import com.netmax.library.model.SMenu;
import com.netmax.library.model.SReport;
import com.netmax.library.model.SRoom;
import com.netmax.library.model.SSeq;
import com.netmax.library.model.SSysuser;
import com.netmax.library.model.STj;
import com.netmax.library.model.SUnit;
import com.netmax.library.model.SUser;
import com.netmax.library.model.SVer;
import com.netmax.library.model.SZtc;
import com.netmax.library.model.SsLendaz;
import com.netmax.library.model.SsLendreturn;
import com.netmax.library.model.SsReturnaz;
import com.netmax.library.model.SsSch;
import com.netmax.library.model.SsServer;
import com.netmax.library.model.SsStoreaz;
import com.netmax.library.model.SsStorestatus;
import com.netmax.library.model.service.BookService;

public class HomeController extends Controller{

	//最新图书
	public void newBooks(){
		renderJson(JsonKit.toJson(BookService.getNewBooks()));
	}
   
	//推荐图书
	public void tuijianBooks(){
		renderJson(JsonKit.toJson(BookService.tuijianBooks()));
	}
	
	//图书详情
	public void bookInfo(){
		String bookId=getPara("bookId");
		renderJson(BookService.getBookDetail(bookId));
	}
	
	//显示图片
	public void getBookPic(){
		String bookId=getPara("bookId");
		byte[] pic=BookService.getBookPic(bookId);
		setAttr("pic",pic);
		renderJsp("/index/pic.jsp");
	}
	
	//热门图书
	public void hotBooks(){
		renderJson(JsonKit.toJson(BookService.hotBooks()));
	}
	
	
	/**
	 * 所有表映射的配置
	 * 
	 * 在此做备份
	 */
	
	/*
     arp.addMapping("app_config", "thekind,thekey", AppConfig.class);
		arp.addMapping("b_auth", "auth_id", BAuth.class);
		arp.addMapping("b_bmarcgs", "b_zdbs", BBmarcgs.class);
		arp.addMapping("b_book", "b_no", BBook.class);
		arp.addMapping("b_book_bz", "b_no", BBookBz.class);
		arp.addMapping("b_book_cg", "b_no", BBookCg.class);
		arp.addMapping("b_book_frame", "b_no", BBookFrame.class);
		arp.addMapping("b_bookkind", "kind_id", BBookkind.class);
		arp.addMapping("b_bookPic", "b_no", BBookpic.class);
		arp.addMapping("b_cf", "cf_no", BCf.class);
		arp.addMapping("b_check", "check_id", BCheck.class);
		arp.addMapping("b_csh", "b_no", BCsh.class);
		arp.addMapping("b_e_book", "b_no", BEBook.class);
		arp.addMapping("b_e_status", "b_barid", BEStatus.class);
		arp.addMapping("b_editer", "edit_id", BEditer.class);
		arp.addMapping("b_issue", "pub_id", BIssue.class);
		arp.addMapping("b_lend", "lend_no", BLend.class);
		// Composite Primary Key order: start_no,end_no
		arp.addMapping("b_lend_log", "start_no,end_no", BLendLog.class);
		// Composite Primary Key order: read_kind,book_kind
		arp.addMapping("b_lendpara", "read_kind,book_kind", BLendpara.class);
		arp.addMapping("b_maintain", "m_id", BMaintain.class);
		arp.addMapping("b_news", "b_bh", BNews.class);
		arp.addMapping("b_prelend", "b_id", BPrelend.class);
		arp.addMapping("b_publisher", "pub_isbn", BPublisher.class);
		arp.addMapping("b_qk", "b_no", BQk.class);
		arp.addMapping("b_qk_bz", "b_no", BQkBz.class);
		arp.addMapping("b_qk_cf", "cf_no", BQkCf.class);
		arp.addMapping("b_qk_detail", "b_d_no", BQkDetail.class);
		arp.addMapping("b_qk_ghs", "code", BQkGhs.class);
		arp.addMapping("b_qk_hd", "b_no", BQkHd.class);
		// Composite Primary Key order: b_hd_no,b_d_no,b_barid
		arp.addMapping("b_qk_hd_detail", "b_hd_no,b_d_no,b_barid", BQkHdDetail.class);
		arp.addMapping("b_qk_lend", "lend_no", BQkLend.class);
		arp.addMapping("b_qk_status", "b_barid", BQkStatus.class);
		arp.addMapping("b_qk_type", "type_id", BQkType.class);
		arp.addMapping("b_qk_zch", "z_zch", BQkZch.class);

		arp.addMapping("b_qk_zd", "z_no", BQkZd.class);
		arp.addMapping("b_qk_zd_detail", "z_id", BQkZdDetail.class);
		arp.addMapping("b_read_dd", "dd_id", BReadDd.class);
		arp.addMapping("b_read_log", "log_id", BReadLog.class);
		arp.addMapping("b_read_sf", "cost_id", BReadSf.class);
		// Composite Primary Key order: read_kind,read_addr
		arp.addMapping("b_readaddr", "read_kind,read_addr", BReadaddr.class);
		arp.addMapping("b_readbm", "bm_id", BReadbm.class);
		arp.addMapping("b_readcash", "id", BReadcash.class);
		arp.addMapping("b_reader", "read_card", BReader.class);
		arp.addMapping("b_readerpic", "read_card", BReaderpic.class);
		arp.addMapping("b_readhols", "id", BReadhols.class);
		arp.addMapping("b_readtype", "code", BReadtype.class);
		arp.addMapping("b_record", "record_id", BRecord.class);
		arp.addMapping("b_reserve", "reserve_no", BReserve.class);
		arp.addMapping("b_sbgsbz", "b_no", BSbgsbz.class);
		arp.addMapping("b_status", "b_barid", BStatus.class);

		arp.addMapping("b_status_cg", "b_barid", BStatusCg.class);
		arp.addMapping("b_sysset", "syssetid", BSysset.class);
		arp.addMapping("b_xz", "b_no", BXz.class);
		arp.addMapping("b_zch", "z_zch", BZch.class);


		arp.addMapping("b_status_cg", "b_barid", BStatusCg.class);

		arp.addMapping("s_charge", "charge_id", SCharge.class);
		arp.addMapping("s_client", "client_id", SClient.class);
		arp.addMapping("s_content", "id", SContent.class);
		// Composite Primary Key order: table_name,field_name,domain
		arp.addMapping("s_domain", "table_name,field_name,domain", SDomain.class);
		arp.addMapping("s_email", "EMail_Addr", SEmail.class);
		arp.addMapping("s_email_log", "ID", SEmailLog.class);
		arp.addMapping("s_ipbind", "ID", SIpbind.class);
		arp.addMapping("s_kind", "id", SKind.class);
		arp.addMapping("s_level", "menu_id", SLevel.class);
		arp.addMapping("s_log", "ID", SLog.class);
		arp.addMapping("s_menu", "menu_id", SMenu.class);

		arp.addMapping("s_room", "room_id", SRoom.class);
		arp.addMapping("s_seq", "SEQ_NAME", SSeq.class);
		// Composite Primary Key order: user_id,unit_id
		arp.addMapping("s_sysuser", "user_id,unit_id", SSysuser.class);
		arp.addMapping("s_tj", "id", STj.class);
		arp.addMapping("s_unit", "unit_id", SUnit.class);
		arp.addMapping("s_user", "user_id", SUser.class);

		// Composite Primary Key order: ID,NAME
		arp.addMapping("s_ztc", "ID,NAME", SZtc.class); 
	 */
}
