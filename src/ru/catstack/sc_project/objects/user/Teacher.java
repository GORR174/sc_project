package ru.catstack.sc_project.objects.user;

import ru.catstack.sc_project.objects.Theme;

import java.util.ArrayList;

public class Teacher {

    private ArrayList<Theme> themes = new ArrayList<>();

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    public Theme getThemeByName(String name){
        Theme rTheme = null;
        for (Theme theme : themes) {
            if(theme.getName().equals(name))
                rTheme = theme;
        }
        return rTheme;
    }

}
