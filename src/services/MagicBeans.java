package services;

public interface MagicBeans /*refines*/ extends Boosts{

	// ===========CONSTRUCTORS==============
	/**
	 * pre : init(E,col,row,str_pow_bonus)  requires
	 * 0 ≤ x < Environment::Width(E) and 0 ≤ y < Environment::Height(E) && E != null && str_pow> 0
	 * 
	 * post : Col()==col && Row()==row && Env()==e && Env().CellContent() == So(this) && Striking_power_bonus()== str_pow_bonus;
	 */
	public void init(Environement env , int col, int row, int Striking_power_bonus);

	// ces constructeurs permettent d'instance des fleches qui seront dans le sac du joueur directement et non déposés sur la map. 
	
	/**
	 * \ pre Striking_power_bonus > 0
	 * \ post Striking_power_bonus ()== Striking_power_bonus ;
	 * @param Striking_power_bonus 
	 */
	public void init(int Striking_power_bonus);
	
	/**
	 * \pre true
	 * \post true
	 */
	public void init();
	/*Obs*/
	
	/**
	 * \pre true
	 * \post true
	 * @return
	 */
	public int Striking_power_bonus();
		
		
	}
