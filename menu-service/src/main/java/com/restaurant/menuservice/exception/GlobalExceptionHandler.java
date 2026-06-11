package com.restaurant.menuservice.exception;

import com.restaurant.sharemodules.dto.ApiResponse;
import com.restaurant.sharemodules.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Bắt lỗi CHUNG: Không tìm thấy tài nguyên (Áp dụng khi tìm không thấy MenuItem, Category...)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<String> response = ApiResponse.notFound(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Lỗi 404
    }

    // 2. Bắt lỗi ĐẶC THÙ: Món ăn không có sẵn
    @ExceptionHandler(MenuNotAvailableException.class)
    public ResponseEntity<ApiResponse<String>> handleMenuNotAvailable(MenuNotAvailableException ex) {
        ApiResponse<String> response = ApiResponse.badRequest(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Lỗi 400
    }

    // 3. Bắt lỗi VALIDATION: Dữ liệu đầu vào không hợp lệ
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ApiResponse<String> response = ApiResponse.badRequest(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 4. Bắt TẤT CẢ các lỗi bắt ngờ khác (Lỗi crash code, null pointer...) để giấu log đi
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        ApiResponse<String> response = ApiResponse.internalError("Internal server error, please try again later!");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Lỗi 500
    }
}
