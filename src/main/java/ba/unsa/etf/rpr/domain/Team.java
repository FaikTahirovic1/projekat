package ba.unsa.etf.rpr.domain;

import java.util.Objects;
/**
 * Team is a JavaBean which represents an entity that exists in the Data Base
 */

public class Team implements Idable {

    private int id;
    private String name;
    private String country;


    public Team(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    public Team(){

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


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name) && Objects.equals(country, team.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return getName();
    }
}
