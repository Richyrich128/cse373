import java.util.*;

public class BinarySearchAutocomplete implements Autocomplete {
    private final List<CharSequence> terms;

    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
        Collections.sort(this.terms, CharSequence::compare);
    }

    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        int index = Collections.binarySearch(this.terms, prefix, CharSequence::compare);
        if (CharSequence.compare(prefix, this.terms.get(index)) != 0) {
            index = -index + 1;
        }
        for (int i = index; i <= this.terms.size() - 1; i++) {
            CharSequence term = this.terms.get(i);
            boolean containsTerm =  compareTerms(term, prefix);
            if (containsTerm) {
                result.add(term);
            } else {
                break;
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            CharSequence term = this.terms.get(i);
            boolean containsTerm =  compareTerms(term, prefix);
            if (containsTerm) {
                result.add(term);
            } else {
                break;
            }
        }
        return result;
    }

    private boolean compareTerms(CharSequence term, CharSequence prefix) {
        if (prefix.length() <= term.length()) {
            CharSequence part = term.subSequence(0, prefix.length());
            return CharSequence.compare(prefix, part) == 0;
        }
        return false;
    }

}
