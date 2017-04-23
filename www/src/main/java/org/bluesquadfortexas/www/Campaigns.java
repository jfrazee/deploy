package org.bluesquadfortexas.www;

import java.io.*;

import org.apache.juneau.microservice.*;
import org.apache.juneau.rest.*;
import org.apache.juneau.rest.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

@RestResource(
    path="/campaigns",
    properties={
        @Property(name="baseURI", value="$R{requestParentURI}")
    },
    children={
        CampaignsList.class
    }
)
public class Campaigns extends Resource {

  @RestMethod(name="GET")
  public ReaderResource getHomePage(RestRequest req) throws IOException {
		return req.getReaderResource("Campaigns.html", true);
	}
}
