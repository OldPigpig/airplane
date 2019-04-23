package com.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.model.manager.ElementManager;
import com.thread.GameThread;

public class Boss extends SuperElement{

	public static int num;
	private int speed=3;       //速度
	private ImageIcon img;
	private boolean flag=true; //方向判定
	private boolean attack;    //开火判定
	
	//构造函数
	public Boss(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.attack=true;
		this.img=img;
	}
	//创建boss的方法
	public static Boss CreateBoss(String str) {
		int x=150;
		int y=-120;
		ImageIcon img=new ImageIcon("img/boos/10.png");
		return new Boss(x, y, 120, 120, img);
	}
	
	//boss的行为
	public void Action() {
		super.Action();
		if(GameThread.time%100==0) {//boss开火间隔
			setAttack(true);
			Fire();
		}

	}
	
	//boss开火
	public void Fire() {
		if(!attack)
			return;
		if (getY()>0) {//当boss出现后开火
			List<SuperElement> list=
					ElementManager.getElementManager().getElementList("boss_fire");
			if(list==null) {
				list=new ArrayList<>();
			}
			ElementManager.getElementManager().getMap().put("boss_fire",list);
			for(int i=0;i<=180;i+=20) {		
				list.add(Boss_Fire.CreateBoss_Fire
						(getX()+getW()/2, getY()+getH()/2, i, ""));
			}
			
		}
	}
	
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	//boss移动
	@Override
	public void Move() {
		//方向判定
		if(getX()>=280) flag=false;
		if(getX()<=-20)   flag=true;
		//向下移动
		if(getY()<=80)
			setY(getY()+speed);
		//左右移动
		if(getY()>=80) {
			if(flag) {
				setX(getX()+speed);
			}else {
				setX(getX()-speed);
			}
		}
	}

	@Override
	public void Destroy() {
		// TODO 自动生成的方法存根
		
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

}
