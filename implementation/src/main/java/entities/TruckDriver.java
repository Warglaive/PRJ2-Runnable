package entities;

import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

import java.time.LocalDate;

public class TruckDriver implements Entity2<Integer> {
    @ID
    private Integer id;
    private LocalDate licensevalidtill;
    private Integer employeeid;

    public TruckDriver(Integer id, LocalDate licensevalidtill, Integer employeeid) {
        this.id = id;
        this.licensevalidtill = licensevalidtill;
        this.employeeid = employeeid;
    }

    public LocalDate getLicensevalidtill() {
        return licensevalidtill;
    }

    public Integer getEmployeeid() {
        return employeeid;
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
        return "TruckPlan{" + "id=" + id + ", licensevalidtill=" + licensevalidtill + ", employeeid=" + employeeid + '}';
    }

}
