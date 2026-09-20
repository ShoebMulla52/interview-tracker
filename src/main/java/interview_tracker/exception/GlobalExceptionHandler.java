package interview_tracker.exception;




import interview_tracker.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception exception) {

        System.out.println("========== EXCEPTION ==========");
        System.out.println("Exception Type: "
                + exception.getClass().getName());
        System.out.println("Exception Message: "
                + exception.getMessage());

        exception.printStackTrace();

        System.out.println("===============================");

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(exception.getClass().getSimpleName()
                        + ": " + exception.getMessage())
                .data(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}