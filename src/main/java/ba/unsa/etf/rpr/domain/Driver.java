package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Driver implements Idable, Comparable<Driver> {
    private String name;
    private Team team;
    private Track favouriteTrack;
    private int id;
    private int age;

    public Driver(String name, Team team, Track favouriteTrack, int id, int age) {
        this.name = name;
        this.team = team;
        this.favouriteTrack = favouriteTrack;
        this.id = id;
        this.age = age;
    }
    public Driver(){
        this.name ="";
        this.team= new Team();
        this.favouriteTrack=new Track();
        this.id = 1;
        this.age = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    public Track getFavouriteTrack() {
        return favouriteTrack;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setFavouriteTrack(Track favouriteTrack) {
        this.favouriteTrack = favouriteTrack;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id && age == driver.age && Objects.equals(name, driver.name) && Objects.equals(team, driver.team) && Objects.equals(favouriteTrack, driver.favouriteTrack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, favouriteTrack, id, age);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", team=" + team +
                ", favouriteTrack=" + favouriteTrack +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Driver d) {
        return this.getAge()-d.getAge();
    }
}
