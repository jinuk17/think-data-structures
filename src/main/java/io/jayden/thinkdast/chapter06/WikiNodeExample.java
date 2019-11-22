package io.jayden.thinkdast.chapter06;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.IOException;
import java.util.*;

public class WikiNodeExample {

    /*
    * Tree Search
    *
    *   1. DFS - Depth First Search
    *
    *
    * */

    public static void main(String[] args) throws IOException {
        final String url = "http://en.wikipedia.org/wiki/Java_(programming_language)";
        final Connection connect = Jsoup.connect(url);
        final Document document = connect.get();
        final Element content = document.getElementById("mw-content-text");
        final Element p = content.select("p").get(1);

//        final WikiNodeIterable nodes = new WikiNodeIterable(p);
//
//        for(Node node: nodes) {
//            if(node instanceof TextNode){
//                System.out.print(node);
//            }
//        }

        recursiveDFS(p);

    }

    private static void recursiveDFS(final Node node) {
        if(node instanceof TextNode) {
            System.out.print(node);
        }
        for(Node child: node.childNodes()) {
            recursiveDFS(child);
        }
    }

    private static void iterativeDFS(final Node root) {
        final Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            final Node node = stack.pop();
            if(node instanceof TextNode) {
                System.out.print(node);
            }

            final List<Node> nodes = new ArrayList<>(node.childNodes());

            Collections.reverse(nodes);

            for(Node child: nodes) {
                stack.push(child);
            }

        }
    }
}
