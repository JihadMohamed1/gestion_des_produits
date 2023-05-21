module com.emsi.product_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens com.emsi.product_app to javafx.fxml;
    opens com.emsi.Maven.jdbc.dao to javafx.base;
    opens com.emsi.Maven.jdbc.Entites to javafx.base;
    opens com.emsi.Maven.jdbc.Services to javafx.base;

    exports com.emsi.product_app;
    exports com.emsi.Maven.jdbc.dao.Imp;
    exports com.emsi.Maven.jdbc.Entites;
    exports com.emsi.Maven.jdbc.Services;
    exports com.emsi.product_app.Views;
    opens com.emsi.product_app.Views to java.base, javafx.base, javafx.fxml;
}