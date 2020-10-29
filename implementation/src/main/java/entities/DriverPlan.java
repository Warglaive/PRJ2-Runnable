package entities;

import availability.LocalDateTimeRange;
import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class DriverPlan implements Entity2<Integer>{
    @ID
    private Integer driverplanid;
    private Integer driverid;
    private LocalDateTimeRange plan;

    public DriverPlan( Integer driverplanid, Integer driverid, LocalDateTimeRange plan ) {
        this.driverplanid = driverplanid;
        this.driverid = driverid;
        this.plan = plan;
    }

    public DriverPlan( Integer driverid, LocalDateTimeRange plan ) {
        this.driverid = driverid;
        this.plan = plan;
    }


    @Override
    public Integer getNaturalId() {
        return driverplanid;
    }

    @Override
    public int getId() {
        return driverplanid;
    }

    Object[] asParts() {
        return new Object[] {
                driverplanid,
                driverid,
                plan
        };
    }

    public void setDriverplanid(Integer driverplanid) {
        this.driverplanid = driverplanid;
    }

    public void setPlan(LocalDateTimeRange plan) {
        this.plan = plan;
    }

    public Integer getDriverplanid() {
        return driverplanid;
    }

    public Integer getDriverid() {
        return driverid;
    }

    public LocalDateTimeRange getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "driverPlan{" + "driverplanid=" + driverplanid + ", driverid=" + driverid + ", plan=" + plan + '}';
    }
}