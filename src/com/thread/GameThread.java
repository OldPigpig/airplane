package com.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.frame.MyJPanel;
import com.main.GameStart;
import com.model.manager.ElementManager;
import com.model.vo.Boss;
import com.model.vo.Enemy;
import com.model.vo.Medicine;
import com.model.vo.Player;
import com.model.vo.Prop;
import com.model.vo.SuperElement;

//�߳���
public class GameThread extends Thread{
    //����һ��time����¼��Ϸʱ��
	public static long time;     //��¼ˢ�´����������ж��л�����Ƶ��
	public static int actack_num;//����������������¼boss������²�����
	private boolean flag;        //���ڴ�������ʱ���ж�
	public static boolean gameover=false;//�����ж���Ϸ�Ƿ����
	private int prop;            //��¼�������ͣ�//0=��ʯ
	//��дrun����
	public void run() {
		//������Դ
		while(true) {
			//��ʼ��ʱ
			loadElement();
			//��Ϸ��ʼ
			if(MyJPanel.flag==1) {
				//��ʾ��Դ
				RunElement();	
				//����
				OverElement();	
			}
			//����
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadElement() {
		//��ʱ��ʼ
		GameStart.start_time=System.currentTimeMillis();
	}
	private void RunElement() {
		while(true) {
		
			Time();
			
			//��¼ˢ�´���
			time++;
			
			//�л�����
			RunEnemy();

			//ҽ�ư�����
			RunMedicine();
			
			//boos����
			RunBoss();
			
			//���߳���
			RunProp();
			
			//����ִ��
			Map<String, List<SuperElement>> map=
					ElementManager.getElementManager().getMap();
			Set<String> set=map.keySet();
			for(String key:set) {
				List<SuperElement> list=map.get(key);
				for(int i=0;i<list.size();i++) {
					list.get(i).Action();
					if(gameover) {
						list.get(i).setVisible(false);
					}
					if (!list.get(i).isVisible()) {
						list.remove(i--);
					}
				}
			}
			
			//�����Ϸ�����򷵻�
			if (gameover) {
				return;
			}	
			
			//��ײ���
			Crash();
			
			//����
			try {
				sleep(45);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

	//��ʱ����
	private void Time() {
		
		GameStart.game_time=(System.currentTimeMillis()-GameStart.start_time)/1000;
		if (GameStart.game_time!=GameStart.flag_time) {
			GameStart.flag_time=GameStart.game_time;
			flag=true;
		}
	}
	
	//boss����
	private void RunBoss() {
		List<SuperElement> list_boss=ElementManager
				.getElementManager().getElementList("boss");
		if(GameStart.flag_time%51==50) {
			if(flag) {
				list_boss.add(Boss.CreateBoss(""));
				flag=false;
			}	
		}
	}
	
	//ҽ�ư�����
	private void RunMedicine() {
		List<SuperElement> list_medicine=ElementManager
				.getElementManager().getElementList("medicine");
		if(GameStart.flag_time%21==20) {
			if(flag) {
				list_medicine.add(Medicine.CreateMedicine(""));
				flag=false;
			}			
		}
	}
	
	//���߳���
	private void RunProp() {
		prop=(int)(Math.random()*2);
		List<SuperElement> list_prop=ElementManager
				.getElementManager().getElementList("prop");
		if(GameStart.flag_time%16==15) {
			if(flag) {
				switch (prop) {
				case 0:
					list_prop.add(Prop.CreateProp("45,60,img/prop/4.png"));
					break;
				case 1:
					list_prop.add(Prop.CreateProp("30,30,img/prop/7.png"));
					break;
				}
				flag=false;
			}
		}
	}
	
	//�л�
	private void RunEnemy() {
		List<SuperElement> list_enemy=ElementManager
				.getElementManager().getElementList("enemy");
		if(time%60==0) {
			list_enemy.add(Enemy.CreateEnemy(""));
		}
	}
	
	
	//��ײ����
	private void Crash() {
		// TODO �Զ����ɵķ������
		List<SuperElement> listA=ElementManager
				.getElementManager().getElementList("fire");
		if (listA==null) {
			listA=new ArrayList<>();
		}
		List<SuperElement> listB=ElementManager
				.getElementManager().getElementList("enemy");
		List<SuperElement> listC=ElementManager
				.getElementManager().getElementList("player");
		List<SuperElement> listD=ElementManager
				.getElementManager().getElementList("boss");
		List<SuperElement> listE=ElementManager
				.getElementManager().getElementList("boss_fire");
		List<SuperElement> listF=ElementManager
				.getElementManager().getElementList("enemy_fire");
		List<SuperElement> listG=ElementManager
				.getElementManager().getElementList("medicine");
		List<SuperElement> listH=ElementManager
				.getElementManager().getElementList("prop");
		SupCrash(listC, listH, prop);
		SupCrash(listC, listG, 2);//2.�ɻ���ҽ�ư���ײ
		SupCrash(listA, listD, 3);//3.�ӵ���boss��ײ
		SupCrash(listC, listE, 4);//4.�ɻ���boss�ӵ���ը��ײ
		SupCrash(listC, listF, 4);//4.�ɻ��͵л��ӵ���ը��ײ
		SupCrash(listC, listB, 4);//4.�ɻ��͵л���ײ
		SupCrash(listA, listB, 5);//5.�ӵ��͵л���ײ
		//SupCrash();
		if (gameover) {
			return;
		}
	}
	
	private void SupCrash(List<SuperElement> listA,List<SuperElement> listB,int k) {
		for(int i=0;i<listA.size();i++) {
			for(int j=0;j<listB.size();j++) {
				if(listA.get(i).getCrash(listB.get(j))) {
					switch (k) {
					case 0:
						listB.get(j).setVisible(false);
						Player.num+=200;
						break;
					case 1:
						listB.get(j).setVisible(false);
						break;
					case 2:
						//������ʧ
						listB.get(j).setVisible(false);
						if(Player.hp<=7) {
							//����Ч��
							Player.hp+=3;
						}
						else {
							Player.hp+=10-Player.hp;
						}
						break;
					case 3:
						//��ײ��ʧ
						listA.get(i).setVisible(false);
						actack_num++;
						if(actack_num>=100) {
							listB.get(j).setVisible(false);
							//�Ƿ�
							Player.num+=500;
							//������������
							actack_num=0;
						}
						break;
					case 4:
						//��ײ��ʧ
						Player.hp--;
						if(Player.hp<=0) {
							listA.get(i).setVisible(false);
							gameover=true;
							return;
						}
						listB.get(j).setVisible(false);
						break;
					case 5:
						//��ײ��ʧ
						listA.get(i).setVisible(false);
						if(listB.get(j).isVisible()) {
							listB.get(j).setVisible(false);
							//�Ƿ�
							Player.num+=50;
						}
						break;
					}
				}
			}
		}
	}
	
	private void OverElement() {
		MyJPanel.flag=2;
	}
}
