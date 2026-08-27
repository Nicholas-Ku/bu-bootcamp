import java.util.*;

public class ContactManager {

    public static void main(String[] args) {

        // A HashMap stores things as key -> value pairs. Here the key is the contact's name (a String)
        // and the value is the whole Contact object. The payoff is speed: looking someone up by name is instant,
        // no matter how many contacts we have, because it jumps straight to the entry instead of scanning the list.
        HashMap<String, Contact> contacts = new HashMap<>();

        // Step 4: add contacts (Girls' Generation members)
        // "put" drops a pair into the map. We add them out of alphabetical order on purpose,
        // so the sort in Step 6 has real work to do and we can see it actually sorted them.
        // These aren't real numbers; every digit is just a fun nod. The first six digits are the group's
        // debut date (08/05/07 -> 080507) and the last four are each member's birthday (MMDD).
        contacts.put("Taeyeon", new Contact("Taeyeon", "+1 080 507 0309"));
        contacts.put("Yoona", new Contact("Yoona", "+1 080 507 0530"));
        contacts.put("Jessica", new Contact("Jessica", "+1 080 507 0418"));
        contacts.put("Tiffany", new Contact("Tiffany", "+1 080 507 0801"));
        contacts.put("Sunny", new Contact("Sunny", "+1 080 507 0515"));
        contacts.put("Hyoyeon", new Contact("Hyoyeon", "+1 080 507 0922"));
        contacts.put("Yuri", new Contact("Yuri", "+1 080 507 1205"));
        contacts.put("Sooyoung", new Contact("Sooyoung", "+1 080 507 0210"));
        contacts.put("Seohyun", new Contact("Seohyun", "+1 080 507 0628"));

        // Step 5: look up a contact by name
        // get() hands back the matching Contact, or null if the name isn't a key in the map.
        Contact found = contacts.get("Taeyeon");
        // Always check for null first, otherwise calling anything on a null would crash the program.
        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            // Printing the Contact automatically uses the toString we wrote, so it comes out clean.
            System.out.println("Found: " + found);
        }

        // Test again with a name that does NOT exist, to prove the "not found" path works.
        Contact missing = contacts.get("IU");
        if (missing == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found: " + missing);
        }

        // Step 6: print the sorted list
        // A HashMap has no real order, so we pour all the values (the Contact objects) into an ArrayList,
        // which is something we CAN sort and loop through in order.
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        // Sort alphabetically by name. The (a, b) -> ... part is a rule that says
        // "compare two contacts by their names"; compareTo does the actual A-before-B comparison.
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        // A header so the output is easy to read.
        System.out.println("\nAll Contacts:");
        // Walk the now-sorted list and print each one (again using toString).
        for (Contact c : sorted) {
            System.out.println(c);
        }

        // Bonus: remove one contact from the map by name, then print the list again to confirm it's gone.
        // remove() takes the key out and hands back the removed Contact (or null if it wasn't there).
        // (Jessica left the group back in 2014, so it fits.)
        Contact removed = contacts.remove("Jessica");
        System.out.println("\nRemoved: " + removed);

        // Rebuild and re-sort the list from the now-smaller map, then print to show the removal took effect.
        ArrayList<Contact> sortedAfter = new ArrayList<>(contacts.values());
        sortedAfter.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println("\nAll Contacts:");
        for (Contact c : sortedAfter) {
            System.out.println(c);
        }
    }
}
