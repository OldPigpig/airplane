package com.model.vo;

import java.awt.Graphics;


import javax.swing.ImageIcon;

public class Medicine extends SuperElement{

	private ImageIcon img;//医疗包图片
	
	private int speed=3;
	
 	public boolean flag=true;//方向判定
	//构造函数
	public Medicine(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
	}
	
	//创建医疗包
	public static Medicine CreateMedicine(String str) {
		int x=(int)(Math.random()*360);
		ImageIcon img=new ImageIcon("img/prop/3.png");
		return new Medicine(x, 0, 40, 40,img);
	}
	
	
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	@Override
	public void Move() {
		//向下移动
		setY(getY()+speed);
		//方向判定
		if(getX()>=360) flag=false;
		if(getX()<=0)   flag=true;
		//左右移动
		if(flag) {
			setX(getX()+speed);
		}
		else {
			setX(getX()-speed);
		}
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
