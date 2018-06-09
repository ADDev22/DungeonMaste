package contracts;

import decorateur.MobDecorator;
import enumeration.Cell;
import enumeration.Dir;
import errors.*;
import services.Containable;
import services.Environement;
import services.Mob;

public class MobContract extends MobDecorator{

	
	
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
	
	
	public MobContract(Mob delegate) {
		super(delegate);
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
		
		if(Col() != col || Row() != row && Env() != e || Face() != d || Env().CellContent(col, row) != delegate) {
		throw new PostConditionError("Invalid post conditions after init\n");	
		}
		
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
