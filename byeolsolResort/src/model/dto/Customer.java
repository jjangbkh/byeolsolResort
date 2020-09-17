package model.dto;

import java.time.LocalDate;


public class Customer {

	private int id;
	private String userId;
	private String password;
	private String name;
	private int zipCode;
	private String email;
	private String address;
	private String addressDetail;
	private String phone;
	private String emailState;
	private LocalDate birthDate;
	
	public Customer() {	}

	public Customer(int id, String userId, String password, String name, int zipCode, String email, String address,
			String addressDetail, String phone, String emailState, LocalDate birthDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.zipCode = zipCode;
		this.email = email;
		this.address = address;
		this.addressDetail = addressDetail;
		this.phone = phone;
		this.emailState = emailState;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailState() {
		return emailState;
	}

	public void setEmailState(String emailState) {
		this.emailState = emailState;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", zipCode="
				+ zipCode + ", email=" + email + ", address=" + address + ", addressDetail=" + addressDetail
				+ ", phone=" + phone + ", emailState=" + emailState + ", birthDate=" + birthDate + "]";
	}

	
	
	
	
	
}
