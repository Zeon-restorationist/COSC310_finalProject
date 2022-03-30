import junit.framework.TestCase;
import org.testng.annotations.Test;

public class ChatBotTest extends TestCase {

    public void testTestReaction() {
        String reaction = "No way, I hate automata";
        ChatBot chatBot = new ChatBot();
        boolean sent = chatBot.testReaction(reaction);
        assertEquals(sent, false);
        reaction = "Yes, I love robots!";
        sent = chatBot.testReaction(reaction);
        assertEquals(sent, true);
    }

    public void testLoopGeneraTitle() {
    }

    public void testLoopGeneraTitleMovie() {
    }

    public void testGetConsolation() {
        String reaction = "No way, I hate automata";
        ChatBot chatBot = new ChatBot();
        String str = chatBot.getConsolation(3);
        assertEquals(str, "Ok, lets try, Searching...");
    }

    public void testGetStatement() {
    }

    public void testTestGetStatement() {
    }

    public void testAskByName() {
    }

    public void testGetPeople() {
    }
}