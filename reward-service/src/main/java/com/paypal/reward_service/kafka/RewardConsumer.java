package com.paypal.reward_service.kafka;
import com.paypal.reward_service.dto.Transaction;
import com.paypal.reward_service.entity.Reward;
import com.paypal.reward_service.service.RewardService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class RewardConsumer {
    private final RewardService rewardService;

    public RewardConsumer(RewardService rewardService) {
        this.rewardService = rewardService;
    }
    
    @KafkaListener(topics = "txn-initiated", groupId = "reward-group")
    public void consumeTransaction(Transaction transaction){
        System.out.println("📥 Received transaction: " + transaction);
        if(transaction.getAmount() < 1000){
         System.out.println("❌ No reward (amount < 1000)");
         return;
        }
        // if transaction amount > 1000 then give reward
        //create new Reward object & send to service layer to the reward -> DB (H2) 
        Reward reward = new Reward();
        //give the reward to the sender
        reward.setUserId(transaction.getSenderId());
        reward.setPoints(transaction.getAmount() * 0.1);// give 10 % cashback if amount > 1000
        reward.setSentAt(LocalDateTime.now());
        reward.setTransactionId(transaction.getId());

        rewardService.sendReward(reward);
        System.out.println("Reward saved" + reward);
    }
    //Business logic to calculate reward points




}