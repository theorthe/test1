package test.zsc.management.utils;


//import com.aliyun.oss.*;
//import com.aliyun.oss.common.auth.*;
//import com.aliyun.oss.common.comm.SignVersion;
//import com.aliyun.oss.model.Bucket;
//
//import java.util.List;


import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * OSS SDK 快速接入示例
 * 演示如何初始化 OSS 客户端并列出所有 Bucket
 */

//public class AliyunOSSOperator {
//    public static void main(String[] args) {
//        // 从环境变量中获取访问凭证(生产环境推荐方式)
//        String accessKeyId = System.getenv("OSS_ACCESS_KEY_ID");
//        String accessKeySecret = System.getenv("OSS_ACCESS_KEY_SECRET");
//
//        // 设置OSS地域和Endpoint
//        String region = "cn-beijing";
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//
//        // 创建凭证提供者
//        DefaultCredentialProvider provider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
//
//        // 配置客户端参数
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        // 显式声明使用V4签名算法
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//
//        // 初始化OSS客户端
//        OSS ossClient = OSSClientBuilder.create()
//                .credentialsProvider(provider)
//                .clientConfiguration(clientBuilderConfiguration)
//                .region(region)
//                .endpoint(endpoint)
//                .build();
//
//        // 列出当前用户的所有Bucket
//        List<Bucket> buckets = ossClient.listBuckets();
//        System.out.println("成功连接到OSS服务，当前账号下的Bucket列表：");
//
//        if (buckets.isEmpty()) {
//            System.out.println("当前账号下暂无Bucket");
//        } else {
//            for (Bucket bucket : buckets) {
//                System.out.println("- " + bucket.getName());
//            }
//        }
//
//        // 释放资源
//        ossClient.shutdown();
//        System.out.println("OSS客户端已关闭");
//    }
//}

//黑马的课

@Component
public class AliyunOSSOperator {

    //方式一: 通过@Value注解一个属性一个属性的注入
    //@Value("${aliyun.oss.endpoint}")
    //private String endpoint;
    //@Value("${aliyun.oss.bucketName}")
    //private String bucketName;
    //@Value("${aliyun.oss.region}")
    //private String region;

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] content, String originalFilename) throws Exception {
        String endpoint = aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region = aliyunOSSProperties.getRegion();

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 填写Object完整路径，例如2024/06/1.png。Object完整路径中不能包含Bucket名称。
        //获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        //生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }

        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

}

