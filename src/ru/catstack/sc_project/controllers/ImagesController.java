package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.impl.HavingImage;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.ImageUtils;

import java.io.File;
import java.util.ArrayList;

public class ImagesController extends GController {

    @FXML
    public VBox imageBox;

    private ArrayList<File> files = new ArrayList<>();

    @Override
    public void onShow() throws Exception {
        File directory = new File("assets");

        for (File file : directory.listFiles()) {
            if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))) {
                files.add(file);
            }
        }

        HBox hBox = new HBox();
        int i = 1;
        for (File file : files) {

            ImageView imageView = new ImageView();

            imageView.setFitWidth(194);
            imageView.setFitHeight(144);
            imageView.setPreserveRatio(true);

            Image image = ImageUtils.getAssetsImage(file.getName(), 194, 144);

            imageView.setImage(image);

            Label label = new Label(file.getName());

            VBox vBox = new VBox();
            hBox.setMargin(vBox, new Insets(5));

            vBox.getChildren().add(imageView);
            vBox.getChildren().add(label);
            vBox.setMargin(label, new Insets(8, 0, 0, 0));

            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.setStyle("-fx-border-color: CCCCCC; -fx-border-radius: 3; -fx-border-width: 3; -fx-background-color: FCFCFC;");
            vBox.setMinWidth(200);
            vBox.setMinHeight(180);
            vBox.setPrefWidth(200);
            vBox.setPrefHeight(180);
            vBox.setMaxWidth(200);
            vBox.setMaxHeight(180);

            vBox.setOnMouseClicked(event -> {
                Core.userInfo.setThisImage(file.getName());
                GApp.app.closeWindow(FXML_FILES.IMAGES.getUrl());
                HavingImage parent = (HavingImage) GApp.app.getChildWindows().get((GApp.app.getChildWindows().size()-1)).getController();
                parent.update();
            });

            if (!image.isError()) {
                hBox.getChildren().add(vBox);

                i++;
                if (i > 4) {
                    i = 1;
                    imageBox.getChildren().add(hBox);
                    hBox = new HBox();
                }
            }
        }

        if (hBox.getChildren().size() > 0) {
            imageBox.getChildren().add(hBox);
        }

    }

    public void onBackClick(ActionEvent actionEvent) {
        Core.userInfo.setThisImage(null);
        GApp.app.closeWindow(FXML_FILES.IMAGES.getUrl());
    }
}
