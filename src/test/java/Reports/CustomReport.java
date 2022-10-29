package Reports;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import java.util.List;
import java.util.Map;

public class CustomReport implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuite, List<ISuite> suites, String outputDirectory) {
        for(ISuite suite:suites){
            String suiteName = suite.getName();

            Map<String , ISuiteResult> iSuiteResult = suite.getResults();
            for(ISuiteResult result: iSuiteResult.values()){
                ITestContext tc = result.getTestContext();
                System.out.println("Passed Tests for Suite " + suiteName+" is " + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed Tests for Suite " + suiteName+" is " + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped Tests for Suite " + suiteName+" is " + tc.getSkippedTests().getAllResults().size());
            }
        }
    }
}
