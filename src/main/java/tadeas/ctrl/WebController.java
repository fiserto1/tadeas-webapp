package tadeas.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
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

    @Value("${backend.url}")
    private String url;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TaskWindowServiceI taskWindowService;

    @Autowired
    private SessionKeyI sessionKey;

    @RequestMapping(value = {"", "/", "index"})
    public String index(String name, Model model, HttpServletRequest request) {
        log.info(sessionKey.getToken());
        List<TaskWindowI> taskWindows = taskWindowService.getWindows();
//        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (TaskWindowI task : taskWindows) {
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
    }

    @RequestMapping(value = {"/confirmTask"}, params = {"id"})
    public String confirmDelivery(String name, Model model, HttpServletRequest request) {
        if (!request.isUserInRole(RoleType.ROLE_STUDENT.name())) {
            //not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_STUDENT);
            return index(name, model, request);
        }

        int deliveryId = Integer.parseInt(request.getParameter("id"));

        boolean result = taskWindowService.confirmDelivery(deliveryId);

        if (result) {
            model.addAttribute("alertOk", true);
        } else {
            model.addAttribute("alertError", true);
            log.warn("Failed to confirm delivery.");
        }

        log.info("Task confirmed: TaskId: {}.", deliveryId);
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
    public String evaluateTaskResult(@Valid @ModelAttribute("form") Evaluation evaluation, final BindingResult bindingResult, Model data) {
        if (bindingResult.hasErrors()) {
            return "evaluation";
        }

        return "redirect:index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/logout")
    public String logout() {
        return "redirect:login";
    }
}
