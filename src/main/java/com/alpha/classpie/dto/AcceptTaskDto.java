package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.task.Task;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class AcceptTaskDto {
    Task task;
    MultipartFile multipartFile;
}
