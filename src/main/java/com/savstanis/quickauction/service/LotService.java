package com.savstanis.quickauction.service;

import com.savstanis.quickauction.model.Lot;
import com.savstanis.quickauction.model.LotStatus;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.repository.LotRepository;
import com.savstanis.quickauction.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {

    private final LotRepository lotRepository;
    private final UserService userService;

    @Autowired
    public LotService(LotRepository lotRepository, UserService userService) {
        this.lotRepository = lotRepository;
        this.userService = userService;
    }

    public Lot createLot(Lot lot) {
        User user = userService.findById(getCurrentUser().getId());
        lot.setSeller(user);

        return lotRepository.save(lot);
    }

    public List<Lot> getActive() {
        return lotRepository.findAllByLotStatus(LotStatus.ACTIVE);
    }

    public Lot findActiveById(Long id) {
        return lotRepository.findByIdAndAndLotStatus(id, LotStatus.ACTIVE);
    }

    private UserDetailsImpl getCurrentUser() {
        return ((UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
