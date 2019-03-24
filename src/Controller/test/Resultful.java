package Controller.test;

import Controller.Enitry.Student;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author 刘棋军
 * @date2019-03-11
 */
//@SessionAttributes(value = "studnet4,student2")
@SessionAttributes(types=Student.class)
@Controller
public class Resultful {
    @RequestMapping(value = "/testPut",method = RequestMethod.PUT)                //测试PUT请求
    public  String demo1(){
        //假设这里是dao的update
        return "hello";
    }
    @RequestMapping(value = "/testDelete",method = RequestMethod.DELETE)          //测试DELETE请求
    public String demo2(){
        System.out.println(123);
        //假设这里是dao的delete
        return "hello";
    }
    @RequestMapping(value = "/testParam",method = RequestMethod.GET)          //获取text中的值
    public String demo3(@RequestParam("userName") String name){
        System.out.println(123);
        System.out.println(name);
        return "hello";
    }

    @RequestMapping(value = "/testParam1",method = RequestMethod.GET)          //非必须参数的接受
    public String demo4(@RequestParam("userName") String name,@RequestParam(value = "age",required = false) int age){
        System.out.println(123);
        System.out.println(name+age);

        return "hello";
    }
    @RequestMapping(value = "/testHeader",method = RequestMethod.GET)          //接受请求头的信息和cookie的值
    public String demo5(@RequestHeader("Accept") String length,@CookieValue("JSESSIONID") String jss){
        System.out.println(length);
        System.out.println(jss);
        return "hello";
    }
    @RequestMapping(value = "/objectTest",method = RequestMethod.GET)          //接受引用类
    public String demo6(Student student){
        System.out.println(student==null);
        System.out.println(student.getName());
        return "hello";
    }
    @RequestMapping(value = "/servletApiTest",method = RequestMethod.GET)          //mvc中也可以使用servlet中的请求
    public String demo7(HttpServletRequest request){
        System.out.println(request.getCookies());             //就像servlet中的一样使用即可
        return "hello";
    }

    /**
     * 处理器末次那个数据以及ModelAndView
     * 如果handler执行完之后，要给DispatchServlet返回的不止是一个jsp页面还有的数据的话，有哪些传输数据的方式，以及怎么传
     * <1>将数据存在域对象Request中：ModelAndView    ModelMap   Map model   都是将数据自动放在了request中，在最终的view页面就可获取到
     *      注：ModelAndView 导包时注意别导错了
     * <2>将数据存到session域中： @seesionAttributes()  (注：将此注解加到类的前面)
     *       示例：@SessionAttributes(value = "studnet4,student2")  这里是将放在request中的studnet4,student2两个对象也放在session中
     *            @SessionAttributes(type=Student.class)    将所有Student的对象放在seesion中
     *
     *       @ModelAttribute修饰的方法，总是在目标方法之前执行，每次请求时先执行@ModelAttribute修饰的方法，在执行handler.常用于数据的更新，还有初始化
     *
     *        @ModelAttribute修饰的参数:目的是将@ModelAttribute修饰的方法的数据传给被修饰的参数示例如下
     *
     *       @ModelAttribute
     *     public void demo12(Map<String,String> map){
     *         System.out.println("hello");
     *         map.put("string","attribute");
     *
     *     }
     *     @RequestMapping(value = "/ModelAttributeTest",method = RequestMethod.GET)
     *     public ModelAndView demo13(@ModelAttribute("string") String  str){
     *         ModelAndView v = new ModelAndView("hello");
     *         System.out.println(str);
     *         return v;
     *     }
     *
     *
     *
     */
    @RequestMapping(value = "/modelAndViewTest",method = RequestMethod.GET)          //mvc中也可以使用servlet中的请求
    public ModelAndView demo8(){
        ModelAndView v = new ModelAndView("hello");     //和返回一个"success"是等价的，也会通过解析器的处理，最终跳转到success.jsp页面
       Student student = new Student();
        System.out.println(student);
        student.setName("zhangsan");
        student.setAge(15);
        v.addObject("student",student); //添加数据 将数据存到了request域对象中 等价于request.setAttribute();
        return v;
    }
    //  Map传数据
    @RequestMapping(value = "/mapTest",method = RequestMethod.GET)
    public ModelAndView demo9(Map<String,Student> map){
        ModelAndView v = new ModelAndView("hello");
        Student student = new Student();
        System.out.println(student);
        student.setName("student1");
        student.setAge(15);
        map.put("student1",student);
        return v;
    }
    //modelMap传数据
    @RequestMapping(value = "/modelMapTest",method = RequestMethod.GET)
    public ModelAndView demo10(ModelMap modelMap){
        ModelAndView v = new ModelAndView("hello");
        Student student = new Student();
        System.out.println(student);
        student.setName("student2");
        student.setAge(15);
        modelMap.put("student2",student);
        return v;
    }
    @RequestMapping(value = "/modelTest",method = RequestMethod.GET)
    public ModelAndView demo11(Model model){
        ModelAndView v = new ModelAndView("hello");
        Student student = new Student();

        student.setName("student4");
        student.setAge(15);
        model.addAttribute("studnet4",student);
        return v;
    }
    @ModelAttribute
    public void demo12(Map<String,String> map){
        System.out.println("This is attribute");
        map.put("string","attribute");  //将值传送给下一个方法

    }
    @RequestMapping(value = "/ModelAttributeTest",method = RequestMethod.GET)
    public ModelAndView demo13(@ModelAttribute("string") String  str){
        ModelAndView v = new ModelAndView("hello");
        System.out.println(str);
        return v;
    }
    @RequestMapping(value = "/TestRedircet",method = RequestMethod.GET)
    public String demo14(){
        System.out.println("得到请求");
        return "redirect:index.jsp";
    }

    //数据校验
    @RequestMapping(value = "/TestValidator",method = RequestMethod.GET)
    public String demo15(@Valid Student student){
        System.out.println("数据校验");
        System.out.println(student.getName()+student.birth);
        return "hello";
    }
    //文件传
    @RequestMapping(value = "/fileUpdown",method = RequestMethod.POST)
    public ModelAndView demo16(@RequestParam(value = "desc",required = false) String descence, @RequestParam(value = "file",required = false)MultipartFile multipartFile){
        ModelAndView v = new ModelAndView("hello");
        System.out.println("文件的上传下载");
        //System.out.println("文件描述："+desc);
        try {

            //1.将文件读进来

            InputStream inputStream = multipartFile.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(new File("D:\\abc.txt"));
            //2.将文件写到盘符
            byte[] buffer = new byte[1024];
            int len = -1;
            while(( len = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }

    //异常处理
    @RequestMapping(value = "/TestException",method = RequestMethod.GET)
    public String demo16(){
        int i =1/0;
        return "hello";
    }

    //异常处理器
  /*  @ExceptionHandler({ArithmeticException.class,ArrayIndexOutOfBoundsException.class})
    public ModelAndView testExceptionHandler(Exception e){
        ModelAndView v = new ModelAndView("error");
        v.addObject("err",e);
        return  v;
    }*/
}
