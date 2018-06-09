package decorateur;

import java.util.List;

import enumeration.Cell;
import enumeration.Command;
import enumeration.Dir;
import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import services.*;

public class PlayerDecorator implements Player{

	protected Player delegate;
	
	public PlayerDecorator(Player delegate) {
		this.delegate=delegate;
	}
	
	@Override
	public int Hp() {
		return delegate.Hp() ;
	}

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp) throws InvariantError, PostConditionError, PreConditionError  {
		delegate.init(e, col, row, d, hp);
	}

	@Override
	public void Step() throws InvariantError, PostConditionError, PreConditionError {
		
		delegate.Step();
	}

	@Override
	public Environement Env() {
		return delegate.Env();
	}

	@Override
	public int Col() {
		return delegate.Col();
	}

	@Override
	public int Row() {
		return delegate.Row();
	}

	@Override
	public Dir Face() {
		return delegate.Face();
	}

	@Override
	public void init(Environement e, int col, int row, Dir d)
			throws Error {
		delegate.init(e, col, row, d);
	}

	@Override
	public void Forward() throws InvariantError, PostConditionError, PreConditionError  {
		delegate.Forward();
	}

	@Override
	public void Backward() throws InvariantError, PostConditionError, PreConditionError  {

		delegate.Backward();
	}

	@Override
	public void TurnL() throws InvariantError, PostConditionError, PreConditionError  {
		delegate.TurnL();
	}

	@Override
	public void TurnR() throws InvariantError, PostConditionError, PreConditionError  {
		delegate.TurnR();
	}

	@Override
	public void StrafeL() throws InvariantError, PostConditionError, PreConditionError  {
		delegate.StrafeL();		
	}

	@Override
	public void StrafeR() throws InvariantError, PostConditionError, PreConditionError {
		delegate.StrafeR();		
	}

	@Override
	public Command LastCom() {
		return delegate.LastCom();
	}

	@Override
	public Containable Content(int col, int row) throws PreConditionError {
		
		return delegate.Content(col, row);
	}

	@Override
	public boolean Viewable(int col, int row) throws PreConditionError {
	return delegate.Viewable(col, row);
	}

	@Override
	public Cell Nature(int col, int row) throws PreConditionError {
	
		return delegate.Nature(col, row);
	}

	@Override
	public void AddCommad(Command c) throws InvariantError, PostConditionError, PreConditionError  {
		delegate.AddCommad(c);
		
	}

	@Override
	public void SetHp(int hp) {
		delegate.SetHp(hp);
	}

	@Override
	public void init(Environement e, int col, int row, Dir d, int hp, int Striking_power) {
		delegate.init(e, col, row, d, hp, Striking_power);
	}

	@Override
	public int Striking_Power() {
		return delegate.Striking_Power();
	}

	@Override
	public void Increase_Striking_Power(int p) {
		delegate.Increase_Striking_Power(p);
	}

	@Override
	public void Attack() {
		delegate.Attack();
	}

	@Override
	public void Arrow() {
		delegate.Arrow();
	}

	@Override
	public void Eat() {
		delegate.Eat();
	}

	@Override
	public void OpenDoor() {
		delegate.OpenDoor();
	}

	@Override
	public void CloseDoor() {
		delegate.CloseDoor();	
	}

	@Override
	public int Nb_Arrows() {
		return delegate.Nb_Arrows();
	}

	@Override
	public List<Weapon> Weapons() {
		return delegate.Weapons();
	}

	@Override
	public int Max_Hp() {
		return delegate.Max_Hp();
	}

	@Override
	public int Nb_Foods() {
		return delegate.Nb_Foods();
	}

	@Override
	public List<Food> Foods() {
		return delegate.Foods() ;
	}

	@Override
	public boolean Has_Key() {
		return delegate.Has_Key();
	}

	@Override
	public void Take() {
		
		delegate.Take();
		
	}

	@Override
	public void Add_Key(Key k) {
		delegate.Add_Key(k);
	}

	@Override
	public List<Treasure> Treasures() {
		return delegate.Treasures();
	}

	@Override
	public List<Key> Keys() {
		return delegate.Keys();
	}

	@Override
	public void Add_Food(Food f) {
		 delegate.Add_Food(f);
		
	}

	@Override
	public void Add_Weapon(Weapon w) {
		delegate.Add_Weapon(w);
	}

	@Override
	public void UseMagicBeans() {
		delegate.UseMagicBeans();
	}

	@Override
	public void Add_Beans(MagicBeans b) {
		delegate.Add_Beans(b);
	}

	@Override
	public List<MagicBeans> Beans() {
		return delegate.Beans();
	}

}
