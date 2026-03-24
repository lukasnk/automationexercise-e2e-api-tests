package base;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FailureArtifactsExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            return;
        }

        Object instance = context.getRequiredTestInstance();
        if (!(instance instanceof BaseTest)) {
            return;
        }

        Page page = ((BaseTest) instance).getPage();
        if (page == null) {
            return;
        }

        String className = context.getRequiredTestClass().getSimpleName();
        String methodName = context.getRequiredTestMethod().getName();
        Path dir = Path.of("target", "artifacts", className + "_" + methodName);
        try {
            Files.createDirectories(dir);
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(dir.resolve("screenshot.png"))
                    .setFullPage(true));
            Files.writeString(dir.resolve("page.html"), page.content());
        } catch (IOException e) {
            System.err.println("Failed to write failure artifacts: " + e.getMessage());
        } catch (PlaywrightException e) {
            System.err.println("Failed to capture failure artifacts: " + e.getMessage());
        }
    }
}