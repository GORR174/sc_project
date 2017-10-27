package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;

public class TheoryController extends GController {

    @FXML
    public VBox helpBox;

    private String helpText = Core.userInfo.getThisTheme().getHelp();

    @Override
    public void onShow() throws IOException {
//        helpBox.getChildren().add();

        helpText = "\n" + helpText + "\n\n";

        ArrayList<Node> nodes = parseHelp();

        for (Node node : nodes) {
            helpBox.getChildren().add(node);
        }

    }

    private ArrayList<Node> parseHelp() {
        ArrayList<Node> nodes = new ArrayList<>();
        String text = helpText;

        while (text != null) {
            text = parseImage(nodes, text);
        }

        return nodes;
    }

    private String parseImage(ArrayList<Node> nodes, String text) {

        String rText = text;

        if (rText.contains("<img=\"")) {

            if (rText.substring(rText.indexOf("<img=\"")).contains("\">")) {
                nodes.add(getLabelFromText(rText.substring(0, rText.indexOf("<img=\""))));
                nodes.add(getLabelFromText(" "));

                ImageView imageView = new ImageView();

                imageView.setFitWidth(400);
                imageView.setFitHeight(250);
                imageView.setPreserveRatio(true);

                String imageName = rText.substring(rText.indexOf("<img=\"")+6);
                imageName = imageName.substring(0, imageName.indexOf("\">"));

                Image image = ImageUtils.getAssetsImage(imageName, 400, 250);

                imageView.setImage(image);

                BorderPane pane = new BorderPane(imageView);

                pane.setMaxWidth(406);
                pane.setMaxHeight(256);
                pane.setMinWidth(406);
                pane.setMinHeight(256);
                pane.setPrefWidth(406);
                pane.setPrefHeight(256);

                pane.setStyle("-fx-border-color: CCCCCC; -fx-border-radius: 3; -fx-border-width: 3; -fx-background-color: FCFCFC;");;

                if (!image.isError()) {
                    nodes.add(pane);
                }

                rText = rText.substring(rText.indexOf("<img=\""));
                rText = rText.substring(rText.indexOf("\">")+2);

            } else {
                nodes.add(getLabelFromText(rText));
                return null;
            }

        } else {
            nodes.add(getLabelFromText(rText));
            return null;
        }

        return rText;
    }

    public void onStartClick(ActionEvent actionEvent) throws Exception {
        GApp.app.setScene(FXML_FILES.STUDENT.getUrl());
    }

    public void onBackClick(ActionEvent actionEvent) throws Exception {
        GApp.app.setScene(FXML_FILES.LOGIN.getUrl());
    }

    private Label getLabelFromText(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setFont(Font.font(14));

        return label;
    }
}
