package com.epam.MyAwesomeSpringProject.aspects;

import com.epam.entity.User;
import com.epam.exeptions.NoSubException;
import com.epam.service.TaskServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.List;

@Component
@Aspect
public class SubscriptionCheckAspect {

    @Pointcut("execution(* com.epam.service.TaskServiceImpl.createTask*(..))")
    private void handle() {
    }

    @Around("handle()")
    public Object doSubscriptionCheck(ProceedingJoinPoint joinPoint) throws Throwable {

        User user = (User) joinPoint.getArgs()[1];
        List tasks = ((TaskServiceImpl) joinPoint.getTarget()).getTasksByUser(user.getId());

        if (user.getSubscription().equals(DigestUtils.md5DigestAsHex(("secret".getBytes()))) && tasks.size() < 10) {
            return joinPoint.proceed();
        }
        throw new NoSubException(user.getName() + " not subscribed, can't create more than 10 tasks");
    }
}