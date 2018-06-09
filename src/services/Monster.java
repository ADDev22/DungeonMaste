package services;

import enumeration.Dir;

public interface Monster /*refines*/ extends Cow {

	
	
	/*Constructeurs*/
	
	/**
	 * \post les memes posts que init de Cow && Striking_Power()== 1 
	 */
	public void init(Environement e,int col , int row, Dir d,int hp);
	
	/** 
	 * \pre  e != null && d !=null && 0 ≤ col < e.Width() && 0 ≤ y < e.Height() && 4 >= hp >= 3 && >= 10 Striking_power > 0 
	 * \post Col()==col && Row()==row && Env()==e && Face()==d 	&& Hp()==hp && Striking_Power()== Striking_power. 
	 * */
	public void init(Environement e,int col , int row, Dir d,int hp,int Striking_power);
	
	/*Observateurs*/
	
	/**
	 * \pre true
	 * \post true
	 * @return
	 */
	
	public int Striking_Power();
	
	/*Operateurs*/
	
	/**
	 * \pre true
	 * \post  Face() @pré == N && Row()+1 < Env().Height() && Env().CellContent(Col(),Row()+1) instanceof Player && Env().CellContent(Col(),Row()+1).Hp()>0 =>
	 *    	  Env().CellContent(Col(),Row()+1).Hp() == Env().CellContent(Col(),Row()+1).Hp() @ pré - this.Striking_power() .
	 *  	 
	 * 		  Face() @pré == S && Row()-1 >= 0 && Env().CellContent(Col(),Row()-1) instanceof Player && Env().CellContent(Col(),Row()-1).Hp()>0 =>
	 *    	  Env().CellContent(Col(),Row()-1).Hp() == Env().CellContent(Col(),Row()-1).Hp() @ pré - this.Striking_power() .
	 *    
	 *        Face() @pré == E && Col()+1 < Env().Width() && Env().CellContent(Col()+1,Row()) instanceof Player && Env().CellContent(Col()+1,Row()).Hp()>0 =>
	 *    	  Env().CellContent(Col(),Row()-1).Hp() == Env().CellContent(Col(),Row()-1).Hp() @ pré - this.Striking_power() .
	 *    
	 *        Face() @pré == W && Col()-1 >= 0 Env().CellContent(Col()-1,Row()) instanceof Player && Env().CellContent(Col()-1,Row()).Hp()>0 =>
	 *    	  Env().CellContent(Col()-1,Row()).Hp() == Env().CellContent(Col()-1,Row()).Hp() @ pré - this.Striking_power() .
	 *  		 
	 *  	  Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	 *        && Hp() == Hp() @pré. && Face() == Face() @ pré .
	 */
	
	
	public void Attack();
	
	
	
	
	/* post step() */
	/**
	 * le monstre a un champ de vision de 8 cases . 1 de chaque coté et 6 devant. selon la visibilité et selon le contenu de ces cases
	 * si le monstre detecte un jouer il avance vers lui de la façon la plus rapide possible sinon il fait un random des 6 actions de bases.
	 * voir la method step() dans MonsterContrat : le comportemnt est bien détaillé. 
	 */
	
	
	
	
	
	
}
