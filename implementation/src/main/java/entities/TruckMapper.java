package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class TruckMapper extends AbstractMapper<Integer, Truck> {

    public TruckMapper(  ) {
        super( Integer.class, Truck.class );
    }

    @Override
    public Function<Truck, Integer> keyExtractor() {
        return Truck::getNaturalId;
    }
}
