package com.savstanis.quickauction.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Data
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column(name = "value")
    private Integer value;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private BidStatus status = BidStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created")
    private Date created;
}
