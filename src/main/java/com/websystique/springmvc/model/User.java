package com.websystique.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_USER")
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;
	
	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	@NotEmpty
	@Column(name="COMPANY", nullable=false)
	private String company;
	
	@NotEmpty
	@Column(name="PHONE",nullable=false)
	private String phone;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="REGISTER_USER")
	private RegisterUser registerUser;
	
	@Column(name ="IS_DELETED")
	private int isDeleted;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserDocument> userDocuments = new HashSet<UserDocument>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserDocument> getUserDocuments() {
		return userDocuments;
	}

	public void setUserDocuments(Set<UserDocument> userDocuments) {
		this.userDocuments = userDocuments;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RegisterUser getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", company=" + company + ", phone=" + phone + ", registerUser=" + registerUser
				+ ", isDeleted=" + isDeleted + ", userDocuments=" + userDocuments + "]";
	}

}
