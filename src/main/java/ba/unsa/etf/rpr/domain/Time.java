package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Time {
    private int minutes;
    private int seconds;
    private int thousands;


    public Time(int minutes, int seconds, int thousands, String name, int id) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.thousands = thousands;
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
