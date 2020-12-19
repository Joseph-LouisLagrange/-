package com.alpha.classpie.dto.warpper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class UnpaidTaskStudentWrapper extends StudentSafeWrapper {
    long expediteCount=0;
    long returnCount=0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date returnLastTime;
}
