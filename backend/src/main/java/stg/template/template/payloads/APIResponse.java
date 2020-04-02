package stg.template.template.payloads;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@ApiModel(value = "APIResponse", description = "Common Api Response")
public class APIResponse {

    @ApiModelProperty(value = "http status", required = true, dataType = "Integer")
    private Integer code;

    @ApiModelProperty(value = "message", required = true, dataType = "String")
    private String message;

    @ApiModelProperty(value = "return data")
    private Object data;


    public static APIResponse of(Integer code, String message, Object data) {
        return new APIResponse(code, message, data);
    }

    public static APIResponse ofStatus(HttpStatus status, Object data) {
        return of(status.value(), status.getReasonPhrase(), data);
    }

    public static APIResponse ofSuccess(Object data) {
        return ofStatus(HttpStatus.OK, data);
    }

    public static APIResponse ofMessage(String message) {
        return of(HttpStatus.OK.value(), message, null);
    }
}