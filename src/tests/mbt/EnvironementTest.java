package tests.mbt;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import contracts.EnvironementContract;
import contracts.MapContract;
import errors.PreConditionError;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.MapImpl;
import services.Environement;
import services.Map;

public class EnvironementTest {

	Environement env ;
	
	@Before
	public void beforeTests() {}; 

	@After
	public final void afterTests() {
		
	}
	
	@Test
	public void TestInitPositif() {
		
		env= new EnvironementContract(new EnvironementImpl());
		try {
			
			Map map = new MapContract( new MapImpl());
			map.init(10, 10);
			env.init(map);
		} catch (PreConditionError e) {
			
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}
	}	
	
	@Test
	public void TestInitNegatif() {
		
		env= new EnvironementContract(new EnvironementImpl());
		try {
			Map map = new MapContract( new MapImpl());
			map.init(-1, 0);
			env.init(map);
		} catch (PreConditionError e) {
			if (e instanceof PreConditionError) {assertTrue(true); return;}
			assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
