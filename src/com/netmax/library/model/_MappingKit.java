package com.netmax.library.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("recmsg", "ID", Recmsg.class);
		// Composite Primary Key order: thekind,thekey
		arp.addMapping("app_user", "mphone", AppUser.class);
                arp.addMapping("app_config", "thekind,thekey", AppConfig.class);
		arp.addMapping("app_mystore", "id", AppMystore.class);
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
		
	}
}

