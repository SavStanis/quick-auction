package com.savstanis.quickauction.repository;

import com.savstanis.quickauction.model.Bid;
import com.savstanis.quickauction.model.BidStatus;
import com.savstanis.quickauction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Bid findByLotAndStatus(Lot lot, BidStatus bidStatus);
}
