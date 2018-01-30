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
	// 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
	  // schedule(TimerTask task, long delay, long period)
	  public static void timer2() {
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	        System.out.println("-------设定要指定任务--------");
	      }
	    }, 1000, 5000);
	  }
    // 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
    // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
    /*public static void timer4() {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时
      calendar.set(Calendar.MINUTE, 0);    // 控制分
      calendar.set(Calendar.SECOND, 0);    // 控制秒
   
      Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00
   
      Timer timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
          System.out.println("-------设定要指定任务--------");
        }
      }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }
*/
	public  void insertSh() throws IOException {
		//抓取上海的天气预告网页
		String url="http://www.weather.com.cn/weather/101020100.shtml";
		//获取com.bawie.util.Spider对象;
		Spider s=new Spider();
		//获取从网页上获取的数据list集合 
		List<Weather> wList=s.getWeather(url);        //涉及李io流异常
		//对数据进行遍历，加到数据库中
		for (Weather weather : wList) {
		        //为weather对象添加一个属性，并重新封装weather
			weather.set("aid", 2);
			//对数据库进行添加
			weather.save();
		}
	}
	
	public void insertBj() throws IOException {
		//瑕佹姄鍙栫殑椤甸潰
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
