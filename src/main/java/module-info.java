module ec.edu.espol.proyectopoo2doparcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectopoo2doparcial to javafx.fxml;
    exports ec.edu.espol.proyectopoo2doparcial;
    requires java.mail;
    requires javafx.graphicsEmpty;
}
