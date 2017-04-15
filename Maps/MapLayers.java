package Maps;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class MapLayers implements ActionListener{
	/** The name of book. */
	String bookName;
	/** The call number. */
	String callNum;
	
	/** The list of FLOOR Objects. */
	List<Floor> flrL;
	/** The index for floor it the book is on. */
	int flr; 
	/** Width of each floor on the screen. */
	int flr_w = 30;
	/** Length of each floor on the screen. */
	int flr_l = 10;
	/** Gap between floors. */
	int flr_g = 50;
	
	/** LinkedList of floor button. */
	LinkedList<Rectangles> flrBt;
	
	
	
	/* The list of SECTION Objects. */
	List secL;
	/* The index for section in FLR the book is on. */
	int sec;
	
	/* The list of SHELF Objects. */
	List slfL;
	/* The index for shelf in SEC the book is on. */
	int slf;
	
	/* The list of COLUMN Objects. */
	List colL;
	/* The index for column in SLF the book is on. */
	int col;
	
	/* !!! The number of rows in the column, since the row is an estimation.  */
	int NumRows;
	/* The index for row in COL the book is on. */
	int row;
	
	
	
	
	JTextField custName = null;
	String name;
	
	JRadioButton Small = new JRadioButton("Small");
	JRadioButton Medium = new JRadioButton("Medium");		
	JRadioButton Large = new JRadioButton("Large");
	String pizzaSize = null;
	
	String [] CrustChoice = {"Thin", "Thick", "Deep Dish"};
	JComboBox Crust = new JComboBox(CrustChoice);
	String crust = null;
	
	String [] ToppingChoice = {"Pepperoni", "Sausage", "Green Peppers", "Onions", "Tomatoes", "Anchovies"};
	JList ToppingList = new JList(ToppingChoice);
	String topping = "";
	
	JCheckBox BreadSticks = new JCheckBox ("BreadSticks");
	JCheckBox Salad = new JCheckBox ("Salad");
	JCheckBox Soda = new JCheckBox ("Soda");
	String extraItems = "";
	
	JTextArea customerComments = null;
	
	JButton PlaceOrder  = new JButton("Place Order");
	JButton ResetValues = new JButton("Reset Values");
	
	String summary = null;
	
	public static void main(String[] args) {
		LinkedList<Floor> qwa = new LinkedList(java.util.Arrays.asList(new Floor("dfs"), new Floor("bfs")));
		
		new MapLayers(qwa, 1);
	}
	
	public MapLayers(List<Floor> fL, int f){
		flrL = fL;
		flr = f; 
		JFrame totalFrame = new JFrame();
		totalFrame.setTitle("Libray Maps");
		totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel totalPanel = new JPanel();
		
		totalPanel.setLayout(new BorderLayout());

		
		
		totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		totalFrame.add(totalPanel);
		
		
		
		/* Floor Printout. */
		
		
		Rectangles rect = new Rectangles(fL, f);
		
		totalPanel.add(rect);
		
		
		//Get Customer Name
		JPanel GetCustNamePanel = new JPanel();
		GetCustNamePanel.add(new JLabel("Customer Name: "));
		custName = new JTextField(20);
		custName.addActionListener(this);
		GetCustNamePanel.add(custName);
		//totalPanel.add(GetCustNamePanel);

		
		
		//Place Order or Reset
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(PlaceOrder);
		PlaceOrder.addActionListener(this);
		buttonPanel.add(ResetValues);
		ResetValues.addActionListener(this);
		totalPanel.add(buttonPanel);
		
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton clickmeButton = new JButton("Click Me");
		buttonPanel.add(clickmeButton);
		totalPanel.add(buttonPanel,BorderLayout.SOUTH);
		
		totalFrame.setSize(600, 600);;
		totalFrame.setVisible(true);
		
		
		
	}
	
	
	
	
	public class Rectangles extends JPanel {
		List<DisplayObj> L;
		int index;
		
		
		public Rectangles(List Lst, int indexGood) {
			L = Lst;
			index = indexGood;
		}
		
		public void paintComponent(Graphics g) {
			System.out.print("sss");
		    super.paintComponent(g);
		    
		    Graphics2D g2d = (Graphics2D) g;

		    
		    //flrBt = new LinkedList <Rectangles>();
			int curDepth = 10;
			for (Floor flr_i : flrL){
				g2d.drawRect(5, curDepth, 500, 500);
				//flrBt.add(new Rectangles(curDepth, 10, flr_w, flr_l));
				curDepth += (flr_l + flr_g);
				System.out.println (curDepth);
			}
			
			/* The target floor button. */
			//targetFlrBt = flrBt.get(flr);
			
			/* Set the listener to listen to the button with the right floor. */
			//targetFlrBt.addMouseListener((MouseListener) this);
			
			//for(Rectangles flr_c: flrBt){
			//	totalFrame.add(flr_c);
			//	FloorPanel.add(new JButton("sfk"));
			//	System.out.println ("1");
			//}
			
		    
		    
		    
		    /*
			g2d.setColor(new Color(212, 212, 212));
		    g2d.drawRect(x, y, w, l);

		    g2d.setColor(new Color(31, 21, 1));
		    g2d.fillRect(x, y, w, l);
		    */

		  }
	}
	
	
	
	
	public void actionPerformed (ActionEvent event){
		Object control = event.getSource();
		
		
		//Customer Name
		if (control == PlaceOrder){
			name = custName.getText();
			summary = "PIZZA ORDER FOR: \n"+ name + "\n";
			
			if (Small.isSelected() == true){
				pizzaSize = "Small";
			}
			else if (Medium.isSelected() == true){
				pizzaSize = "Medium";
			}
			else if (Large.isSelected() == true){
				pizzaSize = "Large";
			}
			summary = summary + "SIZE: \n" + pizzaSize + "\n";
			
		//Crust
			crust = (String)Crust.getSelectedItem();
			summary = summary + "CRUST TYPE: \n" + crust + "\n";
			
		//Toppings
			
			Object [] toppingSelected = ToppingList.getSelectedValues();
			for (int i = 0; i< toppingSelected.length; i++)  
			 {

			       topping = topping + (String) toppingSelected[i] + "\n";
			 } 
			summary = summary + "TOPPINGS: \n" + topping + "\n";
			
			
		//Extras
			if (BreadSticks.isSelected() == true){
				extraItems = extraItems + "BreadSticks" + "\n";
			}
			if (Salad.isSelected() == true){
				extraItems = extraItems + "Salad" + "\n";
			}
			if (Soda.isSelected() == true){
				extraItems = extraItems + "Soda" + "\n";
			}
			summary = summary + "EXTRAS: \n" + extraItems + "\n";
			
			
		//Comments
			summary = summary + "COMMENTS: " + customerComments.getText();
			JOptionPane.showInputDialog(summary);
		}
		if (control == ResetValues){
			custName.setText("");
			Small.setSelected(true);
			Crust.setSelectedIndex(0);
			ToppingList.clearSelection();
			BreadSticks.setSelected(false);
			Salad.setSelected(false);
			Soda.setSelected(false);
			customerComments.setText("");
		}

	}
	
	
}
