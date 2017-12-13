package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

//    @Autowired
//    MessageSource messageSource;
//
//    @RequestMapping("/greeting")
//    public @ResponseBody String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
////        return messageSource.getMessage("greeting", null, LocaleContextHolder.getLocale());
//        return "index";
//    }

        @RequestMapping("/greeting")
        public String index(String name, Model model){
            model.addAttribute("name", name);
            return "index";
        }

}
