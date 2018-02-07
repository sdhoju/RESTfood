package repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import Model.Menu;


public interface RestaurantRepository  extends CrudRepository<Menu, Long>{

}
