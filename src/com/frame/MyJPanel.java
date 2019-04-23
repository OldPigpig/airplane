package com.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.main.GameStart;
import com.model.manager.ElementManager;
import com.model.vo.Background;
import com.model.vo.Player;
import com.model.vo.SuperElement;

public class MyJPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	
	//������ʵ��������Ϊֻ��Ҫnewһ�������༴��
	Background bg=new Background(0, 0, 400, 600);
	public static int flag=0;      //����������Ϸ״̬��0=��ʾ��ʼǰ��1=��ʾ��Ϸ�У�2=��ʾ��Ϸ����
	public static int time_enemy=0;//�л��ӵ�����
	//��дpaint����
	public void paint(Graphics g) {
		super.paint(g);
		//��Ϸ��ʼ�������
		if(flag==0) {
			Start(g);
		}
		else if(flag==1) {
			//��������
			BackGround(g);
			//���Ի���
			Others(g);
			//��ɫ����
			GameRun(g);
		}
		else if(flag==2) {
			//��Ϸ�����������
			GameOver(g);
		}
	}
	

	//��Ϸ��������
	private void GameOver(Graphics g) {
		//ͼƬ����
		ImageIcon img=new ImageIcon("img/Background/over.jpg");
		g.drawImage(img.getImage(),0,0,400,600,null);
		//��������
		g.setColor(Color.WHITE);
		g.setFont(new Font("����", Font.PLAIN, 30));
		g.drawString(""+Player.num, 200, 246);
		//���ۻ���
		g.setFont(new Font("����", Font.PLAIN, 60));
		g.setColor(Color.red);
		if(Player.num<150) {
			g.drawString("��", 165, 400);
		}
		if(Player.num>=150&&Player.num<500)
			g.drawString("��ǿ��", 115, 400);
		if(Player.num>=500&&Player.num<1000)
			g.drawString("��Ӵ������Ӵ", 20, 400);
		if (Player.num>=1000&&Player.num<2000) {
			g.drawString("��ĺܰ�", 85, 400);
		}
		if(Player.num>=2000&&Player.num<4000) {
			g.drawString("Ĥ�ݴ���", 85, 400);
		}
	}


	private void Start(Graphics g) {
		//ͼƬ����
		ImageIcon img=new ImageIcon("img/Background/start.jpg");
		g.drawImage(img.getImage(),0,0,400,600,null);
		
	}


	private void Others(Graphics g) {
		//������Ϸʱ����Ӱ
		g.setColor(Color.BLACK);
		g.setFont(new Font("����", Font.PLAIN, 20));
		g.drawString("ʱ�䣺"+GameStart.flag_time+"��", 2, 570);
		//������Ϸʱ��
		g.setColor(Color.white);
		g.setFont(new Font("����",Font.PLAIN,20));
		g.drawString("ʱ�䣺"+GameStart.flag_time+"��",0,568);
		//���Ʒ�����Ӱ
		g.setColor(Color.black);
		g.setFont(new Font("����",Font.PLAIN,20));
		g.drawString("������"+Player.num,32,42);
		g.drawString("����ֵ��",182,42);
		//���Ʒ���
		g.setColor(Color.white);
		g.setFont(new Font("����",Font.PLAIN,20));
		g.drawString("������"+Player.num,30,40);
		g.drawString("����ֵ��",180,40);
		//���ƿ���Ѫ������
		g.setColor(new Color(190,195,199));
		g.drawRect(275,22,101,18);
		//����ʵ��Ѫ������
		g.setColor(new Color(234,75,53));
		g.fillRect(276,22,Player.hp*10,17);
		//����Ѫ����ֵ��Ӱ
		g.setColor(Color.black);
		g.setFont(new Font("����",Font.PLAIN,12));
		g.drawString(""+Player.hp*10, 317, 39);
		//����Ѫ����ֵ
		g.setColor(Color.white);
		g.setFont(new Font("����",Font.PLAIN,12));
		g.drawString(""+Player.hp*10, 315, 37);		
		//����Ѫ�����;���
		if(Player.hp*10<=30){
			//��������ֵ������ʾ��Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����",Font.PLAIN,16));
			g.drawString("���棺����ֵ���ͣ�", 197, 64);
			//��������ֵ������ʾ
			g.setColor(Color.red);
			g.setFont(new Font("����",Font.PLAIN,16));
			g.drawString("���棺����ֵ���ͣ�", 195, 62);
		}
	}


	private void BackGround(Graphics g) {	
		bg.ShowElement(g);
	}


	private void GameRun(Graphics g) {
		//��ȡԪ�ع������е�Map��key���������Set��
		Map<String, List<SuperElement>> map=
				ElementManager.getElementManager().getMap();
		Set<String> set=map.keySet();
		
		//������Key��
		for(String key:set) {
			List<SuperElement> list=map.get(key);
			for(int i=0;i<list.size();i++) {
				list.get(i).ShowElement(g);
			}
		}

	}

	//��дrun����
	public void run() {
		while(true) {
			try {
			Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
