package com.mac.annotation;

import com.mac.dao.UserDao;
import com.mac.vo.dto.UserPermissionDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yog
 */

@Aspect
@Component
@Slf4j
public class PermissionAspect {

    @Resource
    private UserDao userDao;

    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.mac.annotation.MyPermission)")
    private void permission() {

    }

    /**
     * 目标方法调用之前执行
     */
    @Before("permission()")
    public void doBefore() {
        System.out.println("================== step 2: before ==================");
    }

    /**
     * 目标方法调用之后执行
     */
    @After("permission()")
    public void doAfter() {
        System.out.println("================== step 4: after ==================");
    }

    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around("permission()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("================== step 1: around ==================");
        long startTime = System.currentTimeMillis();
        /*
         * 获取当前http请求中的token
         * 解析token :
         * 1、token是否存在
         * 2、token格式是否正确
         * 3、token是否已过期（解析信息或者redis中是否存在）
         * */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("非法请求，无效token");
        }
        // 校验token的业务逻辑

        //假设token里只是一个userId,查询到他有删除和查看的权限，没有添加和修改的权限
        // 解析token之后，获取当前用户的账号信息，查看它对应的角色和权限信息
        //String[] permissionCodes = {PermissionConsts.R};
        //List<String> codes = Arrays.asList(permissionCodes);

        //解析token，假设token就是userId
        //String userId = parse(token);
        List<UserPermissionDto> codes = userDao.findPermissionCodeByUserId(token);
        List<String> permissionCodes = codes.stream().map(UserPermissionDto::getPermissionCode).collect(Collectors.toList());
        /*
         * 获取注解的值，并进行权限验证
         * */
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        MyPermission myPermission = method.getAnnotation(MyPermission.class);

        String value = myPermission.value();
        // 将注解的值和token解析后的值进行对比，查看是否有该权限，如果权限通过，允许访问方法；否则不允许，并抛出异常
        if(!permissionCodes.contains(value)){
            throw new RuntimeException("对不起，您没有权限访问！");
        }
        // 执行具体方法
        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        /*
         * 记录相关执行结果
         * 可以存入MongoDB 后期做数据分析
         * */
        // 打印请求 url
        System.out.println("URL            : " + request.getRequestURL().toString());
        // 打印 Http method
        System.out.println("HTTP Method    : " + request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        System.out.println("controller     : " + proceedingJoinPoint.getSignature().getDeclaringTypeName());
        // 调用方法
        System.out.println("Method         : " + proceedingJoinPoint.getSignature().getName());
        // 执行耗时
        System.out.println("cost-time      : " + (endTime - startTime) + " ms");

        return result;

    }

}
