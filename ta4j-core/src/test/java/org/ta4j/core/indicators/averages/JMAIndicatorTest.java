/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2024 Ta4j Organization & respective
 * authors (see AUTHORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.averages;

import static org.ta4j.core.TestUtils.*;

import org.junit.Test;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.mocks.MockBarSeriesBuilder;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;
public class JMAIndicatorTest extends AbstractIndicatorTest<Indicator<Num>, Num> {

    public JMAIndicatorTest(NumFactory numFunction) {
        super(numFunction);
    }

    @Test
    public void testCalculateJMAWithNQ() {

        /*
         * Retrieved data from Ta4J EMAIndicator using NQ data which was retrieved from
         * Databento
         */
        BarSeries data = new MockBarSeriesBuilder().withNumFactory(numFactory)
                .withData(20841, 20839, 20825.25, 20826, 20825, 20822.75, 20822.75, 20818.5, 20833, 20837.25, 20833.5,
                        20838.25, 20844.75, 20836.25, 20833.75, 20841, 20846.5, 20857.25, 20860.25, 20861.5, 20856.75,
                        20860.75, 20862.75, 20861, 20851, 20830.5, 20837.75, 20831, 20831.25, 20841, 20849, 20849.75,
                        20854, 20852, 20854.75, 20853.5, 20843.75, 20845, 20844.25, 20838.25, 20840.25, 20852.25,
                        20857.25, 20857.5, 20857.5, 20852.75, 20857, 20855.5, 20855, 20850.5, 20846, 20848, 20844.75,
                        20845.25, 20831.5, 20825.25, 20814.25, 20818.75, 20815.5, 20817.5, 20819, 20820, 20824.5,
                        20819.25, 20811.5, 20808.25, 20796, 20805, 20806.75, 20817.75, 20828.25, 20835.75, 20836, 20845,
                        20836.25, 20832, 20832.25, 20825, 20826.25, 20834.5, 20838.5, 20839, 20836.75, 20839.25, 20844,
                        20847.75, 20854.25, 20852.75, 20849.5, 20845.75, 20845, 20849.75, 20846.75, 20846.25, 20840.25,
                        20833.75, 20840.75, 20826.75, 20824.5, 20824.75, 20827.75, 20836.75, 20840, 20840.25, 20840.75,
                        20843.75, 20848.25, 20855, 20857.75, 20858, 20857.25, 20854.75, 20857.75, 20862, 20866.25,
                        20864.75, 20869, 20871, 20872.75, 20874.75, 20874.25, 20869.5, 20875, 20874.75, 20877.25,
                        20871.5, 20873.5, 20867.25, 20872, 20874.25, 20870.25, 20863.5, 20864.75, 20863.25, 20862.25,
                        20869, 20869.25, 20869.75, 20867.5, 20865, 20863, 20851.5, 20841.5, 20856, 20851.75, 20834,
                        20843.25, 20843.75, 20835.5, 20833.25, 20834.25, 20847, 20846, 20844, 20839.25, 20844.75,
                        20840.5, 20831, 20833.75, 20835.25, 20830.5, 20831.25, 20831.5, 20836.25, 20833.75, 20837.75,
                        20823.75, 20827.5, 20825, 20814, 20819.5, 20819.5, 20815.25, 20808, 20803.75, 20805.25, 20812,
                        20809.5, 20808.75, 20809, 20809.75, 20812.25, 20806.25, 20806.75, 20807.25, 20806.25, 20800,
                        20800.5, 20797.75, 20793, 20796, 20797.5, 20798.25, 20795.75, 20794.75, 20794.25, 20796.75,
                        20798.25, 20801.25, 20805, 20808.25, 20801.25, 20805.5, 20804.75, 20805.75, 20802, 20799.25,
                        20797.75, 20796.5, 20794.75, 20795.25, 20798.25, 20793.25, 20792, 20795, 20799.75, 20799.25,
                        20808.5, 20809.5, 20797.25, 20799, 20800.75, 20800.5, 20802.75, 20800.5, 20795.25, 20793.75,
                        20772.75, 20772, 20782.75, 20786, 20785.5, 20787.5, 20781, 20780.75, 20783.75, 20784.5, 20789.5,
                        20791.75, 20790.5, 20785.25, 20785.75, 20784, 20783.75, 20786, 20784.75, 20784.5, 20786.25,
                        20780.5, 20779.5, 20780.5, 20790.75, 20793.25, 20799, 20799.75, 20798.75, 20800.25, 20800.75,
                        20798.25, 20806.5, 20805, 20804.25, 20802.5, 20805.25, 20804.25, 20801, 20794, 20797.25,
                        20797.75, 20792, 20796.25, 20793.75, 20788.5, 20786.25, 20785, 20783, 20781, 20763.5, 20763.5,
                        20767.75, 20771, 20773.25, 20773.5, 20775.75, 20779.25, 20779.25, 20773.5, 20774, 20772.5,
                        20768.25, 20778.75, 20787, 20784, 20790.25, 20784, 20784, 20784.5, 20781.75, 20775.75, 20777.5,
                        20776.5, 20778.75, 20779, 20779.75, 20779.5, 20781.25, 20784.25, 20780.75, 20784, 20785.25,
                        20785.25, 20787.5, 20789.5, 20786.75, 20791.5, 20789, 20788, 20786.75, 20780, 20785.25, 20777,
                        20776.75, 20781.25, 20778.5, 20783, 20782.25, 20782.5, 20781.25, 20781, 20779.5, 20779.25,
                        20776, 20773.25, 20773.75, 20768, 20766.5, 20767, 20769, 20773, 20777.75, 20777.5, 20779.25,
                        20778.25, 20780, 20776, 20773.25, 20770, 20784.75, 20785, 20784.5, 20779.25, 20773, 20783,
                        20785.75, 20783.25, 20783.75, 20786.75, 20790.25, 20789.75, 20787, 20792, 20795, 20797.25,
                        20795.5, 20795.75, 20799, 20797, 20802, 20802, 20808, 20808.5, 20807.5, 20808.5, 20806.25,
                        20798.75, 20795.25, 20799.5, 20802.5, 20802.25, 20805.5, 20803.75, 20800.5, 20805, 20806.75,
                        20811, 20808.75, 20804, 20803.25, 20801.25, 20799.75, 20803, 20802.5, 20801, 20798.5, 20800,
                        20800.5, 20797.75, 20806, 20810, 20805.75, 20806.75, 20807.25, 20809.5, 20805.25, 20802.75,
                        20800.5, 20800, 20801, 20802, 20806, 20803, 20805.5, 20805.75, 20804.25, 20803.5, 20801,
                        20796.25, 20799.5, 20799.25, 20800.75, 20801.5, 20800.5, 20803.5, 20800.25, 20803, 20802.75,
                        20801.5, 20804.75, 20804, 20804, 20802.5, 20802.5, 20803.75, 20804.25, 20805.5, 20804.25,
                        20804.5, 20806.25, 20802.5, 20802.5, 20802, 20803, 20806, 20809.75, 20811, 20815, 20820.5,
                        20819.5, 20816.75, 20813, 20814.5, 20816, 20817.75, 20820.75, 20820.25, 20819.5, 20819.75,
                        20820.75, 20821, 20821, 20819, 20819, 20820.25, 20821.5, 20819.25, 20820, 20820, 20823,
                        20819.75, 20821.75, 20821.25, 20821.5, 20823, 20821.25, 20823, 20821.75, 20827.5, 20832,
                        20832.25, 20837.5, 20836.5, 20839.75, 20841.25, 20842.75, 20839, 20839.75, 20837.5, 20836,
                        20829, 20832.25, 20831.75, 20836.5, 20842.25, 20839.5, 20834, 20835.75, 20827, 20824.25,
                        20824.25, 20820.75, 20814.5, 20812.25, 20812, 20805.5, 20808, 20806.25, 20785.75, 20773.75,
                        20765.25, 20765.5, 20765.75, 20772.75, 20779.75, 20786, 20784.75, 20783.75, 20781.25, 20785,
                        20781, 20785.25, 20785.25, 20785, 20792, 20790.5, 20789.75, 20789, 20791, 20799, 20801,
                        20800.75, 20798.75, 20802.5, 20797.75, 20794, 20782.5, 20792, 20784, 20789.5, 20797.25,
                        20791.75, 20789.75, 20789.5, 20791.5, 20796.75, 20797.5, 20796, 20793.5, 20800.25, 20801.25,
                        20796.75, 20795.5, 20797, 20798, 20797.75, 20794.75, 20796.75, 20797.75, 20799.25, 20802,
                        20801.5, 20804.75, 20808.5, 20807, 20809, 20819.5, 20819.5, 20817, 20814.75, 20813.75, 20814.5,
                        20811.75, 20812, 20808, 20808.5, 20805.75, 20809.75, 20809.5, 20810.25, 20812.5, 20814.5,
                        20813.5, 20813.75, 20810.75, 20809.5, 20810, 20808.75, 20808.75, 20809.75, 20809, 20803.5,
                        20805, 20803.5, 20801.75, 20803.25, 20801.5, 20814.75, 20809.5, 20806.75, 20809, 20807.25,
                        20805, 20803, 20801.25, 20790, 20781.75, 20781.25, 20782, 20784, 20784.75, 20790.5, 20792,
                        20789.25, 20787.5, 20791, 20789.25, 20793, 20792, 20794.25, 20795.5, 20787.25, 20787, 20785.25,
                        20787.75, 20789, 20788.5, 20790.5, 20787.5, 20789, 20790.75, 20788.5, 20783, 20787.25, 20784.25,
                        20778.5, 20774.5, 20772.25, 20775.25, 20777.5, 20776.75, 20777.5, 20774.75, 20776.75, 20772.5,
                        20771, 20770.25, 20771, 20765.25, 20768.5, 20776, 20778.75, 20784, 20787.25, 20789.5, 20794,
                        20789.5, 20791.5, 20797, 20793.75, 20794.5, 20792.5, 20794, 20796.25, 20803.5, 20811.25,
                        20806.75, 20805.25, 20804.5, 20807.5, 20808.25, 20810.25, 20808.75, 20810.75, 20810, 20805,
                        20803.5, 20807.25, 20803.5, 20795, 20795, 20796.25, 20791.75, 20798.75, 20795.25, 20796.25,
                        20799.75, 20797.75, 20799, 20798.5, 20799.25, 20794.75, 20798.75, 20798.25, 20802.75, 20802,
                        20802, 20803.75, 20800.25, 20795.5, 20796.25, 20796, 20797.25, 20797.25, 20798.75, 20801,
                        20800.5, 20802.75, 20801.25, 20805.25, 20797.5, 20802.75, 20790.5, 20800, 20799.25, 20798.25,
                        20802.25, 20806.5, 20809.75, 20811, 20813.75, 20810.5, 20804.75, 20802, 20803.25, 20801.75,
                        20803, 20800.75, 20799.5, 20798.75, 20795.75, 20798, 20801.5, 20807.75, 20810, 20806.25,
                        20803.25, 20806.75, 20805.75, 20816.25, 20819.5, 20820.5, 20822.75, 20824.75, 20826, 20820,
                        20821.75, 20821.25, 20818.75, 20823.5, 20826)
                .build();

        double[] expectedDoubles = { 20845.8868023864, 20842.2587219602, 20834.0909312952, 20828.296341127,
                20825.1460331684, 20822.9930525444, 20821.9908739626, 20819.9520352938, 20824.7021937658,
                20831.2606810957, 20833.9619350624, 20836.5675670187, 20840.9486543218, 20840.3678759205,
                20837.5361462274, 20838.3934624263, 20842.2310371728, 20849.7906379548, 20856.4993477392,
                20860.7032733758, 20860.4832972895, 20860.8456361803, 20862.0694009397, 20862.1789152759,
                20857.5947618524, 20844.8295797834, 20838.183257424, 20833.1408369315, 20830.5673237745,
                20833.9203787028, 20840.9811769718, 20846.4832436754, 20851.1613630556, 20852.9394631004,
                20854.388292207, 20854.6400080385, 20850.249557043, 20846.8897863031, 20844.8976399953,
                20841.4656562219, 20839.8661799326, 20844.5850326574, 20851.2216126909, 20855.7046461774,
                20857.789126393, 20856.3952305369, 20856.446891255, 20856.2392055896, 20855.7481093949,
                20853.4348317886, 20849.6320455923, 20847.8469220828, 20845.9673382238, 20845.007215239,
                20838.8493318999, 20831.1330146073, 20821.5718387354, 20817.373706431, 20815.0370208947,
                20814.9865491704, 20816.301129406, 20817.9456668064, 20821.0001891988, 20821.0158160316,
                20816.9067310309, 20812.0420497977, 20803.7939249551, 20801.8287829234, 20803.194556658,
                20809.518955414, 20819.1156754424, 20828.8758026964, 20834.8035459202, 20841.1198612856,
                20841.2539747961, 20837.7327279571, 20834.77055748, 20830.0198307178, 20827.1497079055, 20829.47148583,
                20833.9146021347, 20837.2610733145, 20837.9866919132, 20838.8103948541, 20841.3862487256,
                20844.952838809, 20850.0490819494, 20852.8047536179, 20852.358009255, 20849.6281706037,
                20847.0689495982, 20847.6042408017, 20847.432200921, 20846.850852075, 20843.856442999, 20838.6614514039,
                20838.019004754, 20832.9408562401, 20827.6948338692, 20824.7774221404, 20824.9941366018,
                20829.8221483566, 20835.3350118147, 20838.8807463324, 20840.7053946694, 20842.6491840125,
                20845.75961992, 20850.7563954375, 20855.3421737781, 20857.9825420371, 20858.6653879885,
                20857.4487847266, 20857.4572868211, 20859.5756700401, 20863.1400112206, 20864.9362105665,
                20867.2904204578, 20869.7020858879, 20871.8443733758, 20873.8564802894, 20874.7657094854,
                20872.9377471045, 20873.4364442589, 20874.2650065519, 20875.8289165307, 20874.4816663443,
                20873.720421829, 20870.8032112254, 20870.482454622, 20871.9913302013, 20871.6228632444, 20867.998077021,
                20865.5517899381, 20863.8431153388, 20862.578885839, 20864.8386950763, 20867.2641781512,
                20868.9265195472, 20868.777024107, 20867.155668029, 20864.9680588752, 20858.6189197165,
                20849.4535744388, 20849.521343782, 20850.3127753664, 20843.2657316094, 20840.9887837507,
                20841.3616317119, 20838.7209152586, 20835.3970177605, 20833.7910517507, 20838.7998077793,
                20843.1482738134, 20844.6209607322, 20842.754645633, 20843.1520865686, 20842.2270710567,
                20837.1914979597, 20834.2817967904, 20833.8421062075, 20832.1770798332, 20831.1587357868,
                20830.9008345399, 20832.9986441282, 20833.8317214095, 20835.6742761349, 20831.1146072707,
                20828.2583612471, 20826.0799568842, 20820.2238680473, 20818.1028248089, 20817.9565792301,
                20816.5484831166, 20812.2986779771, 20807.2744483257, 20804.8180128179, 20806.985392366,
                20808.4558671589, 20808.7969882104, 20808.8801618589, 20809.234326557, 20810.6027814617,
                20809.1301345786, 20807.6847002516, 20807.1047330095, 20806.5578864995, 20803.5600658466,
                20801.3409526503, 20799.1201074059, 20795.7680313682, 20794.7779689174, 20795.5235629935,
                20796.7446876709, 20796.5441070706, 20795.6442823929, 20794.7592388597, 20795.326748249,
                20796.7031858469, 20799.0012894615, 20802.2133617117, 20805.7433910872, 20804.9163757091,
                20805.0382152286, 20805.1023305485, 20805.4866507071, 20804.175419645, 20801.7385683594,
                20799.3664780511, 20797.451024965, 20795.6871527238, 20794.8990107539, 20795.9971057279,
                20795.0353083029, 20793.368103253, 20793.5368660434, 20796.1869328179, 20798.1959644401,
                20803.1357069125, 20807.3166344688, 20804.3015245593, 20801.3114210802, 20800.3903006299,
                20800.2364693244, 20801.2492509786, 20801.1945129879, 20798.6181684348, 20795.7948354318,
                20785.1366704248, 20776.3706408398, 20776.4344473571, 20780.2333767459, 20783.1947161698,
                20785.6242387747, 20784.2496981064, 20782.3432377768, 20782.428780235, 20783.335825202,
                20786.1902944368, 20789.3685258424, 20790.7799573006, 20788.8945187422, 20787.114994411,
                20785.3729441212, 20784.2064844025, 20784.6175250903, 20784.7463747645, 20784.6218398785,
                20785.2492734705, 20783.3855627067, 20781.1689343921, 20780.2384527796, 20784.4139422581,
                20789.3264305287, 20794.8178015288, 20798.5648664265, 20799.8712487293, 20800.6254048345,
                20801.1251992575, 20800.2301240006, 20802.7922623607, 20804.5927897049, 20805.0330624667,
                20804.1782131965, 20804.4930282226, 20804.5637036365, 20803.1037833868, 20798.8279972426,
                20796.9341754583, 20796.7143509465, 20794.5365969438, 20794.5234093035, 20794.1057130037,
                20791.4878550953, 20788.4168246648, 20786.0021256433, 20783.9174279935, 20781.9295063394,
                20773.3379279771, 20766.4898447806, 20764.8819437835, 20766.7353902366, 20769.7099781941,
                20771.9201540447, 20774.0476554611, 20776.845421755, 20778.7043390835, 20777.0832483523,
                20775.3959321908, 20773.7760598946, 20770.9860710741, 20773.4794667438, 20779.9347557289,
                20783.4649411313, 20787.3867681497, 20787.2180813422, 20785.9662325921, 20785.1763398833,
                20783.6196712786, 20779.8760061059, 20777.8021508399, 20776.6283292645, 20777.1030666476,
                20777.9447415771, 20778.8621491469, 20779.3433114711, 20780.2746498503, 20782.2461378875,
                20782.1948294415, 20782.9805964578, 20784.2398325183, 20785.0805516858, 20786.4053787008,
                20788.1718688401, 20788.1397890392, 20789.6358803696, 20789.8864029194, 20789.2152770092,
                20788.0415187161, 20784.3238005991, 20783.6611920059, 20780.6148388019, 20777.993706318,
                20778.5448235381, 20778.5680720671, 20780.3301549763, 20781.5785945653, 20782.2785470635,
                20782.0410344454, 20781.5523164978, 20780.5656391109, 20779.7335981834, 20777.8985650839,
                20775.3596307116, 20773.9001934081, 20770.8936109423, 20768.0448813473, 20766.6680469849,
                20767.1269107924, 20769.6123169976, 20773.6727856126, 20776.4046488778, 20778.4106248328,
                20779.0096359265, 20779.7233752328, 20778.4655808495, 20775.9727757099, 20772.7809867553,
                20776.990437286, 20781.60159274, 20784.0958857094, 20782.784967521, 20778.3460605274, 20779.1738023965,
                20782.2843963579, 20783.5552325687, 20783.9952908605, 20785.3553498658, 20787.9064315397,
                20789.492540083, 20788.9603353964, 20790.2057198995, 20792.718992584, 20795.4478674784,
                20796.3368193521, 20796.4499790933, 20797.7018176266, 20797.8765808085, 20799.7677419099,
                20801.3533917626, 20804.7148192104, 20807.3935722432, 20808.3137556972, 20808.7992947619,
                20808.0054005751, 20803.9502897366, 20799.1539807802, 20797.9537184342, 20799.4907669171,
                20800.9781656672, 20803.2213084311, 20804.0491488457, 20802.7723496213, 20803.3963350121,
                20805.0568680906, 20808.0843997837, 20809.2695214439, 20807.4160563631, 20805.1972142014,
                20802.9585647182, 20800.9615788654, 20801.2194101075, 20801.771561751, 20801.5133444244,
                20800.0950327524, 20799.6127898609, 20799.8243763002, 20798.9522144882, 20801.6627488771,
                20805.9835029213, 20807.1073839364, 20807.3176626609, 20807.465116238, 20808.4805590887,
                20807.4746330597, 20805.2232176874, 20802.6124469664, 20800.7570153314, 20800.2855656054,
                20800.8000691959, 20803.0745829466, 20803.6294059662, 20804.54903383, 20805.3486083342,
                20805.1382241263, 20804.4154336401, 20802.7899253427, 20799.5502011998, 20798.5865886673,
                20798.5535558166, 20799.3887486755, 20800.4482390963, 20800.7194859602, 20801.9618680161,
                20801.5986983855, 20802.1017702228, 20802.5577585896, 20802.2549864834, 20803.2536886241,
                20803.8875278171, 20804.1318695862, 20803.5359266265, 20802.9541591166, 20803.1527100078,
                20803.6858395605, 20804.608431338, 20804.723769263, 20804.6734446777, 20805.3589684422,
                20804.3597407453, 20803.2895947584, 20802.451061209, 20802.4360786192, 20803.9264456141,
                20806.8101675575, 20809.414018979, 20812.572801209, 20816.9493285556, 20819.4201590996,
                20819.1444746558, 20816.6353733288, 20815.1798200308, 20815.2287243936, 20816.3518825282,
                20818.5490555309, 20819.9118468984, 20820.1559108543, 20820.1164197566, 20820.4475941605,
                20820.8353885816, 20821.0569569633, 20820.2775830555, 20819.5374085688, 20819.6523751335,
                20820.4761481897, 20820.1786660798, 20820.0090881697, 20819.9678889192, 20821.252205173,
                20820.9829523758, 20821.2307955868, 20821.3366326411, 20821.4496723979, 20822.1582533394,
                20821.9889655204, 20822.3825991915, 20822.2496676436, 20824.464478686, 20828.3424738247,
                20831.1503848454, 20834.7348873213, 20836.6542495412, 20838.6811636558, 20840.5461877416,
                20842.196178582, 20841.4723503441, 20840.678399917, 20839.2304416412, 20837.5122854498,
                20833.4187446425, 20831.7245631334, 20831.200667652, 20833.1949477982, 20837.5244992718,
                20839.5380184401, 20837.742309423, 20836.4474848373, 20832.1329930751, 20827.5403748095,
                20824.7880208734, 20822.1625023304, 20817.9598557482, 20814.1225836447, 20811.9178566373,
                20808.3194544135, 20806.9021420648, 20805.99915358, 20796.8695647384, 20784.1566226658,
                20772.2278906682, 20765.573197223, 20763.221164472, 20766.0083101899, 20772.1289116497,
                20779.3959547295, 20783.5550498038, 20784.8125165751, 20783.7860657293, 20784.1819000119,
                20783.0877524543, 20783.7753250783, 20784.6704548431, 20785.095622125, 20788.198034149,
                20790.1249207089, 20790.5661733941, 20790.1200898057, 20790.4716916495, 20794.2969928745,
                20798.3208881911, 20800.5743029424, 20800.5657846821, 20801.5537439606, 20800.3798561338,
                20797.4291055904, 20790.2971770326, 20788.9900756467, 20786.4423500085, 20786.831099688,
                20791.2398762698, 20792.5830250134, 20791.6659866024, 20790.4974303832, 20790.62809285,
                20793.2892262253, 20795.844383325, 20796.6479624026, 20795.5907358063, 20797.3617236745,
                20799.6157533875, 20799.083777437, 20797.4622238393, 20796.8829559381, 20797.2417919001,
                20797.5647127286, 20796.4467867714, 20796.2509881336, 20796.8396744793, 20798.0207224177,
                20800.0461119536, 20801.2577224184, 20803.1281666483, 20806.0274246811, 20807.3566593654,
                20808.5352322029, 20813.6824960001, 20817.7796125305, 20818.7494269426, 20817.5113908089,
                20815.7615900421, 20814.8838875914, 20813.3828384484, 20812.3831586989, 20810.2211580027,
                20808.8250886679, 20807.0611961544, 20807.6179461831, 20808.4988601974, 20809.4076834133,
                20810.9378492141, 20812.8847305928, 20813.7209737348, 20814.0148844035, 20812.7745077654,
                20811.0805579052, 20810.1765082209, 20809.3125965217, 20808.7908875135, 20809.0078958363,
                20809.0210921718, 20806.6252055614, 20805.2081666774, 20804.046629053, 20802.6635667951,
                20802.4356504685, 20801.8918694741, 20807.1382165612, 20809.6102405313, 20809.0580456542,
                20808.9471558791, 20808.2933257388, 20806.7578025152, 20804.7463105965, 20802.6811560209,
                20796.6428059443, 20788.4676297777, 20782.9164078165, 20780.7139121921, 20781.1649820868,
                20782.4927348992, 20786.0182319655, 20789.4131708872, 20790.2331970481, 20789.3119525203,
                20789.8396230563, 20789.8105283436, 20791.193084733, 20791.9717093338, 20793.1936147001,
                20794.5789763763, 20791.9029767818, 20789.0917267199, 20786.7021533412, 20786.4440137468,
                20787.392926135, 20788.0453535678, 20789.2110236141, 20788.7898923362, 20788.7502707996,
                20789.611278481, 20789.3922483647, 20786.6032902147, 20786.0870593297, 20785.1595533738,
                20781.9938641036, 20777.8248773134, 20774.1755145932, 20773.4633137912, 20774.8138217324,
                20775.8419014746, 20776.7013185489, 20776.044108847, 20776.1237982457, 20774.5962915523,
                20772.6002658065, 20771.0048483246, 20770.4971877903, 20768.0384190697, 20767.4361753191,
                20770.8450358918, 20775.1105694231, 20780.0665552376, 20784.5935817796, 20788.1108462488,
                20791.8283573269, 20792.1394201077, 20792.1886905653, 20794.495791104, 20795.0231641814,
                20795.0782439983, 20794.1293312415, 20793.9036435053, 20794.9137873258, 20798.9049569748,
                20805.354979327, 20807.8659597166, 20807.5948586877, 20806.4103994085, 20806.7330241833,
                20807.6095400945, 20809.0621710755, 20809.4086213253, 20810.1411130269, 20810.3658292398,
                20808.1989961358, 20805.6183806991, 20805.6052919161, 20804.6852373924, 20800.2178449616,
                20796.6429701271, 20795.3785161533, 20793.3271639919, 20794.8753830086, 20795.3397890748,
                20795.7200861944, 20797.4828161242, 20798.072382008, 20798.6229522889, 20798.755778353,
                20799.0355524712, 20797.3204570237, 20797.457047525, 20797.8576135558, 20800.0427623957,
                20801.5038043978, 20802.1437665747, 20803.0640078358, 20802.1954478718, 20799.1440369923,
                20797.0800509454, 20796.0393069205, 20796.2030622673, 20796.61908725, 20797.5734197752,
                20799.2641308226, 20800.2585356917, 20801.6149001389, 20801.8922562212, 20803.4546083051,
                20801.4301411861, 20801.4546480006, 20796.8520640603, 20796.8599322847, 20797.8818987276,
                20798.2412337426, 20799.9911802808, 20803.2542082614, 20806.9685537688, 20809.8039413329,
                20812.4076280649, 20812.4964137604, 20809.3782189155, 20805.4885963011, 20803.5169456641,
                20802.2023818204, 20802.0876073497, 20801.3906822685, 20800.2887119112, 20799.2436608802,
                20797.385642574, 20797.039391436, 20798.7719975928, 20803.0204617799, 20807.169224697, 20807.9772685883,
                20806.2808890514, 20806.1337914129, 20806.0449990493, 20810.4221900419, 20815.6013008884,
                20819.223167301, 20821.8940808188, 20824.0785616626, 20825.7433513549, 20823.9798483854,
                20822.7034249333, 20821.8764389858, 20820.3792345848, 20821.3028422697, 20823.5774987628 };

        int phase = 50;
        int power = 2;
        var jma = new JMAIndicator(new ClosePriceIndicator(data), 7, phase, power);

        for (int i = 21; i < data.getBarCount(); i++) {
            
            double j = jma.calculate(i).doubleValue();
            
            assertNumEquals(expectedDoubles[i], jma.getValue(i));
        }

    }

}
