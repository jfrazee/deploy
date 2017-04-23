package org.bluesquadfortexas.www;

import org.apache.streams.config.ComponentConfigurator;
import org.apache.streams.config.StreamsConfigurator;

import org.apache.juneau.microservice.ResourceGroup;
import org.apache.juneau.rest.annotation.Property;
import org.apache.juneau.rest.annotation.RestResource;

import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.UsergridEnums;
import org.apache.usergrid.java.client.auth.UsergridAppAuth;
import org.apache.usergrid.java.client.response.UsergridResponse;
import org.bluesquadfortexas.pojo.Configuration;

import java.util.logging.Level;

import javax.servlet.ServletException;

import static org.apache.juneau.html.HtmlDocSerializerContext.HTMLDOC_links;

/**
 * Root microservice page.
 */
@RestResource(
  defaultRequestHeaders={"Accept: application/json", "Content-Type: application/json"},
  defaultResponseHeaders={"Content-Type: application/json"},
  path="/",
  title="Blue Squad For Texas",
  description="Blue Squad For Texas",
  properties={
    @Property(name=HTMLDOC_links, value="{options:'$R{servletURI}?method=OPTIONS'}")
  },
   children={
     Home.class,
     Campaigns.class,
     Volunteers.class
  }
)
public class RootResources extends ResourceGroup {
  private static final long serialVersionUID = 1L;

  protected static Configuration configuration;
  protected static UsergridClient usergrid;

  public void init() throws ServletException {
    configuration = new ComponentConfigurator<>(Configuration.class).detectConfiguration(StreamsConfigurator.getConfig());
    usergrid = Usergrid.initSharedInstance(configuration.getUsergrid().getOrgId(), configuration.getUsergrid().getAppId(), configuration.getUsergrid().getBaseUrl());
    usergrid.setAuthMode(UsergridEnums.UsergridAuthMode.APP);
    usergrid.setAppAuth(new UsergridAppAuth(configuration.getUsergrid().getClientId(), configuration.getUsergrid().getClientSecret()));
    UsergridResponse authResponse = usergrid.authenticateApp();
    log(Level.INFO, authResponse.toString());
  }

}
