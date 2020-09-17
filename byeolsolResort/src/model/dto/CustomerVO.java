package model.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerVO {

	private Integer id;
	@NotNull(message = "공백은 없어야 합니다.")
	@Pattern(regexp = "(^(?!admin).*)")
	@Pattern(regexp = "[a-zA-Z]{2}+[a-z0-9]{3,10}")
	private String userId;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{9,14}$")
	private String password;
	
	@NotEmpty
	@Pattern(regexp = "^[가-힣]{2,6}|[a-zA-Z]{2,10}||s[a-zA-Z]{2,10}$")
	private String name;
	
	@NotNull
	private Integer zipCode;
	@Email (message = "이메일 형식에 맞지 않습니다.")
	private String email;
	@NotEmpty(message = "공백이 되면 안됩니다.")
	private String address;
	private String addressDetail;
	@NotEmpty
	@Pattern(regexp = "^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$")
	private String phone;
	private String emailState;
	private LocalDate birthDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
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
		return "CustomerVO [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name
				+ ", zipCode=" + zipCode + ", email=" + email + ", address=" + address + ", addressDetail="
				+ addressDetail + ", phone=" + phone + ", emailState=" + emailState + ", birthDate=" + birthDate + "]";
	}
	
	
	
}
