package com.revature.projectone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
     @ManyToMany
    @JoinTable(name = "cartList", joinColumns = @JoinColumn(name = "cartId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
    private List<Item> cartList;

}

