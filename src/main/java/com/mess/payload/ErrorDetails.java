package com.mess.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String detail;
}
