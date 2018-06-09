package contracts;

import java.util.Random;

import decorateur.MonsterDecorator;
import enumeration.Cell;
import enumeration.Dir;
import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Containable;
import services.Environement;
import services.IMob;
import services.Monster;
import services.Player;

public class MonsterContract extends MonsterDecorator  {

	public MonsterContract(Monster delegate) {
		super(delegate);
	}
	
	
	
	
	@Override
	public void Set_Spoil(IMob spoil) {
		if(spoil==null) throw new PreConditionError("SPOIL==NULL");
		CheckInvariants();
		super.Set_Spoil(spoil);
		CheckInvariants();
		if(Drop_Spoil() != spoil) throw new PostConditionError("Drop_Spoil() != spoil)");
	}




	@Override
	public IMob Drop_Spoil() {
		
		return super.Drop_Spoil();
	}




	public void CheckInvariants() throws InvariantError, PreConditionError {

		if( ! ( 0 <= Col() && Col()< Env().width()) 
				|| !(0<= Row() && Row() < Env().height()) 
				|| Env().cellNature(Col(), Row()) == Cell.WLL 
				|| Env().cellNature(Col(), Row()) == Cell.DNC 
				|| Env().cellNature(Col(), Row()) == Cell.DWC
				|| Env().cellNature(Col(), Row()) == Cell.IN 
				|| Env().cellNature(Col(), Row()) == Cell.OUT)
		{
		throw new InvariantError("broken invariants => Col: "+Col()+" Row: "+Row()+" CellNature: "+Env().cellNature(Col(), Row()));
		}
		
		
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
	public void init(Environement e, int col, int row, Dir d)  {

		if(e == null || d ==null || !(0 <= col && col < e.width() && 0 <= row && row < e.height() && e.CellContent(col,row) == null)) {
			throw new PreConditionError("Invalid precondition before init");
		}
		
		super.init(e, col, row, d);
		
		this.CheckInvariants();
		
		if(Col() != col || Row() != row && Env() != e || Face() != d || Env().CellContent(col, row) != delegate || Striking_Power() != 1) {
		throw new PostConditionError("Invalid post conditions after init\n");	
		}
		
	}
	
	@Override
	public void init(Environement e, int col, int row, Dir d, int hp) throws PreConditionError, PostConditionError, InvariantError {

		if(e == null || d ==null || !(0 <= col && col < e.width() && 0 <= row && row < e.height() && e.CellContent(col,row) == null) || hp >4 || hp < 3) {
			throw new PreConditionError("Invalid preCondition before init");
		}
		
		super.init(e, col, row, d,hp);
		
		this.CheckInvariants();
		
		if(Col() != col || Row() != row && Env() != e || Face() != d || Env().CellContent(col, row) != delegate ||  Hp() != hp || Striking_Power() != 1) {
		throw new PostConditionError("Invalid post conditions after init\n");	
		}
	}
	

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp, int Striking_power) {
		

		if(e == null || d ==null || !(0 <= col && col < e.width() && 0 <= row && row < e.height() && e.CellContent(col,row) == null) || 
				!(Striking_power>0 && Striking_power<=10)) {
			throw new PreConditionError("Invalid precondition before init");
		}
		
		super.init(e, col, row, d, hp, Striking_power);
		
		this.CheckInvariants();
		
		if(Col() != col || Row() != row && Env() != e || Face() != d || Env().CellContent(col, row) != delegate || Striking_Power() != Striking_power) {
		throw new PostConditionError("Invalid post conditions after init\n");	
		}

	}

