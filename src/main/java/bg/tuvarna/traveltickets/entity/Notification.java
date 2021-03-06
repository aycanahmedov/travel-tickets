package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.service.impl.NotificationServiceImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import java.io.Serial;
import java.util.Objects;

import static bg.tuvarna.traveltickets.entity.base.BaseAuditAbstractEntity.BaseUserAuditEntity;

@Entity
@Table(name = "notifications")
public class Notification extends BaseUserAuditEntity {

    @Serial
    private static final long serialVersionUID = 1527812768320401028L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false, updatable = false)
    private String message;

    public Notification() {
    }

    public Notification(final String message, final NotificationType notificationType) {
        this.message = message;
        this.notificationType = notificationType;
    }

    public Notification(final Long id, final String message, final NotificationType notificationType) {
        this(message, notificationType);
        this.id = id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getMessage() {
        return message;
    }

    @PostLoad
    public void postLoad() {
        notificationType = NotificationServiceImpl.getInstance().findTypeById(notificationType.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Notification that = (Notification) o;
        return Objects.equals(notificationType, that.notificationType) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), notificationType, message);
    }

}
