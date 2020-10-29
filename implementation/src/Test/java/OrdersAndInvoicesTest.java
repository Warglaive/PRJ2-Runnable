import controllers.OrdersInvoicesController;
import entities.*;
import logic.PGDataSource;
import nl.fontys.sebivenlo.dao.DAO;
import nl.fontys.sebivenlo.dao.pg.PGDAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersAndInvoicesTest {
    private OrdersInvoicesController controller;

    @BeforeEach
    void Initialize() {
        this.controller = new OrdersInvoicesController();
    }

    @Test
    public void displayAllInvoicesTest() {
        //Since the test DATA is too much, I'm going to add one object at the end and test if its the same.
        //create Invoice expected object
        Invoice expectedInvoice = new Invoice(1, 1, false);
        //Create DAO factory
        PGDAOFactory pdaof = new PGDAOFactory(PGDataSource.DATA_SOURCE);
        //Register mapper
        pdaof.registerMapper(Invoice.class, new InvoiceMapper());
        //Create DAO object so we can access the DB
        DAO<Integer, Invoice> invoiceDAO = pdaof.createDao(Invoice.class);
        //save invoice into the DB
        invoiceDAO.save(expectedInvoice);
        //take last element's INDEX
        int lastElementIndex = invoiceDAO.size() - 1;
        //take last element from the DB
        Invoice actualInvoice = invoiceDAO.getAll().get(lastElementIndex);
        //assert it is the same as the last saved in the DB
        assertThat(actualInvoice).as("GetAll works as intended").isEqualToComparingFieldByField(expectedInvoice);
    }

    @Test
    public void createOfferTest() {
        int id = 1;
        int customerId = 1;
        int workOrderId1 = 1;
        int workOrderId2 = 1;
        int workOrderId3 = 1;
        int productId = 1;
        //Take the date in the correct format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate orderDate = LocalDate.parse("19-06-2020");
        boolean isAccepted = true;
        float price = 6666;
        //Create Test Offer Object
        Offer expectedOffer = new Offer(id, customerId, workOrderId1, workOrderId2, workOrderId3, productId, orderDate, isAccepted, price);

        //Create DAO factory
        PGDAOFactory pdaof = new PGDAOFactory(PGDataSource.DATA_SOURCE);
        //Register mapper
        pdaof.registerMapper(Offer.class, new OrderMapper());
        //Create DAO object so we can access the DB
        DAO<Integer, Offer> offerDAO = pdaof.createDao(Offer.class);
        //save invoice into the DB
        offerDAO.save(expectedOffer);
        //take last element's INDEX
        int lastElementIndex = offerDAO.size() - 1;
        //take last element from the DB
        Offer actualOffer = offerDAO.getAll().get(lastElementIndex);
        assertThat(actualOffer).as("Save Offer works as intended").isEqualToComparingFieldByField(expectedOffer);
    }

 //  public void getOfferByIdTest() {
 //      int expectedObjectId = 999;
 //      //create Offer expected object with TEST values
 //      Offer expectedResultOffer = new Offer(expectedObjectId, 1, 1, 2, 1, 2, null, true, 0.99f);
 //      //Create DAO factory
 //      PGDAOFactory pdaof = new PGDAOFactory(PGDataSource.DATA_SOURCE);
 //      //Register mapper
 //      pdaof.registerMapper(Offer.class, new OfferMapper());
 //      //Create DAO object so we can access the DB
 //      DAO<Integer, Offer> offerDAO = pdaof.createDao(Offer.class);
 //      //save Offer into the DB
 //      offerDAO.save(expectedResultOffer);
 //      //initialize controller
 //      //take Offer by ID from the controller
 //      Offer actualResultOffer = this.controller.getOfferById(expectedObjectId);
 //      //check if correct
 //      assertThat(actualResultOffer).isEqualToComparingFieldByField(expectedResultOffer);
 //  }

  // public void getInvoiceByIdTest() {
  //     int expectedObjectId = 888;
  //     //create Invoice expected object with TEST values
  //     Invoice expectedResultInvoice = new Invoice(expectedObjectId, 1, false);
  //     //Create DAO factory
  //     PGDAOFactory pdaof = new PGDAOFactory(PGDataSource.DATA_SOURCE);
  //     //Register mapper
  //     pdaof.registerMapper(Invoice.class, new InvoiceMapper());
  //     //Create DAO object so we can access the DB
  //     DAO<Integer, Invoice> invoiceDAO = pdaof.createDao(Invoice.class);
  //     //save Invoice into the DB
  //     invoiceDAO.save(expectedInvoice);
  //     //initialize controller
  //     //take Invoice by ID from the controller
  //     Invoice actualResultInvoice = this.controller.getInvoiceById(expectedObjectId);
  //     //check if correct
  //     assertThat(actualResultInvoice).isEqualToComparingFieldByField(expectedResultInvoice);
  // }
}
