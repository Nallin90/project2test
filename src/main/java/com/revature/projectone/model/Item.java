package com.revature.projectone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name="items")
@Component
@AllArgsConstructor
@NoArgsConstructor


public class Item {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int itemId;
private String itemName;
private double price;
}
