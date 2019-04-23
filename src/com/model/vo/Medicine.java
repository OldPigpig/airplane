package com.model.vo;

import java.awt.Graphics;


import javax.swing.ImageIcon;

public class Medicine extends SuperElement{

	private ImageIcon img;//ҽ�ư�ͼƬ
	
	private int speed=3;
	
 	public boolean flag=true;//�����ж�
	//���캯��
	public Medicine(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
	}
	
	//����ҽ�ư�
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
		//�����ƶ�
		setY(getY()+speed);
		//�����ж�
		if(getX()>=360) flag=false;
		if(getX()<=0)   flag=true;
		//�����ƶ�
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
		// TODO �Զ����ɵķ������
		
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

}
