package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class TruckDriverMapper extends AbstractMapper<Integer, TruckDriver> {

    public TruckDriverMapper(  ) {
        super( Integer.class, TruckDriver.class );
    }

    @Override
    public Function<TruckDriver, Integer> keyExtractor() {
        return TruckDriver::getNaturalId;
    }
}
