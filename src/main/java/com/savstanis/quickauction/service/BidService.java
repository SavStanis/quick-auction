package com.savstanis.quickauction.service;

import com.savstanis.quickauction.dto.Bid.CreationBidDto;
import com.savstanis.quickauction.exceptions.BadRequestException;
import com.savstanis.quickauction.model.*;
import com.savstanis.quickauction.repository.BidRepository;
import com.savstanis.quickauction.repository.LotRepository;
import com.savstanis.quickauction.repository.UserRepository;
import com.savstanis.quickauction.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BidService {

    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    private final LotRepository lotRepository;

    @Autowired
    public BidService(BidRepository bidRepository, UserRepository userRepository, LotRepository lotRepository) {
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
        this.lotRepository = lotRepository;
    }

    @Transactional
    public Bid createBid(CreationBidDto creationBidDto) throws BadRequestException {
        User user = userRepository.findById(getCurrentUser().getId()).orElse(null);
        if (user == null) {
            throw new BadRequestException("Invalid user");
        }

        Lot lot = lotRepository.findById(creationBidDto.getLotId()).orElse(null);
        if (lot == null
                || !lot.getLotStatus().equals(LotStatus.ACTIVE)
                || lot.getSeller().getId().equals(user.getId())) {
            throw new BadRequestException("Invalid lot id");
        }

        Bid bid = new Bid();
        bid.setValue(creationBidDto.getValue());
        bid.setLot(lot);

        if (user.getBalance() < bid.getValue() || lot.getPrice() >= bid.getValue()) {
            throw new BadRequestException("You don't have enough coins!");
        }

        Bid activeBid = getActiveBidByLot(lot);

        if (activeBid != null) {
            if (activeBid.getValue() >= bid.getValue()) {
                throw new BadRequestException("You don't have enough coins!");
            }

            if (activeBid.getUser().getId().equals(user.getId())) {
                throw new BadRequestException("You can't make a bid now");
            }

            activeBid.setStatus(BidStatus.INACTIVE);

            User activeBidUser = activeBid.getUser();
            activeBidUser.setBalance(activeBidUser.getBalance() + activeBid.getValue());

            bidRepository.save(activeBid);
            userRepository.save(activeBidUser);
        }

        user.setBalance(user.getBalance() - bid.getValue());
        bid.setUser(user);
        lot.setPrice(bid.getValue());

        lotRepository.save(lot);
        userRepository.save(user);
        bidRepository.save(bid);

        return bid;
    }

    private Bid getActiveBidByLot(Lot lot) {
        return bidRepository.findByLotAndStatus(lot, BidStatus.ACTIVE);
    }

    private UserDetailsImpl getCurrentUser() {
        return ((UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
