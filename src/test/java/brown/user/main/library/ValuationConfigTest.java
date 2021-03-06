package brown.user.main.library;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import brown.auction.type.distribution.library.HLTypeDistribution;
import brown.auction.type.generator.library.NormalValGenerator;
import brown.user.main.IValuationConfig;

public class ValuationConfigTest {

  @Test
  public void testValuationConfigOne()
      throws NoSuchMethodException, SecurityException, ClassNotFoundException {

    Constructor<?> distCons =
        HLTypeDistribution.class.getConstructor(List.class);
    Constructor<?> gCons = NormalValGenerator.class.getConstructor(List.class);
    List<Double> params = new LinkedList<Double>();
    params.add(0.0);
    params.add(1.0);

    Class genClass = Class
        .forName("brown.auction.type.generator.library.NormalValGenerator");
    List<Double> genParams = new LinkedList<Double>();
    genParams.add(0.0);
    genParams.add(1.0);

    Constructor<?> genCons = genClass.getConstructor(List.class);

    List<Constructor<?>> genList = new LinkedList<Constructor<?>>();
    List<List<Double>> genParamList = new LinkedList<List<Double>>();
    genList.add(genCons);
    genParamList.add(genParams);

    IValuationConfig tConfig =
        new ValuationConfig(distCons, genList, genParamList);

    assertEquals(tConfig.getValDistribution(), distCons);
    assertEquals(tConfig.getGeneratorConstructors(), genList);
    assertEquals(tConfig.getGeneratorParams(), genParamList);
  }

}
