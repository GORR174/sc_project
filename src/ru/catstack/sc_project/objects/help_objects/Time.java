package ru.catstack.sc_project.objects.help_objects;

public class Time {

    private long seconds = 0;

    public Time(long minutes) {
        this.seconds = minutes*60;
    }

    public void subtract(int number){
        seconds-=number;
    }

    public void add(int number){
        seconds+=number;
    }

    public String getFormatedTime(){

        String hours = (seconds/3600) < 10 ? "0"+(seconds/3600) : "" + (seconds/3600);
        String minutes = (seconds/60-seconds/3600*60) < 10 ? "0"+(seconds/60-seconds/3600*60) :
                "" + (seconds/60-seconds/3600*60);
        String rSeconds = (seconds-(seconds/3600*3600+Integer.valueOf(minutes)*60)) < 10 ?
                "0"+(seconds-(seconds/3600*3600+Integer.valueOf(minutes)*60)) : "" + (seconds-(seconds/3600*3600+Integer.valueOf(minutes)*60));

        return hours + ":" + minutes + ":" + rSeconds;
    }

    public long getSeconds() {
        return seconds;
    }
}
