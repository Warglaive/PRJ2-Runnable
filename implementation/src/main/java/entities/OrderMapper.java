package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class OrderMapper extends AbstractMapper<Integer, Order> {

    /**
     * Create a mapper for entity and key type.
     */
    public OrderMapper() {
        super(Integer.class, Order.class);
    }

    /**
     * Get the id from the entity.
     *
     * @return a method to retrieve the id.
     */
    @Override
    public Function<Order, Integer> keyExtractor() {
        return Order::getId;
    }

    @Override
    public Object[] explode(Order e) {
        return e.asParts();
    }

    @Override
    public String tableName() {
        return "order";
    }
}
