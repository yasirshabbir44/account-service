package com.example.customer.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Account {

    private String id;
    private String name;
    private Double balance;

}