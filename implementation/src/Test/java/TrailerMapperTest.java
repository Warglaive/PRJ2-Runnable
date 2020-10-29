import availability.LocalDateTimeRange;
import entities.Trailer;
import entities.TrailerMapper;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class TrailerMapperTest {

    @Test
    public void testKeyExtractor() {


        Trailer dummy = new Trailer( 1, "KLE-1919", 1);
        TrailerMapper em = new TrailerMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
