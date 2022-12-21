import javax.management.*;
import java.lang.management.ManagementFactory;

public class PrimeNumberCalculator implements PrimeNumberCalculatorInterface {
    private long highestPrimemaxPrime = 1L;
    private long candidate = 1L;

    public void calculate() {
        outer: while (true) {
            candidate++;
            for (long l = 2; l < candidate; l++) {
                if (candidate % l == 0) {
                    continue outer;
                }
            }
            System.out.println("Found prime number: " + candidate);
            highestPrimemaxPrime = candidate;
            candidate++;
        }
    }

    public static void main(String[] args) throws MalformedObjectNameException, MBeanRegistrationException, InstanceAlreadyExistsException, NotCompliantMBeanException {
        PrimeNumberCalculator pnc = new PrimeNumberCalculator();
        registerMBean(pnc);
        pnc.calculate();
    }


    private static void registerMBean(PrimeNumberCalculatorInterface pnc) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        StandardMBean mBean = new StandardMBean(pnc, PrimeNumberCalculatorInterface.class);
        ObjectName objectName = new ObjectName("at.java:name=PrimeNumberCalculator");
        mbs.registerMBean(mBean, objectName);
    }

    @Override
    public long getCandidate() {
        return candidate;
    }

    @Override
    public void setCandidate(long candidate) {
        this.candidate = candidate;
    }

    @Override
    public long getHighestPrimemaxPrime() {
        return highestPrimemaxPrime;
    }
}
