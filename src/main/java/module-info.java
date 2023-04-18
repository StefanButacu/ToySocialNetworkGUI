module com.toysocialnetworkgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.pdfbox;
    requires org.json;

    opens com.toysocialnetworkgui to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.controller to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.utils to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.service to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.domain to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.validator to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.repository to javafx.fxml, javafx.base, javafx.graphics;
    opens com.toysocialnetworkgui.repository.db to javafx.fxml, javafx.base, javafx.graphics;
    exports com.toysocialnetworkgui;
    exports com.toysocialnetworkgui.service;
    exports com.toysocialnetworkgui.controller;
    exports com.toysocialnetworkgui.utils;
    exports com.toysocialnetworkgui.domain;
    exports com.toysocialnetworkgui.validator;
    exports com.toysocialnetworkgui.repository;
    exports com.toysocialnetworkgui.repository.db;
    exports com.toysocialnetworkgui.service.export;
    opens com.toysocialnetworkgui.service.export to javafx.base, javafx.fxml, javafx.graphics;
}