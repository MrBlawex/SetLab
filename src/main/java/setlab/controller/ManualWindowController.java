package setlab.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import setlab.SetLab;
import setlab.Setting;

import java.net.URL;
import java.util.ResourceBundle;

public class ManualWindowController implements Initializable {

    URL url;
    @FXML
    private WebView mainWin;
    @FXML
    private Label info;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        url = getClass().getResource("/setlab/manual.html");
        mainWin.getEngine().load(url.toExternalForm());
        mainWin.contextMenuEnabledProperty().set(false);

        info.setText(SetLab.NAME_PROGRAM + " " + Setting.VERSION);
    }
}
