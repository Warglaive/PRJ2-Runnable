import availability.LocalDateTimeRange;
import entities.Order;
import entities.OrderMapper;
import entities.Order;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class OrderMapperTest {

    @Test
    public void testKeyExtractor() {

        Order dummy = new Order( 1, 1);
        OrderMapper em = new OrderMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
