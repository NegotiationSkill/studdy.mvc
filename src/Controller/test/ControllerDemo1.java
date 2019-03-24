package Controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 刘棋军
 * @date2019-03-11
 */
@Controller
@RequestMapping(value = "/mvc",method = RequestMethod.POST,params = {"user"})
public class ControllerDemo1 {
    @RequestMapping("/hello/*/test")
    public String forst(){
        return "hello";
    }
    @RequestMapping("/hello/?/test")
    public String forst1(){
        return "hello";
    }
    @RequestMapping("/hello/**/test/{name}/{number}")
    public String forst2(@RequestParam("user") String user,@RequestParam(value = "age",required = false) int age){
        System.out.println("hrllo");
       // System.out.println(name+"--"+number);
        return "hello";
    }
}
