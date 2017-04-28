package org.bluesquadfortexas.www;

import org.apache.juneau.microservice.Resource;
import org.apache.juneau.rest.ReaderResource;
import org.apache.juneau.rest.RestRequest;
import org.apache.juneau.rest.annotation.Property;
import org.apache.juneau.rest.annotation.RestMethod;
import org.apache.juneau.rest.annotation.RestResource;
import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.auth.UsergridUserAuth;
import org.apache.usergrid.java.client.model.UsergridUser;

import java.io.IOException;

@RestResource(
    path="/profile",
    properties={
        @Property(name="baseURI", value="$R{requestParentURI}")
    }
)
public class Profile extends Resource {

  @RestMethod(name="GET", guards={LoginGuard.class})
  public ReaderResource getProfile(RestRequest req) throws IOException {
    if( Usergrid.isInitialized() == true && Usergrid.getCurrentUser() != null && Usergrid.getCurrentUser().isActivated()) {
      UsergridUser user = Usergrid.getCurrentUser();
    }
    return req.getReaderResource("Profile.html", true);
	}

  @RestMethod(name="POST", guards={LoginGuard.class})
  public ReaderResource postProfile(RestRequest req) throws IOException {
    System.out.println(req.getBodyAsString());
    return req.getReaderResource("Profile.html", true);
  }

}
