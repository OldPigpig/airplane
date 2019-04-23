package com.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player_Fire extends SuperElement{

	private int speed=12;
	private ImageIcon img;//子弹类型
	
	//构造函数
	public Player_Fire(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
	}
	
	//构造创建子弹方法
	public static Player_Fire CreateFire(int x,int y,String str) {
		ImageIcon img=new ImageIcon("img/fire/14.png");
		return new Player_Fire(x, y, 10, 25, img);
	}
	
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	@Override
	public void Move() {
		setY(getY()-speed);
		if(getY()<0)
			setVisible(false);
	}

	@Override
	public void Destroy() {
		if (!isVisible()) {
			return;
		}
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	

}
