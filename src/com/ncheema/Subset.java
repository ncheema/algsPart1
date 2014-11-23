/**
 * Created by navjotcheema on 11/21/14.
 */

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
        final String[] strings = StdIn.readAllStrings();
        for (String s: strings) {
            randQueue.enqueue(s);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(randQueue.dequeue());
        }
    }
}
