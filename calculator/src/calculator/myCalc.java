package calculator;

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
		this.setLocationRelativeTo(null);
		
		texto = new JTextField(30);
		texto.setText("");
		texto.setEditable(true);
		this.add(texto,"North");
		
		panel.setLayout(new GridLayout(5, 4, 3, 3));
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
			
			panel.add(botones[i]);
		}
		
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
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
	        		if (!isSpecial(press.charAt(0)))
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
			        		input = input.concat(press);
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
	        	System.out.println("Resultado: " + resultado);
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
		case "x^n":
			res = (Math.pow(Double.parseDouble(num1), Double.parseDouble(num2)));
			break;
		}
		return res;
	}
	
	private static boolean isSpecial(char c)
	{
		if (c == '.' || c == '=')
			return true;
		return false;
	}
	
	private static boolean isSymbol(char c)
	{
		if (c == '%' || c == '/' || c == '+' || c == '-' || c == 'X')
			return true;
		return false;
	}
    
	public static void main(String[] args)
	{
		myCalc calculator = new myCalc();
	}
}
