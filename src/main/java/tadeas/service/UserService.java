package tadeas.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tadeas.dto.UserDTO;

@Lazy
@Service
public interface UserService {
    UserDTO getUser(Integer userId);
}
