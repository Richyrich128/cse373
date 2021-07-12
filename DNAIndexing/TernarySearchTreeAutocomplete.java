import java.util.*;

public class TernarySearchTreeAutocomplete implements Autocomplete {
    private Node overallRoot;

    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Your code here
        for (CharSequence term: terms) {
            overallRoot = addAllHelp(overallRoot,term, 0);
        }
    }

    private Node addAllHelp(Node root, CharSequence term, int d) {
        if (root == null) {
                root = new Node(term.charAt(d), false);
        }
        if (term.charAt(d) < root.data) {
            root.left = addAllHelp(root.left, term, d);
        } else if (term.charAt(d) > root.data) {
            root.right = addAllHelp(root.right, term, d);
        } else if (d < term.length() - 1) {
            root.mid = addAllHelp(root.mid, term, d + 1);
        } else {
            root.data = term.charAt(d);
            root.isTerm = true;
        }
        return root;
    }


    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        // TODO: Your code here
        if (prefix == null) {
            return result;
        }
        Node x = get(overallRoot, prefix, 0);
        if (x.isTerm) {
            result.add(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), result);
        return result;
    }

    private Node get(Node root, CharSequence prefix, int d) {
        if (root == null) {
            return null;
        }
        if (prefix.charAt(d) < root.data) {
            return get(root.left, prefix, d);
        } else if (prefix.charAt(d) > root.data) {
            return get(root.right, prefix, d);
        } else if (d < prefix.length() - 1) {
            return get(root.mid, prefix, d + 1);
        } else {
            return root;
        }
    }

    private void collect(Node root, StringBuilder prefix, List<CharSequence> result) {
        if (root == null) {
            return;
        }
        collect(root.left,  prefix, result);
        if (root.isTerm) {
            result.add(prefix.toString() + root.data);
        }
        collect(root.mid, prefix.append(root.data), result);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(root.right, prefix, result);
    }

    private static class Node {
        public char data;
        public boolean isTerm;
        public Node left, mid, right;

        public Node(char data, boolean isTerm) {
            this.data = data;
            this.isTerm = isTerm;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
