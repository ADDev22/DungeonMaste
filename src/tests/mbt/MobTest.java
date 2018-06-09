package tests.mbt;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import contracts.EnvironementContract;
import contracts.MobContract;
import enumeration.Cell;
import enumeration.Dir;
import errors.*;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.MobImpl;
import services.EditMap;
import services.Environement;
import services.Mob;
import contracts.EditMapContract;

public class MobTest {

	protected Mob mobile;
	protected Environement env;
	protected int col;
	protected int row;
	
	@Before
	public void beforeTests() {
		env = new EnvironementContract(new EnvironementImpl());
		col=0;
		row=0;
		try {
			EditMap map = new EditMapContract( new EditMapImpl() ) ;
			
			map.init(5,6);
			map.setNature(0, 0, Cell.IN);  map.setNature(1, 0, Cell.WLL);map.setNature(2, 0, Cell.EMP);map.setNature(3, 0, Cell.EMP);map.setNature(4, 0, Cell.WLL);
			map.setNature(0, 1, Cell.EMP); map.setNature(1, 1, Cell.DNO);map.setNature(2, 1, Cell.EMP);map.setNature(3, 1, Cell.EMP);map.setNature(4, 1, Cell.WLL);
			map.setNature(0, 2, Cell.WLL); map.setNature(1, 2, Cell.WLL);map.setNature(2, 2, Cell.WLL);map.setNature(3, 2, Cell.DWO);map.setNature(4, 2, Cell.WLL);
			map.setNature(0, 3, Cell.EMP); map.setNature(1, 3, Cell.EMP);map.setNature(2, 3, Cell.EMP);map.setNature(3, 3, Cell.EMP);map.setNature(4, 3, Cell.EMP);
			map.setNature(0, 4, Cell.EMP); map.setNature(1, 4, Cell.WLL);map.setNature(2, 4, Cell.DWO);map.setNature(3, 4, Cell.WLL);map.setNature(4, 4, Cell.WLL);
			map.setNature(0, 5, Cell.EMP); map.setNature(1, 5, Cell.WLL);map.setNature(2, 5, Cell.EMP);map.setNature(3, 5, Cell.EMP);map.setNature(4, 5, Cell.OUT);
			
			System.out.println("");
			for(int i=5;i>=0;i--) {
				System.out.println("");
				for(int j=0;j<5;j++) {
					
					System.out.print(map.cellNature(j, i)+" ");
			}
			}
			
			env.init(map);
		} catch (PreConditionError e) {
			assert(false);
		} catch (Exception e) {
			assert(false);
		}
	}; 

	@After
	public final void afterTests() {
		mobile=null;
	}
	
	
	@Test  // tester les pre, post et inv.  
	public void TestInitPositif(){
		
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		try {
			
			mobile.init(env, 0, 1, d);
			assertTrue(mobile.Row()==1);
			assertTrue(mobile.Col()==0);
			assertTrue(mobile.Face()==Dir.N);

		} catch (Exception e) {
			assertFalse(true);
		}
		return;
	}
	

