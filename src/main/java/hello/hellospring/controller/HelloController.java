package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /*
        컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
        - 스프링 부트 템플릿엔진 기본 viewName 매핑
        - resources:templates/ +{ViewName}+ .html
     */

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // model 안에 data 넣기
        return "hello"; // 'resources:templates/hello.html' 찾기
    }

    // 'http://localhost:8080/hello-mvc?name=spring'로 접속
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 외부에서 parameter 받는다
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
        @ResponseBody 를 사용하면
        - HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)
        - 뷰 리졸버( viewResolver )를 사용하지 않고 HttpMessageConverter 가 동작
        - 기본 문자처리: StringHttpMessageConverter
        - 기본 객체처리: MappingJackson2HttpMessageConverter
        - byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
     */

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // @ResponseBody 를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨
    // 'http://localhost:8080/hello-api?name=spring'으로 접속
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }


}