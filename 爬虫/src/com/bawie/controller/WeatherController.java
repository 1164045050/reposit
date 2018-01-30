package com.bawie.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bawie.dao.Weather;
import com.bawie.util.Spider;
import com.jfinal.core.Controller;

public class WeatherController extends Controller{
	public static void main(String[] args) {
            timer2();
        }
	// �ڶ��ַ������趨ָ������task��ָ���ӳ�delay����й̶��ӳ�peroid��ִ��
	  // schedule(TimerTask task, long delay, long period)
	  public static void timer2() {
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	        System.out.println("-------�趨Ҫָ������--------");
	      }
	    }, 1000, 5000);
	  }
    // �����ַ���������ָ��������task��ָ����ʱ��firstTime��ʼ�����ظ��Ĺ̶�����periodִ�У�
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    /*public static void timer4() {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 12); // ����ʱ
      calendar.set(Calendar.MINUTE, 0);    // ���Ʒ�
      calendar.set(Calendar.SECOND, 0);    // ������
   
      Date time = calendar.getTime();     // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00
   
      Timer timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
          System.out.println("-------�趨Ҫָ������--------");
        }
      }, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
    }
*/
	public  void insertSh() throws IOException {
		//ץȡ�Ϻ�������Ԥ����ҳ
		String url="http://www.weather.com.cn/weather/101020100.shtml";
		//��ȡcom.bawie.util.Spider����;
		Spider s=new Spider();
		//��ȡ����ҳ�ϻ�ȡ������list���� 
		List<Weather> wList=s.getWeather(url);        //�漰��io���쳣
		//�����ݽ��б������ӵ����ݿ���
		for (Weather weather : wList) {
		        //Ϊweather�������һ�����ԣ������·�װweather
			weather.set("aid", 2);
			//�����ݿ�������
			weather.save();
		}
	}
	
	public void insertBj() throws IOException {
		//要抓取的页面
		String url="http://www.weather.com.cn/weather/101010100.shtml";
		Spider s=new Spider();
		List<Weather> wList=s.getWeather(url);
		for (Weather weather : wList) {
			weather.set("aid", 1);
			weather.save();
		}
	}
	
	public void list(){
		
		renderFreeMarker("/weather/list.htm");
	}

}
