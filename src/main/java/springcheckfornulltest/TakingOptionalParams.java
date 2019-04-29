package springcheckfornulltest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
class TakingOptionalParams {
    @javax.annotation.CheckForNull private final TestInterface tester;

    // The following doesn't work as of Spring 5.1.6; Spring doesn't detect @CheckForNull as nullable.
    TakingOptionalParams(@javax.annotation.CheckForNull @Qualifier("myExecutor") TestInterface tester) {
        // The following works, and Spring passes null as the argument.
//    TakingOptionalParams(@javax.annotation.Nullable @Qualifier("myExecutor") TestInterface tester) {
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
