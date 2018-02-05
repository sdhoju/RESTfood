package Model;

import Model.Restaurant;
public class Menu {
	Restaurant restaurant;
	
	long menuId;
	String name;
	long restID;
	
	
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRestID(long restID) {
		this.restID = restaurant.getId();
	}
	
}
