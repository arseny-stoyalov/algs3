package com.company;


import java.util.ArrayDeque;
import java.util.Deque;

public final class SearchString {

    private static String kmpSearch(String word, String text) {

        String concat = word + "~" + text;
        int[] occurrences = prefixFunction(concat);
        StringBuilder builder = new StringBuilder();
        for (int i = word.length() + 1; i < occurrences.length; i++) {
            if (occurrences[i] == word.length())
                builder.append(i - word.length() * 2).append(" ");
        }
        return builder.toString();
    }

    private static String defaultSearch(String word, String text) {

        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < text.length() && text.substring(index).contains(word)) {
            int temp = text.indexOf(word, index);
            builder.append(temp).append(" ");
            index = temp + word.length();
        }
        return builder.toString();
    }

    public static String searchWord(String word, String text, boolean cs) {

        String w, t;
        if (cs) {
            w = word.toLowerCase();
            t = text.toLowerCase();
        } else {
            w = word;
            t = text;
        }
        long start = System.nanoTime();
        String r = kmpSearch(w, t).trim();
        long end = System.nanoTime();
        long kmp = end - start;
        start = System.nanoTime();
        defaultSearch(w, t);
        end = System.nanoTime();
        long def = end - start;
        Deque<Integer> separators = new ArrayDeque<>();

        String[] result = r.split(" ");
        for (String s : result) {
            int index = Integer.parseInt(s);
            separators.push(index);
            separators.push(index + word.length());
        }

        StringBuilder res = new StringBuilder();
        res.append("Result:\n");
        for (int i = 0; i < text.length() + 1; i++) {
            int index = !separators.isEmpty() ? separators.getLast() : -1;
            if (index == i) {
                res.append("|");
                separators.removeLast();
            }
            if (i < text.length()) res.append(text.charAt(i));
        }
        res.append("\nFound occurrences at: ").append(r);
        res.append("\nKnuth-Morris-Pratt algorithm time: ").append(kmp).append(" ns");
        res.append("\nDefault algorithm time: ").append(def).append(" ns");

        return res.toString();
    }

    private static int[] prefixFunction(String s) {

        int[] pi = new int[s.length()];
        pi[0] = 0;
        for (int i = 1; i < pi.length; i++) {
            int j = pi[i - 1];
            while ((j > 0) && (s.charAt(i) != s.charAt(j)))
                j = pi[j - 1];

            if (s.charAt(i) == s.charAt(j))
                j++;
            pi[i] = j;
        }
        return pi;
    }
}
