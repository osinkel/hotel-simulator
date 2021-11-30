import context.Context;
import ui.context.MenuInitializer;
import ui.services.CliService;

public class Application {

    public static void main(String[] args) {

        Context appContext = Context.getInstance();
        appContext.initialize();

        MenuInitializer menuInitializer = MenuInitializer.getInstance();
        menuInitializer.initialize(appContext.getFacade());

        CliService cliService = menuInitializer.getCliService();
        cliService.start();
    }
}
