package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "addresses")

public class Address extends BaseEntity {

    private static final long serialVersionUID = -6396179517749137012L;

    @ManyToOne (optional = false)
    private City city;

    @Column (nullable = false)
    private String address;

    public Address() {
    }

    public Address(City city, String address) {
        this.city = city;
        this.address = address;
    }

    public Address(long id, City city, String address) {
        this(city, address);
        super.id = id;
    }

    public City getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(address,address.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, address);
    }
}
