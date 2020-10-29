package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class DisplayMapper extends AbstractMapper<Integer, Display> {

    public DisplayMapper() {
        super( Integer.class, Display.class );
    }

    @Override
    public Function<Display, Integer> keyExtractor() {
        return Display::getNaturalId;
    }
}
