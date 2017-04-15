package Search;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/** JUnit tests for the LibStruc class.
 *  @author mesutyang97
 */

public class LibStrucTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    @Test
    public void testSplitCallNum() {
        String c1 = "A32.H12";
        String[] r1 = LibStruc.splitCallNum(c1);
        assertTrue("A".equals(r1[0]));
        assertTrue("32".equals(r1[1]));
        assertTrue("H12".equals(r1[2]));
    }

}
