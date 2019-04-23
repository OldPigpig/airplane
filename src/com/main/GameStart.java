package com.main;

import com.frame.MyJFrame;
import com.frame.MyJPanel;
import com.thread.GameListener;

public class GameStart {
	public static long start_time;  //记录游戏开始时的系统时间
	public static long game_time;   //记录游戏时间
	public static long flag_time=game_time; //记录游戏时间（由于线程刷新，所以用了两个变量来）
    //游戏启动
	public static void main(String[] args) {
		//窗体、画板实例化
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		
		//监听实例化
		GameListener gl=new GameListener();
		jf.setKeyListener(gl);
		
		//画板注入
		jf.setJpanel(jp);
		jf.AddJPanels();
		//监听加载
		jf.AddListener();
		//启动
		jf.Start();
	}
}
