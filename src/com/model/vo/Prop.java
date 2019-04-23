package com.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Prop extends SuperElement{

	private ImageIcon img;
	
	private int speed;
	
	private boolean flag;
	
	public Prop(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
		this.speed=3;
		this.flag=true;
	}

	public static Prop CreateProp(String str) {
		int x=(int)(Math.random()*360);
		String a[]=str.split(",");
		int w=Integer.parseInt(a[0]);
		int h=Integer.parseInt(a[1]);
		ImageIcon img=new ImageIcon(a[2]);
		return new Prop(x, 0,w , h, img);
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
}
