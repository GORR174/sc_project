package ru.catstack.sc_project.objects;

public class ThemeTask {

    private String text = "";
    private String result = "";
    private String imageFile = "";

    private boolean isTestTask = false;

    private String answer2 = "";
    private String answer3 = "";
    private String answer4 = "";

    public ThemeTask(){
        this("Задача", "0", null);
    }

    public ThemeTask(String text, String result, String imageFile) {
        this.text = text;
        this.result = result;
        this.imageFile = imageFile;
    }

    public boolean isTestTask() {
        return isTestTask;
    }

    public void setTestTask(boolean testTask) {
        isTestTask = testTask;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
