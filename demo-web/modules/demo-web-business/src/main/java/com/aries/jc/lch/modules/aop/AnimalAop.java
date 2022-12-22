package com.aries.jc.lch.modules.aop;


import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.aries.jc.lch.modules.enums.ErrorEnums;
import com.aries.jc.lch.modules.exception.BusinessRuntimeException;
import com.aries.jc.lch.modules.utils.JacksonUtil;
import lombok.ToString;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

/**
 * The type Animal aop.
 *
 * @author lichanghao6
 */
@ToString
@Component
@Aspect
public class AnimalAop {

    /**
     * 记录切面运行时产生的日志
     */
    private static final AriesJcLogger log = AriesJcLoggerFactory.getLogger(AnimalAop.class);

    /**
     * 在执行所有的controller包下的控制器方法之前都要执行下面的操作
     */
    @Pointcut("execution(public * com.aries.jc.lch.modules.controller..*(..))))")
    public void animalAspectSystem(){

    }

    /**
     * Execute log object.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("animalAspectSystem()")
    Object executeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        if (args.length > 0) {
            //打印请求参数
            log.info("请求参数为: {}", JacksonUtil.objectToJson(args[0]));

            //若要使参数校验生效，请求参数必须被@Valid所注解，并BindingResult(最后一个参数)不可省略
            //参数校验统一处理(参数大于2个，并且倒数第一个参数是BindingResult类型)
            if (args.length > 1 && args[args.length - 1] instanceof BindingResult) {
                BindingResult result = (BindingResult) args[args.length - 1];
                if (result.hasErrors()){
                    String errorParams = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                    throw new BusinessRuntimeException(ErrorEnums.M_7777.getErrCode(), errorParams);
                }
            }
        }

        Object proceed = joinPoint.proceed();
        log.info("返回参数为: {}", JacksonUtil.objectToJson(proceed));
        return proceed;
    }
}
