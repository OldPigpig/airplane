package com.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.model.vo.Player;
import com.model.vo.SuperElement;
    //元素管理器
public class ElementManager {

	//定义Map类存储所有元素
	Map<String, List<SuperElement>> map;
	//定义ElementManager
	private static ElementManager elementManager;
	//构造函数
	public ElementManager() {
		init();//初始化
	}

	//这里需要自己去了解一下map、set、list的用法和定义
	protected void init() {
		map=new HashMap<>();
		List<SuperElement> list_player=new ArrayList<>();
		list_player.add(Player.CreatePlayer(null));//创建一架英雄机
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
    
	//构造一个得到元素集合的方法
	public List<SuperElement> getElementList(String key) {
		return map.get(key);
	}
	
	
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}

	//这里为了防止获取ElementManager类时为空值
	public static synchronized ElementManager getElementManager() {
		if (elementManager==null) {
			elementManager=new ElementManager();
		}
		return elementManager;
	}
	
}
