package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.service.impl.TravelServiceImpl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bg.tuvarna.traveltickets.entity.TravelStatus.Enum.INCOMING;
import static bg.tuvarna.traveltickets.entity.base.BaseAuditAbstractEntity.BaseClientAuditEntity;

@Entity
@Table(name = "travels")
public class Travel extends BaseClientAuditEntity<Company> {

    @Serial
    private static final long serialVersionUID = 6063243358784196914L;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_type_id", nullable = false)
    private TravelType travelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_status_id", nullable = false)
    private TravelStatus travelStatus;

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDate;

    @Column(name = "ticket_quantity", nullable = false)
    private Integer ticketQuantity;

    @Column(name = "current_ticket_quantity", nullable = false)
    private Integer currentTicketQuantity;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @Column(name = "ticket_buy_limit")
    private Integer ticketBuyLimit;

    private String details;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<TravelRoute> travelRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<TravelDistributorRequest> distributorRequests = new ArrayList<>();

    public Travel() {
    }

    public Travel(Long id) {
        super.id = id;
    }

    public List<TravelDistributorRequest> getDistributorRequests() {
        return distributorRequests;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDistributorRequests(final List<TravelDistributorRequest> distributorRequests) {
        this.distributorRequests = distributorRequests;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }

    public TravelStatus getTravelStatus() {
        return travelStatus;
    }

    public void setTravelStatus(TravelStatus travelStatus) {
        this.travelStatus = travelStatus;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(Integer ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Integer getCurrentTicketQuantity() {
        return currentTicketQuantity;
    }

    public void setCurrentTicketQuantity(Integer currentTicketQuantity) {
        this.currentTicketQuantity = currentTicketQuantity;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketBuyLimit() {
        return ticketBuyLimit;
    }

    public void setTicketBuyLimit(Integer ticketBuyLimit) {
        this.ticketBuyLimit = ticketBuyLimit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<TravelRoute> getTravelRoutes() {
        return travelRoutes;
    }

    public void setTravelRoutes(List<TravelRoute> travelRoutes) {
        this.travelRoutes = travelRoutes;
    }

    @PrePersist
    public void prePersist() {
        if (travelStatus == null) travelStatus = TravelServiceImpl.getInstance().findStatusByName(INCOMING);
    }

    @PostLoad
    public void postLoad() {
        travelType = TravelServiceImpl.getInstance().findTypeById(travelType.getId());
        travelStatus = TravelServiceImpl.getInstance().findStatusById(travelStatus.getId());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Travel travel = (Travel) o;
        return Objects.equals(name, travel.name) &&
                Objects.equals(travelType, travel.travelType) &&
                Objects.equals(travelStatus, travel.travelStatus) &&
                Objects.equals(startDate, travel.startDate) &&
                Objects.equals(endDate, travel.endDate) &&
                Objects.equals(ticketQuantity, travel.ticketQuantity) &&
                Objects.equals(currentTicketQuantity, travel.currentTicketQuantity) &&
                Objects.equals(ticketPrice, travel.ticketPrice) &&
                Objects.equals(ticketBuyLimit, travel.ticketBuyLimit) &&
                Objects.equals(details, travel.details) &&
                Objects.equals(travelRoutes, travel.travelRoutes) &&
                Objects.equals(distributorRequests, travel.distributorRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, travelType, travelStatus, startDate, endDate, ticketQuantity, currentTicketQuantity, ticketPrice, ticketBuyLimit, details, travelRoutes, distributorRequests);
    }

}
