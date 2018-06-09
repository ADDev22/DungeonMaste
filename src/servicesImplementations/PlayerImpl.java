package servicesImplementations;
import java.util.ArrayList;
import java.util.List;


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
import services.Arrow;

public class PlayerImpl implements Player {

	private Environement env;
	private int col;
	private int row;
	private Dir dir; 
	private int hp;
	private int Max_Hp;
	
	public Command last_command =null;
	private int Striking_power=3;
	
	private List<Key> Keys;
	private List<Treasure> Treasures;
	private List<Weapon> Weapons ;
	private List<Food> Foods;
	private List<MagicBeans> Beans;
	
	@Override
	public void Step() throws InvariantError, PostConditionError, PreConditionError {
		Command cmd= LastCom();
		if (cmd == null) return; // ???
		
		if(cmd == Command.FF) 	 { this.Forward();   return;}
		if(cmd == Command.BB) 	 { this.Backward();  return;}
		if(cmd == Command.LL) 	 { this.StrafeL();   return;}
		if(cmd == Command.RR) 	 { this.StrafeR();   return;}
		if(cmd == Command.TL) 	 { this.TurnL();     return;}
		if(cmd == Command.TR) 	 { this.TurnR();     return;}
		if(cmd == Command.ATT)   { this.Attack();    return;}
		if(cmd == Command.EAT)   { this.Eat();       return;}
		if(cmd == Command.ARROW) { this.Arrow();     return;}
		if(cmd == Command.OPEND) { this.OpenDoor();  return;}
		if(cmd == Command.CLOSD) { this.CloseDoor(); return;}
		if(cmd == Command.TAKE)  { this.Take();      return;}
		if(cmd == Command.BEANS) { this.UseMagicBeans();  return;}
	}
	@Override
	public void init(Environement e, int col, int row, Dir d, int hp) throws PreConditionError, PostConditionError {
		
		this.env=e;
		this.col=col;
		this.row=row;
		this.dir=d;
		this.hp=hp;
		this.Max_Hp=hp;
		e.setCellContent(col, row, this);
		Keys = new ArrayList<>();
		Foods = new ArrayList<>();
		Treasures = new ArrayList<>();
		Weapons = new ArrayList<>();
		Beans= new ArrayList<>();
	
		
		
	}
	
	@Override
	public void init(Environement e, int col, int row, Dir d, int hp, int Striking_power) {
		this.init(e, col, row, d, hp);	
		this.Striking_power = Striking_power;
		Keys = new ArrayList<>();
		Foods = new ArrayList<>();
		Treasures = new ArrayList<>();
		Weapons = new ArrayList<>();
		Beans= new ArrayList<>();
	}

	@Override
	public int Hp() {
		return hp;
	}


	@Override
	public Environement Env() {
		return env;
	}

	@Override
	public int Col() {
	
		return col;
	}

	@Override
	public int Row() {
	
		return row;
	}

	@Override
	public Dir Face() {
		
		return dir;
	}
	@Override
	public void init(Environement e, int col, int row, Dir d)
			throws Error {
		throw new Error("you should use the other init methode and specify hp");
		
	}

