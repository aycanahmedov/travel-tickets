package bg.tuvarna.traveltickets.entity;

import bg.tuvarna.traveltickets.service.impl.NotificationServiceImpl;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import static bg.tuvarna.traveltickets.entity.NotificationStatus.Enum.NOT_SEEN;

@Entity
@Table(name = "notifications_recipients")
public class NotificationRecipient implements Serializable {

    @Serial
    private static final long serialVersionUID = 268742443330560001L;

    @EmbeddedId
    private NotificationRecipientID notificationRecipientID;

    @MapsId("notificationID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notification notification;

    @MapsId("recipientID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_status_id", nullable = false)
    private NotificationStatus notificationStatus;

    public NotificationRecipient() {
    }

    public NotificationRecipient(Notification notification, User recipient) {
        this.notification = notification;
        this.recipient = recipient;
        notificationRecipientID = new NotificationRecipientID(notification.getId(), recipient.getId());
    }

    public NotificationRecipientID getNotificationRecipientID() {
        return notificationRecipientID;
    }

    public Notification getNotification() {
        return notification;
    }

    public User getRecipient() {
        return recipient;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @PrePersist
    public void prePersist() {
        if (notificationStatus == null)
            notificationStatus = NotificationServiceImpl.getInstance().findStatusByName(NOT_SEEN);
    }

    @PostLoad
    public void postLoad() {
        notificationStatus = NotificationServiceImpl.getInstance().findStatusById(notificationStatus.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationRecipient that = (NotificationRecipient) o;
        return Objects.equals(notificationRecipientID, that.notificationRecipientID) &&
                Objects.equals(notification, that.notification) &&
                Objects.equals(recipient, that.recipient) &&
                Objects.equals(notificationStatus, that.notificationStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationRecipientID, notification, recipient, notificationStatus);
    }

}

