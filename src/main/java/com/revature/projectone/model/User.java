package com.revature.projectone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
@Component
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart cart = new Cart();

}