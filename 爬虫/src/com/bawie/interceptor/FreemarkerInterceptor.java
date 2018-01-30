package com.bawie.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
/**
 * ������
 * �״�δ���
 * @author Asus
 *
 */
public class FreemarkerInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		String path=inv.getController().getRequest().getContextPath();
		String basePath=inv.getController().getRequest().getScheme()+"://"+inv.getController().getRequest().getServerName()+":"+inv.getController().getRequest().getServerPort()+path+"/";
		inv.getController().setAttr("path", path);
		inv.getController().setAttr("basePath", basePath);
		inv.invoke();
	}

}
