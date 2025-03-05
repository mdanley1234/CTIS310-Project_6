package edu.guilford.applications;

import edu.guilford.MonkeyLauncher;
import javafx.scene.layout.GridPane;

public class TestApplication extends BaseApplication {

    public TestApplication(MonkeyLauncher launcher) {
        super(launcher);
    }

    @Override
    protected String setMenuImageLocation() {
        return "file:chemistrymonkey/src/main/resources/edu/guilford/testImage.jpg";
    }

    @Override
    protected GridPane getMainPane() {
        return new GridPane();
    }


}
