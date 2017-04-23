package org.bluesquadfortexas.www;

import org.apache.streams.config.ComponentConfigurator;
import org.apache.streams.config.StreamsConfigurator;

import java.io.*;
import java.util.List;
import java.util.logging.Level;

import org.apache.juneau.microservice.*;
import org.apache.juneau.rest.*;
import org.apache.juneau.rest.annotation.*;

import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.model.UsergridUser;
import org.apache.usergrid.java.client.response.UsergridResponse;
import org.bluesquadfortexas.pojo.Campaign;
import org.bluesquadfortexas.pojo.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

@RestResource(
    path="/list",
    properties={
        @Property(name="baseURI", value="$R{requestParentURI}")
    }
)
public class CampaignsList extends Resource {

  protected UsergridClient usergrid;
  protected RestContext root;

  public void init() throws ServletException {
    root = this.getContext().getParentContext().getParentContext();
  }

  @RestMethod(name="GET")
  public List<UsergridUser> listCampaigns() throws IOException {
    UsergridResponse response = RootResources.usergrid.GET("users");
    log(Level.INFO, response.toString());
    if( response.ok() ) {
      return response.users();
    } else throw new RestException(404, "FAIL");
	}
}
