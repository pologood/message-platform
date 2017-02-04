package com.message.output;

/**
 * Created by hpj
 */
public class BaseResponse {

    private String status; //状态
    private String description; //描述
    private Object data;


    public BaseResponse(String status, String description, Object data) {
        this.status = status;
        this.description = description;
        this.data = data;
    }

    public BaseResponse() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
