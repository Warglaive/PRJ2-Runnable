package entities;

import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class Trailer implements Entity2<Integer> {
    @ID
    private Integer id;
    private String licenseplate;
    private Integer maxloadweight;

    public Trailer(Integer id, String licenseplate, Integer macloadweight) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.maxloadweight = macloadweight;

    }

    @Override
    public Integer getNaturalId() {
        return id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TruckPlan{" + "id=" + id + ", licenseplate=" + licenseplate + ", maxloadweight=" + maxloadweight + '}';
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public Integer getMaxloadweight() {
        return maxloadweight;
    }


}
