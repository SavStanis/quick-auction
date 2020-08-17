package com.savstanis.quickauction;

import com.savstanis.quickauction.model.*;
import com.savstanis.quickauction.repository.BidRepository;
import com.savstanis.quickauction.repository.LotRepository;
import com.savstanis.quickauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;

    @Autowired
    public Scheduler(LotRepository lotRepository, BidRepository bidRepository, UserRepository userRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Scheduled(fixedDelay = 2000)
    public void checkActiveLots() {
        System.out.println("Hello Scheduler");
        List<Lot> activeLots = lotRepository.findAllByLotStatus(LotStatus.ACTIVE);

        Date now = new Date();

        for (Lot activeLot: activeLots) {
            if (activeLot.getFinishDate().getTime() > now.getTime()) {
                continue;
            }

            Bid bidWinner = bidRepository.findByLotAndStatus(activeLot, BidStatus.ACTIVE);

            if (bidWinner == null) {
                activeLot.setLotStatus(LotStatus.NOT_SOLD);
                lotRepository.save(activeLot);

                continue;
            }
            bidWinner.setStatus(BidStatus.WON);

            activeLot.setLotStatus(LotStatus.SOLD);
            User seller = activeLot.getSeller();
            seller.setBalance(seller.getBalance() + bidWinner.getValue());

            lotRepository.save(activeLot);
            userRepository.save(seller);
            bidRepository.save(bidWinner);
        }

    }
}
