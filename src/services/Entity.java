package services;

import enumeration.Dir;
import errors.*;

public interface Entity extends Mob {


public int Hp();

/**
 * \pre true
 * \post Hp()== hp. 
 * @param hp
 */
public void SetHp(int hp);


/** 
 * \pre  e != null && d !=null && 0 ≤ col < e.Width() && 0 ≤ y < e.Height() && hp>0
 * \post Col()==col && Row()==row && Env()==e && Face()==d 	&& Hp()==hp
 * @throws PostconditionError 
 * @throws PreconditionError 
 * @throws InvariantException 
 * */

public void init(Environement e,int col , int row, Dir d,int hp) throws PreConditionError, PostConditionError, InvariantError;


/*pour tous les autres opérateurs hérités on pourrait renforcer les post conditions en ajoutant Hp() @pré == Hp() */
/* et on pourrait renforcer les invariants en ajoutant Hp()>=0 mais on laisse le moteur de jeu s'en charger de vérifier les hp avant chaque step()
 *  dans ce cas si c'est <= 0 il supprime l'entité de l'env */


/**
 * \pre true
 * \post true
 * @throws PostconditionError 
 * @throws InvariantException 
 * @throws PreconditionError 
 */
public void Step() throws InvariantError, PostConditionError, PreConditionError;
}
