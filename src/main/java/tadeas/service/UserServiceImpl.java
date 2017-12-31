package tadeas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tadeas.dto.UserDTO;

@Lazy
@Primary
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${backend.url}")
    private String url;

    @Override
    public UserDTO getUser(Integer userId) {
        if (userId == null) {
            return null;
        }
        
        final String userUrl = url + "user/" + userId;

        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(userUrl, UserDTO.class);
        return responseEntity.getBody();
    }
}
