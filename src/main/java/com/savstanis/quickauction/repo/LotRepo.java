package com.savstanis.quickauction.repo;

import com.savstanis.quickauction.domain.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepo extends JpaRepository<Lot, Long> {

}
