module edu.guilford {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;

    opens edu.guilford to javafx.fxml;
    exports edu.guilford;
}
