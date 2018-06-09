package contracts;

import java.util.List;

import decorateur.JeuDecorator;
import enumeration.Cell;
import errors.PostConditionError;
import services.Jeu;
import services.Treasure;

public class JeuContract extends JeuDecorator{

	public JeuContract(Jeu delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void InitAndStartGame() throws Exception {
		
		super.InitAndStartGame();
		
		boolean b =true;
		
		List<Treasure> treasures = Get_Necessary_Treasures();
		
		for(Treasure t : treasures) {
			if (!Get_Player().Treasures().contains(t)) {   b=false ; break;}
			
		}
	
		if(Get_Player().Hp() > 0 && !(Get_Player().Env().cellNature(Get_Player().Col(), Get_Player().Row())== Cell.OUT && b )  ) {
			throw new PostConditionError("the game ended without the player dying or without him finishing the level with the required treasures");
			
		}
	}
	
	

}
;