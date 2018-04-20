package tests.mbt;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import contracts.MapContract;
import errors.PostConditionError;
import errors.PreConditionError;
import services.IMap;
import servicesImplementations.Map;



class TestMap {
	
	IMap map;
	

	/**
	 * Objectif de Test: init(int w,int w) reussi
	 * 
	 * Cas de Test:  map.init(int w,int h)
	 * 		 0 <= w && 0<=h
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  init(10, 6)
	 */
	@Test
	public void testInit() {
		// Conditions initiales
           map = new MapContract();
           int h=10;
           int w=6;
       //Operation
           map.init(h, w);
           
           assertTrue("map.width()= w", map.width()==w);
           assertTrue("map.height()= w", map.height()==h);
	}
	
	/**
	 * Objectif de Test: init(int w,int w) failled
	 * 
	 * Cas de Test:  map.init(int w,int h)
	 * 		 w < 0
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  init(-10, 6)
	 */
	@Test
	public void testInitFaillure1() {
		// Conditions initiales
           map = new MapContract();
           int h=-2;
           int w=6;
       //Operation
          try {
			map.init(h, w);
			fail();
		} catch (PreConditionError e) {
			// TODO: handle exception
		}
           
	}

	/**
	 * Objectif de Test: init(int w,int w) reussi
	 * 
	 * Cas de Test:  map.init(int w,int h)
	 * 		h<0
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  init(10,-6)
	 */
	@Test
	public void testInitFaillure2() {
		// Conditions initiales
           map = new MapContract();
           int h=10;
           int w=-6;
    //Operation
           try {
   			map.init(h, w);
   			fail();
   		} catch (PreConditionError e) {
   			// TODO: handle exception
   		}
           
	}
	
	/**
	 * Objectif de Test: cellNature(int x,int x) reussi
	 * 
	 * Cas de Test:  map.cellNature(x,y)
	 * 		 0 ≤ x < height() and 0 ≤ y < width() 
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  .cellNature(3,4)
	 */
	@Test
	public void testCellNatureSuccess() {
		// Conditions initiales
        map = new MapContract();
        int h=10, x=3;
        int w=6,y=4; 
        map.init(w, h);
        // Operation 
       map.cellNature(x, y);
       
       assertTrue(true);
        
        
		
		
	}

	/**
	 * Objectif de Test: cellNature(int x,int x) reussi
	 * 
	 * Cas de Test:  map.cellNature(x,y)
	 * 		 x < 0
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  .cellNature(3,4)
	 */
	@Test
	public void testCellNatureFaillure() {
		// Conditions initiales
        map = new MapContract();
        int h=10, x=-3;
        int w=6,y=4; 
        map.init(w, h);
        // Operation 
        try {
       map.cellNature(x, y);
       fail();
        }
        catch (PreConditionError e) {
			// TODO: handle exception
		}
       
       
       assertTrue(true);
        
        
		
		
	}


}
