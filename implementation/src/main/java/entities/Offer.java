package entities;

import nl.fontys.sebivenlo.dao.Entity2;

import java.time.LocalDate;

public class Offer implements Entity2<Integer> {
    private int id;
    private int customerId;
    private int workOrderId1;
    private int workOrderId2;
    private int workOrderId3;
    private int productId;
    private LocalDate orderDate;
    private boolean isAccepted;
    private float price;

    public Offer(int id, int customerId, int workOrderId1, int workOrderId2, int workOrderId3, int productId, LocalDate orderDate, boolean isAccepted, float price) {
        this.id = id;
        this.customerId = customerId;
        this.workOrderId1 = workOrderId1;
        this.workOrderId2 = workOrderId2;
        this.workOrderId3 = workOrderId3;
        this.productId = productId;
        this.orderDate = orderDate;
        this.isAccepted = isAccepted;
        this.price = price;
    }


    /**
     * Get the key of this entity.
     *
     * @return the key.
     */
    @Override
    public Integer getNaturalId() {
        return this.id;
    }

    /**
     * Get the id of this entity.
     *
     * @return the id.
     */
    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getWorkOrderId1() {
        return workOrderId1;
    }

    public void setWorkOrderId1(int workOrderId1) {
        this.workOrderId1 = workOrderId1;
    }

    public int getWorkOrderId2() {
        return workOrderId2;
    }

    public void setWorkOrderId2(int workOrderId2) {
        this.workOrderId2 = workOrderId2;
    }

    public int getWorkOrderId3() {
        return workOrderId3;
    }

    public void setWorkOrderId3(int workOrderId3) {
        this.workOrderId3 = workOrderId3;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Object[] asParts() {
        return new Object[]{
                this.id,
                this.customerId,
                this.workOrderId1,
                this.workOrderId2,
                this.workOrderId3,
                this.productId,
                this.orderDate,
                this.isAccepted,
                this.price
        };
    }

    @Override
    public String toString() {
        return "offer{" + "id=" + this.id
                + "customerId=" + this.customerId
                + "workOrderId1=" + this.workOrderId1
                + "workOrderId2=" + this.workOrderId2
                + "workOrderId3=" + this.workOrderId3
                + "productId=" + this.productId
                + "orderDate=" + this.orderDate
                + "isAccepted=" + this.isAccepted
                + "price=" + this.price
                + '}';
    }
}
