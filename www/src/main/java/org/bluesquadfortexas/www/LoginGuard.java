package org.bluesquadfortexas.www;

import org.apache.juneau.rest.RestGuard;
import org.apache.juneau.rest.RestRequest;
import org.apache.usergrid.java.client.Usergrid;

/**
 * Sample guard that only lets administrators through.
 */
public class LoginGuard extends RestGuard {

  @Override /* RestGuard */
  public boolean isRequestAllowed(RestRequest req) {
    if( Usergrid.isInitialized() == true && Usergrid.getCurrentUser() != null && Usergrid.getCurrentUser().isActivated()) {
      System.out.println(Usergrid.getCurrentUser().toPrettyString());
      return true;
    }
    else return false;
  }
}
