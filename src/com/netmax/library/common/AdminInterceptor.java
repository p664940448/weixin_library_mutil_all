package com.netmax.library.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AdminInterceptor implements Interceptor {
    /**
     * 是否登录判断,全局拦截器,暂时不用
     */
	@Override
	public void intercept(Invocation inv) {
		String key=inv.getActionKey();
		if(key.indexOf("mylib")==0){  //不是mylib页面，则判断session
			String userCode = inv.getController().getSessionAttr("userCode");
			if(userCode==null || "".equals(userCode)){
				inv.getController().renderHtml("<script>parent.document.location='/library/login'</script>");
			}else{
				inv.invoke();
			}
		}else{ //其它页面直接放行
			inv.invoke();
		}
	}

}
