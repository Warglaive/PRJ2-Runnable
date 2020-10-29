import availability.LocalDateTimeRange;
import entities.Display;
import entities.DisplayMapper;
import entities.DriverPlan;
import entities.DriverPlanMapper;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class DriverPlanMapperTest {


    @Test
    public void testKeyExtractor() {

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        DriverPlan dummy = new DriverPlan( 1, dummyRange);
        DriverPlanMapper em = new DriverPlanMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
