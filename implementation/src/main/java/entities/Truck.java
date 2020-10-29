package entities;

import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

import java.time.LocalDate;

public class Truck implements Entity2<Integer> {
    @ID
    private Integer id;
    private String licenseplate;
    private Integer maxloadweight;
    private Integer hasmileage;
    private Integer haspower;
    private LocalDate apkdate;

    public Truck(Integer id, String licenseplate, Integer macloadweight, Integer hasmilage, Integer haspower, LocalDate apkdate) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.maxloadweight = macloadweight;
        this.hasmileage = hasmilage;
        this.haspower = haspower;
        this.apkdate = apkdate;
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
        return "TruckPlan{" + "id=" + id + ", licenseplate=" + licenseplate + ", maxloadweight=" + maxloadweight + ", milage" + hasmileage + ", power=" + haspower + '}';
    }

    Object[] asParts() {
        return new Object[] {
                id,
                licenseplate,
                maxloadweight,
                hasmileage,
                haspower,
                apkdate
        };
    }


    public String getLicenseplate() {
        return licenseplate;
    }

    public Integer getMaxloadweight() {
        return maxloadweight;
    }

    public Integer getHasmileage() {
        return hasmileage;
    }

    public Integer getHaspower() {
        return haspower;
    }

    public LocalDate getApkdate(){return  apkdate;}
}
