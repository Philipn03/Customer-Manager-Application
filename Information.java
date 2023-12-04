package labs.lab9;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Information extends JFrame{
	
	private JLabel name;
	private JTextField nameField;
	private JLabel email;
	private JTextField emailField;
	private JLabel pets;
	private JCheckBox dogCheckbox;
    private JCheckBox catCheckbox;
    private JCheckBox birdCheckbox;
    private JCheckBox fishCheckbox;
    private JCheckBox otherCheckbox;
    private JLabel amount;
    private JTextField amountField;
    private JLabel store;
    private JComboBox<String> storeComboBox;
    private JLabel notes;
    private JTextArea noteArea;
    private JButton save;
    private JButton n;
    private JButton delete;
    
    DefaultListModel<String> listModel;
    JList<String> myList;
    List<Customer> info;
    
    private String currentName;
	
	// Constructor create all buttons and fields
	public Information() {
		listModel =  new DefaultListModel<>();
		myList = new JList<>(listModel);
		info = new ArrayList<>();
		
		createNameField();
		createEmailField();
		createCheck();
		createAmountField();
		createComboMenu();
		createNoteArea();
		createButton();
		createDeleteButton();
		
		// listener so when a customer is CLICKED, we can access customer info
		myList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = myList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        // Retrieve the selected customer from the listModel
                        Customer selectedCustomer = info.get(selectedIndex);
                        currentName = selectedCustomer.getName();
                        System.out.println("Clicked:" + currentName);
                        // Update the UI components with the selected customer's information
                        nameField.setText(selectedCustomer.getName());
                        emailField.setText(selectedCustomer.getEmail());

                        dogCheckbox.setSelected(selectedCustomer.getLst().contains("Dog(s)"));
                        catCheckbox.setSelected(selectedCustomer.getLst().contains("Cat(s)"));
                        birdCheckbox.setSelected(selectedCustomer.getLst().contains("Bird(s)"));
                        fishCheckbox.setSelected(selectedCustomer.getLst().contains("Fish"));
                        otherCheckbox.setSelected(selectedCustomer.getLst().contains("Other"));

                        // Update amountField
                        amountField.setText(String.valueOf(selectedCustomer.getAmount()));

                        // Update storeComboBox
                        storeComboBox.setSelectedItem(selectedCustomer.getStore());

                        // Update noteArea
                        noteArea.setText(selectedCustomer.getNote());
                    }
                }
            }
        });
		
		// call function to actually create the layout
		createPanel();
	}
	
	// Creating the Name Field
	private void createNameField() {
		this.name = new JLabel("Name: ");
		final int FIELD_WIDTH = 10;
		this.nameField = new JTextField(FIELD_WIDTH);
		this.nameField.setText("");
	}
	
	// Display the Name Panel
	private JPanel createNamePanel() {
		JPanel namePanel = new JPanel();
		namePanel.add(this.name);
		namePanel.add(this.nameField);
		return namePanel;
	}
	
	// Creating the Email Field
	private void createEmailField() {
		this.email = new JLabel("Email: ");
		final int FIELD_WIDTH = 10;
		this.emailField = new JTextField(FIELD_WIDTH);
		this.emailField.setText("");
	}
	
	// Display the Email Panel
	private JPanel createEmailPanel() {
		JPanel emailPanel = new JPanel();
		emailPanel.add(this.email);
		emailPanel.add(this.emailField);
		return emailPanel;
	}
	
	// Creating the Check Options
	private void createCheck() {
		pets = new JLabel("Pets: ");
		dogCheckbox = new JCheckBox("Dog(s)");
        catCheckbox = new JCheckBox("Cat(s)");
        birdCheckbox = new JCheckBox("Bird(s)");
        fishCheckbox = new JCheckBox("Fish");
        otherCheckbox = new JCheckBox("Other");
	}
	
	// Display the Check Panel
	private JPanel createPetPanel() {
		JPanel petPanel = new JPanel();
		petPanel.add(pets);
		petPanel.add(dogCheckbox);
        petPanel.add(catCheckbox);
        petPanel.add(birdCheckbox);
        petPanel.add(fishCheckbox);
        petPanel.add(otherCheckbox);
        return petPanel;
	}
	
	// Creating the Amount Field
	private void createAmountField() {
		amount = new JLabel("Total Amount Spent: ");
		final int FIELD_WIDTH = 10;
		this.amountField = new JTextField(FIELD_WIDTH);
		this.amountField.setText("0.0");
	}
	
	// Display the Amount Panel
	private JPanel createAmountPanel() {
		JPanel amountPanel = new JPanel();
		amountPanel.add(amount);
		amountPanel.add(amountField);
		return amountPanel;
	}
	
	
	// Creating the Store Locations
	private void createComboMenu() {
		store = new JLabel("Home Store Location: ");
		String[] storeOptions = {"Irvine", "Los Angeles", "Paris", "Shanghai", "New York", "London"};
        this.storeComboBox = new JComboBox<>(storeOptions);
        this.storeComboBox.setSelectedItem("Irvine");
	}
	
	// Display the Store Menu Panel
	private JPanel createComboPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.add(store);
		menuPanel.add(storeComboBox);
		return menuPanel;
	}
	
	// Creating the Optional Notes Field
	private void createNoteArea() {
	    notes = new JLabel("Notes: ");
	    noteArea = new JTextArea(4, 19);  // Set 35 characters per line
	    noteArea.setLineWrap(true);
	    noteArea.setWrapStyleWord(true);
	    JScrollPane scroll = new JScrollPane(noteArea);
	}
	
	// Display the Note Panel
	private JPanel createNotePanel() {
		JPanel notePanel = new JPanel();
		notePanel.add(notes);
		notePanel.add(new JScrollPane(noteArea));
		return notePanel;
	}
	
	// When user clicks on Save button, function executes
	class AddSaveButton implements ActionListener{
		public void actionPerformed(ActionEvent event) { 
			int count = 0;
			////////////////////////////////////////////////////////////

			String nameFieldValue = nameField.getText();
			if (nameFieldValue.trim().isEmpty()) {
				count = 1;
	            JOptionPane.showMessageDialog(
	                    Information.this, 
	                    "Invalid input!", 
	                    "Error",  
	                    JOptionPane.WARNING_MESSAGE
	            );
	            return;
	        } 
			else {
	            System.out.println("Name: " + nameFieldValue);
	        }
			////////////////////////////////////////////////////////////
			
	        String emailFieldValue = emailField.getText();
	        if (emailFieldValue.trim().isEmpty()) {
	        	count = 1;
	            JOptionPane.showMessageDialog(
	                    Information.this,  // parent component
	                    "Invalid input!",  // message
	                    "Error",  // title
	                    JOptionPane.WARNING_MESSAGE
	            );
	            return;
	        } 
	        else {
	            System.out.println("Email: " + emailFieldValue);
	        }
	        
	        ////////////////////////////////////////////////////////////
	        
	        List<String> checks = new ArrayList<>();
	        if (dogCheckbox.isSelected()) {
	            System.out.println("Dog(s) selected");
	            checks.add(dogCheckbox.getText());
	        }
	        if (catCheckbox.isSelected()) {
	            System.out.println("Cat(s) selected");
	            checks.add(catCheckbox.getText());
	        }
	        if (birdCheckbox.isSelected()) {
	            System.out.println("Bird(s) selected");
	            checks.add(birdCheckbox.getText());
	        }
	        if (fishCheckbox.isSelected()) {
	            System.out.println("Fish selected");
	            checks.add(fishCheckbox.getText());
	        }
	        if (otherCheckbox.isSelected()) {
	            System.out.println("Other selected");
	            checks.add(otherCheckbox.getText());
	        }
	        
	        ////////////////////////////////////////////////////////////
	        
	        String amountText = amountField.getText();
	        double amountValue = 0;
	        try {
	            amountValue = Double.parseDouble(amountText);
	            System.out.println("Amount: " + amountValue);

	        } catch (NumberFormatException e) {
	        	count = 1;
	        	JOptionPane.showMessageDialog(
	                    Information.this,  // parent component
	                    "Invalid input!",  // message
	                    "Error",  // title
	                    JOptionPane.WARNING_MESSAGE
	            );
	            return;
	        }
	        ///////////////////////////////////////////////////////////////
	        
	        String selectedStore = storeComboBox.getSelectedItem().toString();
	        System.out.println(selectedStore);	        
	        
	        ////////////////////////////////////////////////////////////
	        
	        String noteText = noteArea.getText();
	        System.out.println(selectedStore);	
	        
	        ////////////////////////////////////////////////////////////
	        
	        if (count == 0) {
	        	Customer new_customer = new Customer(nameFieldValue, emailFieldValue, checks, amountValue, selectedStore, noteText);
	        	System.out.println("Print: " + new_customer.getName());
	        	System.out.println("After: " + currentName);
	        	for (int i = 0; i < info.size(); i++) {
	        		// iterate through all customers
                    Customer c = info.get(i);
                    
                    // If user tries to create new user with ALREADY NAME IN list
                    if (currentName == null && c.getName().equals(new_customer.getName())) {
                    	JOptionPane.showMessageDialog(
        	                    Information.this,  
        	                    "Invalid input!",  
        	                    "Error",  
        	                    JOptionPane.WARNING_MESSAGE
        	            );
                        return;
                    }
                    
                    // User edits existing customer
                    if (c.getName().equals(new_customer.getName()) && new_customer.getName().equals(currentName)) {
                    	info.set(i, new_customer);
                    	updateListModel();
                    	int newIndex = listModel.indexOf(new_customer.getName());
                    	myList.setSelectedIndex(newIndex);
                    	JOptionPane.showMessageDialog(
      	                    Information.this,  
      	                    "Customer saved!",  
      	                    "Success",  
      	                    JOptionPane.WARNING_MESSAGE
                    			);
                    	return;
                    }
                    
                    
                	// If user changes NAME to another name already in list
                    if (c.getName().equals(new_customer.getName()) && !c.getName().equals(currentName)) {
                    	JOptionPane.showMessageDialog(
      	                    Information.this,  // parent component
      	                    "Invalid input!",  // message
      	                    "Error",  // title
      	                    JOptionPane.WARNING_MESSAGE
                    			);
                    	return;
                    }
                    
                    // if NAME is the same
                    if (c.getName().equals(currentName)) {
                    	for (int j=0; j<info.size(); j++) {
                    		Customer k = info.get(j);
                    		// Check to make sure NEW NAME is not used 
                    		if(k.getName().equals(new_customer.getName())) {
                    			JOptionPane.showMessageDialog(
                  	                    Information.this,  // parent component
                  	                    "Invalid input!",  // message
                  	                    "Error",  // title
                  	                    JOptionPane.WARNING_MESSAGE
                                			);
                                	return;
                    		}
                    	}
                    	
                        // Update the existing customer at index i
                    	deleteNew(currentName);
                    	
                    	int insertionIndex = Collections.binarySearch(info, new_customer, Comparator.comparing(Customer::getName));
        	            if (insertionIndex < 0) {
        	                insertionIndex = -insertionIndex - 1; // Get the insertion point
        	            }
        	           
        	            info.add(insertionIndex, new_customer);
        	            
//                        info.set(i, new_customer);
                        updateListModel();
                        int newIndex = listModel.indexOf(new_customer.getName());
        	            myList.setSelectedIndex(newIndex);
                        JOptionPane.showMessageDialog(
        	                    Information.this,  
        	                    "Customer saved!",  
        	                    "Success",  
        	                    JOptionPane.WARNING_MESSAGE
        	            );
                        return;
                    }
                }

	        	
	        	int insertionIndex = Collections.binarySearch(info, new_customer, Comparator.comparing(Customer::getName));
	            if (insertionIndex < 0) {
	                insertionIndex = -insertionIndex - 1; // Get the insertion point
	            }
	           
	            info.add(insertionIndex, new_customer);

	            updateListModel();
	            int newIndex = listModel.indexOf(new_customer.getName());
	            myList.setSelectedIndex(newIndex);
	            JOptionPane.showMessageDialog(
	                    Information.this,  // parent component
	                    "Customer saved!",  // message
	                    "Success",  // title
	                    JOptionPane.WARNING_MESSAGE
	            );
	            return;

	        }
		}
	}
	
	private void deleteNew(String selectedName) {
		Customer customerToDelete = null;
		for (Customer customer : info) {
            if (customer.getName().equals(selectedName)) {
                customerToDelete = customer;
                break;
            }
        }

        if (customerToDelete != null) {
            // Remove the customer from the list model and custList
            listModel.removeElement(selectedName);
            info.remove(customerToDelete);
//            clearInfo();
        }
	}
	
	private void updateListModel() {
        // Get all names from the customerList
        List<String> names = new ArrayList<>();
        for (Customer customer : info) {
            names.add(customer.getName());
        }

        // Sort the names alphabetically
        Collections.sort(names);

        // Clear the existing elements in the listModel
        listModel.clear();
        myList.clearSelection();
        // Add the sorted names to the listModel
        for (String name : names) {
            listModel.addElement(name);
        }
    }
	
	// Reset customer information
	private void clearInfo() {
		nameField.setText("");
		emailField.setText("");	
		amountField.setText("0.0");
		dogCheckbox.setSelected(false);
        catCheckbox.setSelected(false);
        birdCheckbox.setSelected(false);
        fishCheckbox.setSelected(false);
        otherCheckbox.setSelected(false);
        storeComboBox.setSelectedItem("Irvine");
        noteArea.setText("");
        myList.clearSelection();
        currentName = null;
	}
	
	// When user clicks on New button, function executes
	class AddNewButton implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			clearInfo();
		}
	}
	
	// When user clicks on Delete Button, function executes
	class AddDeleteButton implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			int selectedIndex = myList.getSelectedIndex();

	        if (selectedIndex != -1) {
	            String selectedName = listModel.getElementAt(selectedIndex);

	            // Find the customer in the custList based on the selected name
	            Customer customerToDelete = null;
	            for (Customer customer : info) {
	                if (customer.getName().equals(selectedName)) {
	                    customerToDelete = customer;
	                    break;
	                }
	            }

	            if (customerToDelete != null) {
	                // Remove the customer from the list model and custList
	                listModel.removeElement(selectedName);
	                info.remove(customerToDelete);
	                clearInfo();
	            }
	        }
		}
		
	}
	
	// Creating the Save and New Customer buttons
	private void createButton() {
		save = new JButton("Save Customer");
		ActionListener listener = new AddSaveButton();
		save.addActionListener(listener);
		
		n = new JButton("New Customer");
		ActionListener listener2 = new AddNewButton();
		n.addActionListener(listener2);
		
	}
	
	// Displaying the Save and New Customer buttons
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(save);
		buttonPanel.add(n);
		return buttonPanel;
	}
	
	// Creating the Delete button
	private void createDeleteButton() {
		delete = new JButton("Delete");
		ActionListener listener = new AddDeleteButton();
		delete.addActionListener(listener);
	}
	
	// Displaying the Delete button
	private JPanel createDeletePanel() {
		JPanel deletePanel = new JPanel();
		deletePanel.add(delete);
		return deletePanel;
	}
	
	// This function actually creates the display
	private void createPanel() {
		JPanel container = new JPanel(new GridLayout(1, 2));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
	    JScrollPane listScrollPane = new JScrollPane(myList);
	    leftPanel.add(listScrollPane, BorderLayout.CENTER);
	    leftPanel.add(createDeletePanel(), BorderLayout.SOUTH);
	    container.add(leftPanel);
	    
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1));
		panel.add(createNamePanel());
		panel.add(createEmailPanel());
		panel.add(createPetPanel());
		panel.add(createAmountPanel());
		panel.add(createComboPanel());
		panel.add(createNotePanel());
		panel.add(createButtonPanel());
		container.add(panel);
		
		add(container);
	}
}