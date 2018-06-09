package tests.mbt;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EnvironementContract;
import contracts.MonsterContract;
import contracts.PlayerContract;
import enumeration.Cell;
import enumeration.Dir;
import errors.InvariantError;
import errors.PreConditionError;
import services.Cow;
import services.EditMap;
import services.Environement;
import services.Food;
import services.Monster;
import services.Player;
import servicesImplementations.CowImpl;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.FoodImpl;
import servicesImplementations.MonsterImpl;
import servicesImplementations.PlayerImpl;

public class MonsterTest {
	protected Monster cow;
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
		cow=null;
	}
	
	
	
	
	@Test  // tester les pre, post et inv.  
	public void TestInitPositif(){
		
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		try {
			
			cow.init(env, 0, 1, d,3);
			assertTrue(cow.Row()==1);
			assertTrue(cow.Col()==0);
			assertTrue(cow.Face()==Dir.N);
			assertTrue(cow.Hp()==3); 
		
			cow.init(env, 2, 0, d,4);
			assertTrue(cow.Row()==0);
			assertTrue(cow.Col()==2);
			assertTrue(cow.Face()==Dir.N);
			assertTrue(cow.Hp()==4);
			
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
		cow =new MonsterContract(new MonsterImpl());
		Cow cow_bis = new CowContract(new CowImpl());
		
		// Briser les precond en metant un cow dans un case EMP occupé par un autre cow. 
		try {
			cow_bis.init(env, 0, 1, d,3);
			cow.init(env, 0, 1, d,4);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser l'invriant en mettant le cow sur une case IN
		
		try {
			cow.init(env, 0, 0, d,4);
		} catch (Error e) {
			if(e instanceof InvariantError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser les pré cond en mettant le cow dans une case hors MAP 
		try {
			cow.init(env, -1, -1, d,4);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
	
		// Briser les pré cond en passant un env et/ou direction null
		try {
			cow.init(null, 2, 0, null,4);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser les pré cond en passant hp differents de 3 et 4 
				try {
					cow.init(env, 3, 0, d,5);
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
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,3);
		
		cow.TurnL();
		assertTrue(cow.Face()==Dir.W);
		cow.TurnL();
		assertTrue(cow.Face()==Dir.S);
		cow.TurnL();
		assertTrue(cow.Face()==Dir.E);
		cow.TurnL();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Hp()==3);
		assertTrue(cow.Col()==col && cow.Row()==row); 
		
	}
	
	
	
	
		// pre : true (rien a tester)
	@Test  // post conds (+inv).
	public void TestTurnR(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,3);
		
		cow.TurnR();
		assertTrue(cow.Face()==Dir.E);
		cow.TurnR();
		assertTrue(cow.Face()==Dir.S);
		cow.TurnR();
		assertTrue(cow.Face()==Dir.W);
		cow.TurnR();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Hp()==3);
		assertTrue(cow.Col()==col && cow.Row()==row);
	}
	
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestForward(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,3);
		
		cow.Forward();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Col()==0);	
		assertTrue(cow.Row()==1);
	
		cow.TurnR();
		cow.Forward();
		
		
		assertTrue(cow.Face()==Dir.E);
		assertTrue(cow.Col()==1);	
		assertTrue(cow.Row()==1);
		assertTrue(cow.Hp()==3);
		
		
	}
	
	

		// ce test a pour but de tester le mouvenent d'un cow dans une case occupé par un autre cow.
		// pre : true (rien a tester)
		@Test  // post conds (+inv)
	public void TestCMD(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, 2, 0, d,4);
		//assertTrue(env.CellContent(2, 0)==mobile);
		Cow cow_bis= new CowContract(new CowImpl());
		cow_bis.init(env, 2, 1, d,3);
		
		cow.Forward();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Col()==2);	
		assertTrue(cow.Row()==0);
	
		cow.TurnR();
		cow.StrafeL();
		cow.TurnR();
		cow.Backward();
		cow.TurnR();
		cow.StrafeR();
		cow.TurnR();
		cow.TurnR();
		
		
		assertTrue(cow.Face()==Dir.E);
		assertTrue(cow.Col()==2);	
		assertTrue(cow.Row()==0);
		assertTrue(cow.Hp()==4);
		
		assertTrue(cow_bis.Face()==Dir.N);
		assertTrue(cow_bis.Col()==2);	
		assertTrue(cow_bis.Row()==1);
		assertTrue(cow_bis.Hp()==3);
		
		
	}	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestBackward(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,4);
		
		cow.Backward();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Col()==0);	
		assertTrue(cow.Row()==1);
	
		cow.TurnL();
		cow.Backward();
		
		assertTrue(cow.Face()==Dir.W);
		assertTrue(cow.Col()==1);	
		assertTrue(cow.Row()==1);
		
		cow.TurnL();
		cow.Backward();
		
		assertTrue(cow.Face()==Dir.S);
		assertTrue(cow.Col()==1);	
		assertTrue(cow.Row()==1);
		assertTrue(cow.Hp()==4);
	}
	
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestStrafeL(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,4);
		
		cow.StrafeL();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Col()==0);	
		assertTrue(cow.Row()==1);
	
		cow.TurnR();
		cow.TurnR();
		cow.StrafeL();
		
		assertTrue(cow.Face()==Dir.S);
		assertTrue(cow.Col()==1);	
		assertTrue(cow.Row()==1);
		
		cow.StrafeL();
		cow.TurnR();
		cow.StrafeL();
		
		assertTrue(cow.Face()==Dir.W);
		assertTrue(cow.Col()==2);	
		assertTrue(cow.Row()==0);
		assertTrue(cow.Hp()==4);
	}
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestSrafeR(){
		
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,4);
		
		cow.StrafeR();
		assertTrue(cow.Face()==Dir.N);
		assertTrue(cow.Col()==1);	
		assertTrue(cow.Row()==1);
	
		cow.TurnR();
		cow.StrafeR();
		
		assertTrue(cow.Face()==Dir.E);
		assertTrue(cow.Col()==1);	
		assertTrue(cow.Row()==1);
		
		cow.TurnR();
		cow.StrafeR();
		
		assertTrue(cow.Face()==Dir.S);
		assertTrue(cow.Col()==0);	
		assertTrue(cow.Row()==1);
		assertTrue(cow.Hp()==4);
		
	}
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestTransitionsetEtats(){
		col=0;
		row=1;
	
		Dir d = Dir.N;
		cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, col, row, d,4);
		
		cow.StrafeR();
		cow.TurnR();
		cow.Forward();
		cow.Forward();
		cow.TurnR();
		cow.Forward();
		cow.StrafeR();
		cow.Backward();
		cow.TurnL();
		cow.Forward();
		cow.StrafeL();
		cow.StrafeL();
		cow.Forward();
		cow.Forward();
		cow.Backward();
		cow.TurnL();
		cow.StrafeL();
		cow.StrafeL();
		cow.StrafeL();
		cow.StrafeL();
		cow.Forward();
		cow.Forward();
		cow.Forward();
		cow.Backward();
		cow.Backward();
		cow.StrafeR();
		cow.StrafeR();
		cow.Forward();
		cow.Forward();
		cow.StrafeR();
		cow.TurnR();
		
		assertTrue(env==cow.Env());
		assertTrue(cow.Face()==Dir.E);
		assertTrue(cow.Col()==3);	
		assertTrue(cow.Row()==5);		
		assertTrue(cow.Hp()==4);
		
		
		
	}
	
		@Test
		public void TestStep(){
	
			// faire avancer le cow aléatoirement jusqu'a atteindre la case qui est juste avant la sortie. 
			
			col=2;
			row=1;
			Dir d = Dir.N;
			Player p = new PlayerContract(new PlayerImpl());
			p.init(env, 0, 0, d, 10);
			p.Forward();
			p.TurnR();
			p.Forward();
			p.Forward();
			p.Forward();
			assertTrue(p.Col()==3 && p.Row()==1);
			cow = new MonsterContract(new MonsterImpl());
			cow.init(env, col, row, Dir.N,4);
			
			cow.Step();
			System.out.println(cow.Col()+ " "+cow.Row()+" "+cow.Face());
			assertTrue(cow.Col()==2);
			assertTrue(cow.Row()==1);
			assertTrue(cow.Face()== Dir.E);
			cow.TurnL();
			env.setCellContent(3, 1, null);
			 p = new PlayerContract(new PlayerImpl());
			p.init(env, 0, 0, d, 10);
			p.Forward();
			p.TurnR();
			p.Forward();
			p.Forward();
			cow.Step();
			assertTrue(cow.Col()==2);
			assertTrue(cow.Row()==1);
			assertTrue(cow.Face()== Dir.W);
			for(int i = 0; i<10 ; i++) cow.Step();
			assertTrue(p.Hp()==0);	
			System.out.println(p.Col()+" "+p.Row());
			env.setCellContent(p.Col(), p.Row(), null);
			 p = new PlayerContract(new PlayerImpl());
			p.init(env, 0, 0, d, 10);
			p.Forward();
			cow.Step();
			assertTrue(cow.Col()==1);
			assertTrue(cow.Row()==1);
			assertTrue(cow.Face()==Dir.W);
			for(int i = 0; i<10 ; i++) cow.Step();
			assertTrue(p.Hp()==0);
			
			env.setCellContent(0, 1, null);
			env.setCellContent(1, 1, null);
			 p = new PlayerContract(new PlayerImpl());
				p.init(env, 0, 0, d, 10);
				p.Forward();
				p.TurnR();
				p.Forward();
				p.Forward();
				p.Forward();
				p.TurnL();
				p.Forward();
				assertTrue(p.Face()==Dir.N && p.Col()==3 && p.Row()==2);
				cow = new MonsterContract(new MonsterImpl());
				cow.init(env, 2, 1, Dir.N,4);
				cow.Step();
				assertTrue(cow.Col()==3);;
				assertTrue(cow.Row()==1);
				assertTrue(cow.Face()==Dir.N);
				for(int i = 0; i<10 ; i++) cow.Step();
				assertTrue(p.Hp()==0);	
				assertTrue(cow.Col()==3);;
				assertTrue(cow.Row()==1);
				assertTrue(cow.Face()==Dir.N);
			
			
			/*while(cow.Col() != 3 || cow.Row() != 5) {
				
				cow.Step();
			}
			
			assertTrue(cow.Col()==3);
			assertTrue(cow.Row()==5);
			assertTrue(cow.Hp()==4);*/
			
			
			
			
			
		}
		
		@Test
		public void TestAttack(){
	
			// faire avancer le cow aléatoirement jusqu'a atteindre la case qui est juste avant la sortie. 
			
			col=0;
			row=1;
		
			Dir d = Dir.S;
			cow = new MonsterContract(new MonsterImpl());;
			cow.init(env, col, row, d,4);
			
			Player p = new PlayerContract(new PlayerImpl());
			p.init(env, 0, 0, Dir.N, 5);
			
			assertTrue(p.Hp()==5);
			
			while(cow.Col() != 3 || cow.Row() != 5) {
				
				if(p.Hp()==0) env.setCellContent(p.Col(), p.Row(), null);
				cow.Step();
			}
			
		
			
	
			assertTrue(p.Hp()==0);
			assertTrue(cow.Col()==3);
			assertTrue(cow.Row()==5);
			assertTrue(cow.Hp()==4);
			
			
			
			
			
		}
		
		
		@Test 
		public void TestSetHp() {
			
			col=0;
			row=1;
		
			Dir d = Dir.S;
			cow = new MonsterContract(new MonsterImpl());;
			cow.init(env, col, row, d,4);
			assertTrue(cow.Hp()==4);
			cow.SetHp(10);
			assertTrue(cow.Hp()==10);
		}
		
		@Test 
		public void TestSetSpoil() {
			
			col=0;
			row=1;
			Food f = new FoodImpl();
			f.init();
			Dir d = Dir.S;
			cow = new MonsterContract(new MonsterImpl());;
			cow.init(env, col, row, d,4);
			assertTrue(cow.Drop_Spoil()==null);
			cow.Set_Spoil(f);
			assertTrue(cow.Drop_Spoil()==f); 
		}	
		
}
