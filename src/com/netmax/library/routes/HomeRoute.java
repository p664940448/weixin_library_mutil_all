package com.netmax.library.routes;


import com.jfinal.config.Routes;
import com.netmax.library.controller.HomeController;
import com.netmax.library.controller.IndexController;
import com.netmax.library.controller.MylibController;
import com.netmax.library.controller.SearchController;
import com.netmax.library.controller.SettingController;
import com.netmax.library.controller.WeixinController;
import com.netmax.library.controller.loginController;


//路由配置
public class HomeRoute extends Routes{
   public void config(){
	   add("/", IndexController.class);
	   add("/home", HomeController.class);
	   add("/search",SearchController.class);
	   add("/setting",SettingController.class);
	   add("/mylib",MylibController.class);
	   add("/login",loginController.class);
	   

       add("/weixin",WeixinController.class);
       
       
   }
}
