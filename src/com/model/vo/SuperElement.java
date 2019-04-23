package com.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

//����һ�������ุ��
public abstract class SuperElement {
	private int x; //����x
	private int y; //����y
	private int w; //��
	private int h; //��
	private boolean visible; //�Ƿ���
	
	//��д���캯��
	protected SuperElement() {}
	//��д���ι��캯��
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
	
	//�����ײ����
	public boolean getCrash(SuperElement se) {
		Rectangle r1=new Rectangle(x, y, w, h);
		Rectangle r2=new Rectangle(se.x, se.y, se.w, se.h);
		return r1.intersects(r2);
	}
	
	//�����ࣺ��ʾͼƬ
	public abstract void ShowElement(Graphics g);
	//�����ࣺ�ƶ�
	public abstract void Move();
	//�����ࣺ����
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
