package Search;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void testMatcher() {
        String c2 = "A";
        Pattern p = Pattern.compile("([a-zA-Z])");
        Matcher m = p.matcher(c2);
        m.matches();
        System.out.println(m.group());
    }

    @Test
    public void testShelfMatch() {
        String r = "32";
        String c3 = "shelf 30 45";
        Pattern shelf_p = Pattern.compile("shelf ([0-9]+).*?([0-9]+).*?");
        Matcher m = shelf_p.matcher(c3);
        System.out.println(m.matches());
        System.out.println(r.compareTo(m.group(2)) <= 0);
    }



}
