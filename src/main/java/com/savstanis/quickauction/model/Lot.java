package com.savstanis.quickauction.model;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 10, max = 300)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 10, max = 1000)
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LotStatus lotStatus = LotStatus.ACTIVE;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Positive
    @NotNull
    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer = null;

    @CreationTimestamp
    @Column(name = "created")
    private Date created;

}
