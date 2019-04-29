package springcheckfornulltest;

public class StubTestInterface implements TestInterface {
    @Override
    public int process(int i) {
        return i * 1000;
    }
}