	@Override
	public int Striking_Power() {
		
		return super.Striking_Power();
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
			if(content != null && content instanceof Player) { b2 = true; hp = ((Player)content).Hp(); }
		}
		else
		if(d== Dir.S) {
			
			b1= ( row-1 >= 0);
			if(b1) content = Env().CellContent(col, row-1);
			if(content != null && content instanceof Player) { b2 = true; hp = ((Player)content).Hp(); }
		}
		else
		if(d== Dir.E) {
			
			b1= ( col+1 < Env().width());
			if(b1) content = Env().CellContent(col+1, row);
			if(content != null && content instanceof Player) { b2 = true; hp = ((Player)content).Hp(); }
		}
		else
		if(d== Dir.W) {
			
			b1= ( col-1 >= 0);
			if(b1) content = Env().CellContent(col-1, row);
			if(content != null && content instanceof Player) { b2 = true; hp = ((Player)content).Hp(); }
		}
		
		this.CheckInvariants();
		super.Attack();
		this.CheckInvariants();
		
		/*Posts*/
		
		if( b1 && b2 && hp >0 ) {
	
			if ( ((Player)content).Hp() != (hp-Striking_Power()) ) { throw new PostConditionError("this attack did not affect enemy's hp correctly"); }
		}
		
	
		
