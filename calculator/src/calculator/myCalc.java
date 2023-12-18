package calculator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class myCalc extends JFrame
{
	private JPanel panel = new JPanel();
    private JButton[] botones;
    private JTextField texto;
	private double resultado = 0.0;
	private int counter = 0;
	private String input = "";
	
	
    
	public myCalc()
	{
		this.setTitle("Calculadora");
		this.setSize(400, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		texto = new JTextField(30);
		texto.setBackground(Color.LIGHT_GRAY);
		
		texto.setFocusable(false);
		texto.setText("");
		texto.setEditable(true);
		
		buttonMaker(panel, botones);
		
		this.add(texto,"North");
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	void buttonMaker(JPanel panel, JButton[] botones)
	{
		panel.setLayout(new GridLayout(5, 4, 3, 3));
		//panel.setBackground(Color.cyan);
		String asigner[] = {"C", "%", "/", "x^n",
							"7", "8", "9", "X",
							"4", "5", "6", "-",
							"1", "2", "3", "+",
							"EXIT", "0", ".", "="};
		botones = new JButton[asigner.length];
		MyActionListener actionListener = new MyActionListener();

		for (int i = 0; i < asigner.length; i++)
		{
			botones[i] = new JButton(asigner[i]);
			botones[i].addActionListener(actionListener);
			botones[i].setBackground(Color.LIGHT_GRAY);
			
			panel.add(botones[i]);
		}
	}

	class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
	    {
			String press = e.getActionCommand();
			
	        switch (press)
	        {
	        case "C":
	        	input = "";
	        	texto.setText("");
	        	break;
	        case "EXIT":
	        	System.exit(0);
	        	break;
	        default:
	        	if (!isSpecial(press.charAt(0)) && !isSymbol(press.charAt(0)))
	        	{
	        		//if (input.charAt(input.length()-1) == '.' && press == ".")
	        		input = input.concat(press);
	        		texto.setText(input);
	        		counter = 1;
	        	}
	        	else if (isSymbol(press.charAt(0)) && counter != 0 && !isSpecial(press.charAt(0)))
	        	{
	        		input = input.concat(" " + press + " ");
	        		texto.setText(input + " ");
	        		counter = 0;
	        	}
	        	else if (isSpecial(press.charAt(0)))
	        	{
	        		String tmp[] = input.split(" ");
	        		for(int i = 0; i < tmp.length; i += 3)
	        		{
	        			if (resultado != 0)
	        				resultado = calculate(resultado+"", tmp[i], tmp[i+1]);
	        			else
	        				resultado = calculate(tmp[i], tmp[i+1], tmp[i+2]);
	        			System.out.println(resultado);
	        		}
	        		texto.setText(input +" = " + resultado);
		        	counter = 0;
		        	input = "";
	        	}
	        	break;
	        }
	    }
	}
	
	private static double calculate(String num1, String op, String num2)
	{
		double res = 0;
		switch (op)
		{
		case "+":
			res = (Double.parseDouble(num1) + Double.parseDouble(num2));
			break;
		case "-":
			res = (Double.parseDouble(num1) - Double.parseDouble(num2));
			break;
		case "X":
			res = (Double.parseDouble(num1) * Double.parseDouble(num2));
			break;
		case "/":
			res = (Double.parseDouble(num1) / Double.parseDouble(num2));
			break;
		case "%":
			res = (Double.parseDouble(num1) % Double.parseDouble(num2));
			break;
		case "^":
			res = (Math.pow(Double.parseDouble(num1), Double.parseDouble(num2)));
			break;
		}
		return res;
	}
	
	public boolean isSpecial(char c)
	{
		if (c == '=')
			return true;
		return false;
	}

	private static boolean isSymbol(char c)
	{
		if (c == '%' || c == '/' || c == '+' || c == '-' || c == 'X' || c == 'x')
			return true;
		return false;
	}
	
	public static boolean isUpper(char c)
	{
		if ((c >= 'A' && c <= 'Z'))
			return true;
		return false;
	}
	
	public static String printFormatException(String str)
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
    
	public static void main(String[] args)
	{
		try
		{
			myCalc calculator = new myCalc();
		}
		catch (Exception ex)
		{
			if (ex.getMessage() != null)
				System.out.println("Ha ocurrido el error: " + ex.getMessage() + "\nPor favor, vuelve a ejecutar el  programa.");
			else
			{
				String tmp = ex.toString();
				System.out.println("Ha ocurrido el error: " + printFormatException(tmp) + "\nPor favor, vuelve a ejecutar el  programa.");
			}
		}
	}
}
