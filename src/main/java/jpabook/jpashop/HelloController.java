package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {  //Model: Controller에서 뷰로 데이터를 옮겨줄 때 사용
        model.addAttribute("data", "hello!!!");
        return "hello";   //hello 뒤에 자동으로 .html이 붙음 (관례)
    }
}
