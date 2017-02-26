package za.co.zapper.assessment.domain;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "MASTER".
 */
public class Master {

    private Long id;
    private String firstName;
    private String lastName;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Master() {
    }

    public Master(Long id) {
        this.id = id;
    }

    public Master(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
