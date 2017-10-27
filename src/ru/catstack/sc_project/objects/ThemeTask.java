package ru.catstack.sc_project.objects;

public class ThemeTask {

    private String text = "";
    private String result = "";
    private String imageFile = "";

    public ThemeTask(){
        this("Задача", "0", null);
    }

    public ThemeTask(String text, String result, String imageFile) {
        this.text = text;
        this.result = result;
        this.imageFile = imageFile;
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
