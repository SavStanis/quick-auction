package com.savstanis.quickauction.repository;

import com.savstanis.quickauction.model.Lot;
import com.savstanis.quickauction.model.LotStatus;
import com.savstanis.quickauction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LotRepository extends JpaRepository<Lot, Long> {

    Lot findByIdAndAndLotStatus(Long id, LotStatus lotStatus);

    List<Lot> findBySeller(User user);

    List<Lot> findAllByLotStatus(LotStatus lotStatus);

}
