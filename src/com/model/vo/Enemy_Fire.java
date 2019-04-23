package com.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy_Fire extends SuperElement {

	private int speed=8;
	ImageIcon img;//�л�����Ƥ��
	//���캯��
	public Enemy_Fire(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
	}
	
	//���촴���л���������
	public static Enemy_Fire CreateFire(int x,int y,String str) {
		return new Enemy_Fire(x, y,10,25, new ImageIcon("img/fire/15.png"));
	}
	
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
	}

	//�л��ƶ�����
	@Override
	public void Move() {
		setY(getY()+speed);
		if(getY()>600) {
			setVisible(false);
		}
	}

	@Override
	public void Destroy() {
		// TODO �Զ����ɵķ������
		
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}

}
