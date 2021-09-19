package org.example.ba01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Date;

/**
 * @Aspect:是aspectj中的注解
 *   作用：表示当前类是切面类
 *   切面类：是用来给业务方法增加功能的类，在这个类中有切面的功能代码
 *   位置：在类定义的上面
 */
@Aspect
public class MyAspect {
    /**
     * 定义方法，方法是实现切面功能的
     * 方法的定义要求：
     * 1.公共方法public
     * 2.方法没有返回值
     * 3.方法名称自定义
     * 4.方法可以有参数，也可以没有参数
     *   如果有参数，参数不是自定义的，有几个参数类型可以使用
     */

    /**
     * @Before:前置通知注解
     *   属性：value,是切入点表达式，表示切面的功能执行的位置
     *   位置：在方法的上面
     * 特点：
     *    1.在目标方法之前先执行的
     *    2.不会改变目标方法的执行结果
     *    3，不会影响目标方法的执行
     */
/*
    //可简写为execution(* *..SomeServiceImpl.do*(..))
    //也可简写为execution(* do*(..))
    @Before(value = "execution(public void org.example.ba01.SomeServiceImpl.doSome(String,Integer))")
    public void myBefore(){
        //就是你切面要执行的功能代码
        System.out.println("前置通知，切面功能：在目标方法之前输出执行时间："+new Date());
    }
    //如果excution中的方法等参数书写错误，没有找到目标类，则不会创建代理，执行结果只是为目标类的方法*/

    /**
     * 指定通知方法中的参数：JoinPoint
     * JoinPoint :业务方法，要加入切面功能的业务方法
     *   作用是：可以在通知方法中获取方法执行时的信息，例如方法名称，方法的实参
     *   如果你的切面功能中需要用到方法的信息，就加入JoinPoint
     *   这个JoinPoint参数的值是由框架赋予，必须是第一个位置的参数
     */
    @Before(value = "execution(public void org.example.ba01.SomeServiceImpl.doSome(String,Integer))")
    public void myBefore(JoinPoint jp){
        //获取方法的完整定义
        System.out.println("方法的签名(定义)="+jp.getSignature());
        System.out.println("方法的名称="+jp.getSignature().getName());
        //获取方法的实参
        Object args[]=jp.getArgs();
        for (Object arg:args
             ) {
            System.out.println("参数="+arg);
        }
        //就是你切面要执行的功能代码
        System.out.println("前置通知，切面功能：在目标方法之前输出执行时间："+new Date());
    }

    //------------------------------------------------------------
    /**
     * 后置通知定义方法，方法是实现切面功能的
     * 方法的定义要求：
     * 1.公共方法public
     * 2.方法没有返回值
     * 3.方法名称自定义
     * 4.方法有参数的，推荐是Object,参数名自定义
     */

