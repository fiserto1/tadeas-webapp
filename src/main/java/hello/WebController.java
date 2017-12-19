package hello;

import hello.bussiness.models.RoleType;
import hello.bussiness.models.SessionKeyI;
import hello.bussiness.models.TaskList;
import hello.bussiness.models.TaskWindowI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    TaskList tasksList;


    @Value("${backend.url}")
    private String url;

    @Autowired
    SessionKeyI sesion;

    @RequestMapping(value = {"", "/", "index"})
    public String index(String name, Model model, HttpServletRequest request) {
        log.info(sesion.getToken());
        List<TaskWindowI> tasks = tasksList.getWindows();
//        UserEndpoint user = (UserEndpoint) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        RestTemplate restTemplate = beanFactory.getBean(RestTemplate.class, user.getSessionKey());
//        String windowsUrl = url + "windows";
//        ResponseEntity<DeliveryWindowEndpoint[]> responseEntity = restTemplate.getForEntity(windowsUrl, DeliveryWindowEndpoint[].class);
//        DeliveryWindowEndpoint[] windows = responseEntity.getBody();
//        String deliveryUrl = url + "delivery";
//        ResponseEntity<DeliveryEndpoint[]> responseDelivery = restTemplate.getForEntity(deliveryUrl, DeliveryEndpoint[].class);
//        DeliveryEndpoint[] delivery = responseDelivery.getBody();
//        for(DeliveryWindowEndpoint win: windows){
//            log.info(win.toString());
//        }
//        for (DeliveryEndpoint deli: delivery){
//            log.info(deli.toString());
//        }
//        log.info(user.toString());
        for (TaskWindowI task: tasks){
            log.info(task.getLastDelivery().isValid().toString());
        }
        model.addAttribute("tasks", tasks);
        if (request.isUserInRole(RoleType.STUDENT.name())) {
            return "index-student";
        }
        else {
            return "index-teacher";
        }
//        auth.getAuthorities().contains("ROLE")

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


    @RequestMapping("/logout")
    public String logout() {
        return "redirect:login";
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
