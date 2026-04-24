package test.zsc.management.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import test.zsc.management.utils.CurrentHolder;
import test.zsc.management.utils.JwtUtils;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 获取请求url。
        String url = request.getRequestURL().toString();

        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){ //登录请求
            log.info("登录请求 , 直接放行");
            // 登录时没有JWT令牌,所以不需要设置CurrentHolder,operate_emp_id 会为 null
            return true;
        }

        //3. 获取请求头中的令牌（token）。
        String jwt = request.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){ //jwt为空
            log.info("获取到jwt令牌为空, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        //5. 解析token,如果解析失败,返回错误结果(未登录)。
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            // 将用户ID存入ThreadLocal,供后续日志记录使用
            Integer userId = claims.get("id", Integer.class);
            CurrentHolder.setCurrentId(userId);
            log.info("解析令牌成功, 用户ID: {}", userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        //6. 放行。
        log.info("令牌合法, 放行");
        return true;
    }

    // 注意: 不在 afterCompletion 中清除 CurrentHolder
    // 由 TokenFilter 的 finally 块统一清除,确保 AOP 切面能获取到用户ID
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // 请求完成后清除ThreadLocal,防止内存泄漏
//        CurrentHolder.remove();
//    }

}
