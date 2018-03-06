package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.ThemeTask;
import ru.catstack.sc_project.objects.impl.HavingImage;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.ImageUtils;

public class AddTaskController extends GController implements HavingImage {

    @FXML
    public TextArea taskText;
    public TextField result;
    public ImageView taskImage;

    private String imagePath = "";

    @Override
    public void onShow() throws Exception {
        setBlankImage();
    }

    public void onAddClick(ActionEvent actionEvent) {
        Core.userInfo.getThisTheme().getTasks().add(new ThemeTask(taskText.getText(), result.getText(), imagePath));
        GApp.app.closeAllWindows();
        Core.teacherController.taskSelectorUpdate();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        GApp.app.closeAllWindows();
    }

    public void onSetImageClick(ActionEvent actionEvent) throws Exception {
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Добавить изображение");
//        dialog.setHeaderText(null);
//        dialog.setContentText("Поместите изображение в папку assets, которая находится в директории программы,\nукажите имя файла изображения (включая расширение)\n\n");
//
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()) {
//
//            Image image = ImageUtils.getAssetsImage(result.get());
//            imagePath = result.get();
//
//            if (!image.isError()) {
//                taskImage.setImage(image);
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Ошибка");
//                alert.setContentText("Данного файла не существует!");
//                alert.setHeaderText(null);
//                alert.showAndWait();
//            }
//        }

        GApp.app.addModalWindow(FXML_FILES.IMAGES.getUrl(), "Выбрерите изображение", GApp.app.getStage(), false);
    }

    public void onDeleteImageClick(ActionEvent actionEvent) {
        setBlankImage();
    }

    public void setBlankImage() {
        taskImage.setImage(new Image("/ru/catstack/sc_project/resources/images/blank.png"));
        imagePath = null;
    }

    public void onParagraphClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "§");
        setFocus();
    }

    public void onDegreesClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "°");
        setFocus();
    }

    public void onAngleClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "∠");
        setFocus();
    }

    public void onNumberRootClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "√");
        setFocus();
    }

    public void onPwr2Click(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "²");
        setFocus();
    }

    public void onPwr3Click(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "³");
        setFocus();
    }

    public void onLOEClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "≤");
        setFocus();
    }

    public void onMOEClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "≥");
        setFocus();
    }

    public void onSomeEqualClick(ActionEvent actionEvent) {
        taskText.insertText(taskText.getCaretPosition(), "≈");
        setFocus();
    }

    public void setFocus() {
        taskText.requestFocus();
    }

    @Override
    public void update() {
        if (Core.userInfo.getThisImage() != null) {
            imagePath = Core.userInfo.getThisImage();
            taskImage.setImage(ImageUtils.getAssetsImage(Core.userInfo.getThisImage()));
        }
    }
}
