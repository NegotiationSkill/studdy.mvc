package Controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘棋军
 * @date2019-03-11
 */

@RestController
@RequestMapping("/hanler")
public class RequestMethod {
    @RequestMapping("/testpost/{id}")
    public  String testpost(@PathVariable("id") int id){
        System.out.println("增" + id);
        return "hello";
    }
    @RequestMapping(value = "/testput/{id}",method = org.springframework.web.bind.annotation.RequestMethod.PUT)
    public  String testput(@PathVariable("id") int id){
        System.out.println("改" + id);
        return "hello";
    }
    @ResponseBody
    @RequestMapping("/testDelete/{id}")
    public  String testDelete(@PathVariable("id") int id){
        System.out.println("删" + id);
        return "hello";
    }
    @RequestMapping("/testget/{id}")
    public  String testget(@PathVariable("id") int id){
        System.out.println("查" + id);
        return "hello";
    }
}
