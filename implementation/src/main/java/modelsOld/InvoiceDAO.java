package modelsOld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class InvoiceDAO {
    public Invoice searchInvoice(String InvoiceId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Invoice WHERE id=" + InvoiceId;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsInvoice = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getInvoiceFromResultSet method and get invoice object
            Invoice invoice = getInvoiceFromResultSet(rsInvoice);

            //Return invoice object
            return invoice;
        } catch (SQLException e) {
            System.out.println("While searching an Invoice with " + InvoiceId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Invoice Object's attributes and return Invoice object.
    private static Invoice getInvoiceFromResultSet(ResultSet rs) throws SQLException {
        Invoice invoice = null;
        if (rs.next()) {
            invoice = new Invoice();
            //invoice's id == employee_id example
            invoice.setId(rs.getInt("id"));
            invoice.setCustomerId(rs.getInt("customerId"));
            invoice.setTotalSum(rs.getInt("total"));
            invoice.setIsPaid(rs.getBoolean("isPaid"));
            //TODO: MIGHT BE BUG!
            invoice.setDate(rs.getObject("Date", LocalDateTime.class));
        }
        return invoice;
    }

    //*******************************
    //SELECT Invoices
    //*******************************
    public static ObservableList<Invoice> searchInvoices() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Invoice";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsInvoices = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getInvoiceList method and get Invoice object
            ObservableList<Invoice> invoiceList = getInvoiceList(rsInvoices);

            //Return Invoice object
            return invoiceList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from Invoice operation
    private static ObservableList<Invoice> getInvoiceList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Invoice objects
        ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();
        Invoice invoice = null;
        if (rs.next()) {
            invoice = new Invoice();
            //invoice's id == employee_id example
            invoice.setId(rs.getInt("id"));
            invoice.setCustomerId(rs.getInt("customerId"));
            invoice.setTotalSum(rs.getInt("total"));
            invoice.setIsPaid(rs.getBoolean("isPaid"));
            //TODO: MIGHT BE BUG!
            invoice.setDate(rs.getObject("Date", LocalDateTime.class));
        }
        return invoiceList;
    }

    //*************************************
    //UPDATE an Invoice's 'total' table
    //*************************************
    public static void updateInvoiceTotal(String id, int newTotalValue) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE Invoice\n" +
                        "      SET TOTAL = '" + newTotalValue + "'\n" +
                        "    WHERE id = " + id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE a Invoice
    //*************************************
    public static void deleteInvoiceWithId(int id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM Invoice\n" +
                        "         WHERE id =" + id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT a Invoice TODO******************
    //*************************************
    public static void insertInvoice(String name, String lastName, String email) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO Invoice\n" +
                        "(id,  ,  ,  ,  ,  )\n" +
                        "VALUES\n" +
                        "(sequence_employee.nextval, '" + name + "', '" + lastName + "','" + email + "', SYSDATE, 'IT_PROG');\n" +
                        "END;";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
