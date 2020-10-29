import availability.LocalDateTimeRange;
import entities.TrailerPlan;
import entities.TrailerPlanMapper;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class TrailerPlanMapperTest {

    @Test
    public void testKeyExtractor() {

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        TrailerPlan dummy = new TrailerPlan(1, dummyRange);
        TrailerPlanMapper em = new TrailerPlanMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
