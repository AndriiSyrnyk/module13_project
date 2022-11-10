package Task1;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geo geo;

    public Address(String street, String suite, String city, String zipCode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipCode = zipCode;
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Task1.Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", geo=" + geo +
                '}';
    }
}
