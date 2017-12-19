package tadeas.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tadeas.dto.UserDTO;
import tadeas.data.RoleType;
import tadeas.util.MD5Util;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Configurable
public class ApiAuthenticationManager implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(ApiAuthenticationManager.class);

    @Value("${backend.url}")
    private String url;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        RestTemplate restTemplate = new RestTemplate();
        String hash = null;
        try {
            hash = MD5Util.generateMD5Hash(password);
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        String loginUrl = null;
        try {
            loginUrl = url + "user/login/?username=" + URLEncoder.encode(username, String.valueOf(StandardCharsets.UTF_8)) + "&password=" + hash;
        } catch (UnsupportedEncodingException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        UserDTO loginResponse = null;
        try {
            loginResponse = restTemplate.getForObject(loginUrl, UserDTO.class);

            if (loginResponse == null) {
                throw new AuthenticationServiceException("Response from Backend API is null.");
            }

            String role = loginResponse.getRole();
            RoleType acceptedRole = RoleType.valueOf("ROLE_" + role.toUpperCase());
            log.info("Setting role: {}", acceptedRole.name());
            grantedAuths.add(new SimpleGrantedAuthority(acceptedRole.name()));

        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new BadCredentialsException("1000");
            }
            throw new AuthenticationServiceException("Api Error");
        } catch (RestClientException e) {
            throw new AuthenticationServiceException("Api Error");
        }

        return new UsernamePasswordAuthenticationToken(loginResponse, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
