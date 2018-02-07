package Model;


import java.util.Hashtable;


import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class Restaurant {
	
	long id;
	String name;
	long phone;
	static Hashtable<String, RestaurantMenu> menus = new Hashtable<String, RestaurantMenu >();

	//static Hashtable<String, RestaurantMenu> items = new Hashtable<String, RestaurantMenu>();

	public Restaurant() {};
	
	public Restaurant(long id, String name, long phone) {
		this.id=id;
		this.name=name;
		this.phone=phone;
	}
	public Restaurant(long id, String name, long phone, Hashtable<String, RestaurantMenu > menus) {
		this.id=id;
		this.name=name;
		this.phone=phone;
		this.menus=menus;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public static Hashtable<String, RestaurantMenu> getMenus() {
		return menus;
	}
	public static void setMenus(Hashtable<String, RestaurantMenu> menus) {
		Restaurant.menus = menus;
	}
	

}
