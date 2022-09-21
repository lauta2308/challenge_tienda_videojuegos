package com.tiendavideojuegos.challenge_tienda_videojuegos.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @ManyToMany
    @JoinColumn(name = "client_id")
    private List <Client> players;

    private Double amountBet;

    private String results;

    private MatchesStatus matchesStatus;




}
