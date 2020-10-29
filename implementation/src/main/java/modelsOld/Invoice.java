package modelsOld;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Invoice {
    private IntegerProperty id;
    ////product ID, Will be made to collection for production, its good for now.
    private IntegerProperty productsList;
    private SimpleObjectProperty<LocalDateTime> Date;
    private boolean isPaid;
    private DoubleProperty totalSum;
    private IntegerProperty customerId;

    //Constructor
    public Invoice() {
        this.id = new SimpleIntegerProperty();
        //product ID
        this.productsList = new SimpleIntegerProperty();
        //DD-MM-YYYY
        this.Date = new SimpleObjectProperty<LocalDateTime>();
        //false by default;
        this.isPaid = false;
        this.totalSum = new SimpleDoubleProperty();
        this.customerId = new SimpleIntegerProperty();
    }

    //Getter and Setter for Id;
    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty InvoiceIdProperty() {
        return this.id;
    }
    //Getter and Setter for getCustomerId;

    public int getCustomerId() {
        return this.customerId.get();
    }

    public void setCustomerId(int id) {
        this.customerId.set(id);
    }
    //Getter and Setter for total;

    public double getTotalSum() {
        return this.totalSum.get();
    }

    public void setTotalSum(double totalSum) {
        this.totalSum.set(totalSum);
    }

    //Getter and Setter for paid;
    public boolean getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    //Getter and Setter for Date;
    public LocalDateTime getDate() {
        return this.Date.get();
    }

    public void setDate(LocalDateTime date) {
        this.Date.set(date);
    }
}
