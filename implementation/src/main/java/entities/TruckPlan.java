package entities;

import availability.LocalDateTimeRange;
import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class TruckPlan implements Entity2<Integer>{
    @ID
    private Integer truckplanid;
    private Integer truckid;
    private LocalDateTimeRange plan;

    public TruckPlan(Integer id, Integer truckid, LocalDateTimeRange plan ) {
        this.truckplanid = id;
        this.truckid = truckid;
        this.plan = plan;
    }

    public TruckPlan(Integer truckid, LocalDateTimeRange plan ) {
        this.truckid = truckid;
        this.plan = plan;
    }

    @Override
    public Integer getNaturalId() {
        return truckplanid;
    }

    @Override
    public int getId() {
        return truckplanid;
    }

    Object[] asParts() {
        return new Object[] {
                truckplanid,
                truckid,
                plan
        };
    }

    public Integer getTruckplanid() {
        return truckplanid;
    }

    public Integer getTruckid() {
        return truckid;
    }

    public LocalDateTimeRange getPlan() {
        return plan;
    }

    public void setTruckplanid(Integer truckplanid) { this.truckplanid = truckplanid; }

    public void setTruckid(Integer truckid) {
        this.truckid = truckid;
    }

    public void setPlan(LocalDateTimeRange plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "TruckPlan{" + "id=" + truckplanid + ", truckid=" + truckid + ", plan=" + plan + '}';
    }
}
