package pojo;

import java.util.ArrayList;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String contactNo;
	private String emailID;
	private int userMasterID;
	private boolean active;
	private String password;
	private String newPassword;
	
	private ArrayList<User> arrayList = new ArrayList<User>();
	
	

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public int getUserMasterID() {
		return userMasterID;
	}

	public void setUserMasterID(int userMasterID) {
		this.userMasterID = userMasterID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<User> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<User> arrayList) {
		this.arrayList = arrayList;
	}

	
	
}
