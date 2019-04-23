package com.frame;

import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.thread.GameThread;

public class MyJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//串行号，可不管
	
	private JPanel jpanel;           //Jpanel实例化
	
	private KeyListener keyListener; //键盘监听实例化
	
	public MyJFrame() {
		init();                      //初始化
	}

	//初始化方法
	private void init() {
		this.setTitle("飞机大战");//标题
		this.setSize(400, 600);//窗体大小
		this.setResizable(false);//窗体大小可否调整
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	//绑定键盘监听
	public void AddListener() {
		if(keyListener!=null) {
			this.addKeyListener(keyListener);
		}
	}
	
	//绑定画板
	public void AddJPanels() {
		if(jpanel!=null)
			this.add(jpanel);
	}
	
	//启动
	public void Start() {
		//线程实例化和启动
		GameThread gt=new GameThread();
		gt.start();
		//线程刷新启动
		if(jpanel instanceof Runnable) {
			new Thread((Runnable)jpanel).start();
		}
		//可视化
		this.setVisible(true);
	}

	//右键，源码，生成getter和setter可快速实现
	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}	
}
