package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        // model : 이 객체를 통해 뷰에 특정 데이터를 넘 길 수 있다.
        model.addAttribute("data", "hello!!!");
        return "hello";
        // return 화면 이름
        // 관례 - resources의 templates에 리턴받은 이름을 가진 view를 반환 해 준다. .html이 자동으로 붙는다.
    }

}
