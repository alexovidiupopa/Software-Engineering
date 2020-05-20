package ro.ubb.project.core.service;

import ro.ubb.project.core.model.Bidding;

import java.util.List;

public interface BiddingService {

    List<Bidding> getAllBiddings();

    void addBidding(Bidding bidding);

    void deleteBidding(Bidding bidding);

    void updateBidding(Bidding bidding);

    List<Integer> getReviewersForPaperId(Integer pid);
}
