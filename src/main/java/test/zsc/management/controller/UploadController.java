package test.zsc.management.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import test.zsc.management.pojo.Result;
import test.zsc.management.utils.AliyunOSSOperator;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}", file);
        
        // 先检查 file 是否为 null
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的文件");
        }
        
        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
        
        // 上传文件
        String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
        return Result.success(url);
    }


}
