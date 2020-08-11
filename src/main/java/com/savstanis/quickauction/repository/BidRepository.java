package com.savstanis.quickauction.repository;

import com.savstanis.quickauction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
