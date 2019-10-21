package guice;

import com.google.inject.AbstractModule;

import auxiliar.UsefullMethods;
import logger.ProjectLog;
import services.HomePageServices;
import steps.HomePageSteps;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(HomePageSteps.class);
        bind(HomePageServices.class);
        bind(UsefullMethods.class);
        bind(ProjectLog.class);
    }
}
