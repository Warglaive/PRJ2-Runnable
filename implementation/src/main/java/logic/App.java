package logic;

import entities.*;
import availability.LocalDateTimeRange;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.fontys.sebivenlo.dao.pg.PGDAO;
import nl.fontys.sebivenlo.dao.pg.PGDAOFactory;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("LoginUI"));
        stage.setScene(scene);
        stage.setTitle("LogistX Planning Software: Login");
        stage.setResizable(false);
        stage.show();
        PGDAO<Integer, TruckPlan> dao1 = pdaof().createDao(TruckPlan.class);

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getClassLoader().getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static PGDAOFactory pdaof = null;

    public static final PGDAOFactory pdaof() {
        if ( null == pdaof ) {
            pdaof = new PGDAOFactory( PGDataSource.DATA_SOURCE );
            pdaof.registerMapper( TruckPlan.class, new TruckPlanMapper() );
            pdaof.registerMapper(TrailerPlan.class, new TrailerPlanMapper());
            pdaof.registerMapper(DriverPlan.class, new DriverPlanMapper());
            pdaof.registerMapper(TruckDriver.class, new TruckDriverMapper());
            pdaof.registerMapper(Truck.class, new TruckMapper());
            pdaof.registerMapper(Trailer.class, new TrailerMapper());
            pdaof.registerMapper(Invoice.class, new InvoiceMapper());
            pdaof.registerPGMashallers( LocalDateTimeRange.class, LocalDateTimeRange::fromTSRangeObject, x -> PGDAOFactory.pgobject( "tsrange", x ) );
        }
        return pdaof;
    }
}



