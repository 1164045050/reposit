package com.bawie.main;

import com.bawie.controller.WeatherController;
import com.bawie.dao.Weather;
import com.bawie.interceptor.FreemarkerInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class MainConfig extends JFinalConfig {

	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 3);
	}

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true); // 是否为开发模式
		me.setViewType(ViewType.FREE_MARKER); //定义视图类型
		me.setViewExtension(".htm");
		FreeMarkerRender.setProperty("template_update_delay", "0");// 模板更更新时间,0表示每次都加载
		FreeMarkerRender.setProperty("classic_compatible", "true");// 如果为null则转为空值,不需要再用!处理
		FreeMarkerRender.setProperty("whitespace_stripping", "true");// 去除首尾多余空格
		FreeMarkerRender.setProperty("date_format", "yyyy-MM-dd");
		FreeMarkerRender.setProperty("time_format", "HH:mm:ss");
		FreeMarkerRender.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
		FreeMarkerRender.setProperty("default_encoding", "UTF-8");

	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new FreemarkerInterceptor());

	}

	@Override
	public void configPlugin(Plugins me) {
	        //加载属性文件
		loadPropertyFile("dbconfig");
		// 淘宝开发的德鲁伊连接池
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		me.add(dp);
		// 创建数据库映射
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(true);// 显示sql
		me.add(arp);
		arp.addMapping("weatherdata", Weather.class);// 映射用户表

	}
	/**
	 * 路由
	 * Routes 类主要有如下两个方法：
	 * public Routes add(String controllerKey, 
	 *  Class<? extends Controller> controllerClass, 
	 *  String viewPath)
	 * public Routes add(String controllerKey,
	 *  Class<? extends Controller> controllerClass)
	 * 第一个参数 controllerKey 是指访问某个 Controller 所需要的一个字符串，
	 * 该字符串唯一对 应一个 Controller，controllerKey 仅能定位到 Controller。
	 * 第二个参数 controllerClass 是该 controllerKey 所对应到的 Controller。
	 * 第三个参数 viewPath 是指该 Controller 返回的视图的相对 路径
	 * (该参数具体细节将在 Controller 相关章节中给出)。
	 * 当 viewPath 未指定时默认值为 controllerKey。
	 */
	@Override
	public void configRoute(Routes me) {
		// 设置访问路径
		me.add("/weather",WeatherController.class);

	}

}
