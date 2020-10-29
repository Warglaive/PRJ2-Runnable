import availability.LocalDateTimeRange;
import entities.Truck;
import entities.TruckMapper;
import logic.PGDataSource;
import nl.fontys.sebivenlo.dao.DAO;
import nl.fontys.sebivenlo.dao.pg.PGDAOFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class TruckTest {
    static PGDataSource ds = PGDataSource.DATA_SOURCE;
    static PGDAOFactory daof;
    private Truck dummy;
    private LocalDate date;

    @BeforeClass
    public static void setupClass() {
        daof = new PGDAOFactory( ds );
        daof.registerMapper( Truck.class, new TruckMapper() );
    }

    @Before
    public void dummyClass() {

        date = LocalDate.now();
        dummy = new Truck( 1, "KLE-1222", 1, 1, 1, date);
    }

    @Test
    public void t00Size() {
        DAO<Integer, Truck> edao = daof.createDao( Truck.class );

        int size = edao.size();
        assertTrue( size < 1  );

//        fail( "t method t00Size reached its end, you ca remove this line when you aggree." );
    }

    //Check if something can get retrieved from the DB
    @Test
    public void t02GetAll() {
        DAO<Integer, Truck> edao = daof.createDao( Truck.class );
        Collection<Truck> el = edao.getAll();
        assertTrue( el.size() < 1);

    }

    @Test
    public void t03Delete() {
        DAO<Integer, Truck> edao = daof.createDao( Truck.class );

        LocalDateTime begin = LocalDateTime.of(2020, 10, 13, 10, 30, 12);
        LocalDateTime end = LocalDateTime.of(2020, 10, 13, 11, 30, 12);

        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);
        Truck dummy = new Truck( 1, "KLE-1222", 1, 1, 1, date);

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

        DAO<Integer, Truck> edao = daof.createDao( Truck.class );


        int before = edao.size();
        edao.save(dummy);
        int afterInsert = edao.size();

        assertTrue(afterInsert == before + 1);

    }

    //    @Ignore
    @Test
    public void t05Update() {
        DAO<Integer, Truck> edao = daof.createDao( Truck.class );

        LocalDateTime begin = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        end.plusHours(1);
        LocalDateTimeRange dummyRange = new LocalDateTimeRange(begin, end);

        edao.save( dummy );
        int id = edao.get(edao.size()).get().getId();
        dummy = new Truck( 1, "KLE-1222", 1, 1, 1, date);
        edao.update(dummy);
        int updatedID = edao.get(edao.size()).get().getId();

        assertTrue( updatedID == id + 1);

        //fail( "t05Update not yet implemented. Review the code and comment or delete this line" );
    }
}
