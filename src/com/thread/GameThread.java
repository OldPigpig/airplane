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

//线程类
public class GameThread extends Thread{
    //定义一个time来记录游戏时间
	public static long time;     //记录刷新次数，用于判定敌机出现频率
	public static int actack_num;//攻击次数，用来记录boss打多少下才死亡
	private boolean flag;        //用于创建对象时的判定
	public static boolean gameover=false;//用于判定游戏是否结束
	private int prop;            //记录道具类型，//0=宝石
	//重写run方法
	public void run() {
		//加载资源
		while(true) {
			//开始计时
			loadElement();
			//游戏开始
			if(MyJPanel.flag==1) {
				//显示资源
				RunElement();	
				//结束
				OverElement();	
			}
			//休眠
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadElement() {
		//计时开始
		GameStart.start_time=System.currentTimeMillis();
	}
	private void RunElement() {
		while(true) {
		
			Time();
			
			//记录刷新次数
			time++;
			
			//敌机出现
			RunEnemy();

			//医疗包出现
			RunMedicine();
			
			//boos出现
			RunBoss();
			
			//道具出现
			RunProp();
			
			//动作执行
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
			
			//如果游戏结束则返回
			if (gameover) {
				return;
			}	
			
			//碰撞检测
			Crash();
			
			//休眠
			try {
				sleep(45);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

	//计时方法
	private void Time() {
		
		GameStart.game_time=(System.currentTimeMillis()-GameStart.start_time)/1000;
		if (GameStart.game_time!=GameStart.flag_time) {
			GameStart.flag_time=GameStart.game_time;
			flag=true;
		}
	}
	
	//boss出现
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
	
	//医疗包出现
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
	
	//道具出现
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
	
	//敌机
	private void RunEnemy() {
		List<SuperElement> list_enemy=ElementManager
				.getElementManager().getElementList("enemy");
		if(time%60==0) {
			list_enemy.add(Enemy.CreateEnemy(""));
		}
	}
	
	
	//碰撞方法
	private void Crash() {
		// TODO 自动生成的方法存根
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
		SupCrash(listC, listG, 2);//2.飞机和医疗包碰撞
		SupCrash(listA, listD, 3);//3.子弹和boss碰撞
		SupCrash(listC, listE, 4);//4.飞机和boss子弹爆炸碰撞
		SupCrash(listC, listF, 4);//4.飞机和敌机子弹爆炸碰撞
		SupCrash(listC, listB, 4);//4.飞机和敌机碰撞
		SupCrash(listA, listB, 5);//5.子弹和敌机碰撞
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
						//道具消失
						listB.get(j).setVisible(false);
						if(Player.hp<=7) {
							//道具效果
							Player.hp+=3;
						}
						else {
							Player.hp+=10-Player.hp;
						}
						break;
					case 3:
						//碰撞消失
						listA.get(i).setVisible(false);
						actack_num++;
						if(actack_num>=100) {
							listB.get(j).setVisible(false);
							//记分
							Player.num+=500;
							//攻击次数归零
							actack_num=0;
						}
						break;
					case 4:
						//碰撞消失
						Player.hp--;
						if(Player.hp<=0) {
							listA.get(i).setVisible(false);
							gameover=true;
							return;
						}
						listB.get(j).setVisible(false);
						break;
					case 5:
						//碰撞消失
						listA.get(i).setVisible(false);
						if(listB.get(j).isVisible()) {
							listB.get(j).setVisible(false);
							//记分
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
