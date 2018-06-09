package services;

import java.util.List;

import enumeration.Cell;
import enumeration.Command;
import enumeration.Dir;
import errors.*;

public interface Player extends Entity{

	
	
	/* pré init : cellNature(col,row) == Cell.IN ? 
	 * POST init : Env().cellNature(col, row)!= Cell.IN*/
	
	/**
	 * \pre c != null
	 * \post lastCom()==c;
	 * @param c
	 * @throws PreconditionError 
	 * @throws PostconditionError 
	 * @throws InvariantException 
	 */
	public void AddCommad(Command c) throws InvariantError, PostConditionError, PreConditionError;
	/**
	 * \pre true
	 * \post true 
	 */
	public Command LastCom();
	
	/**
	 * \pre  col ∈ {-1,0,1} && row ∈ {-1,+3} 
	 * \post  si les coordonnées sont a l'interieur de la map on renvois ce qu'il y'a a l'interieur de la case sinon retourner null.
	 * @throws PreconditionError 
	 * 
	 * 
	 * 	 */
	public Containable Content(int col , int row ) throws PreConditionError;
	
	/**
	 * \pre  col ∈ {-1,0,1} && row ∈ {-1,+3} 
	 * 
	 * @throws PreconditionError 
	 * 
	 * 
	 * 	 */
	public boolean Viewable(int col , int row ) throws PreConditionError;
	
	/**
	 * \pre  col ∈ {-1,0,1} && row ∈ {-1,+3} 
	 * \post  si les coordonnées sont a l'interieur de la map on renvois ce qu'il y'a a l'interieur de la case sinon retourner WLL.
	 * @throws PreconditionError
	 * 	 */
	public Cell Nature(int col , int row ) throws PreConditionError;

	
	
	/*pour les opérations de deplacement on autorise le player a se déplacer dans une case IN ou OUT contrairement aux cows et on doit impérativement le place
	 * dans une case IN au départ.*/
	
	
	
	/**
	 * Invariants
	 * Face()==N => Content(u,v)== Env().CellContent(Col()+u,Row()+v)
	 * Face()==N => Nature(u,v) == Env().CellNature(Col()+u,Row()+v)
	 * Face()==S => Content(u,v)== Env().CellContent(Col()-u,Row()-v)
	 * Face()==S => Nature(u,v) == Env().CellNature(Col()-u,Row()-v)
	 * Face()==E => Content(u,v)== Env().CellContent(Col()+v,Row()-u)
	 * Face()==E => Nature(u,v) == Env().CellNature(Col()+v,Row()-u)
	 * Face()==W => Content(u,v)== Env().CellContent(Col()-v,Row()+u)
	 * Face()==W => Nature(u,v) == Env().CellNature(Col()-v,Row()+u)
	 * forall u,v in [-1,1]×[-1,0] Viewable(u,v)==false.
	 * Viewable(-1,2)== Nature(-1,1) /∈ {WALL,DWC,DNC}.
	 * Viewable(0,2) == Nature(0,1)  /∈ {WALL,DWC,DNC}.
	 * Viewable(1,2) == Nature(1,1)  /∈ {WALL,DWC,DNC}.
	 * Viewable(-1,3)== Nature(-1,2) /∈ {WALL,DWC,DNC} and Viewable(-1,2)==true.
	 * Viewable(0,3) == Nature(0,2)  /∈ {WALL,DWC,DNC} and Viewable(0,2)==true.
	 * Viewable(1,3) == Nature(1,2)  /∈ {WALL,DWC,DNC} and Viewable(1,2)==true.
	 */
	
	
	/**
	 * Step()
	 * \post (hérite des précedentes post conds)
	 * LastCom() == FF => step()== Forward()
	 * LastCom(P)== BB => step()== Backward()
	 * LastCom(P)== LL => step()== StrafeLeft()
	 * LastCom(P)== RR => step()== StrafeRight()
	 * LastCom(P)== TL => step()== TurnLeft()
	 * LastCom(P)== TR => step()== TurnRight()
	 */
	
	
	
	
	
	
	
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
	