	@Override
	public void Forward() throws PreConditionError, PostConditionError {
		
		Cell nature ;
		if( Face() == Dir.N) { 
			
			nature = null;
			if ( Row()+1 < Env().height() ) { nature =Env().cellNature(Col(), Row()+1); } else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT) && Env().CellContent(col, row+1)== null ) {
				this.row++; Env().setCellContent(col, row-1,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Row()-1 >= 0) { nature =Env().cellNature(Col(), Row()-1); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT) && Env().CellContent(col, row-1)== null ) {
			this.row--; Env().setCellContent(col, row+1,null); 
			Env().setCellContent(col, row, this);
			return;
			}
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Col()+1 < Env().width() ) { nature =Env().cellNature(Col()+1, Row()); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col+1, row)== null ) {
				this.col++; Env().setCellContent(col-1, row,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Col()-1 >= 0 ) { nature = Env().cellNature(Col()-1, Row());} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col-1, row)== null ) {
				this.col--; Env().setCellContent(col+1, row,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
	
	}

	@Override
	public void Backward() throws PreConditionError, PostConditionError {
		
		Cell nature ;
		if( Face() == Dir.N) { 
			
			nature = null;
			if ( Row()-1 >= 0 ) { nature =Env().cellNature(Col(), Row()-1); } else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col, row-1)== null ) {
				this.row--; Env().setCellContent(col, row+1,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Row()+1 < Env().height()) { nature =Env().cellNature(Col(), Row()+1); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col, row+1)== null ) {
			this.row++; Env().setCellContent(col, row-1,null); 
			Env().setCellContent(col, row, this);
			return;
			}  
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Col()-1 >= 0 ) { nature =Env().cellNature(Col()-1, Row()); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col-1, row)== null ) {
				this.col--; Env().setCellContent(col+1, row,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Col()+1 < Env().width() ) { nature = Env().cellNature(Col()+1, Row());} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col+1, row)== null ) {
				this.col++; Env().setCellContent(col-1, row,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;	
		} 
		
		
	}

	@Override
	public void TurnL() {
		
		if(Face()== Dir.N) { dir = Dir.W ; return; }
		if(Face()== Dir.S) { dir = Dir.E ; return; }
		if(Face()== Dir.E) { dir = Dir.N ; return; }
		if(Face()== Dir.W) { dir = Dir.S ; return; }
		
	}

	@Override
	public void TurnR() {
		
		if(Face()== Dir.N) { dir = Dir.E ; return; }
		if(Face()== Dir.S) { dir = Dir.W ; return; }
		if(Face()== Dir.E) { dir = Dir.S ; return; }
		if(Face()== Dir.W) { dir = Dir.N ; return; }	
		
	}

	@Override
	public void StrafeL() throws PreConditionError, PostConditionError {
		
		Cell nature ;
		if( Face() == Dir.N) { 
			
			nature = null;
			if ( Col()-1 >= 0 ) { nature =Env().cellNature(Col()-1, Row()); } else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col-1, row)== null ) {
				this.col--; Env().setCellContent(col+1, row,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Col()+1 < Env().width()) { nature =Env().cellNature(Col()+1, Row()); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col+1, row)== null ) {
			this.col++; Env().setCellContent(col-1, row,null); 
			Env().setCellContent(col, row, this);
			return;
			} 
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Row()+1 < Env().height() ) { nature =Env().cellNature(Col(), Row()+1); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col, row+1)== null ) {
				this.row++; Env().setCellContent(col, row-1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Row()-1 >= 0 ) { nature = Env().cellNature(Col(), Row()-1);} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DWO  || nature == Cell.IN || nature == Cell.OUT) && Env().CellContent(col, row-1)== null ) {
				this.row--; Env().setCellContent(col, row+1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			} 

	}

	@Override
	public void StrafeR() throws PreConditionError, PostConditionError {
		
		Cell nature ;
		if( Face() == Dir.N) { 
			
			nature = null;
			if ( Col()+1 < Env().width() ) { nature =Env().cellNature(Col()+1, Row()); } else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col+1, row)== null ) {
				this.col++; Env().setCellContent(col-1, row,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Col()-1 >= 0 ) { nature =Env().cellNature(Col()-1, Row()); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DNO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col-1, row)== null ) {
			this.col--; Env().setCellContent(col+1, row,null); 
			Env().setCellContent(col, row, this);
			return;
			} 
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Row()-1 >= 0) { nature =Env().cellNature(Col(), Row()-1); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col, row-1)== null ) {
				this.row--; Env().setCellContent(col, row+1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Row()+1 < Env().height() ) { nature = Env().cellNature(Col(), Row()+1);} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DWO || nature == Cell.IN || nature == Cell.OUT)  && Env().CellContent(col, row+1)== null ) {
				this.row++; Env().setCellContent(col, row-1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			}
		 
	}

	@Override
	public Command LastCom() { // a méditer peut on stoquer plusieurs cmds a la fois ? ou les saisir au fur et a mesure ?
							   //=> j'ai choisi de stoquer une a la fois c'est plus facile de gérer la coherence du jeu 
							  //ansi le joueur va saisir ses commands au fur et a mésure du jeu et une seule a la fois 
		Command cmd = last_command;
		last_command=null;
		return cmd;
	}

	@Override
	public Containable Content(int col, int row) throws PreConditionError {
		
		if(Face()==Dir.N) {  
			if (Env().height()<= (Row()+row) || (Row()+row) < 0 || Env().width() <= Col()+col || Col()+col < 0)
			return null;	
			return Env().CellContent(Col()+col, Row()+row);
		}
	
		if(Face()==Dir.S) { 
			if (Env().height()<= (Row()-row) || (Row()-row) < 0 || Env().width() <= Col()-col || Col()-col < 0)
			return null;
			return Env().CellContent(Col()-col, Row()-row);
			}
		
		if(Face()==Dir.E) {  
			if (Env().height()<= (Row()-col) || (Row()-col) < 0 || Env().width() <= Col()+row || Col()+row < 0)
			return null;
			return Env().CellContent(Col()+row,Row()-col );
		}
		
		if(Face()==Dir.W) {  
		if (Env().height()<= (Row()-col) || (Row()-col) < 0 || Env().width() <= Col()-row || Col()-row < 0)
		return null;
		return Env().CellContent(Col()-row, Row()-col);
		}
		return null;
	}
	
	@Override
	public Cell Nature(int col, int row) throws PreConditionError {
				
		if(Face()==Dir.N) {  
			if (Env().height()<= (Row()+row) || (Row()+row) < 0 || Env().width() <= Col()+col || Col()+col < 0)
			return Cell.WLL;
			return Env().cellNature(Col()+col, Row()+row);
		}
		if(Face()==Dir.S) { 
			if (Env().height()<= (Row()-row) || (Row()-row) < 0 || Env().width() <= Col()-col || Col()-col < 0)
			return Cell.WLL;
			return Env().cellNature(Col()-col, Row()-row);
			}
		
		if(Face()==Dir.E) {  
			if (Env().height()<= (Row()-col) || (Row()-col) < 0 || Env().width() <= Col()+row || Col()+row < 0)
			return Cell.WLL;
			return Env().cellNature(Col()+row,Row()-col );
		}
		
		if(Face()==Dir.W) {  
		if (Env().height()<= (Row()-col) || (Row()-col) < 0 || Env().width() <= Col()-row || Col()-row < 0)
		return Cell.WLL;
		return Env().cellNature(Col()-row, Row()-col);
		}
		return null;
	}
	
	
	
	@Override
	public boolean Viewable(int col, int row) throws PreConditionError {
	
		if(col >= -1 && col <= 1 && row >=-1 && row <= 0) { return false;}
		Cell nature;
	
		if(col==-1 && row == 2) {
		
		nature = Nature(-1,1);  
		if ( nature != Cell.WLL && nature != Cell.DWC && nature != Cell.DNC) return true;
		return false;
		
		}
		
		if(col==0 && row == 2) {
			
			nature = Nature(col,1);  
			if ( nature != Cell.WLL && nature != Cell.DWC && nature != Cell.DNC) return true;
			return false;
			
		}
		
		if(col==1 && row == 2) {
			
			nature = Nature(1,1);  
			if ( nature != Cell.WLL && nature != Cell.DWC && nature != Cell.DNC) return true;
			return false;
			
		}
		
		if(col==-1 && row == 3) {
			
			nature = Nature(-1,2);  
			if ( nature != Cell.WLL && nature != Cell.DWC && nature != Cell.DNC && Viewable(-1, 2)) return true;
			return false;
			
		}
		
		if(col==0 && row == 3) {
			
			nature = Nature(0,2);  
			if ( nature != Cell.WLL && nature != Cell.DWC && nature != Cell.DNC && Viewable(0, 2)) return true;
			return false;
			
		}
		
		if(col==1 && row == 3) {
			
			nature = Nature(1,2);  
			if ( nature != Cell.WLL && nature != Cell.DWC && nature != Cell.DNC && Viewable(1, 2)) return true;
			return false;
			
		}
		
		
		
		return true;
	}

	
	@Override
	public void AddCommad(Command c) {
		last_command=c;
		
	}
	@Override
	public void SetHp(int hp) {
		this.hp=hp;
	}

	@Override
	public int Striking_Power() {
		return Striking_power;
	}
	@Override
	public void Increase_Striking_Power(int p) {
		
		Striking_power+=p;
		
	}
	@Override
	public void Attack() {
		
		Containable m = null;
		Dir d = Face();
		Cow c ;
		if(d == Dir.N) { if(Row()+1 < Env().height()) m = Env().CellContent(Col(), Row()+1); }else
		if(d == Dir.S) {if(Row()-1 >= 0)             m = Env().CellContent(Col(), Row()-1);  }else
		if(d == Dir.E) {if(Col()+1 < Env().width())  m = Env().CellContent(Col()+1, Row());  }else
		if(d == Dir.W) {if(Col()-1>= 0)				m = Env().CellContent(Col()-1, Row());}
		
		if(m!= null && m instanceof Cow) { c = ((Cow)m); c.SetHp(c.Hp()-Striking_Power()); }   
	
		
	}
	@Override
	public void Arrow() {
		boolean b = false;
		Arrow arw = null;
		Weapon wep= null;
		Cow c;
		
		for(Weapon w : Weapons) {
			
			if(w instanceof Arrow) { wep =w;  arw = (Arrow)w ; b =true; break;}
			
		}
		
		if(!b) return ;
		
		if(Content(0,1) instanceof Cow) {  c = (Cow)Content(0,1); c.SetHp(c.Hp()-arw.Striking_power()); Weapons.remove(wep); return;  }
		if(Viewable(0,2) && Content(0,2) instanceof Cow ) {   c = (Cow)Content(0,2); c.SetHp(c.Hp()-arw.Striking_power());   Weapons.remove(wep); return;}; 
		if(Viewable(0,3) && Content(0,3) instanceof Cow ) {   c = (Cow)Content(0,3); c.SetHp(c.Hp()-arw.Striking_power());  Weapons.remove(wep); return;}; 
		
		
	}
	@Override
	public void Eat() {
		
		if(Hp()==Max_Hp() || Foods.size()==0 ) return ; 
		Food f = Foods.remove(0);
		int x = Hp()+f.Hp_Value();
		SetHp( ( x >= Max_Hp())? Max_Hp(): x);   
		
		
	}
	@Override
	public void OpenDoor() {
		
		if(Nature(0,1) != Cell.DNC && Nature(0,1) != Cell.DWC) return ; 
		Dir d = Face();
		
		if(d== Dir.N) {
			if(Nature(0,1) == Cell.DNC) return ; 
			if(Content(0, 1) == null) { Env().openDoor(col, row+1); return ; } 
			if(! Has_Key()) return ;
			env.setCellContent(col, row+1, null); 
			Env().openDoor(col, row+1);
		}
		
		if(d== Dir.S) {
			if(Nature(0,1) == Cell.DNC) return ; 
			if(Content(0, 1) == null) { Env().openDoor(col, row-1); return ; } 
			if(! Has_Key()) return ;
			env.setCellContent(col, row-1, null); 
			Env().openDoor(col, row-1);
		}
		
		if(d== Dir.E) {
			if(Nature(0,1) == Cell.DWC) return ; 
			if(Content(0, 1) == null) { Env().openDoor(col+1, row); return ; } 
			if(! Has_Key()) return ;
			env.setCellContent(col+1, row, null); 
			Env().openDoor(col+1, row);
		}
		
		if(d== Dir.W) {
			if(Nature(0,1) == Cell.DWC) return ; 
			if(Content(0, 1) == null) { Env().openDoor(col-1, row); return ; } 
			if(! Has_Key()) return ;
			env.setCellContent(col-1, row, null); 
			Env().openDoor(col-1, row);
		}
		
	}
	@Override
	public void CloseDoor() {
		

		if(Nature(0,1) != Cell.DNO && Nature(0,1) != Cell.DWO) return ; 
		if(Content(0, 1) != null) return;
		
		Dir d = Face();
		
		if(d== Dir.N) {
			 if(Nature(0,1) == Cell.DNO) return ; 
			 Env().closeDoor(col, row+1); 
			 return; 
			
		}
		
		if(d== Dir.S) {
			 if(Nature(0,1) == Cell.DNO) return ; 
			 Env().closeDoor(col, row-1); 
			 return; 
			
		}
		
		if(d== Dir.E) {
			 if(Nature(0,1) == Cell.DWO) return ; 
			 Env().closeDoor(col+1, row); 
			 return; 
			
		}
		
		if(d== Dir.W) {
			 if(Nature(0,1) == Cell.DWO) return ; 
			 Env().closeDoor(col-1, row); 
			 return; 
			
		}
		
		
		
		
		
		
		
	}
	@Override
	public int Nb_Arrows() {
		
		int x=0;
		for(Weapon w : Weapons) {
		
			if(w instanceof Arrow) { x++;}
			
		}
		return x;
	}
	@Override
	public List<Weapon> Weapons() {
		return new ArrayList<Weapon>(Weapons); 
	}
	@Override
	public int Max_Hp() {
		return Max_Hp; 
	}
	@Override
	public int Nb_Foods() {
		return Foods.size();
	}
	@Override
	public List<Food> Foods() {
		return new ArrayList<Food>(Foods); 
	}
	@Override
	public boolean Has_Key() {
		
		
		if (Keys.size()==0) return false;
		Dir d = Face();
		int col=0,row=0;
		if(d == Dir.N) { col = Col();   row = Row()+1; }else 
		if(d == Dir.S) { col = Col();   row = Row()-1; }else
		if(d == Dir.E) { col = Col()+1; row = Row(); }else
		if(d == Dir.W) { col = Col()-1; row = Row(); }
		
		for(Key k :Keys) if (k.Door_col()==col && k.Door_row()==row) return true; 
		
		return false;
	}
	@Override
	public void Take() {
		
		Containable c = Content(0, 1);
		Cell nature = Nature(0,1);
		if ( c== null || !(c instanceof IMob) || nature == Cell.DWC || nature == Cell.DNC) return; // on évite de prendre une clé qui est dans une porte fermée.
		
		if(c instanceof Key) { Keys.add((Key)c); }else
		if(c instanceof Treasure) { Treasures.add((Treasure)c); env.setCellContent(((Treasure)c).col(), ((Treasure)c).row(), null); }else
		if(c instanceof Weapon) { Weapons.add((Weapon)c); }else  
		if(c instanceof Food) { Foods.add((Food)c); }else 
		return;
		
		Dir d = Face();
		
		if(d == Dir.N) { Env().setCellContent(col, row+1, null); }else 
		if(d == Dir.S) { Env().setCellContent(col, row-1, null); }else
		if(d == Dir.E) { Env().setCellContent(col+1, row, null); }else
		if(d == Dir.W) { Env().setCellContent(col-1, row, null); }
		
		
		
	}
	@Override
	public void Add_Key(Key k) {
		Keys.add(k);
	}
	@Override
	public List<Treasure> Treasures() {
	
		return new ArrayList<Treasure>(Treasures);
	}
	@Override
	public List<Key> Keys() {
		
		return new ArrayList<Key>(Keys);
	}
	@Override
	public void Add_Food(Food f) {
		Foods.add(f);
		
	}
	@Override
	public void Add_Weapon(Weapon w) {
		Weapons.add(w);
	}
	@Override
	public void UseMagicBeans() {
		if(Beans.size()==0 ) return ; 
		this.Increase_Striking_Power(Beans.remove(0).Striking_power_bonus());
		
	}
	@Override
	public void Add_Beans(MagicBeans b) {
		Beans.add(b);
		
	}
	@Override
	public List<MagicBeans> Beans() {
		return new ArrayList<>(Beans);
	}
    public String getViewable() {
    	Player p1=this;
    	String res=null;
    	Containable b;
    	for(int i=-1; i< 2; i++)
    		for(int j=3; j> 0; j--)
    		{
    			if(p1.Viewable(i, j)){	res+=p1.Nature(-1, 3).toString()+":"+
    		     (((b=p1.Content(-1, 3))==null)?"Noc":
    		    	 (b instanceof Cow)? b+"|Hp: "+((Cow)b).Hp():b)+"  ";}
    			else { res+="?";}
    			res+="/";
    		}
    	
		
		return res;

    }
}
