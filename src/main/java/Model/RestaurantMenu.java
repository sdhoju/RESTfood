package Model;

import java.util.Hashtable;

public class RestaurantMenu {
	long menuId;
	String menuName;
	long restId;
	public long getRestId() {
		return restId;
	}
	public void setRestId(long restId) {
		this.restId = restId;
	}
	static Hashtable<String, Item> items = new Hashtable<String, Item>();
	
	public RestaurantMenu() {
		
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public static Hashtable<String, Item> getItems() {
		return items;
	}
	public static void setItems(Hashtable<String, Item> items) {
		RestaurantMenu.items = items;
	}
	public RestaurantMenu(long menuId,String menuName,long restId) {
		this.menuId=menuId;
		this.menuName=menuName;
		this.restId=restId;
	}
	
	public RestaurantMenu(long menuId,String menuName,long restId, Hashtable<String, Item> items) {
		this.menuId=menuId;
		this.menuName=menuName;
		this.restId=restId;
		this.items=items;
	}
}
