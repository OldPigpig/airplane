package com.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Boss_Fire extends SuperElement{
	
	private double speed=4;//子弹移动速度
	private double speed_x;
	private double speed_y;
	private ImageIcon img;
	
	//构造函数
	public Boss_Fire(int x,int y,int w,int h,ImageIcon img,int angle) {
		super(x,y,w,h);
		this.img=img;
		this.speed_x=speed*(Math.sin(angle));
		this.speed_y=speed*(Math.cos(angle));
	}
	
	//添加子弹方法
	public static Boss_Fire CreateBoss_Fire(int x,int y,int angle,String str) {
		ImageIcon img=new ImageIcon("img/fire/24.png");
		return new Boss_Fire(x, y, 18, 34, img, angle);
	}
	
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	@Override
	public void Move() {
		setY((int)(getY()+speed_y));
		setX((int)(getX()+speed_x));
		if(getY()>600||getY()<0||getX()>400||getX()<0) {
			this.setVisible(false);
		}
		
	}

	@Override
	public void Destroy() {
		
	}

}
