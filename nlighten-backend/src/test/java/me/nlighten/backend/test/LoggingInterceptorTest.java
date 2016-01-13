package me.nlighten.backend.test;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.nlighten.backend.test.db.services.ClassVerbosityTestBean;
import me.nlighten.backend.test.db.services.MethodVerbosityTestBean;
import me.nlighten.backend.test.db.services.TracedTestBean;


/**
 * Test suit for testing the logging interceptor
 * 
 * @author Ronald Kriek
 *
 */
@RunWith(Arquillian.class)
public class LoggingInterceptorTest {
	@Inject
	private TracedTestBean serviceMethodLoggingTestBean;
	@Inject
	private ClassVerbosityTestBean clVerbosityBean;
	@Inject
    private MethodVerbosityTestBean mVerbosityBean;
	

    @Deployment
    public static WebArchive createDeployment() {
        File[] mavenDependencies = Maven.configureResolver().fromFile("src/test/resources/settings-clean-test.xml").loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withoutTransitivity().asFile();
        WebArchive war = ShrinkWrap.create(WebArchive.class)
        // deploying everything in out app, in future, you might be more restrictive, e.g. deploy only models and EJBs.
        .addPackages(true, "me.nlighten.backend")
        // deploy test specific persistence xml
        .addAsResource( "META-INF/persistence.xml")
        // deploy additional maven libraries
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsLibraries(mavenDependencies);
        System.out.println("Test archive contents: " + war.toString(true));
        return war;
    }

	@Test
	public void testSuccess() {
		Assert.assertEquals("OK", serviceMethodLoggingTestBean.successfulMessage());
	}
	
	@Test
    public void testInterceptorArraySize() {
        Assert.assertEquals(2, serviceMethodLoggingTestBean.interceptorArraySize().size());
        Assert.assertEquals(2, clVerbosityBean.interceptorArraySize().size());
        Assert.assertEquals(2, mVerbosityBean.interceptorArraySize().size());
    }

}
