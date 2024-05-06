package Services;

import Util.UserSession;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class SessionService {

    private Map<String, UserSession> activeSessions = new HashMap<>();
    private Map<String, String> csrfTokens = new HashMap<>();
    private Duration sessionDuration = Duration.ofMinutes(30); // Duración de la sesión: 30 minutos

    public String createSession(Long userId) {
        String sessionId = UUID.randomUUID().toString();
        String csrfToken = generateCSRFToken(sessionId);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plus(sessionDuration);

        UserSession session = new UserSession(userId, now, expirationTime);
        activeSessions.put(sessionId, session);

        return sessionId;
    }

    public boolean isValidSession(String sessionId) {
        UserSession session = activeSessions.get(sessionId);
        if (session == null) {
            return false;
        }
        return session.getExpirationTime().isAfter(LocalDateTime.now());
    }

    public String generateCSRFToken(String sessionId) {
        String csrfToken = UUID.randomUUID().toString();
        csrfTokens.put(sessionId, csrfToken);
        return csrfToken;
    }
}
