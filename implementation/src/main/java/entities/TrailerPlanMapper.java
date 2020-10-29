package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class TrailerPlanMapper extends AbstractMapper<Integer, TrailerPlan> {

    public TrailerPlanMapper(  ) {
        super( Integer.class, TrailerPlan.class );
    }

    @Override
    public Function<TrailerPlan, Integer> keyExtractor() {
        return TrailerPlan::getNaturalId;
    }
}
