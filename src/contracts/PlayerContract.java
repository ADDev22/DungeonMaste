package contracts;

import java.util.List;
import decorateur.PlayerDecorator;
import enumeration.Cell;
import enumeration.Command;
import enumeration.Dir;
import errors.*;
import services.Containable;
import services.Cow;
import services.Environement;
import services.Food;
import services.IMob;
import services.Key;
import services.MagicBeans;
import services.Player;
import services.Treasure;
import services.Weapon;
import servicesImplementations.PlayerImpl;
import services.Arrow;
public class PlayerContract extends PlayerDecorator{

	
	
	
	@Override
	public void Add_Food(Food f) {
		
		if( f == null ) throw new PreConditionError("null food");
		//capture
		List<Food> foods = Foods();
		CheckInvariants();
		super.Add_Food(f);
		CheckInvariants();
		if(! Foods().contains(f)) {throw new PostConditionError("player does not have the food"); }
		if(foods.size() != Foods().size()-1){throw new PostConditionError("the number of foods is not coherent"); }
		
	}

	@Override
	public void Add_Weapon(Weapon w) {
		
		if( w == null ) throw new PreConditionError("null wep");
		//capture
		List<Weapon> weapons = Weapons();
		CheckInvariants();
		super.Add_Weapon(w);
		CheckInvariants();
		if(! Weapons().contains(w)) {throw new PostConditionError("player does not have the weapon"); }
		if(weapons.size() != Weapons().size()-1){throw new PostConditionError("the number of weapons is not coherent"); }
		

	}

	@Override
	public void UseMagicBeans() {
		//capture
		
		
		Cell my_nature = Env().cellNature(Col(), Row());
		Containable c = Env().CellContent(Col(), Row());
		int my_hp = Hp();
		int row = Row();
		int col = Col();
		Dir d = this.Face();
		// capture 
		List<MagicBeans> beans = Beans();
		int str_power = Striking_Power();
		
		CheckInvariants();
		super.UseMagicBeans();
		CheckInvariants();
	
		//post
		if(beans.size()>0) {
			if(Striking_Power() != beans.get(0).Striking_power_bonus()+str_power) { throw new PostConditionError(" striking power is not coherent"); }
			if(beans.size() != Beans().size()+1) { throw new PostConditionError(" number of beans is not coherent "); }
		}else {
		
			if(Striking_Power() != str_power) { throw new PostConditionError(" striking power is not coherent"); }
			if(beans.size() != Beans().size()) { throw new PostConditionError(" number of beans is not coherent "); }
			
		}
		
		if(Face() != d ) { throw new PostConditionError("direction has changed");}
		if( Col() != col || Row() != row || Env().cellNature(col, row) != my_nature || Env().CellContent(col, row) != c || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
			throw new PostConditionError("invalid post conds");
		}
	}

	@Override
	public void Add_Beans(MagicBeans b) {
		if( b == null ) throw new PreConditionError("null wep");
		//capture
		List<MagicBeans> beans = Beans();
		CheckInvariants();
		super.Add_Beans(b);
		CheckInvariants();
		if(! Beans().contains(b)) {throw new PostConditionError("player does not have the added beans"); }
		if(beans.size() != Beans().size()-1){throw new PostConditionError("the number of beanss is not coherent"); }
		

	}

