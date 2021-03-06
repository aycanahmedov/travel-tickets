package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.service.impl.TravelServiceImpl;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "travels_routes")
public class TravelRoute implements Serializable {

    @Serial
    private static final long serialVersionUID = 4885712622963495508L;

    @EmbeddedId
    private TravelCityID travelCityID;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("travelID")
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cityID")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_type_id", nullable = false)
    private TransportType transportType;

    @Column(name = "arrival_date", nullable = false)
    private OffsetDateTime arrivalDate;

    public TravelRoute() {
    }

    public TravelRoute(Travel travel, City city) {
        this.travel = travel;
        this.city = city;
        this.travelCityID = new TravelCityID(travel.getId(), city.getId());
    }

    public TravelCityID getTravelCityID() {
        return travelCityID;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public OffsetDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(OffsetDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @PostLoad
    public void postLoad() {
        transportType = TravelServiceImpl.getInstance().findTransportTypeById(transportType.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelRoute that = (TravelRoute) o;
        return Objects.equals(travelCityID, that.travelCityID) &&
                Objects.equals(travel, that.travel) &&
                Objects.equals(city, that.city) &&
                Objects.equals(transportType, that.transportType) &&
                Objects.equals(arrivalDate, that.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelCityID, travel, city, transportType, arrivalDate);
    }
}
