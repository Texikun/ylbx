package com.neuedu.ylbx.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: xianzhen
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行预检请求
        if(request.getMethod().equalsIgnoreCase("options")){
            return true;
        }
        //关于图片的请求不做token检查
        if(request.getRequestURL().toString().contains("/images")){
            return true;
        }
        //获取token
        String token=request.getHeader("token");
        //校验token
        if(token==null){
            throw  new Exception("token为空，请登录！！！");
        }else{
            //校验token的有效性(正确性，时效性)
            JwtParser parser = Jwts.parser();
            //解析token的SigningKey必须和生成token时设置密码一致
            parser.setSigningKey("neuedu123456");
            //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
        }
        return true;
    }
}