	/**
	 * \pre p > 0 
	 * \ post Striking_Power() == Striking_Power() @ pré + p 
	 * */
	public void Increase_Striking_Power(int p);
	
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
	
	
	
	/**
	 * 
	 * \pre true
	 * 
	 * \post if nb_Arrows > 0 && Content(0,1) instanceof Cow => Cow().hp() == Cow.hp()@pré - Arrow.power()
	 *            && nb_Arrows = nb_Arrows@pré -1.   
	 * 		 else
	 * 		 if nb_Arrows > 0 && viewable(0,2) && Content(0,2) instanceof Cow 
	 *     			=> Cow().hp() == Cow.hp()@pré - Arrow.power() && nb_Arrows = nb_Arrows@pré -1.   
	 * 		 else
	 * 		 if nb_Arrows > 0 && viewable(0,3) && Content(0,3)  instanceof Cow 
	 *       => Cow().hp() == Cow.hp()@pré - Arrow.power() && nb_Arrows = nb_Arrows@pré -1.   
	 * 		 else
	 * 		 nb_Arrows == nb_Arrows @ pré  
	 *   
	 *   	 Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() 
	 *       && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	 *       && Hp() == Hp() @pré. && Face() == Face() @ pré .
	 */
	
	public void Arrow();
	
	/**
	 *  if Hp() < MaxHp() && Nb_Food > 0 => Hp()==  min ( Hp() @pré + Food.HP_value() , MaxHp() ) && Nb_food == Nb_food@ pré -1 ; 
	 * 	else
	 * 	Hp() == Hp() @ pré && Nb_food == Nb_food @pré .
	 * 
	 * Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	 * && Face() == Face() @ pré && Max_hp() == Max_hp() @ pré.
	 */
	
	public void Eat();
	
	// si cellcontent() d'une cell qui est une porte n'est pas null ceci veut dire que pour ouvrir la porte il faut une clé. sinon une clé n'est pas necéssaire.
	// et une fois une porte ouverte puis refermé un clé n'est plus necessaire pour la réouvrir, d'ou le && Content(0,1)==null. 
	/**
	 * \pre  true
	 * 
	 * 			// pour Ouvrir une porte on utilise l'oprateur OpenDoor() de Environnement .
	 * 
	 * \post   ( Nature(0,1)== Cell.DNC ) => ( Content(0,1) != null && HasKey() ) => Nature(0,1)== Cell.DNO && Content(0,1)==null.  
	 *		   ( Nature(0,1)== Cell.DWC ) => ( Content(0,1) != null && HasKey() ) => Nature(0,1)== Cell.DWO && Content(0,1)==null.	
	 * 		   
	 * 			
	 * 	
	 * 		   ( Nature(0,1)== Cell.DNC ) => ( Content(0,1) == null ) => Nature(0,1) == (Cell.DNO && Content(0,1)==null.    
	 *		   ( Nature(0,1)== Cell.DWC ) => ( Content(0,1) == null ) => Nature(0,1) == Cell.DWO && Content(0,1)==null. 
	 * 
	 * 		   ( Nature(0,1) != Cell.DWC  && Nature(0,1) != Cell.DNC ) => Nature(0,1) @ pré == Nature(0,1) && Content(0,1) @ pré == COntent(0,1);
	 * 		
	 * 		// a vérifier dans tous les cas.
	 *  
	 *		 Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	 *       && Hp() == Hp() @pré. && Face() == Face() @ pré .
	 */
	
	public void OpenDoor();
	/**
	 * \pre  true
	 * 		   
	 *  		// pour Fermer une porte on utilise l'oprateur CloseDoor() de Environnement .   
	 * 		  
	 * 
	 * \post   ( Nature(0,1)== Cell.DNO ) => ( Content(0,1) == null ) => Nature(0,1) == Cell.DNC  && Content(0,1)==null. 
	 *		   ( Nature(0,1)== Cell.DWO ) => ( Content(0,1) == null ) => Nature(0,1) == Cell.DWC && Content(0,1)==null.	
	 * 		 	
	 * 		// a vérifier dans tous les cas.
	 *  
	 *		 Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	 *       && Hp() == Hp() @pré. && Face() == Face() @ pré .  
	 */
	
