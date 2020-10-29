import availability.LocalDateTimeRange;
import entities.Offer;
import entities.OfferMapper;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class OfferMapperTest {

    private LocalDate date = LocalDate.now();

    @Test
    public void testKeyExtractor() {


        Offer dummy = new Offer(1,1,1,1,1,1, date, true, 10);
        OfferMapper em = new OfferMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
