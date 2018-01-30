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
	         * ����һ���������ϣ�weather��dao��model�ӿ����ɵ�
	         * ���ʱ��weather����Ϊ��
	         */
		List<Weather> weaList=new ArrayList<Weather>();
		//��URLֱ�Ӽ���HTML�ĵ�
		Document document=Jsoup.connect(url).get();
		// �ṩ�򵥵ķ�����ʹ���߼�������(li��ǩ��class="sky"��Ԫ��)
		Elements element=document.select("li.sky");
		//��ȡҳ�����
//		String title = document.title();
//		System.out.println(title);
		//ѭ��������ȡ��Ԫ�ؼ��ϣ�li.sky��
		for (Element e : element) {
		        //��ȡcom.bawie.dao.Weather����
			Weather weather=new Weather();
			//��ȡh1Ԫ�أ�������
			String day=e.select("h1").text();
			String wea=e.select(".wea").text();
			String tem=e.select(".tem").text();
			//��ȡclass=win��Ԫ����spanԪ�ص�title�����ԣ�������
			String nw = e.select(".win").select("span").attr("title");
			//��ȡclass=win��Ԫ����iԪ�أ�������
			String nf = e.select(".win").select("i").text();
//			System.out.println("����:"+nw);
//			System.out.println("����:"+nf);
//			System.out.println("����:"+day);
//			System.out.println("����:"+wea);
//			System.out.println("�¶�:"+tem);
			weather.set("day", day);
			weather.set("wea", wea);
			weather.set("tem", tem);
			weather.set("nw", nw);
			weather.set("nf", nf);
			//ͨ��set��������weather�̳еĽӿڶ�weather�����˷�װ
			weaList.add(weather);
		}
		return weaList;
		
	}

}
