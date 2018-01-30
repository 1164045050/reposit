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
		me.setDevMode(true); // �Ƿ�Ϊ����ģʽ
		me.setViewType(ViewType.FREE_MARKER); //������ͼ����
		me.setViewExtension(".htm");
		FreeMarkerRender.setProperty("template_update_delay", "0");// ģ�������ʱ��,0��ʾÿ�ζ�����
		FreeMarkerRender.setProperty("classic_compatible", "true");// ���Ϊnull��תΪ��ֵ,����Ҫ����!����
		FreeMarkerRender.setProperty("whitespace_stripping", "true");// ȥ����β����ո�
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
	        //���������ļ�
		loadPropertyFile("dbconfig");
		// �Ա������ĵ�³�����ӳ�
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		me.add(dp);
		// �������ݿ�ӳ��
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(true);// ��ʾsql
		me.add(arp);
		arp.addMapping("weatherdata", Weather.class);// ӳ���û���

	}
	/**
	 * ·��
	 * Routes ����Ҫ����������������
	 * public Routes add(String controllerKey, 
	 *  Class<? extends Controller> controllerClass, 
	 *  String viewPath)
	 * public Routes add(String controllerKey,
	 *  Class<? extends Controller> controllerClass)
	 * ��һ������ controllerKey ��ָ����ĳ�� Controller ����Ҫ��һ���ַ�����
	 * ���ַ���Ψһ�� Ӧһ�� Controller��controllerKey ���ܶ�λ�� Controller��
	 * �ڶ������� controllerClass �Ǹ� controllerKey ����Ӧ���� Controller��
	 * ���������� viewPath ��ָ�� Controller ���ص���ͼ����� ·��
	 * (�ò�������ϸ�ڽ��� Controller ����½��и���)��
	 * �� viewPath δָ��ʱĬ��ֵΪ controllerKey��
	 */
	@Override
	public void configRoute(Routes me) {
		// ���÷���·��
		me.add("/weather",WeatherController.class);

	}

}
