import java.util.ArrayList;
import java.util.Date;

// TO DO: Implement HIPAACompliantUser! (vvv "implements" keyword)
public class Physician extends User implements HIPAACompliantUser {

  // Inside class:
  private ArrayList<String> patientNotes;

  // TO DO: Constructor that takes an ID
  public Physician(Integer id) {
    super(id);
  }

  public void newPatientNotes(String notes, String patientName, Date date) {
    String report = String.format(
        "Datetime Submitted: %s \n", date);
    report += String.format("Reported By ID: %s\n", this.id);
    report += String.format("Patient Name: %s\n", patientName);
    report += String.format("Notes: %s \n", notes);
    this.patientNotes.add(report);
  }

  // TO DO: Implement HIPAACompliantUser!
  // INTERFACES are like contracts
  // All methods from interfaces are required
  @Override
  public boolean assignPin(int pin) {
    int pinLength = Integer.toString(pin).length();
    // System.out.println(pinLength);
    // pin must be exactly 4 digits,
    if (pinLength == 4) {
      return true;
    }
    // returns false if not
    return false;
  }

  @Override
  public boolean accessAuthorized(Integer confirmedAuthID) {
    // Checks the physician's id against the given id;
    // System.out.println(confirmedAuthID);
    // System.out.println(id);
    if (confirmedAuthID.equals(id)) {
      // returns true if they are a match,
      return true;
      // otherwise false.
    }
    return false;
  }

}
