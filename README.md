# Spring @CheckForNull Test

Demonstrating a bug in Spring autowiring where the JSR-305 `@CheckForNull`
annotation is not treated as a nullable annotation.

## Expected Behavior

The application, when run with `./gradlew run`, should execute and print:

    obj.times10(13) is 130

## Actual Behavior

The actual result of `./gradlew run` is that the application fails to start
with this error message:

    Exception encountered during context initialization - cancelling refresh
    attempt: org.springframework.beans.factory.UnsatisfiedDependencyException:
    Error creating bean with name 'takingOptionalParams' defined in file
    [.../build/classes/java/main/springcheckfornulltest/TakingOptionalParams.class]:
    Unsatisfied dependency expressed through constructor parameter 0; nested
    exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
    No qualifying bean of type 'springcheckfornulltest.TestInterface' available:
    expected at least 1 bean which qualifies as autowire candidate. Dependency
    annotations: {@javax.annotation.CheckForNull(),
    @org.springframework.beans.factory.annotation.Qualifier(value=myExecutor)}
    Exception in thread "main"
    org.springframework.beans.factory.UnsatisfiedDependencyException: Error
    creating bean with name 'takingOptionalParams' defined in file
    [/Users/colin.bennett/Code/spring-checkfornull-test/build/classes/java/main/springcheckfornulltest/TakingOptionalParams.class]:
    Unsatisfied dependency expressed through constructor parameter 0; nested
    exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
    No qualifying bean of type 'springcheckfornulltest.TestInterface' available:
    expected at least 1 bean which qualifies as autowire candidate. Dependency
    annotations: {@javax.annotation.CheckForNull(),
    @org.springframework.beans.factory.annotation.Qualifier(value=myExecutor)}
            at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:769)
            at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:218)
            at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1341)
            at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1187)
            at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555)
            at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515)
            at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320)
            at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
            at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318)
            at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199)
            at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:849)
            at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:877)
            at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549)
            at org.springframework.context.annotation.AnnotationConfigApplicationContext.<init>(AnnotationConfigApplicationContext.java:88)
            at springcheckfornulltest.App.main(App.java:11)
    Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'springcheckfornulltest.TestInterface' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@javax.annotation.CheckForNull(), @org.springframework.beans.factory.annotation.Qualifier(value=myExecutor)}
            at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1654)
            at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1213)
            at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1167)
            at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:857)
            at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:760)
            ... 14 more

If you comment out the `// BROKEN` line and uncomment the `// WORKING` line in
`TakingOptionalParams.java`, the program runs correctly, using the `@Nullable`
annotation instead of `@CheckForNull`.
