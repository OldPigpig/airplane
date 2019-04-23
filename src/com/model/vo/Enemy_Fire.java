package com.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy_Fire extends SuperElement {

	private int speed=8;
	ImageIcon img;//敌机火力皮肤
	//构造函数
	public Enemy_Fire(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
	}
	
	//构造创建敌机火力方法
	public static Enemy_Fire CreateFire(int x,int y,String str) {
		return new Enemy_Fire(x, y,10,25, new ImageIcon("img/fire/15.png"));
	}
	
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	//敌机移动方法
	@Override
	public void Move() {
		setY(getY()+speed);
		if(getY()>600) {
			setVisible(false);
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

}
