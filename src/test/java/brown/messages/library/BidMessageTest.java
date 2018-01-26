package brown.messages.library;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import brown.accounting.MarketState;
import brown.accounting.bid.ComplexBid;
import brown.accounting.bidbundle.library.ComplexBidBundle;
import brown.bid.library.AuctionBid;
import brown.bidbundle.library.AuctionBidBundle;
import brown.tradeable.library.MultiTradeable;

/**
 * test for the bid message. 
 * C
 * @author andrew
 *
 */
public class BidMessageTest {
  
  @Test
  public void testBidMessage() {
    Map<MultiTradeable, MarketState> map = new HashMap<MultiTradeable, MarketState>(); 
    Map<Set<MultiTradeable>, MarketState> complexMap = new HashMap<Set<MultiTradeable>, MarketState>(); 
    map.put(new MultiTradeable(0), new MarketState(0, 1.0)); 
    AuctionBidBundle s = new AuctionBidBundle(map); 
    TradeMessage bm = new TradeMessage(0, s, 1, 2); 
    TradeMessage bmTwo = bm.safeCopy(2); 
    //test public fields
    assertEquals(bm.Bundle, s); 
    assertEquals(bm.AuctionID, new Integer(1)); 
    assertEquals(bm.AgentID, new Integer(2)); 
    assertEquals(bm, bmTwo); 
    //test complex case
    Set<MultiTradeable> tSet = new HashSet<MultiTradeable>();
    tSet.add(new MultiTradeable(0)); 
    complexMap.put(tSet, new MarketState(0, 1.0));
    ComplexBidBundle c  = new ComplexBidBundle(complexMap, 0);
    TradeMessage cBid = new TradeMessage(0, c, 1, 2); 
    assertEquals(cBid, cBid.safeCopy(2)); 
    assertEquals(cBid.Bundle, c);
    assertEquals(cBid.Bundle.getBids(), c.getBids());
    ComplexBid com = (ComplexBid) cBid.Bundle.getBids();
    assertEquals(com.bids, c.getBids().bids);
  }
}