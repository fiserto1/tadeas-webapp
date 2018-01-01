package tadeas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tadeas.data.RoleType;
import tadeas.dto.UserDTO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Lazy
@Primary
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${backend.url}")
    private String url;

    @Override
    public UserDTO authenticate(String username, String hash) {
        String loginUrl;
        try {
            loginUrl = url + "user/login/?username=" + URLEncoder.encode(username, String.valueOf(StandardCharsets.UTF_8)) + "&password=" + hash;
        } catch (UnsupportedEncodingException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        return new RestTemplate().getForObject(loginUrl, UserDTO.class);
    }

    @Override
    public RoleType authorize(UserDTO user) {
        String role = user.getRole();
        return RoleType.valueOf("ROLE_" + role.toUpperCase());
    }
}
