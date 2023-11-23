//package B911021.Kim.exception;
//
//import B911021.Kim.dto.ErrorDto;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//@ControllerAdvice(annotations = RestController.class)
//@Log4j2
//class GlobalControllerExceptionHandler {
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<ErrorDto> handleResponseStatusException(ResponseStatusException ex) {
//        log.error("response status exception", ex);
//        ErrorDto errorDto = new ErrorDto(ex.getMessage());
//        return new ResponseEntity<>(errorDto, ex.getStatus());
//    }
//
////    @ExceptionHandler(ToDoException.class)
////    public ResponseEntity<ErrorDto> handleToDoException(ToDoException ex) {
////        log.error("todo custom exception", ex);
////        ErrorDto errorDto = new ErrorDto(ex.getMessage());
////        HttpStatus httpStatus = HttpStatus.resolve(ex.getStatus());
////        return new ResponseEntity<>(errorDto, httpStatus);
////    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException ex) {
//        log.error("internal server error", ex);
//        ErrorDto errorDto = new ErrorDto("internal server error");
//        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
//
////
////package kim.todo.exception;
////
////public class ToDoException extends RuntimeException {
////    private int status;
////
////    public ToDoException(int status, String message) {
////        super(message);
////        this.status = status;
////    }
////
////    public int getStatus() {
////        return status;
////    }
////}