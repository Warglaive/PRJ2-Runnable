import availability.LocalDateTimeRange;
import entities.TruckDriver;
import entities.TruckDriverMapper;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class TruckDriverMapperTest {

    @Test
    public void testKeyExtractor() {

        LocalDate date = LocalDate.now();
        TruckDriver dummy = new TruckDriver( 1, date, 1);
        TruckDriverMapper em = new TruckDriverMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