	@Override
	public void Arrow() {
		// capture 
		Cell my_nature = Env().cellNature(Col(), Row());
		Containable cnt = Env().CellContent(Col(), Row());
		int my_hp = Hp();
		int row = Row();
		int col = Col();
		Dir d = this.Face();
		//capture
		int nb_arw = this.Nb_Arrows();
		List<Weapon> weps = this.Weapons();
		Cow c = null;
		int cow_hp=0;
		int x=0;
		Arrow arw=null;
		
		if(nb_arw>0 && Content(0,1) instanceof Cow) {  c = (Cow)Content(0,1);  cow_hp=c.Hp(); x=1; }
		else
		if(nb_arw>0 && Viewable(0,2) && Content(0,2) instanceof Cow ) {  c = (Cow)Content(0,2); cow_hp=c.Hp(); x=2;}
		else
		if(nb_arw>0 && Viewable(0,3) && Content(0,3) instanceof Cow ) {  c = (Cow)Content(0,3); cow_hp=c.Hp(); x=3;}
		
		this.CheckInvariants();
		super.Arrow();
		this.CheckInvariants();
		
		if(x>0) { 
			
			for(Weapon w :weps) { if (w instanceof Arrow) arw = (Arrow)w; break; }
			if ( c.Hp() != (cow_hp-arw.Striking_power()) ) { throw new PostConditionError("enemiy's hp haven't been affected correctly"); }
			if(Nb_Arrows() != nb_arw-1){ throw new PostConditionError("nb of arrows hasn't been affected correctly"); }
		}else {
			if(Nb_Arrows() != nb_arw){ throw new PostConditionError("nb of arrows wasn't supposed to change"); }
		}
		
		
		if(Face() != d ) { throw new PostConditionError("direction has changed");}
		if( Col() != col || Row() != row || Env().cellNature(col, row) != my_nature || Env().CellContent(col, row) != cnt || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
			throw new PostConditionError("invalid post conds ");
		}
		
		
	}

	@Override
	public void Eat() {
		//capture
		int hp= Hp();
		int max_hp= Max_Hp();
		int nb_foods = Nb_Foods();
		List<Food> foods = this.Foods();
		
		CheckInvariants();
		super.Eat();
		CheckInvariants();
		
		if(hp==Max_Hp() || nb_foods==0) { 
			
			if(Hp()!= hp) throw new PostConditionError("hp wasn't supposed to change");
			if(Nb_Foods() != nb_foods) throw new PostConditionError("nb_foods wasn't supposed to change");
		}
		else {
			
			Food f = foods.get(0);
			int x=hp+f.Hp_Value() ;
			int h = ( x >= max_hp ) ? max_hp : x ;  
			
			if(Hp() != h) throw new PostConditionError("player's hp hasn't been affected correctly");
			
		}
		if(Max_Hp() != max_hp)  throw new PostConditionError("player's max hp wasn't supposed to change");
		
		
		
	}

	
	
	@Override
	public void OpenDoor() {
		
		// capture 
		Cell my_nature = Env().cellNature(Col(), Row());
		Containable c = Env().CellContent(Col(), Row());
		int my_hp = Hp();
		int row = Row();
		int col = Col();
		Dir d = this.Face();
		Containable content = Content(0,1);
		Cell nature = Nature(0,1);
		boolean has_key = Has_Key();
		
		
		CheckInvariants();
		super.OpenDoor();
		CheckInvariants();
		
		//posts
				
		if(nature == Cell.DNC) {
			
			if( (content != null && has_key) || content == null) { if(Nature(0, 1) != Cell.DNO || Content(0,1) != null) {throw new PostConditionError("Nature(0, 1) != Cell.DNO || Content(0,1) != null");}  }
			
		}
		else
		if(nature == Cell.DWC) {
			
			if( (content != null && has_key) || content == null) { if(Nature(0, 1) != Cell.DWO || Content(0,1) != null) {throw new PostConditionError("Nature(0, 1) != Cell.DNO || Content(0,1) != null");}  }
			
		}
		
		if(Face() != d ) { throw new PostConditionError("direction has changed during Opendoor");}
		if( Col() != col || Row() != row || Env().cellNature(col, row) != my_nature || Env().CellContent(col, row) != c || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
			throw new PostConditionError("invalid post conds for OpenDoor");
		}
	}

