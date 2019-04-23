package com.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

//构造一个抽象类父类
public abstract class SuperElement {
	private int x; //坐标x
	private int y; //坐标y
	private int w; //宽
	private int h; //高
	private boolean visible; //是否存活
	
	//重写构造函数
	protected SuperElement() {}
	//重写带参构造函数
	public SuperElement(int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.visible=true;
	}
	
	public void Action() {
		Move();
		Destroy();
	}
	
	//体积碰撞方法
	public boolean getCrash(SuperElement se) {
		Rectangle r1=new Rectangle(x, y, w, h);
		Rectangle r2=new Rectangle(se.x, se.y, se.w, se.h);
		return r1.intersects(r2);
	}
	
	//抽象类：显示图片
	public abstract void ShowElement(Graphics g);
	//抽象类：移动
	public abstract void Move();
	//抽象类：销毁
	public abstract void Destroy();
	
	
	//
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
