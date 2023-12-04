module com.example.maven_pp_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.xml;
    requires exp4j;


    opens com.example.maven_pp_javafx to javafx.fxml;
    exports com.example.maven_pp_javafx;
}