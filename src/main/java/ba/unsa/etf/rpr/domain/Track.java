package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Track implements Idable {
    private String country;
    private Time bestTime;
    private String name;
    private int id;

    public Track(String country, Time bestTime, String name, int id) {
        this.country = country;
        this.bestTime = bestTime;
        this.name = name;
        this.id = id;
    }
    public Track(){
        this.id = 1;
        this.bestTime = new Time(0,0,0);
        this.name ="";
        this.country="";
    }

    public String getCountry() {
        return country;
    }

    public Time getBestTime() {
        return bestTime;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBestTime(Time bestTime) {
        this.bestTime = bestTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id && Objects.equals(country, track.country) && Objects.equals(bestTime, track.bestTime) && Objects.equals(name, track.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Track{" +
                "country='" + country + '\'' +
                ", bestTime=" + bestTime +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
