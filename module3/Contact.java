public class Contact {

    // The two things every contact needs. private means only this class can touch them directly,
    // so nobody from the outside can reach in and scramble the fields; they have to go through the getters below.
    private String name;
    private String phone;

    // Constructor: this is the recipe for building a new Contact.
    // When we say "new Contact("Taeyeon", "+82 10 ...")" these two values get handed in here.
    public Contact(String name, String phone) {
        // "this.name" is the field on the object; "name" is the value that was passed in.
        // We use "this" to tell them apart since they share a name.
        this.name = name;
        this.phone = phone;
    }

    // Getter: hands back the name when someone asks. This is how outside code reads a private field.
    public String getName() {
        return name;
    }

    // Getter: same idea, but for the phone number.
    public String getPhone() {
        return phone;
    }

    // toString decides what a Contact looks like when we print it.
    // Without this, printing a Contact would spit out something ugly like "Contact@1b6d3586".
    // We glue the name and phone together with a " | " in the middle so it reads cleanly.
    @Override
    public String toString() {
        return name + " | " + phone;
    }
}
