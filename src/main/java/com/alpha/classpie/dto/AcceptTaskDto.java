package com.alpha.classpie.dto;

import com.alpha.classpie.pojo.task.Task;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 杨能
 * @create 2020/11/15
 * 定义后台接收任务的DTO
 */
@Data
public class AcceptTaskDto {
    Task task;
    MultipartFile multipartFile;
}
