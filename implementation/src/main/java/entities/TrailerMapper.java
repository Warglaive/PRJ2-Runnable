package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class TrailerMapper extends AbstractMapper<Integer, Trailer> {

    public TrailerMapper(  ) {
        super( Integer.class, Trailer.class );
    }

    @Override
    public Function<Trailer, Integer> keyExtractor() {
        return Trailer::getNaturalId;
    }
}
