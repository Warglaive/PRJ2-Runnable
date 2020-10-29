import availability.LocalDateTimeRange;
import entities.Order;
import entities.OrderMapper;
import logic.PGDataSource;
import nl.fontys.sebivenlo.dao.DAO;
import nl.fontys.sebivenlo.dao.pg.PGDAOFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class OrderDAOTest {
    static PGDataSource ds = PGDataSource.DATA_SOURCE;
    static PGDAOFactory daof;
    private Order dummy;

    @BeforeClass
    public static void setupClass() {
        daof = new PGDAOFactory( ds );
        daof.registerMapper( Order.class, new OrderMapper() );
    }

    @Before
    public void dummyClass() {

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        dummy = new Order( 1, 1);
    }

    @Test
    public void t00Size() {
        DAO<Integer, Order> edao = daof.createDao( Order.class );

        int size = edao.size();
        assertTrue( size < 1  );

//        fail( "t method t00Size reached its end, you ca remove this line when you aggree." );
    }

    //Check if something can get retrieved from the DB
    @Test
    public void t02GetAll() {
        DAO<Integer, Order> edao = daof.createDao( Order.class );
        Collection<Order> el = edao.getAll();
        assertTrue( el.size() < 1);

    }

    @Test
    public void t03Delete() {
        DAO<Integer, Order> edao = daof.createDao( Order.class );

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        Order dummy = new Order( 1, 1);

        int before = edao.size();
        edao.save(dummy);
        int afterInsert = edao.size();
        edao.delete( dummy );
        int afterDelete = edao.size();

        assertTrue(afterInsert == before + 1);
        assertTrue(afterDelete == before);

    }

    @Test
    public void t04Create() {

        DAO<Integer, Order> edao = daof.createDao( Order.class );


        int before = edao.size();
        edao.save(dummy);
        int afterInsert = edao.size();

        assertTrue(afterInsert == before + 1);

    }

    //    @Ignore
    @Test
    public void t05Update() {
        DAO<Integer, Order> edao = daof.createDao( Order.class );

        LocalDateTime begin = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        end.plusHours(1);
        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);

        edao.save( dummy );
        int id = edao.get(edao.size()).get().getId();
        dummy = new Order( 1, 1);
        edao.update(dummy);
        int updatedID = edao.get(edao.size()).get().getId();

        assertTrue( updatedID == id + 1);

        //fail( "t05Update not yet implemented. Review the code and comment or delete this line" );
    }
}
