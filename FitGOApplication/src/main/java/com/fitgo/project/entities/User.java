package com.fitgo.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String email;
private String password;
private String userType;

// links the user table with the other tables (Address, FitGoPlans,
// TransactionHistory)
// mappedBy-inverse side mirrors the relationship but doesn’t interfere with the
// join table management
@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
private Address address;

@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
private FitGoPlans currentPlan;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<TransactionHistory> history;

public User() {
	super();
}

public User(Long id, String name, String email, String password, String userType, Address address,
		FitGoPlans currentPlan, List<TransactionHistory> history) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.userType = userType;
	this.address = address;
	this.currentPlan = currentPlan;
	this.history = history;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getUserType() {
	return userType;
}

public void setUserType(String userType) {
	this.userType = userType;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public FitGoPlans getCurrentPlan() {
	return currentPlan;
}

public void setCurrentPlan(FitGoPlans currentPlan) {
	this.currentPlan = currentPlan;
}

public List<TransactionHistory> getHistory() {
	return history;
}

public void setHistory(List<TransactionHistory> history) {
	this.history = history;
}

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", userType="
			+ userType + ", address=" + address + ", currentPlan=" + currentPlan + ", history=" + history + "]";
}



}