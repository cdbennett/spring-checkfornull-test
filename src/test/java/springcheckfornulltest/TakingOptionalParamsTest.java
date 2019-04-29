package springcheckfornulltest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TakingOptionalParamsTest {
    @Test
    public void providingTestInterface() {
        TakingOptionalParams sut = new TakingOptionalParams(new StubTestInterface());
        assertEquals(130000, sut.times10(13));
    }
}
