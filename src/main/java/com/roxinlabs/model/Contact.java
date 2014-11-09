package com.roxinlabs.model;

public class Contact {

	private int Id;
	private String Name;
	private String Email;
	private String Address;
	private String Telephone;
	
	public Contact() {
		
	}
	
	public Contact(String name, String email, String address, String telephone) {
		this.Name = name;
		this.Email = email;
		this.Address = address;
		this.Telephone = telephone;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	
	
}
