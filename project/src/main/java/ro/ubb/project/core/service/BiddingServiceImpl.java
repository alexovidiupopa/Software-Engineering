package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.Bidding;
import ro.ubb.project.core.repository.BiddingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    private BiddingRepository biddingRepository;

    @Override
    public List<Bidding> getAllBiddings() {
        return this.biddingRepository.findAll();
    }

    @Override
    public void addBidding(Bidding bidding) {
        this.biddingRepository.save(bidding);
    }

    @Override
    public void deleteBidding(Bidding bidding) {
        this.biddingRepository.delete(bidding);
    }

    @Override
    public void updateBidding(Bidding bidding) {
        Optional<Bidding> toUpdate = this.biddingRepository.findAll()
                .stream()
                .filter(b -> b.getPcid() == bidding.getPcid() && b.getPid() == bidding.getPid())
                .findAny();
        if (toUpdate.isPresent()) {
            Bidding b = toUpdate.get();
            this.biddingRepository.save(b);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }

    @Override
    public List<Integer> getReviewersForPaperId(Integer pid) {
        return biddingRepository.findAll()
                .stream()
                .filter(bid -> bid.getPid() == pid)
                .map(Bidding::getPcid)
                .collect(Collectors.toList());
    }
}
