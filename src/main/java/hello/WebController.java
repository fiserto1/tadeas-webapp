package hello;

import hello.bussiness.forms.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    MessageSource messageSource;

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

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new Login());
        return "login";
    }
    @PostMapping("/login")
    public String loginSend(@Valid @ModelAttribute("form") Login login, final BindingResult bindingResult, Model model) {
//        model.addAttribute("loginForm", login);
        if(bindingResult.hasErrors()){
            log.trace(bindingResult.getFieldError("login").getDefaultMessage());
//            System.out.println(bindingResult.getFieldError("login").getDefaultMessage());
//            model.addAttribute("form", login);
            return "login";}
        model.addAttribute("login", login.getLogin());
        model.addAttribute("password", login.getPassword());
        return "loged";
    }
}
