package servicesImplementations;

import enumeration.Cell;
import enumeration.Dir;
import errors.*;

import services.Environement;
import services.Mob;

public class MobImpl implements Mob{

	private Environement env;
	private int col;
	private int row;
	private Dir dir; 
	
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
	public void init(Environement e, int col, int row, Dir d) throws PreConditionError, PostConditionError {
		
		this.env=e;
		this.col=col;
		this.row=row;
		this.dir=d;
		e.setCellContent(col, row, this);
		
	}
	

	@Override
	public void Forward() throws PreConditionError, PostConditionError {
		
		Cell nature ;
		if( Face() == Dir.N) { 
			
			nature = null;
			if ( Row()+1 < Env().height() ) { nature =Env().cellNature(Col(), Row()+1); } else { return ;}
		
			if(  ( nature == Cell.EMP || nature == Cell.DWO)  && Env().CellContent(col, row+1)== null ) {
				this.row++; Env().setCellContent(col, row-1,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Row()-1 >= 0) { nature =Env().cellNature(Col(), Row()-1); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DWO) && Env().CellContent(col, row-1)== null ) {
			this.row--; Env().setCellContent(col, row+1,null); 
			Env().setCellContent(col, row, this);
			return;
			} 
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Col()+1 < Env().width() ) { nature = Env().cellNature(Col()+1, Row()); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DNO) && Env().CellContent(col+1, row)== null ) {
				this.col++; Env().setCellContent(col-1, row,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
		} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Col()-1 >= 0 ) { nature = Env().cellNature(Col()-1, Row());} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO) && Env().CellContent(col-1, row)== null ) {
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
		
			if(  (nature == Cell.EMP || nature == Cell.DWO)  && Env().CellContent(col, row-1)== null ) {
				this.row--; Env().setCellContent(col, row+1,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Row()+1 < Env().height()) { nature =Env().cellNature(Col(), Row()+1); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DWO) && Env().CellContent(col, row+1)== null ) {
			this.row++; Env().setCellContent(col, row-1,null); 
			Env().setCellContent(col, row, this);
			return;
			} 
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Col()-1 >= 0 ) { nature = Env().cellNature(Col()-1, Row()); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DNO) && Env().CellContent(col-1, row)== null ) {
				this.col--; Env().setCellContent(col+1, row,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Col()+1 < Env().width() ) { nature = Env().cellNature(Col()+1, Row());} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO) && Env().CellContent(col+1, row)== null ) {
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
		
			if(  (nature == Cell.EMP || nature == Cell.DNO)  && Env().CellContent(col-1, row)== null ) {
				this.col--; Env().setCellContent(col+1, row,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Col()+1 < Env().width()) { nature =Env().cellNature(Col()+1, Row()); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DNO) && Env().CellContent(col+1, row)== null ) {
			this.col++; Env().setCellContent(col-1, row,null); 
			Env().setCellContent(col, row, this);
			return;
			} 
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Row()+1 < Env().height() ) { nature = Env().cellNature(Col(), Row()+1); } else { return ;}
			
			if(  ( nature == Cell.EMP || nature == Cell.DWO ) && Env().CellContent(col, row+1)== null ) {
				this.row++; Env().setCellContent(col, row-1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Row()-1 >= 0 ) { nature = Env().cellNature(Col(), Row()-1);} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DWO) && Env().CellContent(col, row-1)== null ) {
				this.row--; Env().setCellContent(col, row+1,null); 
				Env().setCellContent(col, row, this);
				return;
				}
			return;
			} 

	}

	@Override
	public void StrafeR() throws PreConditionError, PostConditionError {
		
		Cell nature ;
		if( Face() == Dir.N) {  
			
			nature = null;
			if ( Col()+1 < Env().width() ) { nature =Env().cellNature(Col()+1, Row()); } else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DNO)  && Env().CellContent(col+1, row)== null ) {
				this.col++; Env().setCellContent(col-1, row,null); 
				Env().setCellContent(col, row, this);
				return;
			}
			return;
		}
		
		
		if( Face() == Dir.S) { 
			
			
			nature = null;
			if ( Col()-1 >= 0 ) { nature =Env().cellNature(Col()-1, Row()); } else { return ;}
			
			if( (nature == Cell.EMP || nature == Cell.DNO) && Env().CellContent(col-1, row)== null ) {
			this.col--; Env().setCellContent(col+1, row,null); 
			Env().setCellContent(col, row, this);
			return;
			} 
			return;
		}
		
		if( Face() == Dir.E) { 
			
			nature = null;
			if ( Row()-1 >= 0) { nature = Env().cellNature(Col(), Row()-1); } else { return ;}
			
			if(  (nature == Cell.EMP || nature == Cell.DWO) && Env().CellContent(col, row-1)== null ) {
				this.row--; Env().setCellContent(col, row+1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			} 
		if( Face() == Dir.W) { 
			 
			nature = null;
			if ( Row()+1 < Env().height() ) { nature = Env().cellNature(Col(), Row()+1);} else { return ;}
		
			if(  (nature == Cell.EMP || nature == Cell.DWO) && Env().CellContent(col, row+1)== null ) {
				this.row++; Env().setCellContent(col, row-1,null); 
				Env().setCellContent(col, row, this);
				return;
				} 
			return;
			}
		 
	}

	
	
	
	
	
	
	
	
}
