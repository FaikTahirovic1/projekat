package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Team implements Ideable {

    private int id;
    private String name;
    private String country;
    private Driver driver1;
    private Driver driver2;

    public Team(int id, String name, String country, Driver driver1, Driver driver2) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.driver1 = driver1;
        this.driver2 = driver2;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Driver getDriver1() {
        return driver1;
    }

    public Driver getDriver2() {
        return driver2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDriver1(Driver driver1) {
        this.driver1 = driver1;
    }

    public void setDriver2(Driver driver2) {
        this.driver2 = driver2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name) && Objects.equals(country, team.country) && Objects.equals(driver1, team.driver1) && Objects.equals(driver2, team.driver2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, driver1, driver2);
    }

    @Override
    public String toString() {
        return "Tim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", driver1=" + driver1 +
                ", driver2=" + driver2 +
                '}';
    }
}
