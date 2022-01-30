module edu.wsu {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens edu.wsu to javafx.fxml;
    exports edu.wsu;
    exports edu.wsu.controller;
    opens edu.wsu.controller to javafx.fxml;
}
