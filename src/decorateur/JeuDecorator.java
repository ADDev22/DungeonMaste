package decorateur;

import java.util.List;

import services.Jeu;
import services.Player;
import services.Treasure;

public class JeuDecorator implements Jeu{

	protected Jeu delegate;
	
	public JeuDecorator(Jeu delegate) {
		this.delegate=delegate;
	}
	@Override
	public Player Get_Player() {
		return delegate.Get_Player();
	}

	@Override
	public List<Treasure> Get_Necessary_Treasures() {
		return delegate.Get_Necessary_Treasures();
	}

	@Override
	public void InitAndStartGame() throws Exception {
		delegate.InitAndStartGame();
		
	}

}
