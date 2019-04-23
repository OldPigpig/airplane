package com.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.model.manager.ElementManager;
import com.thread.GameThread;

public class Enemy extends SuperElement{
	private ImageIcon img;//敌机皮肤
	private boolean attack;//攻击状态
	private int speed=4;
	//构造函数
	public Enemy(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
		this.attack=true;
	}
	
	//构建创建敌机方法
	public static Enemy CreateEnemy(String str) {
		int x=(int)(Math.random()*360);
		return new Enemy(x, 0, 40, 40, new ImageIcon("img/enemy/13.png"));
	}
	
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}
	
	public void Action() {
		super.Action();
		if(GameThread.time%20==0) {
			setAttack(true);
			Fire();
		}
	}

	//敌机火力
	public void Fire() {
		if(!attack) {//非攻击状态
			return;
		}
		List<SuperElement> list=
				ElementManager.getElementManager().getElementList("enemy_fire");
		if(list==null) {
			list=new ArrayList<>();
		}
		ElementManager.getElementManager().getMap().put("enemy_fire",list);
		list.add(Enemy_Fire.CreateFire(getX()+15, getY()+5, ""));
		this.attack=false;
	}
	//爆炸
	public void getBoom() {
		List<SuperElement> list=ElementManager.
				getElementManager().getElementList("boom");
		list.add(Boom.CreateBoom(getX(), getY(), ""));
	}
	
	@Override
	public void Move() {
		setY(getY()+speed);
		if(getY()>600) {
			setVisible(false);
//			System.out.println("boom");
		}
//		System.out.println(getY());
	}

	@Override
	public void Destroy() {
		if(!isVisible()) {
			getBoom();
		}
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	
}
