package labs.lab9;

import java.util.List;

public class Customer{
	private String name;
	private String email;
	private List<String> lst;
	private double amount;
	private String store;
	private String note;
	
	
	public Customer(String n, String e, List<String> l, double a, String s, String not) {
		name = n;
		email = e;
		lst = l;
		amount = a;
		store = s;
		note = not;
	}
	
	   public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    // Getter and Setter methods for 'email'
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    // Getter and Setter methods for 'lst'
	    public List<String> getLst() {
	        return lst;
	    }

	    public void setLst(List<String> lst) {
	        this.lst = lst;
	    }

	    // Getter and Setter methods for 'amount'
	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    // Getter and Setter methods for 'store'
	    public String getStore() {
	        return store;
	    }

	    public void setStore(String store) {
	        this.store = store;
	    }

	    // Getter and Setter methods for 'note'
	    public String getNote() {
	        return note;
	    }

	    public void setNote(String note) {
	        this.note = note;
	    }
	
}