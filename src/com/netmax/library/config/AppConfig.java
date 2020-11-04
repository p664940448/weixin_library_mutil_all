package com.netmax.library.config;


import com.netmax.library.common.AdminInterceptor;
import com.netmax.library.common.BaseHandle;
import com.netmax.library.model._MappingKit;
import com.netmax.library.routes.HomeRoute;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;

/**
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", true));
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
	    me.add(new HomeRoute());
	}
	
	public static C3p0Plugin createC3p0Plugin() {
		//return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim(), PropKit.get("jdbc.driverClassName").trim());
		System.out.println(PropKit.get("jdbcUrl"));
		System.out.println(PropKit.get("user").trim());
		System.out.println(PropKit.get("password").trim());
		
		return new C3p0Plugin(PropKit.get("jdbcUrl").trim(), PropKit.get("user").trim(), PropKit.get("password").trim(), "com.microsoft.sqlserver.jdbc.SQLServerDriver");
	}
	
	public static DruidPlugin createDruidPlugin(){
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl").trim(), PropKit.get("user").trim(), PropKit.get("password").trim());
		return druidPlugin;
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		/**
		C3p0Plugin C3p0Plugin = createC3p0Plugin();		
		me.add(C3p0Plugin);
		**/
		
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);		
		me.add(arp);
		arp.setDialect(new AnsiSqlDialect());
		
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
		
		//配置缓存
		me.add(new EhCachePlugin());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
        //登录判断，暂时不用
		//AdminInterceptor adminIpt=new AdminInterceptor();
       // me.add(adminIpt);
		me.add(new SessionInViewInterceptor());//session拦截器，用于在View模板中取出session值 
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		//跨域访问
		BaseHandle baseHandle=new BaseHandle();
		me.add(baseHandle);
		
	}
	

}