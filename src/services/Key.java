package services;

public interface Key /*includes */ extends IMob {

	// ===========CONSTRUCTORS==============
	/**
	 * pre : init(int door_col, int door_row,int col,int row, Environement env);   requires
	 * 0 ≤ col < Environment::Width(E) and 0 ≤ row < Environment::Height(E) && E != null
	 * 0 ≤ door_col < Environment::Width(E) and 0 ≤ door_row < Environment::Height(E) && Env().CellContent(col,row)== null. 
	 * post : Col()==col && Row()==row && Env()==e && Env().CellContent() == So(this) &&
	 * 			Door_col() == door_col && Door_row()== door_row. 
	 */
	
	// l'autre constructeur héritéé est deprécié 
	
	public void init(int door_col, int door_row,int col,int row, Environement env); 
	
	// sert a créer une clé sans la mettre dans la map. elle sera plassé comme butin sur un Cow ou monstre.
	
	/**
	 * \pre 0 ≤ door_col < Environment::Width(E) and 0 ≤ door_row < Environment::Height(E)&& E != null
	 * \post Env()==e && Door_col() == door_col && Door_row()== door_row. 
	 * @param door_col
	 * @param door_row
	 * @param env
	 */
	public void init(int door_col, int door_row,Environement env);
	/*Observateurs*/
	// permettent de récuperer les coordonnées de la porte qu'elle ouvre. 
	// on suppose qu'une clé ne peut ouvrir qu'une seule porte. 
	
	public int Door_col();
	public int Door_row();
}
