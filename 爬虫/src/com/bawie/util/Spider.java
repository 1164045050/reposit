package com.bawie.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bawie.dao.Weather;

public class Spider {
	
	public List<Weather> getWeather(String url) throws IOException {
	        /**
	         * 创建一个天气集合，weather是dao层model接口生成的
	         * 这个时候weather对象为空
	         */
		List<Weather> weaList=new ArrayList<Weather>();
		//从URL直接加载HTML文档
		Document document=Jsoup.connect(url).get();
		// 提供简单的方法供使用者检索数据(li标签下class="sky"的元素)
		Elements element=document.select("li.sky");
		//获取页面标题
//		String title = document.title();
//		System.out.println(title);
		//循环遍历获取的元素集合（li.sky）
		for (Element e : element) {
		        //获取com.bawie.dao.Weather对象
			Weather weather=new Weather();
			//获取h1元素，并解析
			String day=e.select("h1").text();
			String wea=e.select(".wea").text();
			String tem=e.select(".tem").text();
			//获取class=win的元素下span元素的title的属性，并解析
			String nw = e.select(".win").select("span").attr("title");
			//获取class=win的元素下i元素，并解析
			String nf = e.select(".win").select("i").text();
//			System.out.println("风向:"+nw);
//			System.out.println("风力:"+nf);
//			System.out.println("日期:"+day);
//			System.out.println("天气:"+wea);
//			System.out.println("温度:"+tem);
			weather.set("day", day);
			weather.set("wea", wea);
			weather.set("tem", tem);
			weather.set("nw", nw);
			weather.set("nf", nf);
			//通过set方法，被weather继承的接口对weather进行了封装
			weaList.add(weather);
		}
		return weaList;
		
	}

}
