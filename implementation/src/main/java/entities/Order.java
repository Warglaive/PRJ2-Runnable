package entities;

import nl.fontys.sebivenlo.dao.Entity2;
import nl.fontys.sebivenlo.dao.ID;

public class Order implements Entity2<Integer> {
    @ID
    private int id;
    private int offerId;

    public Order(int id, int offerId) {
        this.id = id;
        this.offerId = offerId;
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

    public int getOfferId() {
        return this.offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    static Order fromParts(Object[] parts) {
        return new Order((int) parts[0], (int) parts[1]);
    }

    Object[] asParts() {
        return new Object[]{
                this.id,
                this.offerId
        };
    }

    @Override
    public String toString() {
        return "order{" + "id=" + this.id + "offerId=" + this.offerId + '}';
    }
}
