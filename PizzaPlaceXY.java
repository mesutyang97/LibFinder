import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PizzaPlaceXY implements ActionListener{
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
		new PizzaPlaceXY();
	}
	
	public PizzaPlaceXY(){
		JFrame totalFrame = new JFrame();
		totalFrame.setTitle("Pizza Place");
		totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		totalFrame.add(totalPanel);
		
		//Get Customer Name
		JPanel GetCustNamePanel = new JPanel();
		GetCustNamePanel.add(new JLabel("Customer Name: "));
		custName = new JTextField(20);
		custName.addActionListener(this);
		GetCustNamePanel.add(custName);
		totalPanel.add(GetCustNamePanel);
		
		//Pizza Size:
		JPanel PiSi = new JPanel();
		ButtonGroup PizzaSize = new ButtonGroup();
		PizzaSize.add(Small);
		PizzaSize.add(Medium);
		PizzaSize.add(Large);
		
		PiSi.add(Small);
		PiSi.add(Medium);
		PiSi.add(Large);
		totalPanel.add(PiSi);	
		
		//Type of Crust
		JPanel CrTy = new JPanel();
		CrTy.add(new JLabel("Crust Type: "));
		CrTy.add(Crust);
		totalPanel.add(CrTy);
		
		//Pizza Toppings
		JPanel PiTo = new JPanel();
		PiTo.add(new JLabel("Toppings: "));
		ToppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		JScrollPane ToScroll = new JScrollPane(ToppingList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		PiTo.add(ToScroll);
		
		PiTo.add(ToppingList);
		totalPanel.add(PiTo);
		
		//extra items
		JPanel ExItem = new JPanel();
		ExItem.add(new JLabel("Extras: "));
		ExItem.add(BreadSticks);
		ExItem.add(Salad);
		ExItem.add(Soda);
		totalPanel.add(ExItem);
		
		//Customer Comments
		JPanel CuCom = new JPanel();
		CuCom.add(new JLabel("Order Comments: "));
		customerComments = new JTextArea(5,20);
		customerComments.setLineWrap(true);
		JScrollPane cuComScroll = new JScrollPane(customerComments, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CuCom.add(cuComScroll);
		totalPanel.add(CuCom);
		
		//Place Order or Reset
		JPanel Buttoms = new JPanel();
		Buttoms.add(PlaceOrder);
		PlaceOrder.addActionListener(this);
		Buttoms.add(ResetValues);
		ResetValues.addActionListener(this);
		totalPanel.add(Buttoms);
		
		totalFrame.pack();
		totalFrame.setVisible(true);
		
		
		
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
