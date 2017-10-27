package ru.catstack.sc_project.utils;

import javafx.scene.image.Image;

import java.io.File;

public class ImageUtils {

    public static Image getAssetsImage(String path) {
        return new Image("file:///" + new File("").getAbsolutePath().replaceAll("\\\\", "/") + "/assets/" + path,
                300, 200, true, true);
    }

    public static Image getAssetsImage(String path, int width, int height) {
        return new Image("file:///" + new File("").getAbsolutePath().replaceAll("\\\\", "/") + "/assets/" + path,
                width, height, true, true);
    }

}
