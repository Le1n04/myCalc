/**
 * Nombre: Daniel Josue Janssen Gil
 * Fecha: Dec 15, 2023
 * Ejercicio: calculator
 * Hora: 6:02:57 PM
 */
package calculator;

/**
 * 
 */
public class Maker
{
	private utils ut;
	
	public String printFormatException(String str)
	{
		int i = 0;
		while (ut.isSymbol(str.charAt(i)))
			i++;
		String msg = "";
		while (i < str.length())
		{
			if (ut.isUpper(str.charAt(i)))
					msg += ' ';
			msg += str.charAt(i);
			i++;
		}
		return msg;	
	}
}
