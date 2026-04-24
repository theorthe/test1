package test.zsc.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.zsc.management.annotate.LogOperation;
import test.zsc.management.pojo.Emp;
import test.zsc.management.pojo.LoginInfo;
import test.zsc.management.pojo.Result;
import test.zsc.management.service.EmpService;
import test.zsc.management.utils.CurrentHolder;

import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @LogOperation
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工来登录啦 , {}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null){
            // 登录成功后，设置当前用户ID到CurrentHolder，供AOP切面记录日志使用
            CurrentHolder.setCurrentId(loginInfo.getId());
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误~");
    }

    /**
     * 修改密码
     * @param params 包含 id, oldPassword, newPassword 的Map
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String, Object> params) {
        try {
            Integer id = (Integer) params.get("id");
            String oldPassword = (String) params.get("oldPassword");
            String newPassword = (String) params.get("newPassword");
            
            log.info("修改密码请求: id={}, oldPassword={}, newPassword={}", id, oldPassword, newPassword);
            
            // 参数校验
            if (id == null || oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()) {
                return Result.error("参数不能为空");
            }
            
            // 调用服务层修改密码
            boolean success = empService.updatePassword(id, oldPassword, newPassword);
            
            if (success) {
                log.info("密码修改成功: id={}", id);
                return Result.success("密码修改成功");
            } else {
                log.warn("密码修改失败，旧密码错误: id={}", id);
                return Result.error("原密码错误");
            }
        } catch (Exception e) {
            log.error("修改密码异常", e);
            return Result.error("密码修改失败: " + e.getMessage());
        }
    }

}