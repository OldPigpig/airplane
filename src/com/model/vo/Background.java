package com.model.vo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Background extends SuperElement{

	private ImageIcon img;//����ͼƬ
	private int moveY;//���������ٶ�
	
	public Background(int x,int y,int w,int h) {
		super(x,y,w,h);
		this.moveY=y-h;
		String url="img/Background/1.png";
		this.img=new ImageIcon(url);
	}
	
	//��������
	@Override
	public void ShowElement(Graphics g) {
		
		g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
		g.drawImage(img.getImage(), getX(),getMoveY(),getW(),getH(),null);
		//�����ƶ�
		Move();
	}

	//�����ƶ�
	@Override
	public void Move() {
		setY(getY()+3);
		setMoveY(getMoveY()+3);
		if(getY()>=getH()) {
			setY(getMoveY()-getH());
		}
		if(getMoveY()>=getH()) {
			setMoveY(getY()-getH());
		}
	}

	@Override
	public void Destroy() {
		
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public int getMoveY() {
		return moveY;
	}

	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
	
}
