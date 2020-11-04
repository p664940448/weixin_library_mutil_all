package com.netmax.library.model.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.netmax.library.model.AppConfig;
import com.netmax.library.model.SContent;

public class SettingService {
   
	//公告，分页( kind=newsxx)
	public static List<SContent> getNotices(int thePage){
		Page<SContent> page=SContent.dao.paginate(thePage, 10, "select * ", "from s_content where kind like 'news%' order by createdate desc ");
		return page.getList();
	}
	
	//公告详情
	public static SContent noticeDetail(BigDecimal id){
		return SContent.dao.findFirst("select * from s_content where id=?",id);
	}
	
	//指南(kind=guid01)
	public static SContent guid(String kind){
		return SContent.dao.findFirst("select * from s_content where kind=?",kind);
	}
	
	//地图信息
	public static HashMap mapInfo(){
		HashMap result=new HashMap();
		AppConfig tmp=AppConfig.dao.findFirst("select * from app_config where thekind='app' and thekey='map_name'");
		if(tmp==null){
			result.put("mapName", "未设置");
		}else{
			result.put("mapName", tmp.getThevalue());
		}
		
		tmp=AppConfig.dao.findFirst("select * from app_config where thekind='app' and thekey='map_lat'");
		if(tmp==null){
			result.put("mapLat", "39.9289250000");
		}else{
			result.put("mapLat", tmp.getThevalue());
		}
		
		tmp=AppConfig.dao.findFirst("select * from app_config where thekind='app' and thekey='map_lon'");
		if(tmp==null){
			result.put("mapLon", "116.3917260000");
		}else{
			result.put("mapLon", tmp.getThevalue());
		}
		
		tmp=AppConfig.dao.findFirst("select * from app_config where thekind='app' and thekey='map_default_city'");
		if(tmp==null){
			result.put("mapDefaultCity", "北京");
		}else{
			result.put("mapDefaultCity", tmp.getThevalue());
		}
		
		return result;
	}
	
	/**
	 * 获取主分馆多个馆信息
	 * app_config表中记录形如:
	 * thekind=app_map
	 * thekey=map_1
	 * thevalue=北京市|39.9289250000|116.3917260000|西校区主馆|地址
	 * 
	 * @return
	 */
	public static List<HashMap>mapInfoMulti(){
		List<HashMap> result=new ArrayList();
		String sql="select * from app_config where thekind='app_map' ";
		List<Record> list=Db.find(sql);
		if(list!=null){
			for(Record rs : list){
				String value=rs.getStr("thevalue");
				if(value!=null){
					String values[]=value.split("\\|");
					System.out.println(values.length);
					if(values.length==5){
						HashMap map=new HashMap();
						map.put("mapDefaultCity", values[0]);
						map.put("mapLat", values[1]);
						map.put("mapLon", values[2]);
						map.put("mapName", values[3]);
						map.put("mapAddress", values[4]);
						map.put("mapId",rs.getStr("thekey"));
						result.add(map);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 主分馆取某个馆地图信息
	 * 
	 * @param mapId
	 * @return
	 */
	public static HashMap getMapInfo(String mapId){
		HashMap map=new HashMap();
		String sql="select * from app_config where thekind='app_map' and thekey=?";
		Record rs=Db.findFirst(sql,mapId);
		if(rs!=null){
			String value=rs.getStr("thevalue");
			if(value!=null){
				String values[]=value.split("\\|");
				if(values.length==5){
					map.put("mapDefaultCity", values[0]);
					map.put("mapLat", values[1]);
					map.put("mapLon", values[2]);
					map.put("mapName", values[3]);
					map.put("mapAddress", values[4]);
					map.put("mapId",rs.getStr("thekey"));
				}
			}
		}
		
		return map;
	}
	
	//服务指南
	public static List<Record> getGuid(){
		String sql="select * from s_kind where kindtype='guid' order by kind";
		return Db.find(sql);
	}
}
