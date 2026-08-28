// --- Imports: what each line pulls in ---
import org.junit.jupiter.api.BeforeEach;           // the @BeforeEach annotation for shared setup
import org.junit.jupiter.api.Test;                 // the @Test annotation that marks a method as a test
import static org.junit.jupiter.api.Assertions.*;  // static import so we can write assertEquals / assertTrue / assertNotSame directly

// Tests for the Contact class. Unlike GradeAnalyzer (static methods), Contact is a real object
// we have to build with `new`, so we lean on @BeforeEach: it hands every test a fresh, known
// Contact so nothing can leak from one test into the next. Independent tests = trustworthy tests.
public class ContactTest {

    // Shared field. It's re-created before every single test by setUp() below, so each test
    // starts from an identical clean slate rather than reusing a leftover object.
    private Contact contact;

    @BeforeEach
    void setUp() {
        // @BeforeEach runs automatically before EACH @Test method (not once total, but every time).
        // That's what guarantees isolation. Tests that need this exact person just use `contact`;
        // tests that need someone different make their own local Contact below.
        contact = new Contact("Ada Lovelace", "+1 617 555 0101");
    }

    @Test
    void constructor_setsNameCorrectly() {
        // Whatever name we hand the constructor should come back untouched from getName().
        // assertEquals(expected, actual): the value we WANT goes first, the real result second.
        assertEquals("Ada Lovelace", contact.getName());
    }

    @Test
    void constructor_setsPhoneCorrectly() {
        // Same deal for the phone number: in through the constructor, out through getPhone().
        assertEquals("+1 617 555 0101", contact.getPhone());
    }

    @Test
    void getName_returnsExactString_notTransformed() {
        // A different person on purpose, to prove getName() hands back the exact string
        // it was given and doesn't quietly trim, uppercase, or otherwise mangle it.
        Contact c = new Contact("Grace Hopper", "555-0000");
        assertEquals("Grace Hopper", c.getName());
    }

    @Test
    void toString_containsName() {
        // assertTrue expects a boolean that should be true. String.contains(...) returns a boolean,
        // so we're asserting the printed form actually includes the name.
        Contact c = new Contact("Alan Turing", "555-0001");
        assertTrue(c.toString().contains("Alan Turing"));
    }

    @Test
    void toString_containsPhone() {
        // ...and the phone number has to show up too, otherwise printing a Contact loses info.
        // We check contains() rather than an exact string so the test doesn't break if we later
        // tweak the separator (e.g. " | " vs " - ").
        Contact c = new Contact("Alan Turing", "555-0001");
        assertTrue(c.toString().contains("555-0001"));
    }

    // --- My own extra test (not from the module) ---

    @Test
    void twoContactsWithSameName_areIndependent() {
        // Same name, different phones. Even though they share a name, they are two separate
        // objects with their own state, so one's phone number should never bleed into the other's.
        Contact a = new Contact("Katherine Johnson", "555-1111");
        Contact b = new Contact("Katherine Johnson", "555-2222");

        // assertNotSame checks they are NOT the same object in memory (different references),
        // which is the whole idea of independence, not just that they happen to look equal.
        assertNotSame(a, b);
        assertEquals("555-1111", a.getPhone()); // contact a kept its own number
        assertEquals("555-2222", b.getPhone()); // contact b kept its own number, unaffected by contact a
    }
}
