package com.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Boom extends SuperElement{

	private int MoveX;
	private ImageIcon img;
	public Boom(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;
	}
	
	public static Boom CreateBoom(int x,int y, String str) {
		return new Boom(x,y,40,40,new ImageIcon("img/boom/bang1.png"));
	}
	
	@Override
	public void ShowElement(Graphics g) {
		g.drawImage(img.getImage(),
				getX(), getY(), 
				getX()+getW(),getY()+getH(),
				0+MoveX*66,0,
				66+MoveX*66,66,
				null);
	}

	@Override
	public void Move() {
		MoveX++;
	}

	@Override
	public void Destroy() {
		if(MoveX==8) {
			this.setVisible(false);
		}
	}


}
