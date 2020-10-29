import availability.LocalDateTimeRange;
import entities.Invoice;
import entities.InvoiceMapper;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class InvoiceMapperTest {


    @Test
    public void testKeyExtractor() {

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        Invoice dummy = new Invoice(1,1, true );
        InvoiceMapper em = new InvoiceMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
