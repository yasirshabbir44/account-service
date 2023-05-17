package com.example.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Account {


    @Id
    private String id;
    private String name;
    private Double balance;

}