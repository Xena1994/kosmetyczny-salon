package com.example.salon_kosmetyczny.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pracownicy")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    @Size(min = 1, max = 50)
    private String firstName;
    @Column(name="last_name")
    @Size(min = 1, max = 50)
    private String lastName;



}
