package ru.stqa.lesson11.tests;

import org.junit.After;
import org.junit.Before;
import ru.stqa.lesson11.app.Application;

public class BaseTest {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }

}
