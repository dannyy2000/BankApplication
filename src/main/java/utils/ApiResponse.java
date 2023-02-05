package utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {

    private ZonedDateTime time;
    private Object data;
    private int statusCode;
    private  String path;
    private Boolean isSuccessful;



}
