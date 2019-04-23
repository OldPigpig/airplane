package com.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.model.manager.ElementManager;

public class Player extends SuperElement {

	public static int hp;//Ѫ��
	public static int num;//����
	private ImageIcon img;//Ƥ��
	private boolean top,down,right,left;//��������
	private boolean attack;//����״̬
	private int speed=8;//�ƶ��ٶ�
	//����
	public Player(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
		Player.hp=10;
		Player.num=0;
		this.top=false;
		this.down=false;
		this.right=false;
		this.left=false;
		this.attack=false;
	}
	
	//����һ��������ɫ����
	public static Player CreatePlayer(String str) {
		int x=200;
		int y=300;
		int w=50;
		int h=60;
		String url="img/play/11.PNG";
		return new Player(x, y, w, h, new ImageIcon(url));
	}
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
	}
	
	public void Action() {
		super.Action();
		Fire();
	}
	
	//����
	public void Fire() {
		if(!attack) {//�ǹ���״̬
			return;
		}
		List<SuperElement> list=
				ElementManager.getElementManager().getElementList("fire");
		if(list==null) {
			list=new ArrayList<>();
		}
		ElementManager.getElementManager().getMap().put("fire",list);
		list.add(Player_Fire.CreateFire(getX()+1, getY(), ""));
		list.add(Player_Fire.CreateFire(getX()+getW()-6, getY(), ""));
		this.attack=false;
	}

	@Override
	public void Move() {
		if(top) {
			if(getY()>0)
				setY(getY()-speed);
//			System.out.println(getY());//������
		}
		if(down) {
			if(getY()<515)
				setY(getY()+speed);
//			System.out.println(getY());
		}
		if(right) {
			if(getX()<(400-getW()))
				setX(getX()+speed);
//			System.out.println(getX());
		}
		if(left) {
			if(getX()>0)
				setX(getX()-speed);
//			System.out.println(getX());
		}
	}
	//��ը
	public void getBoom() {
		List<SuperElement> list=ElementManager.
				getElementManager().getElementList("boom");
		list.add(Boom.CreateBoom(getX(), getY(), ""));
	}
	
	@Override
	public void Destroy() {
		if(!isVisible())
			getBoom();
	}

	
	
	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	
}
