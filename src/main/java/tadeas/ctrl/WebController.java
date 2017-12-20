package tadeas.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tadeas.data.RoleType;
import tadeas.data.SessionKeyI;
import tadeas.data.TaskWindowI;
import tadeas.dto.TaskDTO;
import tadeas.form.EvaluationForm;
import tadeas.service.DeliveryService;
import tadeas.service.TaskService;
import tadeas.service.TaskWindowService;

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
    private TaskWindowService taskWindowService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SessionKeyI sessionKey;

    @RequestMapping(value = {"", "/", "index"})
    public String index(String name, Model model, HttpServletRequest request) {
        log.info(sessionKey.getToken());
        List<TaskWindowI> taskWindows = taskWindowService.getTaskWindows();
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

        boolean result = deliveryService.confirmDelivery(deliveryId);

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
    public String evaluateTaskScreen(EvaluationForm evaluation, Model model, HttpServletRequest request) {
        if (!request.isUserInRole(RoleType.ROLE_TEACHER.name())) {
            //not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_TEACHER);
            return "redirect:/index";
        }

        int taskId = Integer.parseInt(request.getParameter("id"));

        model.addAttribute("taskId", taskId);
        model.addAttribute("form", new EvaluationForm());

        return "evaluation";
    }

    @PostMapping(value = {"/evaluateDelivery"}, params = {"id"})
    public String evaluateTaskResult(@Valid @ModelAttribute("form") EvaluationForm evaluation,
                                     final BindingResult bindingResult, Model model,  HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "evaluation";
        }
        if (!request.isUserInRole(RoleType.ROLE_TEACHER.name())) {
//            Not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_TEACHER);
            return "redirect:/index";
        }

        int deliveryId = Integer.parseInt(request.getParameter("id"));

        boolean result = deliveryService.evaluateDelivery(deliveryId, evaluation);

        if (result) {
            model.addAttribute("alertOk", true);
        } else {
            model.addAttribute("alertError", true);
            log.warn("Failed to evaluate delivery.");
        }

        return index(null, model, request);
    }

    @RequestMapping(value = "/uploadFile", params = "id")
    public String uploadScreen(Model model, HttpServletRequest request) {
        if (!request.isUserInRole(RoleType.ROLE_STUDENT.name())) {
            //not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_STUDENT);
            return "redirect:/index";
        }

        int taskId = Integer.parseInt(request.getParameter("id"));
        TaskDTO task = taskService.getTask(taskId);

        model.addAttribute("task", task);

        return "upload";
    }

    @PostMapping(value = "/uploadFile", params = "id")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
        if (!request.isUserInRole(RoleType.ROLE_STUDENT.name())) {
//            Not authorized
            log.error("User does not have role: {}.", RoleType.ROLE_STUDENT);
            return "redirect:/index";
        }

        int taskId = Integer.parseInt(request.getParameter("id"));

        log.info("File uploaded: {}. Assigned to task: {}", file.getOriginalFilename(), taskId);

        model.addAttribute("alertOk", true);

        return uploadScreen(model, request);
    }

    @RequestMapping(value = "/downloadDelivery", params = "id")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(Model model, HttpServletRequest request) {

        int deliveryId = Integer.parseInt(request.getParameter("id"));

        Resource file = deliveryService.getDeliveryResource(deliveryId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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
