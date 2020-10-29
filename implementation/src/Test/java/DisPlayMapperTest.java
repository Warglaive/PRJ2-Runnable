import availability.LocalDateTimeRange;
import entities.Display;
import entities.DisplayMapper;
import logic.PGDataSource;
import nl.fontys.sebivenlo.dao.DAO;
import nl.fontys.sebivenlo.dao.pg.PGDAOFactory;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class DisPlayMapperTest {

    @Test
    public void testKeyExtractor() {

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        Display dummy = new Display( 1, 1, 1, 1, dummyRange);
        DisplayMapper em = new DisplayMapper();
        assertTrue( em.keyExtractor().apply( dummy ).equals( 1 ) );

        //fail( "testKeyExtractor not yet implemented. Review the code and comment or delete this line" );
    }
}
