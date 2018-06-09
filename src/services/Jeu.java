package services;

import java.util.List;

public interface Jeu {

	
	public Player Get_Player();
	public List<Treasure> Get_Necessary_Treasures(); 
	/**
	 * \true
	 * \post Get_Player().hp()<=0 ||
	 * 
	 * ( Get_Player().env().cellNature(Get_Player().col(),Get_Player().row()) == Cell.OUT 
	 *  && for(Treasure t : Get_Necessary_Treasures()) Get_Player().treasures().contains(t) ==true. )
	 * @throws Exception 
	 */
	public void InitAndStartGame() throws Exception;
	
}
