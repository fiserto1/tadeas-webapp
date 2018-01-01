package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tadeas.data.RoleType;
import tadeas.dto.UserDTO;

@Lazy
@Service
public interface AuthService {
    UserDTO authenticate(String username, String hash);

    RoleType authorize(UserDTO user);
}
