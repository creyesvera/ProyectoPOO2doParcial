module ec.edu.espol.proyectopoo2doparcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.model to javafx.base;
    opens ec.edu.espol.proyectopoo2doparcial to javafx.fxml;    
    opens ec.edu.espol.controller to javafx.fxml;
    
    exports ec.edu.espol.proyectopoo2doparcial;
    exports ec.edu.espol.controller;
    
    requires java.mail;
    requires javafx.graphicsEmpty;
    requires java.base;
}