		if(Face() != d ) { throw new PostConditionError("direction has changed during attack");}
		if( Col() != col || Row() != row || Env().cellNature(col, row) != nature || Env().CellContent(col, row) != c || Env().CellContent(col, row) != delegate || my_hp != Hp()) {
			throw new PostConditionError("invalid post conds for Attack()");
		}
	}

	@Override
	public void Step() {
		/* capture */
		int col = Col();
		int row = Row();
		CheckInvariants();
		Cell nature=null;
		Containable c = null;
		
		boolean b= false;
		
		if(Face() == Dir.N) {
		
			
			if(Row()+1 < Env().height()) {
			
				if ( Env().CellContent(Col(), Row()+1) instanceof Player ) { this.Attack(); b= true;} 
			}
			
			if(Col()-1 >= 0) { 
				if(!b)
				if ( Env().CellContent(Col()-1, Row()) instanceof Player ) { this.TurnL(); b= true;}
			}
			
			if(Col()+1 < Env().width()) {
				if(!b)
				if ( Env().CellContent(Col()+1, Row()) instanceof Player ) { this.TurnR(); b= true;}
			}
			
			if( !b && Col()-1 >=0 && Row()+1 < Env().height()) {
				nature = Env().cellNature(Col()-1, Row());
				c = Env().CellContent(Col()-1, Row());
				
			 if ( Env().CellContent(Col()-1, Row()+1) instanceof Player ) {
					
				if(nature == Cell.EMP && c==null) {
					this.StrafeL(); b= true;
				}else {
					nature = Env().cellNature(Col(), Row()+1);
					c=Env().CellContent(Col(), Row()+1);
					if ( c==null && nature == Cell.EMP ) { this.Forward(); b =true ;}
				}
			 }
			}
			
			if( !b && Col()+1 < Env().width() && Row()+1 < Env().height() ) {
					nature = Env().cellNature(Col()+1, Row());
					c = Env().CellContent(Col()+1, Row());
					
					if ( Env().CellContent(Col()+1, Row()+1) instanceof Player) {
						
						if(nature == Cell.EMP && c==null) {
							this.StrafeR(); b= true; 
						}
						else {
							
							nature = Env().cellNature(Col(), Row()+1);
							c=Env().CellContent(Col(), Row()+1);
							if ( c==null && nature == Cell.EMP ) { this.Forward(); b =true ;}
						}
					}
			}	
			
			
			if(!b && Row()+1 < Env().height() && ( Env().cellNature(Col(), Row()+1)== Cell.EMP || Env().cellNature(Col(), Row()+1)== Cell.DWO) && Env().CellContent(Col(), Row()+1)==null ) {
					if(Row()+2 < Env().height()) {
						if(Env().CellContent(col, Row()+2) instanceof Player) { this.Forward(); b= true;}
						else
						if(Env().cellNature(Col(), Row()+1)== Cell.EMP && Col()-1 >= 0 && Env().cellNature(Col()-1, Row()+1)!= Cell.WLL && Env().CellContent(col-1, Row()+2) instanceof Player)	{ this.Forward();b= true;}
						else
						if(Env().cellNature(Col(), Row()+1)== Cell.EMP && Col()+1 < Env().width() && Env().cellNature(Col()+1, Row()+1)!= Cell.WLL && Env().CellContent(col+1, Row()+2) instanceof Player){ this.Forward();b= true;}
					}
					
			}
			
			if(!b && Row()+2 < Env().height()) {
				if(Col()-1 >=0 ) {
					nature = Env().cellNature(Col()-1, Row());
					c = Env().CellContent(Col()-1, Row());
					if ( nature == Cell.EMP && c==null && Env().CellContent(Col()-1, Row()+1) == null  &&( Env().cellNature(Col()-1, Row()+1)==Cell.EMP || Env().cellNature(Col()-1, Row()+1)==Cell.DWO)) {
						
						if(Env().CellContent(col-1, Row()+2) instanceof Player) {this.StrafeL();b= true;}
					
			    }
			
				}
				else
				if(Col()+1 < Env().width() ) {
					nature = Env().cellNature(Col()+1, Row());
					c = Env().CellContent(Col()+1, Row());
					if ( nature == Cell.EMP && c==null && Env().CellContent(Col()+1, Row()+1) == null  &&( Env().cellNature(Col()+1, Row()+1)==Cell.EMP || Env().cellNature(Col()+1, Row()+1)==Cell.DWO)) {
						
						if( Env().CellContent(col+1, Row()+2) instanceof Player) {this.StrafeR();b= true;} 
					
				    }
			
				}
				
					
			}
		}
		else
			if(Face() == Dir.S) {
				
				if(Row()-1 >= 0) {
					
					if ( Env().CellContent(Col(), Row()-1) instanceof Player ) { this.Attack(); b= true;} 
				}

				if(Col()-1 >= 0) { 
					if(!b)
					if ( Env().CellContent(Col()-1, Row()) instanceof Player ) { this.TurnR();b= true; }
				}
				
				if(Col()+1 < Env().width()) {
					if(!b)
					if ( Env().CellContent(Col()+1, Row()) instanceof Player ) { this.TurnL();b= true; }
				}
				
				if(!b && Col()-1 >=0 && Row()-1 >= 0) {
					nature = Env().cellNature(Col()-1, Row());
					c = Env().CellContent(Col()-1, Row());

					if ( Env().CellContent(Col()-1, Row()-1) instanceof Player) {
						
						
						if(nature == Cell.EMP && c==null) {
							this.StrafeR(); b= true;
						}else {
							
							nature = Env().cellNature(Col(), Row()-1);
							c = Env().CellContent(Col(), Row()-1);
							if(nature == Cell.EMP && c==null) {
								this.Forward(); b= true;
						    }
						}	
					}
				}
				
				if( !b && Col()+1 < Env().width() && Row()-1 >= 0) {
						nature = Env().cellNature(Col()+1, Row());
						c = Env().CellContent(Col()+1, Row());
					
						if ( Env().CellContent(Col()+1, Row()-1) instanceof Player) {
							
							
							if(nature == Cell.EMP && c==null) {
								this.StrafeL(); b= true;
							}else {
								
								nature = Env().cellNature(Col(), Row()-1);
								c = Env().CellContent(Col(), Row()-1);
								if(nature == Cell.EMP && c==null) {
									this.Forward(); b= true;
							    }
							}
						}
							
				}	
				
				if(!b && Row()-1 >= 0 && ( Env().cellNature(Col(), Row()-1)== Cell.EMP || Env().cellNature(Col(), Row()-1)== Cell.DWO) && Env().CellContent(Col(), Row()-1)==null ) {
						if(Row()-2 >=0 ) {
							if(Env().CellContent(col, Row()-2) instanceof Player) { this.Forward();b= true;}
							else
							if(Env().cellNature(Col(), Row()-1)== Cell.EMP && Col()-1 >= 0 && Env().cellNature(Col()-1, Row()-1)!= Cell.WLL && Env().CellContent(col-1, Row()-2) instanceof Player)	{ this.Forward();b= true;}
							else
							if(Env().cellNature(Col(), Row()-1)== Cell.EMP && Col()+1 < Env().width() && Env().cellNature(Col()+1, Row()-1)!= Cell.WLL && Env().CellContent(col+1, Row()-2) instanceof Player){ this.Forward();b= true;}
						}
						
				}
				
				if(!b && Row()-2 >= 0 ) {
					if(Col()-1 >=0 ) {
						nature = Env().cellNature(Col()-1, Row());
						c = Env().CellContent(Col()-1, Row());
						if ( nature == Cell.EMP && c==null && Env().CellContent(Col()-1, Row()-1) == null  &&( Env().cellNature(Col()-1, Row()-1)==Cell.EMP || Env().cellNature(Col()-1, Row()-1)==Cell.DWO)) {
							
							if(Env().CellContent(col-1, Row()-2) instanceof Player) {this.StrafeR();b= true;}
						
					    }
				
					}
					else
					if(Col()+1 < Env().width() ) {
						nature = Env().cellNature(Col()+1, Row());
						c = Env().CellContent(Col()+1, Row());
						if ( nature == Cell.EMP && c==null && Env().CellContent(Col()+1, Row()-1) == null  &&( Env().cellNature(Col()+1, Row()-1)==Cell.EMP || Env().cellNature(Col()+1, Row()-1)==Cell.DWO)) {
							
							if( Env().CellContent(col+1, Row()-2) instanceof Player) {this.StrafeL();b= true;} 
						
					    }
				
					}
					
						
				}
			}	
			else
				if(Face() == Dir.E) {
					
					if(Col()+1 < Env().width()) {
						if ( Env().CellContent(Col()+1, Row()) instanceof Player ) { this.Attack(); b= true;} 
					}
				
					if(Row()-1 >= 0) {
						if(!b)
						if ( Env().CellContent(Col(), Row()-1) instanceof Player ) { this.TurnR(); b= true;}
					}
					
					if(Row()+1 < Env().height()) {
						if(!b)
						if ( Env().CellContent(Col(), Row()+1) instanceof Player ) { this.TurnL(); b= true;}
					}
					
					if(!b && Col()+1 < Env().width() && Row()+1 < Env().height()) {
						nature = Env().cellNature(Col(), Row()+1);
						c = Env().CellContent(Col(), Row()+1);
						if ( Env().CellContent(Col()+1, Row()+1) instanceof Player) {
						
							if(nature == Cell.EMP && c==null) {
								this.StrafeL();b= true; 
							}
							else {
								nature = Env().cellNature(Col()+1, Row());
								c = Env().CellContent(Col()+1, Row());
								if(nature == Cell.EMP && c==null) {
									this.Forward(); b=true;
								}
								
							}
						}
					}
					
					if(!b && Col()+1 < Env().width() && Row()-1 >= 0) {
							nature = Env().cellNature(Col(), Row()-1);
							c = Env().CellContent(Col(), Row()-1);
							if ( Env().CellContent(Col()+1, Row()-1) instanceof Player) {
							
								if(nature == Cell.EMP && c==null) {
									
									this.StrafeR(); b= true;	
								}
								else {
									
									nature = Env().cellNature(Col()+1, Row());
									c = Env().CellContent(Col()+1, Row());
									if(nature == Cell.EMP && c==null) {
										
										this.Forward(); b= true;
									}
								}
							}
					}	
					
					if(!b && Col()+1 < Env().width() && ( Env().cellNature(Col()+1, Row())== Cell.EMP || Env().cellNature(Col()+1, Row())== Cell.DNO) && Env().CellContent(Col()+1, Row())==null ) {
							if(Col()+2 < Env().width()) {
								if(Env().CellContent(col+2, Row()) instanceof Player) {  this.Forward();b= true;}
								else
								if(Env().cellNature(Col()+1, Row())== Cell.EMP && Row()-1 >= 0 && Env().cellNature(Col()+1, Row()-1)!= Cell.WLL && Env().CellContent(col+2, Row()-1) instanceof Player)	{ this.Forward();b= true;}
								else
								if(Env().cellNature(Col()+1, Row())== Cell.EMP && Row()+1 < Env().height() && Env().cellNature(Col()+1, Row()+1)!= Cell.WLL && Env().CellContent(col+2, Row()+1) instanceof Player){ this.Forward();b= true;}
							}
							
						}
				
					if(!b && Col()+2 < Env().width()) {
						if(Row()-1 >=0 ) {
							nature = Env().cellNature(Col(), Row()-1);
							c = Env().CellContent(Col(), Row()-1);
							if ( nature == Cell.EMP && c==null && Env().CellContent(Col()+1, Row()-1) == null  &&( Env().cellNature(Col()+1, Row()-1)==Cell.EMP || Env().cellNature(Col()+1, Row()-1)==Cell.DNO)) {
								
								if(Env().CellContent(col+2, Row()-1) instanceof Player) {this.StrafeR();b= true;}
							
						    }
					
						}
						else
						if(Row()+1 < Env().height() ) {
							nature = Env().cellNature(Col(), Row()+1);
							c = Env().CellContent(Col(), Row()+1);
							if ( nature == Cell.EMP && c==null && Env().CellContent(Col()+1, Row()+1) == null  &&( Env().cellNature(Col()+1, Row()+1)==Cell.EMP || Env().cellNature(Col()+1, Row()+1)==Cell.DNO)) {
								
							if( Env().CellContent(col+2, Row()+1) instanceof Player) {this.StrafeL();b= true;} 
							
						    }
					
						}
						
							
					}
				}		
				else
					if(Face() == Dir.W) {
						
						if(Col()-1 >= 0 ) {
							if ( Env().CellContent(Col()-1, Row()) instanceof Player ) { this.Attack();b= true; } 
						}
						
						if(Row()-1 >= 0) { 
							if(!b)
							if ( Env().CellContent(Col(), Row()-1) instanceof Player ) { this.TurnL(); b= true;}
						}
						
						if(Row()+1 < Env().height()) {
							if(!b)
							if ( Env().CellContent(Col(), Row()+1) instanceof Player ) { this.TurnR(); b= true;}
						}
						
						if(!b && Col()-1 >=0 && Row()+1 < Env().height()) {
							nature = Env().cellNature(Col(), Row()+1);
							c = Env().CellContent(Col(), Row()+1);
						
							if ( Env().CellContent(Col()-1, Row()+1) instanceof Player) {
							
								if(nature == Cell.EMP && c==null) {
									this.StrafeR();b= true; 
								}
								else {
									nature = Env().cellNature(Col()-1, Row());
									c = Env().CellContent(Col()-1, Row());
									if(nature == Cell.EMP && c==null) {
										this.Forward(); b=true;
									}
									
								}
							}
						}
						
						if(!b && Col()-1 >= 0 && Row()-1 >= 0) {
								nature = Env().cellNature(Col(), Row()-1);
								c = Env().CellContent(Col(), Row()-1);
								if ( Env().CellContent(Col()-1, Row()-1) instanceof Player) {
									
									if(nature == Cell.EMP && c==null) {
										this.StrafeL();b= true; 
									}
									else {
										nature = Env().cellNature(Col()-1, Row());
										c = Env().CellContent(Col()-1, Row());
										if(nature == Cell.EMP && c==null) {
											this.Forward(); b=true;
										}
										
									}
								}
						}	
						
						if(!b && Col()-1 >= 0 && ( Env().cellNature(Col()-1, Row())== Cell.EMP || Env().cellNature(Col()-1, Row())== Cell.DNO) && Env().CellContent(Col()-1, Row())==null ) {
								if(Col()-2 >= 0 ) {
									if(Env().CellContent(col-2, Row()) instanceof Player) { this.Forward();b= true;}
									else
									if(Env().cellNature(Col()-1, Row())== Cell.EMP && Row()-1 >= 0 && Env().cellNature(Col()-1, Row()-1)!= Cell.WLL && Env().CellContent(col-2, Row()-1) instanceof Player)	{ this.Forward();b= true;}
									else
									if(Env().cellNature(Col()-1, Row())== Cell.EMP && Row()+1 < Env().height() && Env().cellNature(Col()-1, Row()+1)!= Cell.WLL && Env().CellContent(col-2, Row()+1) instanceof Player){ this.Forward();b= true;}
								}
								
						}
						
						if(!b && Col()-2 >= 0 ) {
							if(Row()-1 >=0 ) {
								nature = Env().cellNature(Col(), Row()-1);
								c = Env().CellContent(Col(), Row()-1);
								if ( nature == Cell.EMP && c==null && Env().CellContent(Col()-1, Row()-1) == null  &&( Env().cellNature(Col()-1, Row()-1)==Cell.EMP || Env().cellNature(Col()-1, Row()-1)==Cell.DNO)) {
									
									if(Env().CellContent(col-2, Row()-1) instanceof Player) {this.StrafeL();b= true;}
								
							    }
						
							}
							else
							if(Row()+1 < Env().height() ) {
								nature = Env().cellNature(Col(), Row()+1);
								c = Env().CellContent(Col(), Row()+1);
								if ( nature == Cell.EMP && c==null && Env().CellContent(Col()-1, Row()+1) == null  &&( Env().cellNature(Col()-1, Row()+1)==Cell.EMP || Env().cellNature(Col()-1, Row()+1)==Cell.DNO)) {
									
								if( Env().CellContent(col-2, Row()+1) instanceof Player) {this.StrafeR();b= true;} 
								
							    }
						
							}
							
								
						}
					}		
					
		// decider s'il faut attaquer ou pas null 
		
		if(!b) {
		Random r = new Random();
		int action = r.nextInt(6);
		if(action == 0) { this.Forward();  }else
		if(action == 1) { this.Backward(); }else
		if(action == 2) { this.StrafeL();  }else
		if(action == 3) { this.StrafeR();  }else
		if(action == 4) { this.TurnL();    }else
		if(action == 5) { this.TurnR();    }
		}			
					
		CheckInvariants();
		/* post */
		if(Col() != (col-1) && Col() != (col+1) && Col() != col ) { throw new PostConditionError("Invalid post conditions for step() => the cow moved for more than a step (1)");}
		if(Row() != (row-1) && Row() != (row+1) && Row() != row ) { throw new PostConditionError("Invalid post conditions for step() => the cow moved for more than a step (2)");}
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
			
			if( row+1 < Env().height() && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( row+1 < Env().height() && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
		
		/**
		 *  \pre true
		 *  \post Face() @pré == N => Face()== W
		 *  	  Face() @pré == S => Face()== E
		 * 		  Face() @pré == E => Face()== N
		 *  	  Face() @pré == W => Face()== S
		 *  Env().CellNature() @ pré == Env().CellNature() && Env().CellContent(Row(),Col()) == Env().CellContent(Row(),Col())  @ pré ==So(this).   
		 * @throws PostConditionError 
		 *  	
		 */
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
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( row +1  < Env().height() && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( col+1 < Env().width() && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( col-1 >= 0 && (nature == Cell.EMP || nature == Cell.DNO) && content == null){
				
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
			
			if( row-1 >= 0 && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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
			
			if( row +1  < Env().height() && (nature == Cell.EMP || nature == Cell.DWO) && content == null){
				
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

	
	
	
	
	
	
	
	
	

}
