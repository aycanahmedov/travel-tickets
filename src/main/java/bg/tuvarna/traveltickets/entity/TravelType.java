package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.entity.base.BaseEntity;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Objects;

@Immutable
@Entity
@Table(name = "travel_types")
public class TravelType extends BaseEntity {

    private static final long serialVersionUID = 1565334927400421244L;

    public enum Enum {EDUCATIONAL, ADVENTURE, BUSINESS, MEDICAL}

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TravelType.Enum name;

    public TravelType() {
    }

    public TravelType(Enum name) {
        this.name = name;
    }

    public TravelType(long id,Enum name) {
        this(name);
        super.id = id;
    }

    public Enum getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TravelType travelType = (TravelType) o;
        return Objects.equals(name, travelType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