	public void CloseDoor();
	
	/**
	Content(0,1) instanceof IMob && nature(0,1) != Cell.DWC && Nature(0,1) != Cell.DNC => ( Content(0,1) instanceof Key => Keys().contains(Content(0,1)@pré) && Keys().size() == Keys().size @pré +1  && Content(0,1)== null).  
	Content(0,1) instanceof IMob && nature(0,1) != Cell.DWC && Nature(0,1) != Cell.DNC => ( Content(0,1) instanceof Food => Foods().contains(Content(0,1)@pré)&& Foods().size() == Keys().size @pré +1 && Content(0,1)== null).  
	Content(0,1) instanceof IMob && nature(0,1) != Cell.DWC && Nature(0,1) != Cell.DNC => ( Content(0,1) instanceof Weapon => Weapons().contains(Content(0,1)@pré) && Weapons().size() == Weapons().size @pré +1 && Content(0,1)== null).  
	Content(0,1) instanceof IMob && nature(0,1) != Cell.DWC && Nature(0,1) != Cell.DNC => ( Content(0,1) instanceof Treasure => Treasures().contains(Content(0,1)@pré && Treasures().size() == Treasures().size @pré +1 Content(0,1)== null ).  
	
		// a vérifier dans tous les cas.
   
	*		Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	*       && Hp() == Hp() @pré. && Face() == Face() @ pré .  
	*
	*/
	public void Take();
	
	/**
	 * \pre true
	 * \post 
	 * 
	 *  Beans().size > 0 => striking_power() == striking_power() @ré + Beans.get(0).striking_power_bonus().	&&  Beans().size() ==  Beans().size()@pré -1.
	 * 	Beans().size == 0 => striking_power() == striking_power() @ pré &&   Beans().size()==  Beans().size() @pré.
	 * 
	 * Col() == Col() @ pré && Row() == Row() @ pré && Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Col(),Row()) == Env().CellContent(Col(),Row()) @ pré ==So(this).
	 * && Face() == Face() @ pré && Max_hp() == Max_hp() @ pré.
	 */ 
	
	public void UseMagicBeans();
	
	/**
	 * \ pre b != null 
	 * \ post Beans().contains(b) == true; 
	 * 		Beans().size() == Beans().size()@ pré +1 . 
	 * @param b
	 */
	
	public void Add_Beans(MagicBeans b);
	/**
	 * \ pre k != null 
	 * \ post Keys().contains(k) == true; 
	 * 		Keys().size() == Keys().size()@ pré +1 . 
	 * @param k
	 */
	
	public void Add_Key(Key k);
	
	/**
	 * \ pre f != null 
	 * \ post Foods().contains(f) == true; 
	 * 		Foods().size() == Foods().size()@ pré +1 . 
	 * @param k
	 */
	public void Add_Food(Food f);
	
	/**
	 * \ pre w != null 
	 * \ post Weapons().contains(w) == true; 
	 * 		Weapons().size() == Weapons().size()@ pré +1 . 
	 * @param k
	 */
	public void Add_Weapon(Weapon w );

	/*Observateurs*/
	
	/**
	 * \pre true
	 * \post true
	 * @return
	 */
	public int Nb_Arrows();
	public int Nb_Foods();
	public List<Weapon> Weapons();
	public List<Food> Foods();
	public List<Treasure> Treasures();
	public List<Key> Keys();
	public List<MagicBeans> Beans();
	public int Max_Hp();
	/**
	 * \pre true
	 * \post
	 *  si Nature(0,1)=={DNC,DWC} && Content(0,1) instanceof Key && exists Key in Keys() tel que Key.door_col()== la colonne de la porte d'en face && key.door_row() == la ligne de la porte d'en face ==> true. 
	 *  sinon false.
	 *   
	 *  voir Has_key() dans PlyaerContract pour voir le comportemnt détaillé.
	 * @return
	 */
	public boolean Has_Key();
	
	
}