	@Override
	public void Take() {
		// pre true
		//capture
		Dir d =Face();
		int my_hp=Hp();
		Cell my_nature = Env().cellNature(Col(), Row());
		Containable c = Env().CellContent(Col(), Row());
		int col=Col();
		int row=Row();
		
		Containable content = Content(0,1);
		Cell nature = Nature(0,1);
		List<Key> k=null;
		List<Food> f=null;
		List<Treasure> t=null;
		List<Weapon> w=null;
		
		if(	content instanceof IMob && nature != Cell.DWC && nature != Cell.DNC ) {
			
			if (content instanceof Key) { k = Keys(); }else
			if (content instanceof Food) { f = Foods(); }else
			if (content instanceof Treasure) { t = Treasures(); }else
			if (content instanceof Weapon) { w = Weapons(); }
			
		}
		CheckInvariants();
		super.Take();
		CheckInvariants();
		
		//post
		if(	content instanceof IMob && nature != Cell.DWC && nature != Cell.DNC ) {
			
			if (content instanceof Key) {
			    if( !Keys().contains((Key)content) || Keys().size() != k.size()+1 ) { throw new PostConditionError("player didn't pick up the key");}
			}
			else
			if (content instanceof Food) {
				if( !Foods().contains((Food)content) || Foods().size() != f.size()+1 ) { throw new PostConditionError("player didn't pick up the food");}
			}
			else
			if (content instanceof Treasure) { 
				if( !Treasures().contains((Treasure)content) || Treasures().size() != t.size()+1 ) { throw new PostConditionError("player didn't pick up the Treasure");} }
			else 
			if (content instanceof Weapon) {
				if( !Weapons().contains((Weapon)content) || Weapons().size() != w.size()+1 ) { throw new PostConditionError("player didn't pick up the Weapon");}
			}
			
			if(Content(0,1) != null) throw new PostConditionError("player didn't pick up the Object");
			
		}
		
		
		
		if(Face() != d ) { throw new PostConditionError("direction has changed during Opendoor");}
		if( Col() != col || Row() != row || Env().cellNature(col, row) != my_nature || Env().CellContent(col, row) != c || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
			throw new PostConditionError("invalid post conds for OpenDoor");
		}
	}

	@Override
	public void Add_Key(Key k) {
		if( k == null ) throw new PreConditionError("null key");
		//capture
		List<Key> keys = Keys();
		CheckInvariants();
		super.Add_Key(k);
		CheckInvariants();
		if(! Keys().contains(k)) {throw new PostConditionError("player does not have the key"); }
		if(keys.size() != Keys().size()-1){throw new PostConditionError("the number of keys is co coherent"); }
	}

	@Override
	public void CloseDoor() {
		
		// capture 
		Cell my_nature = Env().cellNature(Col(), Row());
		Containable c = Env().CellContent(Col(), Row());
		int my_hp = Hp();
		int row = Row();
		int col = Col();
		Dir d = this.Face();
		Containable content = Content(0,1);
		Cell nature = Nature(0,1);

		
		
		CheckInvariants();
		super.CloseDoor();
		CheckInvariants();
		
		//post
		
		if(nature == Cell.DNO) {
			
			if( content == null) { if(Nature(0, 1) != Cell.DNC || Content(0,1) != null) {throw new PostConditionError("Nature(0, 1) != Cell.DNC || Content(0,1) != null");}  }
			
		}
		else
		if(nature == Cell.DWO) {
			
			if( content == null) { if(Nature(0, 1) != Cell.DWC || Content(0,1) != null) {throw new PostConditionError("Nature(0, 1) != Cell.DNC || Content(0,1) != null");}  }
			
		}
		
		if(Face() != d ) { throw new PostConditionError("direction has changed during Closedoor");}
		if( Col() != col || Row() != row || Env().cellNature(col, row) != my_nature || Env().CellContent(col, row) != c || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
			throw new PostConditionError("invalid post conds for CloseDoor");
		}
		
	}

	@Override
	public int Nb_Arrows() {
		// TODO Auto-generated method stub
		return super.Nb_Arrows();
	}

