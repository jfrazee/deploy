package org.bluesquadfortexas.www;

import org.apache.juneau.microservice.Resource;
import org.apache.juneau.rest.RestContext;
import org.apache.juneau.rest.RestException;
import org.apache.juneau.rest.annotation.Property;
import org.apache.juneau.rest.annotation.RestMethod;
import org.apache.juneau.rest.annotation.RestResource;
import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.model.UsergridUser;
import org.apache.usergrid.java.client.query.UsergridQuery;
import org.apache.usergrid.java.client.response.UsergridResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;

@RestResource(
    path="/list",
    properties={
        @Property(name="baseURI", value="$R{requestParentURI}")
    }
)
public class VolunteersList extends Resource {

  protected UsergridClient usergrid;
  protected RestContext root;

  UsergridQuery query = new UsergridQuery("users");

  public void init() throws ServletException {
    root = this.getContext().getParentContext().getParentContext();
  }

  @RestMethod(name="GET")
  public List<UsergridUser> listVolunteers() throws IOException {
    UsergridResponse response = RootResources.usergrid.GET(query);
    log(Level.INFO, response.toString());
    if( response.ok() ) {
      return response.users();
    } else throw new RestException(404, "FAIL");
	}
}
