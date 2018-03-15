package ru.catstack.sc_project.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.catstack.sc_project.objects.user.Teacher;
import ru.catstack.sc_project.resources.Core;

import java.io.File;
import java.io.IOException;

public class TeacherFileLoader {
    public static void load() throws IOException {
        File teacherFile = new File("teacher.json");

        ObjectMapper mapper = new ObjectMapper();

        if(teacherFile.exists())
            Core.teacher = mapper.readValue(teacherFile, Teacher[].class);
        else {
            for(int i=1; i<12; i++){
                Core.teacher[i] = new Teacher();
            }
            mapper.writeValue(teacherFile, Core.teacher);
        }
    }
}
