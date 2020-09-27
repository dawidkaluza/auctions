package eu.horyzont.auctions.modules.bid;

import eu.horyzont.auctions.modules.item.Item;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.BidForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    private final BidRepository bidRepository;

    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public List<Bid> findBidsOrderedByOfferDesc(Item item) {
        return bidRepository.findAllByItemOrderByOfferDesc(item);
    }

    public void updateBid(BidForm bidForm, Item item, User user) throws NewBidMustBeGreaterException {
        Optional<Bid> optionalBid = bidRepository.findByItemAndUser(item, user);
        Bid bid;
        if (optionalBid.isPresent()) {
            bid = optionalBid.get();
            if(bidForm.getOffer().compareTo(bid.getOffer()) <= 0) {
                throw new NewBidMustBeGreaterException();
            }

            bid.setOffer(bidForm.getOffer());
        } else {
            bid = new Bid(bidForm.getOffer(), item, user);
        }
        bidRepository.save(bid);
    }
}
