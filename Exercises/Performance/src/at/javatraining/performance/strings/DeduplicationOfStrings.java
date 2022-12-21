package at.javatraining.performance.strings;

public class DeduplicationOfStrings {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hel";
        s2 = (s2 + "lo").intern(); //deduplication of the string

        System.out.println(s1 == s2); // -> returns true because of deduplication

        // alternatively use the -XX:+UseStringDeduplication command line argument on the jvm

        Integer i = Integer.valueOf(12);
    }
}
