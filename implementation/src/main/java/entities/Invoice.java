package entities;

import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class Invoice implements Entity2<Integer> {
    @ID
    private int id;
    private int orderId;
    private boolean isPaid;

    //Constructor
    public Invoice(int id, int orderId, boolean isPaid) {
        //AutoGenerated ID
        this.id = id;
        this.orderId = orderId;
        this.isPaid = isPaid;
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

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean getIsPaid() {
        return this.isPaid;
    }

    static Invoice fromParts(Object[] parts) {
        return new Invoice((int) parts[0], (Integer) parts[1], (boolean) parts[3]);
    }

    Object[] asParts() {

        return new Object[]{
                this.id,
                this.orderId,
                this.isPaid
        };
    }

    @Override
    public String toString() {
        return "invoice{" + "id=" + this.id + "orderId=" + this.orderId + "isPaid" + this.isPaid + '}';
    }
}