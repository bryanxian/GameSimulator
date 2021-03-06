package brown.auction.type.distribution;

import brown.auction.type.valuation.IType;

/**
 * IValuationDistribution samples IValuations from a distribution. 
 * @author andrew
 */
public interface ITypeDistribution {
  
  /**
   * samples IValuations from a distribution
   * @return IValuation
   */
  public IType sample();
  
  
}