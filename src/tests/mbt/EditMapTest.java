package tests.mbt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;
import contracts.EditMapContract;
import contracts.EnvironementContract;
import contracts.MapContract;
import enumeration.Cell;
import errors.PreConditionError;
import services.EditMap;
import services.Environement;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.MapImpl;

class EditMapTest {
   EditMap map;

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
    map = new EditMapContract(new EditMapImpl());
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
    map = new EditMapContract(new EditMapImpl());
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
        map = new EditMapContract(new EditMapImpl());
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
            map = new EditMapContract(new EditMapImpl());
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
        /**
         * Objectif de Test: opendoor(int x,int x) reussi
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(3,2) == Cell.DWC;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(3,2)
         */
        @Test
        public void testOpenDoorSuccess() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.openDoor(3, 2);
            }	
            catch (Error e) {
    		    fail();
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(4,4) == Cell.DNO;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(4,4)
         */
        @Test
        public void testOpenDoorFaillureIN() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.openDoor(4, 4);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);	
    		
    	}
        
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(4,4) == Cell.EMP;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(5,5)
         */
        @Test
        public void testOpenDoorFaillureEMP() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.openDoor(5, 5);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(4,4) == Cell.DNO;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(4,4)
         */
        @Test
        public void testOpenDoorFaillureDNO() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.openDoor(4, 4);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(1,1) == Cell.IN;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(1,0)
         */
        @Test
        public void testOpenDoorFaillureOUT() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.openDoor(2, 5);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        
        /**
         * Objectif de Test: opendoor(int x,int x) reussi
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(3,2) == Cell.DWC;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(3,2)
         */
        @Test
        public void testCloseDoorSuccess() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.closeDoor(1, 3);
            }	
            catch (Error e) {
    		    fail();
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(4,4) == Cell.DNO;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(1,0)
         */
        @Test
        public void testCloseDoorFaillureIN() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.closeDoor(1, 0);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);	
    		
    	}
        
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(4,4) == Cell.EMP;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(5,5)
         */
        @Test
        public void testCloseDoorFaillureEMP() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.closeDoor(5, 5);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(4,4) == Cell.DNO;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(4,4)
         */
        @Test
        public void testCloseDoorSuccessDNO() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.closeDoor(4, 4);
            	
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);
            
            
    		
    		
    	}
        /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(1,1) == Cell.IN;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(1,0)
         */
        @Test
        public void testCloseDoorFaillureOUT() {
    		// Conditions initiales

            map= new EditMapContract(new EditMapImpl());
            int w=6,h=6; 
            map.init(w, h);
            // Operation 
            try {
            	map.setNature(0,0,Cell.EMP);
            	map.setNature(0,1,Cell.EMP);
            	map.setNature(0,2,Cell.EMP);
            	map.setNature(0,3,Cell.EMP);
            	map.setNature(0,4,Cell.EMP);
            	map.setNature(0,5,Cell.EMP);

            	map.setNature(1,0,Cell.IN);
            	map.setNature(1,1,Cell.EMP);
            	map.setNature(1,2,Cell.WLL);
            	map.setNature(1,3,Cell.DWO);
            	map.setNature(1,4,Cell.WLL);
            	map.setNature(1,5,Cell.EMP);

            	map.setNature(2,0,Cell.EMP);
            	map.setNature(2,1,Cell.EMP);
            	map.setNature(2,2,Cell.EMP);
            	map.setNature(2,3,Cell.EMP);
            	map.setNature(2,4,Cell.EMP);
            	map.setNature(2,5,Cell.OUT);

            	map.setNature(3,0,Cell.EMP);
            	map.setNature(3,1,Cell.WLL);
            	map.setNature(3,2,Cell.DWC);
            	map.setNature(3,3,Cell.WLL);
            	map.setNature(3,4,Cell.WLL);
            	map.setNature(3,5,Cell.EMP);

            	map.setNature(4,0,Cell.EMP);
            	map.setNature(4,1,Cell.EMP);
            	map.setNature(4,2,Cell.EMP);
            	map.setNature(4,3,Cell.EMP);
            	map.setNature(4,4,Cell.DNO);
            	map.setNature(4,5,Cell.EMP);

            	map.setNature(5,0,Cell.EMP);
            	map.setNature(5,1,Cell.EMP);
            	map.setNature(5,2,Cell.EMP);
            	map.setNature(5,3,Cell.EMP);
            	map.setNature(5,4,Cell.WLL);
            	map.setNature(5,5,Cell.EMP);
            	
            	map.closeDoor(2, 5);
            	
            	fail();
            }	
            catch (PreConditionError e) {
    		}
           
           
           assertTrue(true);
            
   }
        
   @Test
   public void testIsReachableSuccess() {
	   
		// Conditions initiales

       map= new EditMapContract(new EditMapImpl());
       int w=4,h=4; 
       map.init(w, h);
       // Operation 
       try {
	   map.setNature(0,0,Cell.IN);
	   map.setNature(0,1,Cell.EMP);
	   map.setNature(0,2,Cell.EMP);
	   map.setNature(0,3,Cell.EMP);

	   map.setNature(1,0,Cell.EMP);
	   map.setNature(1,1,Cell.EMP);
	   map.setNature(1,2,Cell.WLL);
	   map.setNature(1,3,Cell.EMP);

	   map.setNature(2,0,Cell.EMP);
	   map.setNature(2,1,Cell.EMP);
	   map.setNature(2,2,Cell.DNC);
	   map.setNature(2,3,Cell.EMP);

	   map.setNature(3,0,Cell.EMP);
	   map.setNature(3,1,Cell.EMP);
	   map.setNature(3,2,Cell.WLL);
	   map.setNature(3,3,Cell.OUT);
	   
	   map.isReachable(0, 0, 3, 3);
   }
       catch (Exception e) {
	    fail();
	}
}
 /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(1,1) == Cell.IN;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  
         *  map.setNature(3,0,Cell.WLL)
	     *  map.setNature(3,1,Cell.WLL)
	     *  map.setNature(3,2,Cell.WLL)
            map.setNature(3,3,Cell.OUT)
         */
   @Test
   public void testIsReachableFaillure() {
	   
		// Conditions initiales

       map= new EditMapContract(new EditMapImpl());
       int w=4,h=4; 
       map.init(w, h);
       // Operation 
       try {
	   map.setNature(0,0,Cell.IN);
	   map.setNature(0,1,Cell.EMP);
	   map.setNature(0,2,Cell.EMP);
	   map.setNature(0,3,Cell.EMP);

	   map.setNature(1,0,Cell.EMP);
	   map.setNature(1,1,Cell.EMP);
	   map.setNature(1,2,Cell.WLL);
	   map.setNature(1,3,Cell.WLL);

	   map.setNature(2,0,Cell.WLL);
	   map.setNature(2,1,Cell.WLL);
	   map.setNature(2,2,Cell.WLL);
	   map.setNature(2,3,Cell.WLL);

	   map.setNature(3,0,Cell.WLL);
	   map.setNature(3,1,Cell.WLL);
	   map.setNature(3,2,Cell.WLL);
	   map.setNature(3,3,Cell.OUT);
	  
	   assertFalse(map.isReachable(0, 0, 3, 3));
	 
   }
       catch (Exception e) {
	    fail();
	}
}
   
   @Test
   public void testReadySuccess() {
	   
		// Conditions initiales

       map= new EditMapContract(new EditMapImpl());
       int w=4,h=4; 
       map.init(w, h);
       // Operation 
       try {
	   map.setNature(0,0,Cell.IN);
	   map.setNature(0,1,Cell.EMP);
	   map.setNature(0,2,Cell.EMP);
	   map.setNature(0,3,Cell.EMP);

	   map.setNature(1,0,Cell.EMP);
	   map.setNature(1,1,Cell.EMP);
	   map.setNature(1,2,Cell.WLL);
	   map.setNature(1,3,Cell.EMP);

	   map.setNature(2,0,Cell.EMP);
	   map.setNature(2,1,Cell.EMP);
	   map.setNature(2,2,Cell.DNC);
	   map.setNature(2,3,Cell.EMP);

	   map.setNature(3,0,Cell.EMP);
	   map.setNature(3,1,Cell.EMP);
	   map.setNature(3,2,Cell.WLL);
	   map.setNature(3,3,Cell.OUT);
	   
	   assertTrue(map.isReady());
   }
       catch (Exception e) {
	    fail();
	}
}
    /**
         * Objectif de Test: opendoor(int x,int x) faillure
         * 
         * Cas de Test:  map.opendoor(x,y)
         * 		 x = < 0&& y <0 && 	map.cellNature(1,1) == Cell.IN;
         * 
         * Condition initiale: Aucune
         * 
         * Operation:
         *  .opendoor(1,0)
         */
   
   @Test
   public void testReadyFaillure() {
	   
		// Conditions initiales

       map= new EditMapContract(new EditMapImpl());
       int w=4,h=4; 
       map.init(w, h);
       // Operation 
       try {
	   map.setNature(0,0,Cell.IN);
	   map.setNature(0,1,Cell.EMP);
	   map.setNature(0,2,Cell.EMP);
	   map.setNature(0,3,Cell.EMP);

	   map.setNature(1,0,Cell.EMP);
	   map.setNature(1,1,Cell.EMP);
	   map.setNature(1,2,Cell.WLL);
	   map.setNature(1,3,Cell.EMP);

	   map.setNature(2,0,Cell.EMP);
	   map.setNature(2,1,Cell.EMP);
	   map.setNature(2,2,Cell.DNC);
	   map.setNature(2,3,Cell.EMP);

	   map.setNature(3,0,Cell.OUT);
	   map.setNature(3,1,Cell.EMP);
	   map.setNature(3,2,Cell.WLL);
	   map.setNature(3,3,Cell.OUT);
	   
	   assertFalse(map.isReady());
   }
       catch (Exception e) {
	    fail();
	}
}
   }