package io.jayden.thinkdast.chapter06;

import org.jsoup.nodes.Node;

import java.util.*;

public class WikiNodeIterable implements Iterable<Node> {

    private final Node root;

    public WikiNodeIterable(final Node root) { this.root = root; }

    @Override
    public Iterator<Node> iterator() {
        return new WikiNodeIterator(root);
    }

    private class WikiNodeIterator implements Iterator<Node> {

        private final Deque<Node> stack;

        public WikiNodeIterator(final Node root) {
            stack = new ArrayDeque<>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }

            final Node node = stack.pop();

            List<Node> nodes = new ArrayList<>(node.childNodes());
            Collections.reverse(nodes);
            nodes.stream().forEach(stack::push);

            return node;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
