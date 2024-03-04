    package it.akt.handler;

    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.servlet.ModelAndView;
    import org.springframework.web.servlet.resource.NoResourceFoundException;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(NoResourceFoundException.class)
        public ModelAndView handleNoHandlerFoundException(NoResourceFoundException ex) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
    }


