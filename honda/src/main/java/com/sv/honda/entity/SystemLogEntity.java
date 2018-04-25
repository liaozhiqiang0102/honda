package com.sv.honda.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "system_log", schema = "honda_om")
public class SystemLogEntity {
    private int logId;
    private String logMessage;
    private Timestamp logTimestamp;
    private String userId;
    private int activityType;
    private String activityModuleName;
    private String activityClass;

    @Id
    @Column(name = "log_id", nullable = false)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "log_message", nullable = false, length = 500)
    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    @Basic
    @Column(name = "log_timestamp", nullable = false)
    public Timestamp getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(Timestamp logTimestamp) {
        this.logTimestamp = logTimestamp;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 36)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "activity_type", nullable = false)
    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    @Basic
    @Column(name = "activity_module_name", nullable = false, length = 300)
    public String getActivityModuleName() {
        return activityModuleName;
    }

    public void setActivityModuleName(String activityModuleName) {
        this.activityModuleName = activityModuleName;
    }

    @Basic
    @Column(name = "activity_class", nullable = false, length = 300)
    public String getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(String activityClass) {
        this.activityClass = activityClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemLogEntity that = (SystemLogEntity) o;
        return logId == that.logId &&
                activityType == that.activityType &&
                Objects.equals(logMessage, that.logMessage) &&
                Objects.equals(logTimestamp, that.logTimestamp) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(activityModuleName, that.activityModuleName) &&
                Objects.equals(activityClass, that.activityClass);
    }

    @Override
    public int hashCode() {

        return Objects.hash(logId, logMessage, logTimestamp, userId, activityType, activityModuleName, activityClass);
    }
}
