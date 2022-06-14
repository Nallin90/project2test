package com.revature.projectone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private int userId;

    @ManyToMany
    @JoinTable(name = "orderList", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
    private List<Item> orderList;

}
