package com.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.frame.MyJPanel;
import com.model.manager.ElementManager;
import com.model.vo.Player;
import com.model.vo.SuperElement;

//监听类
public class GameListener implements KeyListener{

	private List<?> list;
	//按下键
	@Override
	public void keyPressed(KeyEvent e) {
		    //判定游戏未结束才可操纵英雄机
		if (!GameThread.gameover) {
			list=ElementManager
					.getElementManager().getElementList("player");
			Player player=(Player)list.get(0);
			switch (e.getKeyCode()) {
			case 37:player.setLeft(true);break;//左
			case 38:player.setTop(true);break;//上
			case 39:player.setRight(true);break;//右
			case 40:player.setDown(true);break;//下
			case 32:player.setAttack(true);break;//空格
		}
			//Enter键和ESC键的监听
		}
		switch (e.getKeyCode()) {
		case 10:
			if(MyJPanel.flag==0) {
				MyJPanel.flag=1;
			}
			break;
		case 27:
			if(MyJPanel.flag==2) {
				MyJPanel.flag=0;
				List<SuperElement> list=ElementManager
						.getElementManager().getElementList("player");
				list.add(Player.CreatePlayer(null));
				Player.num=0;
				Player.hp=10;
				GameThread.gameover=false;
			}
			break;
		}
	}

	//松开键
	@Override
	public void keyReleased(KeyEvent e) {
		if (!GameThread.gameover) {
			list=ElementManager
					.getElementManager().getElementList("player");
			Player player=(Player)list.get(0);
			switch (e.getKeyCode()) {
			case 37:player.setLeft(false);break;//左
			case 38:player.setTop(false);break;//上
			case 39:player.setRight(false);break;//右
			case 40:player.setDown(false);break;//下
			case 32:player.setAttack(false);break;//空格
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
