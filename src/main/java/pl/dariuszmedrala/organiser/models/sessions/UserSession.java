package pl.dariuszmedrala.organiser.models.sessions;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.dariuszmedrala.organiser.models.entities.UserEntity;

@Component
@Data
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    private boolean isLoggedIn;
    private UserEntity userEntity;
}
