package tadeas.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tadeas.data.RoleType;
import tadeas.data.SessionKeyI;
import tadeas.data.TaskWindowI;
import tadeas.form.Evaluation;
import tadeas.service.TaskWindowServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private TaskWindowServiceI taskWindowService;


    @Value("${backend.url}")
    private String url;

    @Autowired
    SessionKeyI sesion;

    @RequestMapping(value = {"", "/", "index"})
    public String index(String name, Model model, HttpServletRequest request) {
        log.info(sesion.getToken());
        List<TaskWindowI> taskWindows = taskWindowService.getWindows();
//        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        RestTemplate restTemplate = beanFactory.getBean(RestTemplate.class, user.getSessionKey());
//        String windowsUrl = url + "windows";
//        ResponseEntity<DeliveryWindowDTO[]> responseEntity = restTemplate.getForEntity(windowsUrl, DeliveryWindowDTO[].class);
//        DeliveryWindowDTO[] windows = responseEntity.getBody();
//        String deliveryUrl = url + "delivery";
//        ResponseEntity<DeliveryDTO[]> responseDelivery = restTemplate.getForEntity(deliveryUrl, DeliveryDTO[].class);
//        DeliveryDTO[] delivery = responseDelivery.getBody();
//        for(DeliveryWindowDTO win: windows){
//            log.info(win.toString());
//        }
//        for (DeliveryDTO deli: delivery){
//            log.info(deli.toString());
//        }
//        log.info(user.toString());
        for (TaskWindowI task: taskWindows){
            log.info(task.getLastDelivery().isValid().toString());
        }
        model.addAttribute("taskWindows", taskWindows);

        if (request.isUserInRole(RoleType.ROLE_STUDENT.name())) {
            log.info("Showing task list for role: {}", RoleType.ROLE_STUDENT);
            return "index-student";
        } else {
            log.info("Showing task list for role: {}", RoleType.ROLE_TEACHER);
            return "index-teacher";
        }
//        auth.getAuthorities().contains("ROLE")

    }

    @RequestMapping(value = {"/confirmTask"}, params = {"id"})
    public String confirmTask(String name, Model model, HttpServletRequest request) {
        if (!request.isUserInRole(RoleType.ROLE_STUDENT.name())) {
            //not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_STUDENT);
            return index(name, model, request);
        }


        int taskId = Integer.parseInt(request.getParameter("id"));
        log.info("Task confirmed: TaskId: {}.", taskId);

        return index(name, model, request);
    }

    @GetMapping(value = {"/evaluateDelivery"}, params = {"id"})
    public String evaluateTask(Evaluation evaluation, Model model, HttpServletRequest request) {
        if (!request.isUserInRole(RoleType.ROLE_TEACHER.name())) {
            //not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_TEACHER);
            return "redirect:/index";
        }


        int deliveryId = Integer.parseInt(request.getParameter("id"));
        log.info("Delivery confirmed: DeliveryId: {}.", deliveryId);

        model.addAttribute("form", new Evaluation());

        return "evaluation";
    }

    @PostMapping(value = {"/evaluateDelivery"}, params = {"id"})
    public String evaluateTaskResult(@Valid @ModelAttribute("form") Evaluation evaluation, final BindingResult bindingResult, Model data){
        log.info("HERERERE");
        System.out.println("hey there");
        if (bindingResult.hasErrors()) {
            return "evaluation";
        }

        return "redirect:index";
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
//    public String loginSend(@Valid @ModelAttribute("form") Login login, final BindingResult bindingResult, Model data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//        RestTemplate restTemplate = new RestTemplate();
//        String hash = MD5Util.generateMD5Hash(login.getPassword());
//        log.info(hash);
//        String loginUrl = url + "users/login/?username=" + URLEncoder.encode(login.getLogin(), String.valueOf(StandardCharsets.UTF_8)) + "&password=" + hash;
//        log.info(loginUrl);
//        try {
//            UserEndopoint loginResponse = restTemplate.getForObject(loginUrl, UserEndopoint.class);
//            log.info(loginResponse.toString());
//        } catch (HttpStatusCodeException e) {
//            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
//                data.addAttribute("badCredentials", true);
//                return "login";
//            }
//            data.addAttribute("apiError", true);
//            return "login";
//        }
//        data.addAttribute("login", login.getLogin());
//        data.addAttribute("password", login.getPassword());
//        return "loged";
//    }
}
