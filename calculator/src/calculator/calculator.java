
package calculator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class calculator extends JFrame{ // class calculator extends Jframe means this is a GUI app
	private boolean start = true; // It is a flag that indicates whether the calculator is in the initial state => start is true, calculator is starting a new operation
	private double result = 0; // It holds the current result of arithmetic operations.
	private String command = "="; //  It represents the current arithmetic operation to be performed => if + is pressed command is set to '+'
	private JTextField jTextField; // It is a text field where the user input and calculator output are displayed. 
	private JPanel jPanel = new JPanel(); // It is a container for organizing and holding the calculator buttons. 
	private JButton[] jButtons; //  It is an array to store the individual calculator buttons.
	
	public calculator() {
		//this es una keyword de java, no es un nombre personalizado, https://www.w3schools.com/java/ref_keyword_this.asp
		this.setTitle("Calculator"); // titulo de la ventana
		this.setSize(600, 300); // resolucion de la ventana
		this.setLocationRelativeTo(null); // lugar de la ventana, si es null va al centro
		
		jTextField = new JTextField(30); // la barra de input se inicia en 30 para saber los caracteres que se veran
		jTextField.setText(""); // el texto se inicia vacio
		jTextField.setEditable(true); // asi es editable
		this.add(jTextField,"North"); // esto le pone la barra en el norte de la ventana ()
		
		jPanel.setLayout(new GridLayout(5,4,3,3)); // el layout es 5 filas, 4 columnas, espacio entre botones, espacio entre botones.
		String name[] = { // el array de botones de 5x4
				"C","/","*","Back",
				"7","8","9","-","4","5",
				"6","+","1","2","3","%",
				"time","0",".","="
		};
		jButtons = new JButton[name.length]; // It is an array to store the individual calculator buttons.
		MyActionListener actionListener= new MyActionListener(); // funcion que escuchara a la acccion
		
		for(int i = 0; i < name.length; i++) { //bucle que pasa por todo String name[]
			
			jButtons[i] = new JButton(name[i]); // crea el boton en la posicion 'i'
			jButtons[i].addActionListener(actionListener); // al boton le mete un actionlistener
			
			jButtons[i].setBackground(Color.lightGray); // le pone un color de fondo en este caso gris claro
			if(name[i].equals("=")) // al signo de igual le pone color rojo
				jButtons[i].setBackground(Color.RED); // aqui
			else if((int)name[i].charAt(0)>=48 && (int)name[i].charAt(0)<=57 // a los numeros le pone el color blanco
					&& name[i].length() == 1) // mira que sean de 1 de longitud
				jButtons[i].setBackground(Color.WHITE); //color blanco
			else if(name[i].equals("Back")) // si el boton es back
				jButtons[i].setBackground(Color.GRAY); //gris total
			
			jPanel.add(jButtons[i]);// le mete al panel gui el boton que le toque
		}
		
		this.add(jPanel); // mete el panel que acabamos de crear en el bucle
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // se usa para que darle a la X cierre la mquina virtual
		this.setVisible(true); // hace que se vea la ventana
		
	}
	
	class MyActionListener implements ActionListener{ // crea una nueva clase
		public void actionPerformed(ActionEvent e) {
			
			String input = e.getActionCommand();
			if(start) {
				if((int)input.charAt(0)>=48 && (int)input.charAt(0)<=57
						&& input.length() == 1 ) {
					jTextField.setText(""+input);
				}			
			}
					start = false;
			if(input.equals("C"))
			{
				result = 0.0;
				jTextField.setText("");
			}
			else if(input.equals("%")) {
				result = Double.parseDouble(jTextField.getText())/ 100.0;
				jTextField.setText(""+getPrettyNumber(Double.toString(result)));
				start = true;	
			}
			else if(input.equals("time")) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jTextField.setText(df.format(System.currentTimeMillis()));
				start = true;
			}
			
			else {
				if(!start) {
					
					if(command.equals("+"))
						result += Double.parseDouble(jTextField.getText());
					else if(command.equals("-"))
						result -= Double.parseDouble(jTextField.getText());
					else if(command.equals("*"))
						result *= Double.parseDouble(jTextField.getText());
					else if(command.equals("/")) {
						if(Double.parseDouble(jTextField.getText()) != 0) {
							result /= Double.parseDouble(jTextField.getText());
							}else {
								jTextField.setText(""+"Division en cero");
								JOptionPane.showMessageDialog(null, "Division en cero", "Error!", JOptionPane.ERROR_MESSAGE);
								command = "=";
								start = true;
								throw new ArithmeticException("division by 0");
							}
						
					}
					else if(command.equals("=")) 
						result = Double.parseDouble(jTextField.getText());
					
					jTextField.setText(""+getPrettyNumber(Double.toString(result)));
					command = input;
					start = true;
				}
			}
			}
	}
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }
    public static int factorial(int num) {
    	int sum = 1;
        for(int i = 1;i <= num; i++){
            sum *= i;
        }
        return sum;
    }
    
	public static void main(String[] args) {
		calculator calculator = new calculator();

	}

}