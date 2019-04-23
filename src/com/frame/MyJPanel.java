package com.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.main.GameStart;
import com.model.manager.ElementManager;
import com.model.vo.Background;
import com.model.vo.Player;
import com.model.vo.SuperElement;

public class MyJPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	
	//背景类实例化，因为只需要new一个背景类即可
	Background bg=new Background(0, 0, 400, 600);
	public static int flag=0;      //用来控制游戏状态，0=表示开始前，1=表示游戏中，2=表示游戏结束
	public static int time_enemy=0;//敌机子弹控制
	//重写paint方法
	public void paint(Graphics g) {
		super.paint(g);
		//游戏开始界面绘制
		if(flag==0) {
			Start(g);
		}
		else if(flag==1) {
			//背景绘制
			BackGround(g);
			//属性绘制
			Others(g);
			//角色绘制
			GameRun(g);
		}
		else if(flag==2) {
			//游戏结束界面绘制
			GameOver(g);
		}
	}
	

	//游戏结束动画
	private void GameOver(Graphics g) {
		//图片绘制
		ImageIcon img=new ImageIcon("img/Background/over.jpg");
		g.drawImage(img.getImage(),0,0,400,600,null);
		//分数绘制
		g.setColor(Color.WHITE);
		g.setFont(new Font("黑体", Font.PLAIN, 30));
		g.drawString(""+Player.num, 200, 246);
		//评价绘制
		g.setFont(new Font("黑体", Font.PLAIN, 60));
		g.setColor(Color.red);
		if(Player.num<150) {
			g.drawString("菜", 165, 400);
		}
		if(Player.num>=150&&Player.num<500)
			g.drawString("勉强吧", 115, 400);
		if(Player.num>=500&&Player.num<1000)
			g.drawString("哎哟，不错哟", 20, 400);
		if (Player.num>=1000&&Player.num<2000) {
			g.drawString("玩的很棒", 85, 400);
		}
		if(Player.num>=2000&&Player.num<4000) {
			g.drawString("膜拜大神", 85, 400);
		}
	}


	private void Start(Graphics g) {
		//图片绘制
		ImageIcon img=new ImageIcon("img/Background/start.jpg");
		g.drawImage(img.getImage(),0,0,400,600,null);
		
	}


	private void Others(Graphics g) {
		//绘制游戏时间阴影
		g.setColor(Color.BLACK);
		g.setFont(new Font("黑体", Font.PLAIN, 20));
		g.drawString("时间："+GameStart.flag_time+"秒", 2, 570);
		//绘制游戏时间
		g.setColor(Color.white);
		g.setFont(new Font("黑体",Font.PLAIN,20));
		g.drawString("时间："+GameStart.flag_time+"秒",0,568);
		//绘制分数阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体",Font.PLAIN,20));
		g.drawString("分数："+Player.num,32,42);
		g.drawString("生命值：",182,42);
		//绘制分数
		g.setColor(Color.white);
		g.setFont(new Font("黑体",Font.PLAIN,20));
		g.drawString("分数："+Player.num,30,40);
		g.drawString("生命值：",180,40);
		//绘制空心血条方框
		g.setColor(new Color(190,195,199));
		g.drawRect(275,22,101,18);
		//绘制实心血条方框
		g.setColor(new Color(234,75,53));
		g.fillRect(276,22,Player.hp*10,17);
		//绘制血条数值阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体",Font.PLAIN,12));
		g.drawString(""+Player.hp*10, 317, 39);
		//绘制血条数值
		g.setColor(Color.white);
		g.setFont(new Font("黑体",Font.PLAIN,12));
		g.drawString(""+Player.hp*10, 315, 37);		
		//绘制血量过低警告
		if(Player.hp*10<=30){
			//绘制生命值过低提示阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体",Font.PLAIN,16));
			g.drawString("警告：生命值过低！", 197, 64);
			//绘制生命值过低提示
			g.setColor(Color.red);
			g.setFont(new Font("黑体",Font.PLAIN,16));
			g.drawString("警告：生命值过低！", 195, 62);
		}
	}


	private void BackGround(Graphics g) {	
		bg.ShowElement(g);
	}


	private void GameRun(Graphics g) {
		//获取元素管理器中的Map的key，将其存入Set中
		Map<String, List<SuperElement>> map=
				ElementManager.getElementManager().getMap();
		Set<String> set=map.keySet();
		
		//遍历该Key的
		for(String key:set) {
			List<SuperElement> list=map.get(key);
			for(int i=0;i<list.size();i++) {
				list.get(i).ShowElement(g);
			}
		}

	}

	//重写run方法
	public void run() {
		while(true) {
			try {
			Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
