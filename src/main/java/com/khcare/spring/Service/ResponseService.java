package com.khcare.spring.Service;

import com.khcare.spring.dto.BaseResponse;
import com.khcare.spring.dto.SingleDataResponse;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    public <T> SingleDataResponse<T> getSingleDataResponse(boolean success, String message, T data) {
        SingleDataResponse<T> response = new SingleDataResponse<>();
        response.setSuccess(success);
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    public BaseResponse getBaseResponse(boolean success, String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(success);
        response.setMessage(message);

        return response;
    }
}
