package tests.mbt;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contracts.EditMapContract;
import contracts.EnvironementContract;
import contracts.FoodContract;
import contracts.PlayerContract;
import enumeration.Cell;
import enumeration.Dir;
import errors.PreConditionError;
import services.EditMap;
import services.Environement;
import services.Food;
import services.Player;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.FoodImpl;

import servicesImplementations.PlayerImpl;

public class FoodTest {


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
		
	}
	
	
	
	@Test
	public void TestFoodInitPositif() {
		
		//Tester les differents constructeurs ( pré et post )
	
		Food f = new FoodContract(new FoodImpl());
		f.init();
		assertTrue(f.Hp_Value()==1);
		assertTrue(f.row()==0);
		assertTrue(f.col()==0);
		
		f.init(3);
		assertTrue(f.Hp_Value()==3);
		assertTrue(f.row()==0);
		assertTrue(f.col()==0);	
		
		f.init(env, 0, 1);
		assertTrue(f.Hp_Value()==1);
		assertTrue(f.row()==1);
		assertTrue(f.col()==0);
		assertTrue(f.env()==env);
		
		f.init(env, 1, 1, 3);
		assertTrue(f.Hp_Value()==3);
		assertTrue(f.row()==1);
		assertTrue(f.col()==1);
		assertTrue(f.env()==env);
		
		
		Player p = new PlayerContract(new PlayerImpl());
		p.init(env, col, row, Dir.N, 10);
		//Monster m = new MonsterContract(new MonsterImpl());
		//m.init(env, 0, 1, Dir.S, 4, 3);
		
		
		
		
		
	}
	
	@Test
	public void TestFoodInitNegatif() {
		
		Food f = new FoodContract(new FoodImpl());
		
		// briser les pre cond 
		try {
		
		f.init(null, col, row);
		assertTrue(false);
		
		}catch(PreConditionError e) { 
			assertTrue(true);
			
		}
		
		try {
			
			f.init(env, -1, 0);
			assertTrue(false);
			
			}catch(PreConditionError e) { 
				assertTrue(true);
				
			}
		
		try {
			
			f.init(env, 1, -1);
			assertTrue(false);
			
			}catch(PreConditionError e) { 
				assertTrue(true);
				
			}
		try {
			
			f.init(null, col, row,4);
			assertTrue(false);
			
			}catch(PreConditionError e) { 
				assertTrue(true);
				
			}
			
			try {
				
				f.init(env, -1, 0,4);
				assertTrue(false);
				
				}catch(PreConditionError e) { 
					assertTrue(true);
					
				}
			
			try {
				
				f.init(env, 1, -1,4);
				assertTrue(false);
				
				}catch(PreConditionError e) { 
					assertTrue(true);
					
				}
			// hp <= 0
			try {
				
				f.init(env, 1, 1,0);
				assertTrue(false);
				
				}catch(PreConditionError e) { 
					assertTrue(true);
					
				}
			
			f.init(env, col, row);
			
			// case pleine 
			
			try { 
				
				f.init(env, col, row);
				assertTrue(false);
				
				}catch(PreConditionError e) { 
					assertTrue(true);
					
				}
			
	}
	
	
	
	
	
	
}
