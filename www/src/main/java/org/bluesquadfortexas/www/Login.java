package org.bluesquadfortexas.www;

import java.io.*;

import org.apache.juneau.microservice.*;
import org.apache.juneau.rest.*;
import org.apache.juneau.rest.annotation.*;

import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.auth.UsergridUserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

@RestResource(
    path="/login",
    properties={
        @Property(name="baseURI", value="$R{requestParentURI}")
    }
)
public class Login extends Resource {

  @RestMethod(name="GET")
  public ReaderResource getLogin(RestRequest req) throws IOException {
		return req.getReaderResource("Login.html", true);
	}

  @RestMethod(name="POST")
  public ReaderResource postLogin(RestRequest req) throws IOException {
    String username = req.getFormDataParameter("username");
    String password = req.getFormDataParameter("password");
    UsergridUserAuth userAuth = new UsergridUserAuth(username,password);
    Usergrid.authenticateUser(userAuth); // Usergrid.currentUser is set to the authenticated user and the token is stored within that context
    return req.getReaderResource("Home.html", true);
  }

}