    /**
     * @AfterReturning:后置通知
     *   属性：1.value  切入点表达式
     *        2.returning 自定义的变量，表示目标方法的返回值的
     *            自定义变量名必须和通知方法的形参名一样。
     *   位置：在方法定义的上面
     * 特点：
     *   1.在目标方法之后执行的
     *   2.能够获取到目标方法的返回值，可以根据这个返回值做不同的处理功能
     *   3.可以修改这个返回值
     *
     *   后置通知的执行
     *    Object res=doOther();
     *    myAfterReturning(res);
     *    System.out.println("====="+res)
     *
      */
    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))" ,
                   returning = "res")
    public void myAfterReturning(JoinPoint jp,Object res){
        //Object res：是目标方法执行后的返回值，根据返回值做你的切面的功能处理
        System.out.println("后置通知：方法的定义"+jp.getSignature());
        System.out.println("后置通知：在目标方法之后执行的，获取的返回值是："+res);

    }

    //================================================================
    /**
     * 环绕通知方法的定义格式
     *   1.public
     *   2.必须有一个返回值，推荐使用Object
     *   3.方法名称自定义
     *   4.方法有参数，固定的参数ProceedingJoinPoint
     */

    /**
     * @Around:环绕通知
     *    属性：value  切入点表达式
     *    位置：在方法的定义上面
     * 特点：  1.它是功能最强的通知
     *        2.在目标方法的前和后都能增强功能
     *        3.控制目标方法是否被调用执行
     *        4.修改原来的目标方法的执行结果，影响最后的调用结果
     *环绕通知，等同于jdk动态代理的，InvocationHandler接口
     *
     * 参数：ProceedingJoinPoint  就等同于Method
     *     作用：执行目标方法的
     *
     * 返回值：就是目标方法的执行结果，可以被修改
     *
     * 环绕通知：经常做事务，在目标方法之前开启事务，执行目标方法，在目标方法之后提交事务
     */
    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        String name="";
        //获取第一个参数值
        Object args[] =pjp.getArgs();
        if (args!=null&&args.length>1){
            Object arg=args[0];
            name=(String) arg;
        }

        //实现环绕通知
        Object result=null;
        System.out.println("环绕通知：在目标方法之前，输出时间："+new Date());
        //1.目标方法调用
        if ("ch".equals(name)){
            //符合条件，调用目标方法
            result=pjp.proceed();//method.invoke(); Object result=doFirst();
        }

        //2.在目标方法的前或者后加入功能
        System.out.println("环绕通知：在目标方法之后，提交事务");

        //修改目标方法的执行结果，影响方法最后的调用结果
        if (result!=null){
            result="hello Aspectj AOP";
        }

        //返回目标方法的执行结果
        return result;
    }

    //=============================================================
    /**
     * 异常通知方法的定义格式
     * 1.public
     * 2.没有返回值
     * 3.方法名称自定义
     * 4.方法有一个Exception,如果还有是JoinPoint
     */

    /**
     * @AfterThrowing:异常通知
     *   属性：1.value  切入点表达式
     *        2.throwing 自定义的变量，表示目标方法抛出的异常对象
     *        变量名必须和方法的参数名一样
     *
     *    特点：1.在目标方法抛出异常时执行的
     *         2.可以做异常的监控程序，监控目标方法执行时是不是有异常
     *            如果有异常，可以发送邮件，短信进行通知
     *
     *    执行就是：
     *    try{
     *          SomeServiceImpl.doSecond(..)
     *    }catch(Exception e){
     *        myAfterThrowing(e);
     *    }
     */
    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doSecond(..))",
                    throwing = "ex")
    public void myAfterThrowing(Exception ex){
        System.out.println("异常通知：方法发生异常时，执行："+ex.getMessage());
        //发送邮件，短信，通知开发人员
    }

    /**
     * 最终通知方法的定义格式
     * 1.public
     * 2.没有返回值
     * 3.方法名称自定义
     * 4.方法没有参数，如果有则是JoinPoint
     */

    /**
     * @After:最终通知
     *   属性：value 切入点表达式
     *   位置：在方法的上面
     * 特点：1.总是会执行
     *      2.在目标方法之后执行的
     *
     * try{
     *     SomeServiceImpl.doThird(..)
     * }catch(Exception e){}
     * finally{
     *     myAfter()
     * }
     */
    //由最后的@Pointcut ，所以下面这行可以表达为：@After(value = "mypt()")
    @After(value = "execution(* *..SomeServiceImpl.doThird(..))")
    public void  myAfter(){
        System.out.println("执行最终通知，总是会被执行的代码");
        //一般做资源清除工作的
    }

    /**
     * @Pointcut (辅助的注解)：定义和管理切入点，如果你的项目中有多个切入点表达式是重复的，可以复用的
     *           可以使用@Pointcut
     *      属性：value 切入点表达式
     *      位置：在自定义的方法上面
     * 特点：当使用@Pointcut定义在一个方法的上面，此时这个方法的名称就是切入点表达式的别名
     *     其他的通知中，value属性就可以使用这个方法名称，替代切入点表达式了
     *
     * 这个方法一般是私有的，因为它不需要被外界所调用
     */
    @Pointcut(value = "execution(* *..SomeServiceImpl.doThird(..))")
    private void mypt(){
         //无需代码
    }
}
