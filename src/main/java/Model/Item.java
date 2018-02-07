package Model;

public class Item {
	private long itemId;
	String itemName;
	double price;
	long menuId;
	
	public Item() {
	}
	
	 public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public Item(long itemId, String itemName, double price,long menuId) {
		this.itemId=itemId;
		this.itemName=itemName;
		this.price=price;
		this.menuId=menuId;
	}
}
