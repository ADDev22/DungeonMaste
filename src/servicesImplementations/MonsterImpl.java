package servicesImplementations;

import java.util.Random;

import enumeration.Cell;
import enumeration.Dir;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Containable;
import services.Environement;
import services.Monster;
import services.Player;;

public class MonsterImpl extends CowImpl implements Monster {

	protected int Striking_power = 1 ; // by default if super init method is used directly. 

	@Override
	public String toString() {
		return "Monster";
	}
	
	@Override
	public void Attack() {
		Containable m = null;
		Dir d = Face();
		Player p ;
		if(d == Dir.N) m = Env().CellContent(Col(), Row()+1);else
		if(d == Dir.S) m = Env().CellContent(Col(), Row()-1);else
		if(d == Dir.E) m = Env().CellContent(Col()+1, Row());else
		if(d == Dir.W) m = Env().CellContent(Col()-1, Row());
		
		if(m!= null && m instanceof Player) { p = ((Player)m); p.SetHp(p.Hp()-Striking_Power()); }   
	}

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp, int Striking_power) {
		super.init(e, col, row, d, hp);
		this.Striking_power=Striking_power;
	}

	@Override
	public int Striking_Power() {
		return Striking_power;
	}

	@Override
	public void Step() throws PreConditionError, PostConditionError {
		
		Cell nature=null;
		Containable c = null;
		int col = Col();
		
		
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
		
	}
	
	
	
}