	@Override
	public void SetHp(int hp) {
		super.SetHp(hp);
	}

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp, int Striking_power) {
		if(e == null || d ==null || !(0 <= col && col < e.width() && 0 <= row && row < e.height() && e.CellContent(col,row) == null) 
				|| hp <= 0 || e.cellNature(col, row)!= Cell.IN || Striking_power <= 0 ) {
			throw new PreConditionError("Invalid precondition before init");
		}
		
		super.init(e, col, row, d, hp, Striking_power);
		
		this.CheckInvariants();
		
		if(Col() != col || Row() != row || Env() != e || Face() != d || Env().CellContent(col, row) != delegate ||  Hp() != hp || Env().cellNature(col, row)!= Cell.IN
				|| Striking_Power() != Striking_power) {
		throw new PostConditionError("Invalid post conditions after init\n");	}
		
	}

	@Override
	public int Striking_Power() {
		
		return super.Striking_Power();
	}

	@Override
	public void Increase_Striking_Power(int p) {
		// capture
		int sp = Striking_Power();
		if(p<=0) throw new PreConditionError("striking power needs to be positive");
		this.CheckInvariants();
		super.Increase_Striking_Power(p);
		this.CheckInvariants();
		if(Striking_Power() != (sp+p)) throw new PostConditionError(" invalid post cond for Increase_Striking_power()"); 
	}

	@Override
	public void Attack() {
		
		// capture
				Cell nature = Env().cellNature(Col(), Row());
				Containable c = Env().CellContent(Col(), Row());
				int my_hp = Hp();
			
				Containable content = null;
				int row = Row();
				int col = Col();
				int hp=0;
				Dir d = this.Face();
				boolean b1=false,b2=false ;
				
				if(d== Dir.N) {
					
					b1= ( row+1 < Env().height());
					if(b1) content = Env().CellContent(col, row+1);
					if(content != null && content instanceof Cow) { b2 = true; hp = ((Cow)content).Hp(); }
				}
				else
				if(d== Dir.S) {
					
					b1= ( row-1 >= 0);
					if(b1) content = Env().CellContent(col, row-1);
					if(content != null && content instanceof Cow) { b2 = true; hp = ((Cow)content).Hp(); }
				}
				else
				if(d== Dir.E) {
					
					b1= ( col+1 < Env().width());
					if(b1) content = Env().CellContent(col+1, row);
					if(content != null && content instanceof Cow) { b2 = true; hp = ((Cow)content).Hp(); }
				}
				else
				if(d== Dir.W) {
					
					b1= ( col-1 >= 0);
					if(b1) content = Env().CellContent(col-1, row);
					if(content != null && content instanceof Cow) { b2 = true; hp = ((Cow)content).Hp(); }
				}
				
				this.CheckInvariants();
				super.Attack();
				this.CheckInvariants();
				
				/*Posts*/
				
				if( b1 && b2 && hp >0 ) {
			
					System.out.println(((Cow)content).Hp() +" "+(hp-Striking_Power()) +" ");
					if ( ((Cow)content).Hp() != (hp-Striking_Power()) ) { throw new PostConditionError("this attack did not affect enemy's hp correctly"); }
				}
				
			
				
				if(Face() != d ) { throw new PostConditionError("direction has changed during attack");}
				if( Col() != col || Row() != row || Env().cellNature(col, row) != nature || Env().CellContent(col, row) != c || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
					throw new PostConditionError("invalid post conds for Attack()");
				}
	}

	public void CheckInvariants() throws InvariantError, PreConditionError {

		if(		   ! ( 0 <= Col() && Col()< Env().width()) 
				|| !(0<= Row() && Row() < Env().height()) 
				|| Env().cellNature(Col(), Row()) == Cell.WLL 
				|| Env().cellNature(Col(), Row()) == Cell.DNC 
				|| Env().cellNature(Col(), Row()) == Cell.DWC)
		{
		throw new InvariantError("broken invariants => Col: "+Col()+" Row: "+Row()+" CellNature: "+Env().cellNature(Col(), Row()));
		}
		
		
	}
	
	@Override
	public int Hp() {
		return super.Hp();
	}

	@Override
	public Environement Env() {
	
		return super.Env();
	}

	@Override
	public int Col() {
	
		return super.Col();
	}

	@Override
	public int Row() {
		
		return super.Row();
	}

	@Override
	public Dir Face() {
	
		return super.Face();
	}
	
	@Override
	public void init(Environement e, int col, int row, Dir d, int hp) throws PreConditionError,PostConditionError, InvariantError {
		if(e == null || d ==null || !(0 <= col && col < e.width() && 0 <= row && row < e.height() && e.CellContent(col,row) == null) 
				|| hp <= 0 || e.cellNature(col, row)!= Cell.IN) {
			throw new PreConditionError("Invalid precondition before init");
		}
		
		super.init(e, col, row, d,hp);
		
		this.CheckInvariants();
		
		if(Col() != col || Row() != row || Env() != e || Face() != d || Env().CellContent(col, row) != delegate ||  Hp() != hp || Env().cellNature(col, row)!= Cell.IN) {
		throw new PostConditionError("Invalid post conditions after init\n");	
		}
	
	}
	@Override
	public void Step() throws InvariantError, PostConditionError, PreConditionError {
		
		/* capture */
		int col = Col();
		int row = Row();
		CheckInvariants();
		
		Command cmd= LastCom();
		
		if(cmd == Command.FF)    { this.Forward();  }else
		if(cmd == Command.BB)    { this.Backward(); }else
		if(cmd == Command.LL)    { this.StrafeL();  }else
		if(cmd == Command.RR)    { this.StrafeR();  }else
		if(cmd == Command.TL)    { this.TurnL();    }else
		if(cmd == Command.TR)    { this.TurnR();    }else
		if(cmd == Command.EAT)   { this.Eat();      }else
		if(cmd == Command.ARROW) { this.Arrow();    }else 
		if(cmd == Command.ATT)   { this.Attack();   }else 	
		if(cmd == Command.CLOSD) { this.CloseDoor();}else 
		if(cmd == Command.OPEND) { this.OpenDoor(); }else 
		if(cmd == Command.TAKE)  { this.Take();     }else
		if(cmd == Command.BEANS) { this.UseMagicBeans();} 
		
		CheckInvariants();
		/* post */
		if(Col() != (col-1) && Col() != (col+1) && Col() != col ) { throw new PostConditionError("Invalid post conditions for step() => the player moved for more than a step (1)");}
		if(Row() != (row-1) && Row() != (row+1) && Row() != row ) { throw new PostConditionError("Invalid post conditions for step() => the player moved for more than a step (2)");}
		
	}

	@Override
	public void init(Environement e, int col, int row, Dir d)
			throws Error {
		super.init(e, col, row, d);
	}

	@Override
	public void Forward() throws InvariantError, PostConditionError, PreConditionError {
		
		// capture
		
				Cell nature = null;
				Containable content = null;
				int row = Row();
				int col = Col();
				
				
				if(Face() == Dir.N) {
					if(  row+1 < Env().height() ) { nature = Env().cellNature(col, row+1); content = Env().CellContent(col, row+1); }
				}
				
				if(Face() == Dir.S) {
					if(  row-1 >= 0 ) { nature = Env().cellNature(col, row-1); content = Env().CellContent(col, row-1); }
				}
				
				if(Face() == Dir.E) {
					if(  col+1 < Env().width() ) { nature = Env().cellNature(col+1, row); content = Env().CellContent(col+1, row); }
				}
				
				if(Face() == Dir.W) {
					if( col-1 >= 0) { nature = Env().cellNature(col-1, row); content = Env().CellContent(col-1, row); }
				}
				
				this.CheckInvariants();
				super.Forward();
				this.CheckInvariants();
				
				/*Post Conditions*/
				
				if(Face() == Dir.N) {
					
					if( row+1 < Env().height() && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
						
					if(Row() != row+1 || Col() != col || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
						
						throw new PostConditionError("invalid post conditions for Forward() (1) \n");
						
					}
					
					}else {
						
						if(Row() != row || Col() != col || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
							
							throw new PostConditionError("invalid post conditions for Forward() (2) \n");
							
						}	
						
					}
					return;
				}
				
				if(Face() == Dir.S) {
					
					if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
						
					if(Row() != row-1 || Col() != col || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
						
						throw new PostConditionError("invalid post conditions for Forward() (1) \n");
						
					}
					
					}else {
						
						if(Row() != row || Col() != col || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
							
							throw new PostConditionError("invalid post conditions for Forward() (2) \n");
											}	
						
					}
					return;
				}
				
				

				if(Face() == Dir.E) {
					
					if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
						
					if(Col() != col+1 || Row() != row || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
						
						throw new PostConditionError("invalid post conditions for Forward() (1) \n");
						
					}
					
					}else {
						
						if(Row() != row || Col() != col || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
							
							throw new PostConditionError("invalid post conditions for Forward() (2) \n");
											}	
						
					}
					return;
				}
				
				
				

				if(Face() == Dir.W) {
					
					if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
						
					if(Row() != row || Col() != col-1 || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
						
						throw new PostConditionError("invalid post conditions for Forward() (1) \n");
						
					}
					
					}else {
						
						if(Row() != row || Col() != col || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
							
							throw new PostConditionError("invalid post conditions for Forward() (2) \n");
											}	
						
					}
					return;
				}
	}

	@Override
	public void Backward() throws InvariantError, PostConditionError, PreConditionError {
		
	// capture
		
		Cell nature = null;
		Containable content = null;
		int row = Row();
		int col = Col();
		
		
		if(Face() == Dir.N) {
			if(  row-1 >= 0) { nature = Env().cellNature(col, row-1); content = Env().CellContent(col, row-1); }
		}
		
		if(Face() == Dir.S) {
			if( row+1 < Env().height()   ) { nature = Env().cellNature(col, row+1); content = Env().CellContent(col, row+1); }
		}
		
		if(Face() == Dir.E) {
			if( col-1 >= 0  ) { nature = Env().cellNature(col-1, row); content = Env().CellContent(col-1, row); }
		}
		
		if(Face() == Dir.W) {
			if( col+1 < Env().width()) { nature = Env().cellNature(col+1, row); content = Env().CellContent(col+1, row); }
		}
		
		this.CheckInvariants();
		super.Backward();
		this.CheckInvariants();
	
	
		/*Post Conditions*/
		
		if(Face() == Dir.S) {
			
			if( row+1 < Env().height() && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT  ) && content == null){
				
			if(Row() != row+1 || Col() != col || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for Backward() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for Backward() (2) \n");
					
				}	
				
			}
			return;
		}
		
		if(Face() == Dir.N) {
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row-1 || Col() != col || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for Backward() (1) \n");
				 
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for Backward() (2) \n");
									}	
				
			}
			return;
		}
		
		

		if(Face() == Dir.W) {
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Col() != col+1 || Row() != row || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for Backward() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for Backward() (2) \n");
									}	
				
			}
			return;
		}
		
		
		

		if(Face() == Dir.E) {
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row || Col() != col-1 || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for Backward() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for Backward() (2) \n");
									}	
				
			}
			return;
		}
	
	

	}

	@Override
	public void TurnL() throws InvariantError, PostConditionError, PreConditionError {
		
	
		/* Capture */
		
		Cell nature = Env().cellNature(Col(), Row());
		Containable content = Env().CellContent(Col(),Row());
		Dir d = Face();
		
		/* pré true */
		
		this.CheckInvariants();
		super.TurnL();
		this.CheckInvariants();
			
		/* post */
		
		if(d==Dir.N) { if(Face() != Dir.W) { throw new PostConditionError("invalid post conditions for TurnL() (1)"); }  }
		if(d==Dir.S) { if(Face() != Dir.E) { throw new PostConditionError("invalid post conditions for TurnL() (2)"); }  }
		if(d==Dir.E) { if(Face() != Dir.N) { throw new PostConditionError("invalid post conditions for TurnL() (3)"); }  }
		if(d==Dir.W) { if(Face() != Dir.S) { throw new PostConditionError("invalid post conditions for TurnL() (4)"); }  }
		if( Env().cellNature(Col(), Row()) != nature || Env().CellContent(Col(),Row()) != content ) { throw new PostConditionError("invalid post conditions for TurnL() (5)");}
 
	}

	@Override
	public void TurnR() throws InvariantError, PostConditionError, PreConditionError {
		
		/* Capture */
		
		Cell nature = Env().cellNature(Col(), Row());
		Containable content = Env().CellContent(Col(),Row());
		Dir d = Face();
		
		/* pré true */
		
		
		this.CheckInvariants();
		super.TurnR();
		this.CheckInvariants();
		
		/* post */
		
		if(d==Dir.N) { if(Face() != Dir.E) { throw new PostConditionError("invalid post conditions for TurnR() (1)"); }  }
		if(d==Dir.S) { if(Face() != Dir.W) { throw new PostConditionError("invalid post conditions for TurnR() (2)"); }  }
		if(d==Dir.E) { if(Face() != Dir.S) { throw new PostConditionError("invalid post conditions for TurnR() (3)"); }  }
		if(d==Dir.W) { if(Face() != Dir.N) { throw new PostConditionError("invalid post conditions for TurnR() (4)"); }  }
		if( Env().cellNature(Col(), Row()) != nature || Env().CellContent(Col(),Row()) != content ) { throw new PostConditionError("invalid post conditions for TurnR() (5)");}
	}

	@Override
	public void StrafeL() throws InvariantError, PostConditionError, PreConditionError {
		
		// capture
		
		Cell nature = null;
		Containable content = null;
		int row = Row();
		int col = Col();
		
		
		if(Face() == Dir.N) {
			if(  col-1 >= 0) { nature = Env().cellNature(col-1, row); content = Env().CellContent(col-1, row); }
		}
		
		if(Face() == Dir.S) {
			if( col+1 < Env().width()  ) { nature = Env().cellNature(col+1, row); content = Env().CellContent(col+1, row); }
		}
		
		if(Face() == Dir.E) {
			if( row+1< Env().height()  ) { nature = Env().cellNature(col, row+1); content = Env().CellContent(col, row+1); }
		}
		
		if(Face() == Dir.W) {
			if( row-1 >= 0 ) { nature = Env().cellNature(col, row-1); content = Env().CellContent(col, row-1); }
		}
		
		
		this.CheckInvariants();
		super.StrafeL();
		this.CheckInvariants();
		
		
		/*Post Conditions*/
		
		if(Face() == Dir.S) {
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row || Col() != col+1 || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeL() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeL() (2) \n");
					
				}	
				
			}
			return;
		}
		
		if(Face() == Dir.N) {
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row || Col() != col-1 || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeL() (1) \n");
				 
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeL() (2) \n");
									}	
				
			}
			return;
		}
		
		

		if(Face() == Dir.W) {
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Col() != col || Row() != row-1 || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeL() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeL() (2) \n");
									}	
				
			}
			return;
		}
		
		
		

		if(Face() == Dir.E) {
			
			if( row +1  < Env().height() && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row+1 || Col() != col || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeL() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeL() (2) \n");
									}	
				
			}
			return;
		}
	
	}

	@Override
	public void StrafeR() throws InvariantError, PostConditionError, PreConditionError {
		
		// capture
		
		Cell nature = null;
		Containable content = null;
		int row = Row();
		int col = Col();
		
		
		if(Face() == Dir.N) {
			if( col+1 < Env().width()) { nature = Env().cellNature(col+1, row); content = Env().CellContent(col+1, row); }
		}
		
		if(Face() == Dir.S) {
			if( col-1 >= 0  ) { nature = Env().cellNature(col-1, row); content = Env().CellContent(col-1, row); }
		}
		
		if(Face() == Dir.E) {
			if( row-1 >= 0  ) { nature = Env().cellNature(col, row-1); content = Env().CellContent(col, row-1); }
		}
		
		if(Face() == Dir.W) {
			if(  row+1< Env().height()) { nature = Env().cellNature(col, row+1); content = Env().CellContent(col, row+1); }
		}
		
		
		
		this.CheckInvariants();
		super.StrafeR();
		this.CheckInvariants();
		
		
		

		/*Post Conditions*/
		
		if(Face() == Dir.N) {
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row || Col() != col+1 || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeR() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.N || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeR() (2) \n");
					
				}	
				
			}
			return;
		}
		
		if(Face() == Dir.S) {
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row || Col() != col-1 || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeR() (1) \n");
				 
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.S || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeR() (2) \n");
									}	
				
			}
			return;
		}
		
		

		if(Face() == Dir.E) {
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Col() != col || Row() != row-1 || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeR() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.E || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeR() (2) \n");
									}	
				
			}
			return;
		}
		
		
		

		if(Face() == Dir.W) {
			
			if( row +1  < Env().height() && (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT ) && content == null){
				
			if(Row() != row+1 || Col() != col || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
				
				throw new PostConditionError("invalid post conditions for StrafeR() (1) \n");
				
			}
			
			}else {
				
				if(Row() != row || Col() != col || Face() != Dir.W || Env().CellContent(Col(), Row()) != delegate ) {
					
					throw new PostConditionError("invalid post conditions for StrafeR() (2) \n");
									}	
				
			}
			return;
		}
	
	}
	@Override
	public Command LastCom() {
		
		return super.LastCom();
		
	}

	
	@Override
	public Containable Content(int col, int row) throws PreConditionError {
	
	    if(col < -1 || col > 1 || row < -1 || row > 3) { throw new PreConditionError("Invalid precodition for content() => invalid row or col");}
		return super.Content(col, row);
	}

	@Override
	public boolean Viewable(int col, int row) throws PreConditionError {
	  	if(col < -1 || col > 1 || row < -1 || row > 3) { throw new PreConditionError("Invalid precodition for Viewable() => invalid row or col");}
		return super.Viewable(col, row);
	}

	@Override
	public Cell Nature(int col, int row) throws PreConditionError {
		if(col < -1 || col > 1 || row < -1 || row > 3) { throw new PreConditionError("Invalid precodition for Nature() => invalid row or col");}
		return super.Nature(col, row);
	}

	public PlayerContract(Player delegate) {
		super(delegate);
	}

	@Override
	public void AddCommad(Command c) throws PreConditionError, PostConditionError, InvariantError {
		if(c == null) { throw new PreConditionError("null command"); }
		CheckInvariants();
		super.AddCommad(c);
		CheckInvariants();
		if( ( (PlayerImpl)delegate).last_command != c) { throw new PostConditionError("last command isn't c ");}
	}
	 public String getViewable() {
	    	Player p1=this;
	    	String res="";
	    	Containable b;
	    	for(int j=3; j> 0; j--) {
	    	for(int i=-1; i< 2; i++)
	    		{
	    			System.out.println(i +","+ j);
	    			if(p1.Viewable(i, j)){	res+=p1.Nature(i, j).toString()+":"+
	    		     (((b=p1.Content(i, j))==null)?"Noc":
	    		    	 (b instanceof Cow)? b+"|Hp: "+((Cow)b).Hp():b)+"  ";}
	    			else { res+="???";}
	    			if(i!=1)
	    			res+="eS";
	    		}
	    	res+="/";
	    	}
			return res;

	    }

}
