package org.ta4j.core.rules;

import org.junit.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.mocks.MockBarSeriesBuilder;
import org.ta4j.core.num.Num;

import org.ta4j.core.num.NumFactory;

import static org.junit.Assert.*;

public class CrossedDownLowestPriceRuleTest extends AbstractIndicatorTest<Indicator<Num>, Num> {
  public CrossedDownLowestPriceRuleTest(NumFactory numFactory) {
    super(numFactory);
  }

  @Test
  public void isSatisfied() {
    // MockBarSeries series = new MockBarSeries(numFunction, 4, 3, 2, 1, 1, 2);
    double[] data = {4, 3, 2, 1, 1, 2};
    BarSeries series = new MockBarSeriesBuilder().withNumFactory(numFactory).withData(data).build();
    ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
    CrossedDownLowestPriceRule crossedDownLowestPriceRule =
        new CrossedDownLowestPriceRule(closePrice, 3);

    assertTrue(
        "When latest price is < lowest price and index is > bar count then rule satisfied.",
        crossedDownLowestPriceRule.isSatisfied(3));
    assertFalse(
        "When latest price is > lowest price and index is > bar count then rule is not satisfied.",
        crossedDownLowestPriceRule.isSatisfied(5));
    assertFalse(
        "When index is < bar count then rule is not satisfied.",
        crossedDownLowestPriceRule.isSatisfied(2));
    assertFalse(
        "When index is 0 then rule is not satisfied.", crossedDownLowestPriceRule.isSatisfied(0));
  }
}
