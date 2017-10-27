package ru.catstack.sc_project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.sc_project.objects.StudentTask;
import ru.catstack.sc_project.objects.help_objects.Time;
import ru.catstack.sc_project.objects.help_objects.Timer;
import ru.catstack.sc_project.objects.user.Student;
import ru.catstack.sc_project.objects.user.Tasks;
import ru.catstack.sc_project.resources.Core;
import ru.catstack.sc_project.resources.FXML_FILES;
import ru.catstack.sc_project.utils.ImageUtils;

import java.util.Optional;

public class StudentController extends GController{

    @FXML
    public Label latestTime;
    public Label thisTask;
    public Label taskText;
    public TextField resultField;
    public Label lastResult;
    public ImageView taskImage;


    private boolean trigger = false;

    private Time time = new Time(Core.userInfo.getThisTheme().getTime());
    private Timer timer;

    @Override
    public void onShow() throws Exception {

        Core.tasks = new Tasks(Core.userInfo.getThisTheme());

        Core.student = new Student();

        onTaskChanged();
        latestTime.setText("Осталось времени: " + time.getFormatedTime());
        timer = new Timer(1, () -> {
            if(time != null) {
                time.subtract(1);
                latestTime.setText("Осталось времени: " + time.getFormatedTime());
                long seconds = time.getSeconds();
                if (seconds < 0) {
                    try {
                        exit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (seconds < 60 && seconds >= 30)
                    latestTime.setTextFill(new Color(255 / 255f, 150 / 255f, 0, 1));
                else if (time.getSeconds() < 30)
                    latestTime.setTextFill(new Color(255 / 255f, 0, 0, 1));
            }
        });
        timer.run();

        resultField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    onSendClick(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onEndClick(ActionEvent actionEvent) throws Exception {
        exit();
    }

    public void onSendClick(ActionEvent actionEvent) throws Exception {
        Core.tasks.getTasks().get(Core.student.thisTaskNumber-1).setLastResult(resultField.getText());

        if(Core.tasks.getTasks().get(Core.student.thisTaskNumber-1).getTask().getResult().equalsIgnoreCase(resultField.getText()))
            Core.tasks.getTasks().get(Core.student.thisTaskNumber-1).setCorrectly(true);
        else
            Core.tasks.getTasks().get(Core.student.thisTaskNumber-1).setCorrectly(false);

        if(Core.student.thisTaskNumber < Core.userInfo.getThisTheme().getqCount())
            Core.student.thisTaskNumber++;
        onTaskChanged();
    }

    public void onPreviousClick(ActionEvent actionEvent) throws Exception {
        if(Core.student.thisTaskNumber > 1)
            Core.student.thisTaskNumber--;
        onTaskChanged();
    }

    public void onNextClick(ActionEvent actionEvent) throws Exception {
        if(Core.student.thisTaskNumber < Core.userInfo.getThisTheme().getqCount())
            Core.student.thisTaskNumber++;
        onTaskChanged();
    }

    private void exit() throws Exception {
        GApp.app.setScene(FXML_FILES.RESULTS.getUrl());
        time = null;
        if(timer != null)
        timer.stop();
    }

    private void onTaskChanged() throws Exception {
        StudentTask thisStudentTask = Core.tasks.getTasks().get(Core.student.thisTaskNumber-1);
        thisTask.setText("" + Core.student.thisTaskNumber + "/" + Core.userInfo.getThisTheme().getqCount());
        lastResult.setText("Ваш ответ: " + thisStudentTask.getLastResult());
        taskText.setText(thisStudentTask.getTask().getText());
        resultField.setText("");
        if (thisStudentTask.getTask().getImageFile() != null) {
            Image thisTaskImage = ImageUtils.getAssetsImage(thisStudentTask.getTask().getImageFile());
            taskImage.setImage(thisTaskImage);
        } else {
            taskImage.setImage(null);
        }
        boolean all = false;
        for (StudentTask studentTask : Core.tasks.getTasks()) {
            if(studentTask.getLastResult().equals("не решено"))
                all = true;
        }
        if(!all && !trigger) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы ответили на все вопросы");
            alert.setContentText("Закончить работу?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                exit();
            } else {
                trigger = true;
            }
        }
    }
}
