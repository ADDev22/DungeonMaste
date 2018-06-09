package services;

public interface IMob extends /*refines*/ Containable {
	
	// ===========OBSERVATORS==============
			/**
			 * 
			 * pre : cellContent(x1,y1)requires
			 * cellContent(x1,y1)(x,y) requires 0 ≤ x < width(E) and 0 ≤ y < height(E)
			 *
			 */
			public Environement env();
			public int row();
			public int col();
			// ========INVARIANTS==================
			
			//true 

			
			// ===========CONSTRUCTORS==============
			/**
			 * pre : init(E,col,row)  requires
			 * 0 ≤ x < Environment::Width(E) and 0 ≤ y < Environment::Height(E) && E != null && Env().CellContent(col,row)== null
			 * 
			 * post : Col()==col && Row()==row && Env()==e && Env().CellContent() == So(this)
			 */
			public void init(Environement env , int col, int row);



}
