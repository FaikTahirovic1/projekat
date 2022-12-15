package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Driver implements Idable {
    private String name;
    private Team team;
    private Track favouriteTrack;
    private int id;

    public Driver(String name, Team team, Track favouriteTrack, int id) {
        this.name = name;
        this.team = team;
        this.favouriteTrack = favouriteTrack;
        this.id = id;
    }

    public String getName() {
        return name;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id && Objects.equals(name, driver.name) && Objects.equals(team, driver.team) && Objects.equals(favouriteTrack, driver.favouriteTrack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, favouriteTrack, id);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", team=" + team +
                ", favouriteTrack=" + favouriteTrack +
                ", id=" + id +
                '}';
    }
}
