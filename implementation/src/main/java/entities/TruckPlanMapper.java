package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class TruckPlanMapper extends AbstractMapper<Integer, TruckPlan> {

    public TruckPlanMapper() {
        super(Integer.class, TruckPlan.class);
    }

    @Override
    public Function<TruckPlan, Integer> keyExtractor() {
        return TruckPlan::getNaturalId;
    }
}
