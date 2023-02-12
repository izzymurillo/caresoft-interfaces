import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

  // Inside class:
  private Integer employeeID;
  private String role;
  private ArrayList<String> securityIncidents;

  // TO DO: Implement a constructor that takes an ID and a role
  public AdminUser(Integer id, String role) {
    super(id);
    this.role = role;
    this.securityIncidents = new ArrayList<String>();
  }

  public void newIncident(String notes) {
    String report = String.format(
        "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n",
        new Date(), this.id, notes);
    securityIncidents.add(report);
  }

  public void authIncident() {
    String report = String.format(
        "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n",
        new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER");
    securityIncidents.add(report);
  }

  // TO DO: Implement HIPAACompliantUser!
  @Override
  public boolean assignPin(int pin) {
    int pinLength = Integer.toString(pin).length();
    // Pin must be 6 digits or more;
    if (pinLength >= 6) {
      return true;
    }
    // returns false if not
    return false;
  }

  @Override
  public boolean accessAuthorized(Integer confirmedAuthID) {
    // Compares the ids,
    if (confirmedAuthID.equals(id)) {
      // -Returns true if ids match,
      return true;
    }
    // and if they are not a match,
    // creates an incident report using the authIncident method,
    authIncident();
    // -false if not.
    return false;
  }

  // TO DO: Implement HIPAACompliantAdmin!
  @Override
  public ArrayList<String> reportSecurityIncidents() {
    // Returns a list of strings (incidents reported)
    return securityIncidents;
  }

}
