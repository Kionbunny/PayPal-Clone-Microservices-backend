package com.paypal.reward_service.controller;
import com.paypal.reward_service.entity.Reward;
import com.paypal.reward_service.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards/")
public class RewardController {
    @Autowired
    private RewardService rewardService;
    //just for testing purpose in actual flow rewards are created automatically via Kafka, not via REST API.
   // Rewards are event-driven (Kafka) we never manually create rewards from frontend/Postman.
    @PostMapping("/create")
    public Reward createReward(@RequestBody Reward reward) {
        return rewardService.sendReward(reward);
    }

    // 🔹 Get rewards by user ID
    @GetMapping("/user/{userId}")
    public List<Reward> getRewardsByUserId(@PathVariable Long userId) {
        return rewardService.getRewardsByUserId(userId);
    }

    // 🔹 Get all rewards
    @GetMapping("/all-rewards")
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }



}