	@Test
	public void TestInitNegatif(){
	
		col=1;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		Mob mobile_bis = new MobContract(new MobImpl());
		
		// Briser les precond en metant un mob dans un case EMP occupé par un autre mob. 
		try {
			mobile_bis.init(env, 0, 1, d);
			mobile.init(env, 0, 1, d);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser l'invriant en mettant le mob sur un case IN
		
		try {
			mobile.init(env, 0, 0, d);
		} catch (Error e) {
			if(e instanceof InvariantError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser les pré cond en mettant le mob dans un case hors MAP 
		try {
			mobile.init(env, -1, -1, d);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
	
		// Briser les pré cond en passant un env et/ou direction null
		try {
			mobile.init(null, 2, 0, null);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
				
		return;
		
	}
	
	
	
		   // pre : true (rien a tester)
	@Test  // post conds (+inv).
	public void TestTurnL(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.TurnL();
		assertTrue(mobile.Face()==Dir.W);
		mobile.TurnL();
		assertTrue(mobile.Face()==Dir.S);
		mobile.TurnL();
		assertTrue(mobile.Face()==Dir.E);
		mobile.TurnL();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==col && mobile.Row()==row);
		
	}
	
	
	
	
		// pre : true (rien a tester)
	@Test  // post conds (+inv).
	public void TestTurnR(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.TurnR();
		assertTrue(mobile.Face()==Dir.E);
		mobile.TurnR();
		assertTrue(mobile.Face()==Dir.S);
		mobile.TurnR();
		assertTrue(mobile.Face()==Dir.W);
		mobile.TurnR();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==col && mobile.Row()==row);
	}
	
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestForward(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.Forward();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==0);	
		assertTrue(mobile.Row()==1);
	
		mobile.TurnR();
		mobile.Forward();
		
		
		assertTrue(mobile.Face()==Dir.E);
		assertTrue(mobile.Col()==1);	
		assertTrue(mobile.Row()==1);
		
		
		
	}
		// ce test a pour but de tester le mouvenent d'un mob dans une case occupé par un autre mob.
		// pre : true (rien a tester)
		@Test  // post conds (+inv)
	public void TestCMD(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, 2, 0, d);
		//assertTrue(env.CellContent(2, 0)==mobile);
		Mob mobile_bis = new MobContract(new MobImpl());
		mobile_bis.init(env, 2, 1, d);
		
		mobile.Forward();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==2);	
		assertTrue(mobile.Row()==0);
	
		mobile.TurnR();
		mobile.StrafeL();
		mobile.TurnR();
		mobile.Backward();
		mobile.TurnR();
		mobile.StrafeR();
		mobile.TurnR();
		mobile.TurnR();
		
		
		assertTrue(mobile.Face()==Dir.E);
		assertTrue(mobile.Col()==2);	
		assertTrue(mobile.Row()==0);
		
		assertTrue(mobile_bis.Face()==Dir.N);
		assertTrue(mobile_bis.Col()==2);	
		assertTrue(mobile_bis.Row()==1);
		
		
		
		
		
	}	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestBackward(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.Backward();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==0);	
		assertTrue(mobile.Row()==1);
	
		mobile.TurnL();
		mobile.Backward();
		
		assertTrue(mobile.Face()==Dir.W);
		assertTrue(mobile.Col()==1);	
		assertTrue(mobile.Row()==1);
		
		mobile.TurnL();
		mobile.Backward();
		
		assertTrue(mobile.Face()==Dir.S);
		assertTrue(mobile.Col()==1);	
		assertTrue(mobile.Row()==1);
	}
	
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestStrafeL(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.StrafeL();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==0);	
		assertTrue(mobile.Row()==1);
	
		mobile.TurnR();
		mobile.TurnR();
		mobile.StrafeL();
		
		assertTrue(mobile.Face()==Dir.S);
		assertTrue(mobile.Col()==1);	
		assertTrue(mobile.Row()==1);
		
		mobile.StrafeL();
		mobile.TurnR();
		mobile.StrafeL();
		
		assertTrue(mobile.Face()==Dir.W);
		assertTrue(mobile.Col()==2);	
		assertTrue(mobile.Row()==0);
	}
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestSrafeR(){
		
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.StrafeR();
		assertTrue(mobile.Face()==Dir.N);
		assertTrue(mobile.Col()==1);	
		assertTrue(mobile.Row()==1);
	
		mobile.TurnR();
		mobile.StrafeR();
		
		assertTrue(mobile.Face()==Dir.E);
		assertTrue(mobile.Col()==1);	
		assertTrue(mobile.Row()==1);
		
		mobile.TurnR();
		mobile.StrafeR();
		
		assertTrue(mobile.Face()==Dir.S);
		assertTrue(mobile.Col()==0);	
		assertTrue(mobile.Row()==1);		
		
	}
	
	
		
		
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestTransitionsetEtats(){
		col=0;
		row=1;
	
		Dir d = Dir.N;
		mobile = new MobContract(new MobImpl());
		mobile.init(env, col, row, d);
		
		mobile.StrafeR();
		mobile.TurnR();
		mobile.Forward();
		mobile.Forward();
		mobile.TurnR();
		mobile.Forward();
		mobile.StrafeR();
		mobile.Backward();
		mobile.TurnL();
		mobile.Forward();
		mobile.StrafeL();
		mobile.StrafeL();
		mobile.Forward();
		mobile.Forward();
		mobile.Backward();
		mobile.TurnL();
		mobile.StrafeL();
		mobile.StrafeL();
		mobile.StrafeL();
		mobile.StrafeL();
		mobile.Forward();
		mobile.Forward();
		mobile.Forward();
		mobile.Backward();
		mobile.Backward();
		mobile.StrafeR();
		mobile.StrafeR();
		mobile.Forward();
		mobile.Forward();
		mobile.StrafeR();
		mobile.TurnR();
		
		assertTrue(env==mobile.Env());
		assertTrue(mobile.Face()==Dir.E);
		assertTrue(mobile.Col()==3);	
		assertTrue(mobile.Row()==5);		
		
		
		
		
	}
	
	
	
	
	
	
}
