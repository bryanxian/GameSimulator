package brown.rules.paymentrules;

import brown.market.marketstate.ICompleteState;

public interface IPaymentRule {

  public void setOrders(ICompleteState state);

  public void permitShort(ICompleteState state);  
}
