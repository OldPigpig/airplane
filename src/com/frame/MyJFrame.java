package com.frame;

import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.thread.GameThread;

public class MyJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//���кţ��ɲ���
	
	private JPanel jpanel;           //Jpanelʵ����
	
	private KeyListener keyListener; //���̼���ʵ����
	
	public MyJFrame() {
		init();                      //��ʼ��
	}

	//��ʼ������
	private void init() {
		this.setTitle("�ɻ���ս");//����
		this.setSize(400, 600);//�����С
		this.setResizable(false);//�����С�ɷ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	//�󶨼��̼���
	public void AddListener() {
		if(keyListener!=null) {
			this.addKeyListener(keyListener);
		}
	}
	
	//�󶨻���
	public void AddJPanels() {
		if(jpanel!=null)
			this.add(jpanel);
	}
	
	//����
	public void Start() {
		//�߳�ʵ����������
		GameThread gt=new GameThread();
		gt.start();
		//�߳�ˢ������
		if(jpanel instanceof Runnable) {
			new Thread((Runnable)jpanel).start();
		}
		//���ӻ�
		this.setVisible(true);
	}

	//�Ҽ���Դ�룬����getter��setter�ɿ���ʵ��
	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}	
}
