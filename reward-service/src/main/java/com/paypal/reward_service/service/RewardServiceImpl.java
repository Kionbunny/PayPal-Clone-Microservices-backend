package com.paypal.reward_service.service;


import com.paypal.reward_service.entity.Reward;
import com.paypal.reward_service.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;


    //service is using repository to save the data
    public Reward sendReward(Reward reward) {

        /* We are NOT preventing new transactions,
        we are preventing SAME transaction being processed multiple times.
        */

        //If Reward already given for this transaction
        if (rewardRepository.existsByTransactionId(reward.getTransactionId())) {
            System.out.println("⚠️ Reward already exists for txn: " + reward.getTransactionId());
            return reward;
        }
        reward.setSentAt(LocalDateTime.now());
        //If the reward is not given for transaction, then create a new reward and save to DB
        return rewardRepository.save(reward);
    }

    public List<Reward>getRewardsByUserId(Long UserId){
        return rewardRepository.findByUserId(UserId);
    }
    public List<Reward>getAllRewards(){
        return rewardRepository.findAll();
    }
}
