package me.nlighten.backend.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * The Class AbstractTest creates war archive with all required dependencies.
 */
public class AbstractTest {

  /**
   * Creates the deployment.
   *
   * @return the web archive
   */
  @Deployment
  public static WebArchive createDeployment() {
    WebArchive war = null;
    try {
      File[] mavenDependencies = Maven.configureResolver()
          .fromFile("src/test/resources/settings-clean-test.xml").loadPomFromFile("pom.xml")
          .importCompileAndRuntimeDependencies().resolve().withoutTransitivity().asFile();
      war = ShrinkWrap.create(WebArchive.class)
          // deploying everything in out app, in future, you might be more restrictive, e.g. deploy
          // only models and EJBs.
          .addPackages(true, "me.nlighten.backend")
          // JSON has to be packed in war for NlightenConfigServiceTest
          .addAsResource(new File("src/main/java/me/nlighten/backend/config/NlightenConfig.json"),
              ArchivePaths.create("me/nlighten/backend/config/NlightenConfig.json"))
          // deploy test specific persistence xml
          .addAsResource("META-INF/persistence.xml")
          // deploy additional maven libraries
          .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsLibraries(mavenDependencies);
      System.out.println("Test archive contents: " + war.toString(true));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return war;
  }
}
