package brown.user.main;

/**
 * config for specifying tradeables from user-given input. See implementation for details. 
 * @author andrewcoggins
 *
 */
public interface IItemConfig extends IInputConfig {
  

  public String getItemName(); 
  

  public Integer getNumItems();
  
  
}