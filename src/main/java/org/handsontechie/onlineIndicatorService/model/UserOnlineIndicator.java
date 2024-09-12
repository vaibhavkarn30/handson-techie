package org.handsontechie.onlineIndicatorService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class UserOnlineIndicator {

    @Id
    private int userId;
    private Boolean isOnline;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
