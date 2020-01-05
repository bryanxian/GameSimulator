package brown.auction.rules.ir;

import java.util.List;

import brown.auction.marketstate.IMarketState;
import brown.auction.rules.AbsRule;
import brown.auction.rules.IInformationRevelationPolicy;
import brown.communication.messages.IActionMessage;

public class NonAnonymousPolicy extends AbsRule implements IInformationRevelationPolicy {

  @Override
  public void updatePublicState(IMarketState state, IMarketState publicState) {
    
    
    // TODO: sanitize the agent IDs. 
    
    publicState.tick();
    List<IActionMessage> recentHistory = state.getTradeHistory().get(state.getTradeHistory().size() - 1); 
    publicState.addToTradeHistory(recentHistory);
    publicState.setUtilities(state.getUtilities());
    
  }


}
