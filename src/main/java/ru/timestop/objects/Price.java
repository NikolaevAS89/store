package ru.timestop.objects;

import javax.persistence.*;

/**
 * @author Tor
 * @version 1.0.0
 * @since 19.08.2018
 */
@Entity
@Table(name="market")
@NamedQueries({@NamedQuery(name = "Market.getAll", query = "select m from market m")})
public class Price {
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="name", unique = true)
    private String name;
}
