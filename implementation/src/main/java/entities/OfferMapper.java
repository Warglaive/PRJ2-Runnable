package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class OfferMapper extends AbstractMapper<Integer, Offer> {

    /**
     * Create a mapper for entity and key type.
     */
    public OfferMapper() {
        super(Integer.class, Offer.class);
    }

    /**
     * Get the id from the entity.
     *
     * @return a method to retrieve the id.
     */
    @Override
    public Function<Offer, Integer> keyExtractor() {
        return Offer::getId;
    }

    @Override
    public Object[] explode(Offer e) {
        return e.asParts();
    }

    @Override
    public String tableName() {
        return "offer";
    }
}
