package android.com.wallet.UITest.retry;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class Retry2junit implements TestRule {
    private int retryCount;

    public Retry2junit(int retryCount) {
        this.retryCount = retryCount;
    }

    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                // TODO Auto-generated method stub
                Throwable caughtThrowable = null;
                // implement retry logic here
                for (int i = 0; i < retryCount; i++) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable t) {
                        //Common.takeScreenShot(description.getDisplayName()+ "_" +(i+1));
                        System.err.println("screenshot as " + description.getDisplayName() + "_" + (i + 1) + ".png in pic");
                        caughtThrowable = t;
                        System.err.println(description.getDisplayName() + ": run " + (i + 1) + " failed");
                    } finally {
                        tearDown();
                    }
                }
                System.err.println(description.getDisplayName() + ": giving up after " + retryCount + " failures");
                throw caughtThrowable;
            }

            public void tearDown() throws Exception {
                //Base.tearDownWithoutQuit();
            }

        };
    }




    }
