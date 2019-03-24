springMvc: 一个核心三个组件
        核心：DispatchServlet
        三个组件：适配器   映射器  分析器

ant风格：
    通配符                  有效路径            注 
   helli/ ？/test      helli/ A/test        ?只可以代表一个字符
   helli/ */test       helli/ mv/test       *可以代表多个字符
   helli/ **/test      helli/ A/C/test      **可以代表多级目录
   
基于ant的传参：
    之前的传参：
        <a href="hello?name=zs"></a>
        Request.getParam("name");
    ant的传参：
    <form action="/mvc/hello/ad/c/test/liuqijun/4160902" method="post">
    @RequestMapping("/hello/**/test/{name}/{number}")
        public String forst2(@PathVariable("name")String name,@PathVariable("number") String number){
            System.out.println("hrllo");
            System.out.println(name+"--"+number);
            return "hello";
        }
        
result风格：
    请求方法：
    GET:查
    POST：增
    DELETE：删
    PUT：改
   注：一般的浏览器只支持post和get,要实现put和delet要使用过滤器
   原理：过滤器接受的到用户的请求，按规定将请求的方法改变
   《1》在web.xml中配置过滤器
       <filter>
           <filter-name>filter</filter-name>
           <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
       </filter>
       <filter-mapping>
           <filter-name>filter</filter-name>
           <url-pattern>/*</url-pattern>
          <!-- <servlet-name>dispatcher</servlet-name>-->
       </filter-mapping>
    《2》在jsp表中设置，加入隐藏域，注这里的方法必须是post方法 过滤器会通过 _method 的value 修改请求方法
        <form action="/hanler/testput/123" method="post">
              <input type="hidden" name="_method" value="PUT">
              <input type="submit" value="改">
        </form>
 
 传参：
    <form action="/mvc/hello/ad/c/test/liuqijun/4160902" method="post">
        <a href="/mvc/hello/a/c/test/liuqijun/4160902">succeses</a>
        <input type="text" name="user">
        
        
    @RequestMapping("/hello/**/test/{name}/{number}")
        public String forst2(@RequestParam("user") String user){
            System.out.println("hrllo");
            System.out.println(name+"--"+number);
            return "hello";
    }
    
    注：Controller要是要求接受n个参数，而前端只传来n-1个就会出现错误，要想避免错误将接受的参数的require属性设置为false
    public String forst2(@RequestParam("user") String user,@RequestParam(value = "age",required = false) int age)
    如上user是必须参数，而age是非必须参数。也可以直接给age默认值
    
 视图解析器，视图，视图国际化
 
 cilent--->DispatchServler---->handlerMapping----->DispatchServler----->handler(Controller)
 
 ------>viewResolver---->Cilent
 注：不管handler返回的是View/ModelAndView/String 最终都会被转化为ModelAndView然后交给视图解析器解析
    经过视图解析器的渲染之后可以交给客户端了
    
 常见的视图解析器：InternalResourceViewResolver  以及他的子类  Jstl
 重点学习jstl:jstl可以实现国际化视图。mvc视图解析器会默认使用InternalResourceViewResolver
              但当有jstl预言时，mvc会自动使用他的子类jstl解析
              
 国际化：即使不同的地区使用不同的语言
 国际化步骤：
        <1>编写资源文件  命名规则  基名_语言_地区   例如：base_zh_CN
                
        <2>在applicationContext.xml文件中配置，加载资源文件
            <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
                        <property name="basename" value="i18n"/>
            注：这里的解析器只有在响应后才起作用
            注：这里id必须是id="messageSource"
            </bean>
        <3>使用资源文件 
                添加jstl.jar    standar.jstl
                将jstl引入jsp中（就是最终生成的那个页面hello.jsp） <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                再将资源文件的key写到jsp页面中
                     <fmt:message key="resource.exit"></fmt:message>
                            <fmt:message key="resource.welecom"></fmt:message> 
                            
                            
                            
         
 MVC怎样从请求路径直接到目的页，不经过DispatcherServlet?
  <!--配置路径，让请求路径直接到目的页不经过Dispatcher
             path:请求路径
             view-name：目的页
             注：这个配置会让所有的请求忽略RequesstMapping(),直接去找目的路径，要是其和Mapping共存必须加一个注解
         -->
         <mvc:view-controller path="/direct" view-name="hello"></mvc:view-controller>
         <!--共存注解-->
         <mvc:annotation-driven></mvc:annotation-driven>
         
mvc默认跳转方式是请求转发，如何改成重定向？
    public String demo14(){
            System.out.println("得到请求");
            return "redirect:index.jsp";
    }
    redirect 重定向，forward请求转发，但是使用这两个关键字之后，需要些完整目的路径，解析器不会给你加上前后缀
    
mvc如何访问静态资源？
原因：因为mvc的入口是Dispatcher因为静态资源不需要Dispatcher所以无法访问到
解决方法：
    <!--这个注解会让没有RequestMapping，就让他直接访问资源，-->
        <mvc:default-servlet-handler></mvc:default-servlet-handler>
    
 数据格式化：
    <1>配置bean
         <bean id="conversionService" class="org.springframework.format.support.FormattingConversionService"></bean>
    <2>使用注解    
mvc数据校验：
        检验前端填充的数据的格式和正确性。
        <1>使用JSR 303验证标准
        <2>导入Hibernate Vaildate.jar包
        <3>使用
        
     @ResponseBody修饰的方法，返回的不是view而是一个json数组，谁请求将结果就给谁
     
     
     springMvc实现文件上传和  拦截器
     <1>将commons-fileupdown.jar   commons-io.jar加入环境
     <2> 注：上传文件时请求方式必须是post方式。handler接受时使用MutiaprtFile接受
         <!--用于文件上传  id值不能变 必须是 commonsMultipartResolver-->
                 <bean id="commonsMultipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
                         <property name="defaultEncoding " value="UTF-8"/>
                         <property name="maxUploadSizePerFile" value="102400"/>  <!--文件最大值-->
                         <property name="maxInMemorySize"  value="1024"/><!--缓存-->
                 </bean>b.multipart.commons.CommonsMultipartResolver"></bean>
     <3>前端页面form中要有enctype="multipart/form-data" 属性

拦截器：
    <1>编写拦截器 
       实现org.springframework.web.servlet.HandlerInterceptor接口
    <2>配置拦截器让springmvc知道
        <!--配置拦截器          默认拦截全部-->
                <mvc:interceptors>
                              <mvc:interceptor>
                                        <mvc:mapping path="/*"/>           <!--指定拦截的请求-->
                                        <mvc:exclude-mapping path="/fileUpdown"></mvc:exclude-mapping><!--指定不拦截的请求-->
                                        <bean class="Controller.Enitry.MyIntercepter"></bean><!--配置烂拦截器-->
                              </mvc:interceptor>
                 </mvc:interceptors>
                 
异常处理（ExceptionHandler）：
    mvc规定，异常处理必粗使用ExceptionHandler来处理
    注：@ExceptionHandler修饰的方法只能处理当前类的异常
        @ControllerAdvice修饰的类中的@ExceptionHandler的方法可以处理所有的类中的异常
    