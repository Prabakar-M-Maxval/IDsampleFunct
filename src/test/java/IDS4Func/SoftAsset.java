/**
 * prabakar
 */
package IDS4Func;

import com.thoughtworks.selenium.SeleneseTestNgHelper;
import java.util.Arrays;
import java.util.Map;

import org.testng.Assert;

import org.testng.Reporter;

/**
 * @author prabakar
 *
 */
public class SoftAsset extends SeleneseTestNgHelper {

    private StringBuffer verificationErrors;
    private StringBuffer verificationSuccess;

    public SoftAsset() {
        verificationErrors = new StringBuffer();
        verificationSuccess = new StringBuffer();
    }

    public void verifyFalse(String msg, Boolean b) {
        try {
            Assert.assertFalse(b);
        } catch (Error e) {

            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void verifyEquals(String actual, String expected, String msg) {
        try {
            Assert.assertEquals(actual, expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");
			// verificationErrors.append("Actual Result:" + actual + ""
            // + "Expected Result:" + expected + " - Condition FAILED"
            // + "\n");
        }
    }

    public void verifyFalse(Boolean actual, String msg) {
        try {
            Assert.assertFalse(actual, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + " - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");
        }
    }

    public void verifyTrue(Boolean actual, String msg) {
        try {
            Assert.assertTrue(actual, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + " - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");
        }
    }

    public void verifyEquals(Map<String, Integer> actual,
            Map<String, Integer> expected, String msg) {
        try {
            Assert.assertEquals(actual, expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");

        }
    }

    public void verifyEquals(Float actual, Float expected, String msg) {
        try {
            Assert.assertEquals(actual, expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");

        }
    }

    public void verifyEquals(Double actual, Double expected, String msg) {
        try {
            Assert.assertEquals(actual, expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");
        }
    }

    public void verifyEquals(Boolean actual, String expected, String msg) {
        try {
            Boolean Expected = true;
            Assert.assertEquals(actual, Expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");
        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");

        }
    }

    public void verifyEquals(Boolean actual, Boolean expected, String msg) {
        try {

            // Boolean Expected = true;
            Assert.assertEquals(actual, expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");
        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");

        }
    }

    public void verifyEquals(int actual, int expected, String msg) {
        try {
            Assert.assertEquals(actual, expected, msg);
            verificationSuccess.append(msg).append(":" + " " + "Actual Result:" + " ").append(actual).append(" " + "Expected Result:" + " ").append(expected).append(" - Condition PASSED" + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");
        }
    }

    public void verifyEquals(String[] actual, String[] expected, String msg) {
        try {
            Assert.assertEquals(actual, expected, msg);
            String S1 = Arrays.toString(actual);
            String S2 = Arrays.toString(expected);
            verificationSuccess.append(msg).append(" " + "Actual Result:").append(S1).append(" " + "Expected Result:" + " ").append(S2).append(" - Condition PASSED"
                    + "\n");

        } catch (AssertionError e) {
            verificationErrors.append(e).append("-Condition FAILED" + "\n");
        }
    }

    @Override
    public void clearVerificationErrors() {
        verificationErrors = new StringBuffer();
    }

    @Override
    public void checkForVerificationErrors() {
        String verificationErrorString = verificationErrors.toString();

		// Clear Verification Errors so that it is ready to test new
        // verifications
        clearVerificationErrors();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void clearVerificationSuccess() {
        verificationSuccess = new StringBuffer();
    }

    public String assertPassedCondition() {
        String returnpassed = verificationSuccess.toString();
        clearVerificationSuccess();
        return returnpassed;
    }
}
