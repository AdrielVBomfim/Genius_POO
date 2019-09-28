/**@author Adriel Venâncio Bonfim - 201500030838
 *@author Igor de Figueiredo Rodrigues - 201500018083
 *@since  20/04/2015
 *@version v1.0.3
 *
 *Interface fazParte: responsável pela comunicação de metodos da classe principal (Interface.java) com a classe de jogo (Jogo.java).
 */
package programa;

public interface fazParte {
	/*Metodos sobrecarregados com ações de piscar os botões utilizados tanto na classe principal quanto na classe Jogo.**/
	public void actRed();
	public void actYellow();
	public void actGreen();
	public void actBlue();
}
