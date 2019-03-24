package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 刘棋军
 * @date2019-03-14
 */
@ControllerAdvice
public class TestException {
    @ExceptionHandler({ArithmeticException.class,ArrayIndexOutOfBoundsException.class})
    public ModelAndView testExceptionHandler(Exception e){
        ModelAndView v = new ModelAndView("error");
        v.addObject("err",e);
        return  v;
    }
}
