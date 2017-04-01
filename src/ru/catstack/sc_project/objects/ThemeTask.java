package ru.catstack.sc_project.objects;

public class ThemeTask {

    private String text = "";
    private String result = "";

    public ThemeTask(){
        this("Задача", "0");
    }

    public ThemeTask(String text, String result) {
        this.text = text;
        this.result = result;
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
}
