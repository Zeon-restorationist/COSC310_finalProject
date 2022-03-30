module com.log.chatlog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.log.chatlog to javafx.fxml;
    exports com.log.chatlog;
}