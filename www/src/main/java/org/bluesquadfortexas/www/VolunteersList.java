package org.bluesquadfortexas.www;

import org.apache.streams.jackson.StreamsJacksonMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.juneau.ObjectMap;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.microservice.Resource;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.rest.RestContext;
import org.apache.juneau.rest.RestException;
import org.apache.juneau.rest.annotation.Property;
import org.apache.juneau.rest.annotation.RestMethod;
import org.apache.juneau.rest.annotation.RestResource;
import org.apache.juneau.serializer.SerializeException;
import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.model.UsergridUser;
import org.apache.usergrid.java.client.query.UsergridQuery;
import org.apache.usergrid.java.client.response.UsergridResponse;
import org.bluesquadfortexas.pojo.Volunteer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

@RestResource(
    path="/list",
    properties={
        @Property(name="baseURI", value="$R{requestParentURI}")
    }
)
public class VolunteersList extends Resource {

  protected ObjectMapper mapper;
  protected UsergridClient usergrid;
  protected RestContext root;

  UsergridQuery query = new UsergridQuery("users");

  public void init() throws ServletException {
    root = this.getContext().getParentContext().getParentContext();
    mapper = StreamsJacksonMapper.getInstance();
  }

  @RestMethod(name="GET")
  public List<UsergridUser> listVolunteers() throws IOException {
    UsergridResponse response = RootResources.usergrid.GET(query);
    log(Level.INFO, response.toString());
    if( response.ok() ) {
      //List<Volunteer> volunteerList = response.users().stream().map(x -> (Volunteer)x).collect(Collectors.toList());
      return response.users();
    } else throw new RestException(404, "FAIL");
	}

  @RestMethod(name="GET", path="admin", guards={AdminGuard.class})
  public List<Volunteer> adminVolunteers() throws IOException {
    UsergridResponse response = RootResources.usergrid.GET(query);
    log(Level.INFO, response.toString());
    if( response.ok() ) {
      List<JsonNode> volunteerJsonNodes = response.getEntities().stream().map(x -> {
        return x.toJsonObjectValue();
      }).collect(Collectors.toList());
      List<Volunteer> volunteerList = volunteerJsonNodes.stream().map(x -> {
        return mapper.convertValue(x, Volunteer.class);
      }).collect(Collectors.toList());
      return volunteerList;
    } else throw new RestException(404, "FAIL");
  }
}
