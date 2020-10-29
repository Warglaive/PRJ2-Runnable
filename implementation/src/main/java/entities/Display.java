package entities;

import availability.LocalDateTimeRange;
import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class Display implements Entity2<Integer> {
    @ID
    private Integer id;
    private Integer truckid;
    private Integer trailerid;
    private Integer driverid;
    private LocalDateTimeRange plan;

    public Display( Integer id, Integer truckid,Integer trailerid,Integer driverid, LocalDateTimeRange plan ) {
        this.id = id;
        this.truckid = truckid;
        this.trailerid = trailerid;
        this.driverid = driverid;
        this.plan = plan;
    }



    @Override
    public Integer getNaturalId() {
        return id;
    }

    @Override
    public int getId() {
        return id;
    }

    Object[] asParts() {
        return new Object[] {
                id,
                truckid,
                plan
        };
    }

    public Integer getid() {
        return id;
    }

    public Integer getTruckid() {
        return truckid;
    }

    public LocalDateTimeRange getPlan() {
        return plan;
    }

    public Integer getTrailerid() {
        return trailerid;
    }

    public Integer getDriverid() {
        return driverid;
    }

    @Override
    public String toString() {
        return "TruckPlan{" + "id=" + id + ", truckid=" + truckid + ", plan=" + plan + '}';
    }
}
