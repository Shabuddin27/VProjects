package com.fitgo.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String houseNumber;
private String city;
private String pincode;
private String country;

// the "owning side” of a relationship takes charge of managing and updating the
// join table.
@OneToOne
@JoinColumn(name = "userid")
private User user;

}