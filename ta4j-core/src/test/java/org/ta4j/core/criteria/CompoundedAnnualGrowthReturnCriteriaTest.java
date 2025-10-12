package org.ta4j.core.criteria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;
import org.ta4j.core.*;
import org.ta4j.core.mocks.MockBarBuilder;
import org.ta4j.core.mocks.MockBarSeriesBuilder;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ta4j.core.num.NumFactory;

import static org.junit.Assert.assertEquals;

public class CompoundedAnnualGrowthReturnCriteriaTest extends AbstractCriterionTest {
  public CompoundedAnnualGrowthReturnCriteriaTest(NumFactory numFactory) {
    super((params) -> new CompoundedAnnualGrowthReturnCriteria(), numFactory);
  }

  @Test
  public void benchMark() {
    double startingValue = 100;
    double endingValue = 150;
    int startingYear = 2015;
    int endingYear = 2018;
    int holdingPeriod = endingYear - startingYear;
    double cagr = Math.pow((endingValue / startingValue), (1.0 / holdingPeriod)) - 1;
    System.out.printf("The compounded annual growth rate is %.2f%%.", cagr * 100);
  }

  @Test
  public void calculateWithWinningPosition() {
    ZonedDateTime startTimestamp = ZonedDateTime.of(2015, 1, 1, 8, 0, 0, 0, ZoneId.systemDefault());
    ZonedDateTime endTimestamp = ZonedDateTime.of(2018, 1, 1, 8, 0, 0, 0, ZoneId.systemDefault());

    List<Bar> barSeries = new ArrayList<>();
    Bar bar1 =
        new MockBarBuilder(numFactory).endTime(startTimestamp.toInstant()).closePrice(100).build();
    Bar bar2 =
        new MockBarBuilder(numFactory).endTime(endTimestamp.toInstant()).closePrice(150).build();
    barSeries.add(bar1);
    barSeries.add(bar2);
    BarSeries series =
        new MockBarSeriesBuilder().withNumFactory(numFactory).withBars(barSeries).build();

    TradingRecord tradingRecord =
        new BaseTradingRecord(Trade.buyAt(0, series), Trade.buyAt(1, series));
    AnalysisCriterion criterion = getCriterion();
    Num cagr = criterion.calculate(series, tradingRecord).multipliedBy(DoubleNum.valueOf(100));

    assertEquals(new BigDecimal("14.47"), cagr.bigDecimalValue().setScale(2, RoundingMode.HALF_UP));
  }
}
