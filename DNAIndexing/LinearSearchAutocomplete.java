import java.util.*;

public class LinearSearchAutocomplete implements Autocomplete{
    private final List<CharSequence> terms;

    public LinearSearchAutocomplete() {
        this.terms = new ArrayList<>();
    }
    // I was here richy
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Your code here
    }

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        // TODO: Your code here
        return result;
    }
}
