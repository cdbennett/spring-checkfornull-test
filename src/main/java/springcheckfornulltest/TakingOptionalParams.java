package springcheckfornulltest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
class TakingOptionalParams {
    private final TestInterface tester;

    // As of Spring 5.1.6:
    // - BROKEN: jsr305 CheckForNull (jsr305 3.0.1)
    // - BROKEN: JetBrains Nullable (jetbrains-annotations 17.0.0)
    // - OK: Spring Nullable (spring-core 5.1.9)
    // - OK: jsr305 Nullable (jsr305 3.0.1)

    TakingOptionalParams(
//            @javax.annotation.CheckForNull // BROKEN
//            @org.jetbrains.annotations.Nullable // BROKEN
//            @org.springframework.lang.Nullable // WORKING
            @javax.annotation.Nullable  // WORKING
            @Qualifier("myExecutor") TestInterface tester) {
        this.tester = tester;
    }

    int times10(int i) {
        int value = i * 10;
        if (tester != null) {
            value = tester.process(value);
        }
        return value;
    }
}
