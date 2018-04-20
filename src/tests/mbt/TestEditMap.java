package tests.mbt;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import contracts.EditMapContract;
import contracts.MapContract;
import enumeration.Cell;
import errors.PreConditionError;
import services.IEditMap;
import servicesImplementations.EditMap;

class TestEditMap {
   IEditMap map;

/**
 * Objectif de Test: cellNature(int x,int x) reussi
 * 
 * Cas de Test:  map.setNature(x,y)
 * 		 0 ≤ x < height() and 0 ≤ y < width() 
 * 
 * Condition initiale: Aucune
 * 
 * Operation:
 *  .cellNature(3,4)
 */
@Test
public void testsetNatureSuccess() {
	// Conditions initiales
    map = new EditMapContract(new EditMap());
    int w=10, x=3;
    int h=6,y=4; 
    map.init(w, h);
    // Operation 
   map.setNature(x, y,Cell.EMP);
   
   assertTrue(true);
    
    
	
	
}
/**
 * Objectif de Test: cellNature(int x,int x) reussi
 * 
 * Cas de Test:  map.setNature(x,y)
 * 		 x < 0 && y =?
 * 
 * Condition initiale: Aucune
 * 
 * Operation:
 *  .setNature(3,4)
 */
@Test
public void testsetNatureFaillure() {
	// Conditions initiales
    map = new EditMapContract(new EditMap());
    int w=10, x=-3;
    int h=6,y=4; 
    map.init(w, h);
    // Operation 
    try {
    	   map.setNature(x, y,Cell.EMP);
    	   fail();
	} catch (PreConditionError e) {
	}
}
/**
 * Objectif de Test: cellNature(int x,int x) reussi
 * 
 * Cas de Test:  map.setNature(x,y)
 * 		 x = ?&& y <0
 * 
 * Condition initiale: Aucune
 * 
 * Operation:
 *  .setNature(3,4)
 */
    
    @Test
    public void testsetNatureFaillure2() {
    	// Conditions initiales
        map = new EditMapContract(new EditMap());
        int w=10, x=3;
        int h=6,y=-4; 
        map.init(w, h);
        // Operation 
        try {
        	   map.setNature(x, y,Cell.EMP);
        	   fail();
    	} catch (PreConditionError e) {
    	}
 
}
    /**
     * Objectif de Test: cellNature(int x,int x) reussi
     * 
     * Cas de Test:  map.setNature(x,y)
     * 		 x = < 0&& y <0
     * 
     * Condition initiale: Aucune
     * 
     * Operation:
     *  .setNature(-3,-4)
     */
        
        @Test
        public void testsetNatureFaillure3() {
        	// Conditions initiales
            map = new EditMapContract(new EditMap());
            int w=10, x=-3;
            int h=6,y=-4; 
            map.init(w, h);
            // Operation 
            try {
            	   map.setNature(x, y,Cell.EMP);
            	   fail();
        	} catch (PreConditionError e) {
        	}
       
}
}