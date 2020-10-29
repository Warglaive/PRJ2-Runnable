package entities;

import nl.fontys.sebivenlo.dao.AbstractMapper;

import java.util.function.Function;

public class InvoiceMapper extends AbstractMapper<Integer, Invoice> {
    /**
     * Create a mapper for entity and key type.
     */
    public InvoiceMapper() {
        super(Integer.class, Invoice.class);
    }

    @Override
    public Object[] explode(Invoice e) {
        return e.asParts();
    }

    @Override
    public Function<Invoice, Integer> keyExtractor() {
        return Invoice::getId;
    }

    @Override
    public String tableName() {
        return "invoice";
    }
}