package com.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.frame.MyJPanel;
import com.model.manager.ElementManager;
import com.model.vo.Player;
import com.model.vo.SuperElement;

//������
public class GameListener implements KeyListener{

	private List<?> list;
	//���¼�
	@Override
	public void keyPressed(KeyEvent e) {
		    //�ж���Ϸδ�����ſɲ���Ӣ�ۻ�
		if (!GameThread.gameover) {
			list=ElementManager
					.getElementManager().getElementList("player");
			Player player=(Player)list.get(0);
			switch (e.getKeyCode()) {
			case 37:player.setLeft(true);break;//��
			case 38:player.setTop(true);break;//��
			case 39:player.setRight(true);break;//��
			case 40:player.setDown(true);break;//��
			case 32:player.setAttack(true);break;//�ո�
		}
			//Enter����ESC���ļ���
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

	//�ɿ���
	@Override
	public void keyReleased(KeyEvent e) {
		if (!GameThread.gameover) {
			list=ElementManager
					.getElementManager().getElementList("player");
			Player player=(Player)list.get(0);
			switch (e.getKeyCode()) {
			case 37:player.setLeft(false);break;//��
			case 38:player.setTop(false);break;//��
			case 39:player.setRight(false);break;//��
			case 40:player.setDown(false);break;//��
			case 32:player.setAttack(false);break;//�ո�
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
