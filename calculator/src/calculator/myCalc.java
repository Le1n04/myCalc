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
		this.setSize(600, 300);
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
							"N/A", "0", ".", "="};
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
	        case "N/A":
	        	break;
	        default:
	        	if (!isSymbol(press.charAt(0)))
	        	{
	        		if (!IsSpecial(press.charAt(0)))
	        		{
	        			if (counter == 0 || counter == 2)
	        			{
			        		input = input.concat(press);
							texto.setText(input + " ");
							counter++;
				        	input = input.concat(" ");
	        			}
	        		}
	        	}
	        	else
	        	{
	        		if (isSymbol(press.charAt(0)))
	        		{
	        			if (counter == 1)
	        			{
			        		if (press != "x^n")
				        		input = input.concat(press);
			        		else
			        			input = input.concat("^");
		        			texto.setText(input + " ");
			        		counter++;
				        	input = input.concat(" ");
	        			}
	        		}
	        	}
	        	break;
	        }
	        
	        if (counter == 3)
	        {
	        	String tmp[] = input.split(" ");
	        	resultado = calculate (tmp[0], tmp[1], tmp[2]);
	        	texto.setText(input +" = " + resultado);
	        	counter = 0;
	        	input = "";
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
	
	public boolean IsSpecial(char c)
	{
		if (c == '.' || c == '=')
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
