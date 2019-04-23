package com.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.model.vo.Player;
import com.model.vo.SuperElement;
    //Ԫ�ع�����
public class ElementManager {

	//����Map��洢����Ԫ��
	Map<String, List<SuperElement>> map;
	//����ElementManager
	private static ElementManager elementManager;
	//���캯��
	public ElementManager() {
		init();//��ʼ��
	}

	//������Ҫ�Լ�ȥ�˽�һ��map��set��list���÷��Ͷ���
	protected void init() {
		map=new HashMap<>();
		List<SuperElement> list_player=new ArrayList<>();
		list_player.add(Player.CreatePlayer(null));//����һ��Ӣ�ۻ�
		map.put("player", list_player);
		List<SuperElement> list_enemy=new ArrayList<>();
		map.put("enemy", list_enemy);
		List<SuperElement> list_boom=new ArrayList<>();
		map.put("boom", list_boom);
		List<SuperElement> list_fire=new ArrayList<>();
		map.put("enemy_fire", list_fire);
		List<SuperElement> list_medicine=new ArrayList<>();
		map.put("medicine", list_medicine);
		List<SuperElement> list_boss=new ArrayList<>();
		map.put("boss", list_boss);
		List<SuperElement> list_bossfire=new ArrayList<>();
		map.put("boss_fire", list_bossfire);
		List<SuperElement> list_prop=new ArrayList<>();
		map.put("prop", list_prop);
	}
    
	//����һ���õ�Ԫ�ؼ��ϵķ���
	public List<SuperElement> getElementList(String key) {
		return map.get(key);
	}
	
	
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}

	//����Ϊ�˷�ֹ��ȡElementManager��ʱΪ��ֵ
	public static synchronized ElementManager getElementManager() {
		if (elementManager==null) {
			elementManager=new ElementManager();
		}
		return elementManager;
	}
	
}
