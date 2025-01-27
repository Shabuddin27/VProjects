package com.fitgo.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransactionHistory {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String product;
private String timeStamp;
private double amount;

@ManyToOne
@JoinColumn(name = "userid")
private User user;

}