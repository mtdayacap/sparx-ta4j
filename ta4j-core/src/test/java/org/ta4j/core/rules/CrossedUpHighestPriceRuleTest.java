/*
 * SPDX-License-Identifier: MIT
 */
package org.ta4j.core.rules;

import org.junit.jupiter.api.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.mocks.MockBarSeriesBuilder;
import org.ta4j.core.num.Num;

import org.ta4j.core.num.NumFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrossedUpHighestPriceRuleTest extends AbstractIndicatorTest<Indicator<Num>, Num> {

    public CrossedUpHighestPriceRule crossedUpHighestPriceRule;

    public CrossedUpHighestPriceRuleTest(NumFactory numFactory) {
        super(numFactory);
    }

    @Test
    public void isSatisfied() {
        // MockBarSeries series = new MockBarSeries(numFunction, 1, 3, 2, 4, 3, 6, 2, 3,
        // 4, 5);
        double[] data = { 1, 3, 2, 4, 3, 6, 2, 3, 4, 5 };
        BarSeries series = new MockBarSeriesBuilder().withNumFactory(numFactory).withData(data).build();
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        crossedUpHighestPriceRule = new CrossedUpHighestPriceRule(closePrice, 3);

        assertTrue(crossedUpHighestPriceRule.isSatisfied(3),
                "When bar count is > index and last price is > highest then satisfied");
        assertTrue(crossedUpHighestPriceRule.isSatisfied(9),
                "When bar count is > index and last price > highest then satisfied");
        assertFalse(crossedUpHighestPriceRule.isSatisfied(4),
                "When bar count is > index and last price < highest then fail rule");
        assertFalse(crossedUpHighestPriceRule.isSatisfied(1), "When bar count is < index then fail rule");
        assertFalse(crossedUpHighestPriceRule.isSatisfied(2), "When bar count is = index then fail rule");
        assertFalse(crossedUpHighestPriceRule.isSatisfied(0), "When bar count is 0 index then fail rule");
    }
}
