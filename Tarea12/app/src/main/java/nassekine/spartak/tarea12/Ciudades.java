package nassekine.spartak.tarea12;

public class Ciudades {

    private int id;
    private String name;
    private String country;
    private double lon,lat;

    @Override
    public String toString() {
        return "Ciudades{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    public int getId() {
        return id;
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

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public Ciudades() {
    }

    public Ciudades(int id, String name, String country, double lon, double lat) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.lon = lon;
        this.lat = lat;
    }
}
