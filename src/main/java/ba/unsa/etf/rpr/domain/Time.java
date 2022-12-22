package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Time {
    private int minutes;
    private int seconds;
    private int thousands;


    public Time(int minutes, int seconds, int thousands) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.thousands = thousands;
    }
    public Time(int a){
        this.thousands = a%1000;
        a = a/1000;
        this.seconds = a%60;
        a = a/60;
        this.minutes = a;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getThousands() {
        return thousands;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setThousands(int thousends) {
        this.thousands = thousands;
    }
    public int timeToInt(){
        return this.thousands + 1000 * this.seconds + 60 * 1000 * this.minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return minutes == time.minutes && seconds == time.seconds && thousands == time.thousands;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, seconds, thousands);
    }

    @Override
    public String toString() {
        return "Time{" +
                "minutes=" + minutes +
                ", seconds=" + seconds +
                ", thousands=" + thousands +
                '}';
    }
}
