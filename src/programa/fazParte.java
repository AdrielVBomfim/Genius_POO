/**@author Adriel Ven�ncio Bonfim - 201500030838
 *@author Igor de Figueiredo Rodrigues - 201500018083
 *@since  20/04/2015
 *@version v1.0.3
 *
 *Interface fazParte: respons�vel pela comunica��o de metodos da classe principal (Interface.java) com a classe de jogo (Jogo.java).
 */
package programa;

public interface fazParte {
	/*Metodos sobrecarregados com a��es de piscar os bot�es utilizados tanto na classe principal quanto na classe Jogo.**/
	public void actRed();
	public void actYellow();
	public void actGreen();
	public void actBlue();
}
