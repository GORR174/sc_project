package ru.catstack.sc_project.resources;

import ru.catstack.sc_project.engine.Main;

import java.net.URL;

public enum  FXML_FILES {
    LOGIN("login.fxml"),
    ABOUT("about.fxml"),
    TEACHER("teacher.fxml"),
    STUDENT("student.fxml"),
    ADD_TASK("addTask.fxml"),
    EDIT_TASK("editTask.fxml"),
    RESULTS("results.fxml"),
    ADD_THEORY("addTheory.fxml"),
    THEORY("theory.fxml"),
    IMAGES("images.fxml"),
    ;

    private URL url;
    FXML_FILES(String fileName) {
        url = Main.class.getResource("/fxml/" + fileName);
    }

    public URL getUrl() {
        return url;
    }
}
