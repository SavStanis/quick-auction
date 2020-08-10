package com.savstanis.quickauction.repository;

import com.savstanis.quickauction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Long> {

}
