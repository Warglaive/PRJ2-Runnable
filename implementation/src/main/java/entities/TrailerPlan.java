package entities;

import availability.LocalDateTimeRange;
import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class TrailerPlan implements Entity2<Integer>{
    @ID
    private Integer trailerplanid;
    private Integer trailerid;
    private LocalDateTimeRange plan;

    public TrailerPlan( Integer trailerplanid, Integer trailerid, LocalDateTimeRange plan ) {
        this.trailerplanid = trailerplanid;
        this.trailerid = trailerid;
        this.plan = plan;
    }

    public TrailerPlan( Integer trailerid, LocalDateTimeRange plan ) {
        this.trailerid = trailerid;
        this.plan = plan;
    }

    @Override
    public Integer getNaturalId() {
        return trailerplanid;
    }

    @Override
    public int getId() {
        return trailerplanid;
    }

    Object[] asParts() {
        return new Object[] {
                trailerplanid,
                trailerid,
                plan
        };

    }

    public void setTrailerplanid(Integer trailerplanid) {
        this.trailerplanid = trailerplanid;
    }

    public void setPlan(LocalDateTimeRange plan) {
        this.plan = plan;
    }

    public Integer getTrailerplanid() {
        return trailerplanid;
    }

    public Integer getTrailerid() {
        return trailerid;
    }

    public LocalDateTimeRange getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "trailerPlan{" + "trailerplanid=" + trailerplanid + ", trailerid=" + trailerid + ", plan=" + plan + '}';
    }
}
