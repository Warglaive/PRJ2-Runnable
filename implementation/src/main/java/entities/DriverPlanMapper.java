package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class DriverPlanMapper extends AbstractMapper<Integer, DriverPlan> {

    public DriverPlanMapper(  ) {
        super( Integer.class, DriverPlan.class );
    }

    @Override
    public Function<DriverPlan, Integer> keyExtractor() {
        return DriverPlan::getNaturalId;
    }
}
