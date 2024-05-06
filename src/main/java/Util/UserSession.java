package Util;

import java.time.LocalDateTime;

public class UserSession {

    private Long userId;
    private LocalDateTime loginTime;
    private LocalDateTime expirationTime;

    public UserSession(Long userId, LocalDateTime loginTime, LocalDateTime expirationTime) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.expirationTime = expirationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
