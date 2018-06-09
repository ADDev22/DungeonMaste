package contracts;

import java.util.Random;

import decorateur.CowDecorator;
import enumeration.Cell;
import enumeration.Dir;
import errors.*;
import services.*;

public class CowContract extends CowDecorator{

	

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
		{  // doit on accepter de le placer dans une entrée ou sortie ? => j'ai choisi que non sauf si c'est un player.
		throw new InvariantError("broken invariants => Col: "+Col()+" Row: "+Row()+" CellNature: "+Env().cellNature(Col(), Row()));
		}
		
		
	}
	
	
	 public CowContract(Cow delegate) {
		
		 super(delegate);
	}
	
	@Override
	public int Hp() {
		
		return super.Hp();
	}

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp) throws PreConditionError,PostConditionError, InvariantError {
		
		if(e == null || d ==null || !(0 <= col && col < e.width() && 0 <= row && row < e.height() && e.CellContent(col,row) == null) || hp >4 || hp < 3) {
			throw new PreConditionError("Invalid preCondition before init");
		}
		
		super.init(e, col, row, d,hp);
		
		this.CheckInvariants();
		
		if(Col() != col || Row() != row && Env() != e || Face() != d || Env().CellContent(col, row) != delegate ||  Hp() != hp) {
		throw new PostConditionError("Invalid post conditions after init\n");	
		}
	
	}

	@Override
	public void Step() throws InvariantError, PostConditionError, PreConditionError {
		/* capture */
		int col = Col();
		int row = Row();
		CheckInvariants();
		
		Random r = new Random();
		int action = r.nextInt(6);
		if(action == 0) { this.Forward();  }else
		if(action == 1) { this.Backward(); }else
		if(action == 2) { this.StrafeL();  }else
		if(action == 3) { this.StrafeR();  }else
		if(action == 4) { this.TurnL();    }else
		if(action == 5) { this.TurnR();    }
		
		CheckInvariants();
		/* post */
		if(Col() != (col-1) && Col() != (col+1) && Col() != col ) { throw new PostConditionError("Invalid post conditions for step() => the cow moved for more than a step (1)");}
		if(Row() != (row-1) && Row() != (row+1) && Row() != row ) { throw new PostConditionError("Invalid post conditions for step() => the cow moved for more than a step (2)");}
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
