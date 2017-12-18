package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    MessageSource messageSource;

    @Value("${backend.url}")
    private String url;

    @RequestMapping(value = {"", "/", "index"})
    public String index(String name, Model model) {
        return "index";
    }

    @RequestMapping("/greeting")
    public String greeting(String name, Model model) {
        String greeting = messageSource.getMessage("greeting", null, LocaleContextHolder.getLocale());
        log.trace("Message translation: {}", greeting);
        model.addAttribute("name", name);
        return "greeting";
    }



    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
//    public String loginSend(@Valid @ModelAttribute("form") Login login, final BindingResult bindingResult, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        String hash = Md5.md5(login.getPassword());
//        log.info(hash);
//        String loginUrl = url + "users/login/?username=" + URLEncoder.encode(login.getLogin(), String.valueOf(StandardCharsets.UTF_8)) + "&password=" + hash;
//        log.info(loginUrl);
//        try {
//            UserEndopoint loginResponse = restTemplate.getForObject(loginUrl, UserEndopoint.class);
//            log.info(loginResponse.toString());
//        } catch (HttpStatusCodeException e) {
//            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
//                model.addAttribute("badCredentials", true);
//                return "login";
//            }
//            model.addAttribute("apiError", true);
//            return "login";
//        }
//        model.addAttribute("login", login.getLogin());
//        model.addAttribute("password", login.getPassword());
//        return "loged";
//    }
}
