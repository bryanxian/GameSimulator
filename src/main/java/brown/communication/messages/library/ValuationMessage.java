package brown.communication.messages.library;

import brown.auction.type.valuation.IType;
import brown.communication.messages.ITypeMessage;
import brown.user.agent.IAgent;

public class ValuationMessage extends AbsServerToAgentMessage implements ITypeMessage {
  
  private IType valuation; 
  
  public ValuationMessage() {
    super(null, null); 
  }
  
  public ValuationMessage(Integer messageID, Integer agentID, IType valuation) {
    super(messageID, agentID);
    this.valuation = valuation; 
  }
  

  @Override
  public void agentDispatch(IAgent agent) {
    agent.onTypeMessage(this);
  }
  
  
  public IType getValuation() {
    return this.valuation; 
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((valuation == null) ? 0 : valuation.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ValuationMessage other = (ValuationMessage) obj;
    if (valuation == null) {
      if (other.valuation != null)
        return false;
    } else if (!valuation.equals(other.valuation))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ValuationMessage [valuation=" + valuation + "]";
  }

}
