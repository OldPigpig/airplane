package com.main;

import com.frame.MyJFrame;
import com.frame.MyJPanel;
import com.thread.GameListener;

public class GameStart {
	public static long start_time;  //��¼��Ϸ��ʼʱ��ϵͳʱ��
	public static long game_time;   //��¼��Ϸʱ��
	public static long flag_time=game_time; //��¼��Ϸʱ�䣨�����߳�ˢ�£���������������������
    //��Ϸ����
	public static void main(String[] args) {
		//���塢����ʵ����
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		
		//����ʵ����
		GameListener gl=new GameListener();
		jf.setKeyListener(gl);
		
		//����ע��
		jf.setJpanel(jp);
		jf.AddJPanels();
		//��������
		jf.AddListener();
		//����
		jf.Start();
	}
}
