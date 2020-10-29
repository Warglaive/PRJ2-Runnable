import availability.LocalDateTimeRange;
import entities.Truck;
import entities.TruckMapper;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class TruckMapperTest {

    @Test
    public void testKeyExtractor() {

        LocalDate date = LocalDate.now();
        Truck dummy = new Truck( 1, "KLE-1222", 1, 1, 1, date);
        TruckMapper em = new TruckMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
