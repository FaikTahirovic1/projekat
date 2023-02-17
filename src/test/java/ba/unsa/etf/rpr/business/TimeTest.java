package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Time;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
    /**
     * This tests using constructor with one parameter
     */

    @Test
    void TestIntToTimeConversion(){
        Time t = new Time(229924);
        assertAll(
                "Int to Time",
                () -> assertEquals(t.getMinutes() , 3),
                () -> assertEquals(t.getSeconds() , 49),
                () -> assertEquals(t.getThousands(), 924)

        );
    }

    /**
     * Tests toString method
     */
    @Test
    void TestToStringMethod(){
        Time t = new Time(1,11,233);
        assertEquals(t.toString(), "1:11:233");
    }
}
