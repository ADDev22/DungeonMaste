package services;

public interface Arrow extends Weapon{

	
	// ===========CONSTRUCTORS==============
		/**
		 * pre : init(E,col,row,str_pow)  requires
		 * 0 ≤ x < Environment::Width(E) and 0 ≤ y < Environment::Height(E) && E != null && str_pow> 0
		 * 
		 * post : Col()==col && Row()==row && Env()==e && Env().CellContent() == So(this) && Striking_power()== str_pow;
		 */
		public void init(Environement env , int col, int row, int Striking_power);
	
		// ces constructeurs permettent d'instance des fleches qui seront dans le sac du joueur directement et non déposés sur la map. 
		
		/**
		 * \ pre Striking_power > 0
		 * \ post Striking_power()== Striking_power;
		 * @param Striking_power
		 */
		public void init(int Striking_power);
		
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
	public int Striking_power();
	
}
