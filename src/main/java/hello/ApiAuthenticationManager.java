package hello;

import hello.bussiness.endpoints.UserEndpoint;
import hello.utills.Md5;
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

    @Value("${backend.url}")
    private String url;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        RestTemplate restTemplate = new RestTemplate();
        String hash = null;
        try {
            hash = Md5.md5(password);
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        String loginUrl = null;
        try {
            loginUrl = url + "users/login/?username=" + URLEncoder.encode(username, String.valueOf(StandardCharsets.UTF_8)) + "&password=" + hash;
        } catch (UnsupportedEncodingException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        //        just dummy hopfully we do not need this
//        List<String> userRights = new ArrayList<>();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        UserEndpoint loginResponse = null;
        try {
            loginResponse = restTemplate.getForObject(loginUrl, UserEndpoint.class);
            if (loginResponse.getRole().equals("TeacherTeacher")) {
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            } else {
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            }
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new BadCredentialsException("1000");
            }
            throw new AuthenticationServiceException("Api Error");
        }
        catch (RestClientException e) {
            throw new AuthenticationServiceException("Api Error");
        }

        return new UsernamePasswordAuthenticationToken(loginResponse, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
