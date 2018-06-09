package tests.mbt;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import contracts.GridContract;
import contracts.PlayerContract;
import errors.PostConditionError;
import services.Grid;
import servicesImplementations.GridImpl;
import servicesImplementations.PlayerImpl;
import servicesImplementationsBug.GridBugImpl;

class GridTest {

	@Test
	public void generatedGrid() {
		Grid grid = new GridContract(new GridImpl());
		grid.init(6,6);
		grid.generatedGrid(new PlayerContract(new PlayerImpl()));
		
		assertTrue(true);
		
		
	}
	
	@Test
	public void generatedGridFaillure() {
		Grid grid = new GridContract(new GridBugImpl());
		try {
			grid.init(40,15);
		    grid.generatedGrid(new PlayerContract(new PlayerImpl()));
		       fail();
		}
		catch (Error e) {
			e.printStackTrace();			
		}
	}
}
