package com.example.chuchu.common.global;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

public class HttpResponseEntity {

    public static <T> ResponseResult<T> success(T response) {
        return new ResponseResult<>(true, response, null);
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static ResponseResult<?> error(Throwable throwable, HttpStatus status) {
        return new ResponseResult<>(false, null, new ResponseError(throwable, status));
    }

    public static ResponseResult<?> error(String message, HttpStatus status) {
        return new ResponseResult<>(false, null, new ResponseError(message, status));
    }

    @Getter
    public static class ResponseError {
        private final String message;
        private final int status;

        ResponseError(Throwable throwable, HttpStatus status) {
            this(throwable.getMessage(), status);
        }

        ResponseError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("message", message)
                    .append("status", status)
                    .toString();
        }
    }

    @Getter
    public static class ResponseResult<T> {
        private final boolean success;
        private final T response;
        private final ResponseError error;

        private ResponseResult(boolean success, T response, ResponseError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("success", success)
                    .append("response", response)
                    .append("error", error)
                    .toString();
        }
    }
}
