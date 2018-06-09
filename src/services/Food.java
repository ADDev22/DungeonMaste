package services;

public interface Food /*refines*/ extends IMob{

	
	// en plus des posts hérités on aura Hp_value()==1 ; 
	
	public void init(Environement env , int col, int row);
	
	// ===========CONSTRUCTORS==============
	/**
	 * pre : init(E,col,row,Hp_value)  requires
	 * 0 ≤ x < Environment::Width(E) and 0 ≤ y < Environment::Height(E) && E != null && Hp_value> 0
	 * 
	 * post : Col()==col && Row()==row && Env()==e && Env().CellContent() == So(this) && Hp_value()== Hp_value;
	 */
	public void init(Environement env , int col, int row, int Hp_value);

	// ces constructeurs permettent d'instance des foods qui seront dans le sac du joueur directement et non déposés sur la map. 
	
	/**
	 * \ pre Hp_value > 0
	 * \ post Hp_value()== Hp_value;
	 * @param Hp_value
	 */
	public void init(int Hp_value);
	
	/**
	 * \pre true
	 * \post true
	 */
	public void init();
	
	/**
	 * \pre true
	 * \post true 
	 * @return
	 */
	public int Hp_Value(); 
}
