/**
 * Nombre: Daniel Josue Janssen Gil
 * Fecha: Dec 15, 2023
 * Ejercicio: calculator
 * Hora: 5:07:30 PM
 */
package calculator;

/**
 * 
 */
public class utils
{
	public boolean IsSpecial(char c)
	{
		if (c == '.' || c == '=')
			return true;
		return false;
	}
	
	public boolean isSymbol(char c)
	{
		if (c == '%' || c == '/' || c == '+' || c == '-' || c == 'X' || c == 'x')
			return true;
		return false;
	}
	
	public boolean isUpper(char c)
	{
		if ((c >= 'A' && c <= 'Z'))
			return true;
		return false;
	}
	
	public String printFormatException(String str)
	{
		int i = 0;
		while (!isUpper(str.charAt(i)))
			i++;
		String msg = "";
		while (i < str.length())
		{
			if (isUpper(str.charAt(i)))
					msg += ' ';
			msg += str.charAt(i);
			i++;
		}
		return msg;	
	}
}
