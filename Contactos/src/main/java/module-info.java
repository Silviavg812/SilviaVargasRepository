module org.example.contactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jdk.compiler;
    requires java.desktop;
    requires java.net.http;

    opens org.example.contactos to javafx.fxml;
    exports org.example.contactos;
    opens org.example.contactos.controller to javafx.fxml,java.sql;
    exports org.example.contactos.controller;
    opens org.example.contactos.model to javafx.fxml;
    exports org.example.contactos.model;




}