package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.service.impl.ClientServiceImpl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = -9066096581032000088L;

    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    @MapsId("user_id")
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_type_id", nullable = false)
    private ClientType clientType;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    public Client() {
    }

    public Client(final User user) {
        userId = user.getId();
        this.user = user;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setUser(final User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    @PostLoad
    public void postLoad() {
        clientType = ClientServiceImpl.getInstance().findTypeById(clientType.getId());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Client client = (Client) o;
        return Objects.equals(userId, client.userId)
                && Objects.equals(user, client.user)
                && Objects.equals(clientType, client.clientType)
                && Objects.equals(name, client.name) && Objects.equals(phone, client.phone)
                && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, user, clientType, name, phone, address);
    }

}