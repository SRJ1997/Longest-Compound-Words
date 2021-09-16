import java.io.*;
import java.util.*;

public class Tester {

    private ArrayList<String> sortedlist;
    private ArrayList<String> list;
    private int minLength = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Tester test = new Tester();
        test.findLongestWord("words.txt");
    }

    public void findLongestWord(String path) {
        try {
            readFile(path);
            this.list = new ArrayList<String>(this.sortedlist);
            this.list.sort(new myComparator());
            int counter = 0;
            while (this.list.size() > 0) {
                String word = this.list.remove(0);
                this.removeFromList(word);
                HashMap<Node, Boolean> hashMap = new HashMap<Node, Boolean>();
                if (this.generateCombinations(word, 0, word.length() - 1, hashMap)) {
                    counter++;
                    if (counter == 1) {
                        System.out.println("Longest word in file is: " + word);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid File or File path entered!");
        }
    }

    public Tester() {
        sortedlist = new ArrayList<String>();
        list = new ArrayList<String>();
    }

    public void removeFromList(String str) {
        int index = binarySearch(str, this.sortedlist, 0, this.sortedlist.size() - 1);
        if (index >= 0)
            this.sortedlist.remove(index);
    }

    private void readFile(String path) throws IOException {
        minLength = Integer.MAX_VALUE;
        String word;
        Scanner fileReader = new Scanner(new FileReader(path));
        while (fileReader.hasNextLine()) {
            word = fileReader.nextLine();
            if (word.length() == 0) {
                continue;
            }
            if (word.length() < minLength)
                minLength = word.length();
            sortedlist.add(word);
        }
        fileReader.close();
    }

    private int binarySearch(String word, ArrayList<String> words, int start, int end) {
        if (start > end || word.length() < minLength)
            return -1;
        int mid = start + (end - start) / 2;
        if (words.get(mid).compareTo(word) == 0)
            return mid;
        else if (words.get(mid).compareTo(word) < 0)
            return binarySearch(word, words, mid + 1, end);
        else
            return binarySearch(word, words, start, mid - 1);
    }

    public boolean CheckIfPresent(String word) {
        return binarySearch(word, this.sortedlist, 0, this.sortedlist.size() - 1) != -1;
    }

    private boolean generateCombinations(String str, int begIndex, int lastIndex, HashMap<Node, Boolean> hashMap) {
        if (str.length() == 0)
            return true;

        if (str.length() < minLength)
            return false;

        Node n = new Node(begIndex, lastIndex);
        if (hashMap.containsKey(n))
            return hashMap.get(n);

        if (CheckIfPresent(str)) {
            hashMap.put(n, true);
            return true;
        }

        int start = 0;
        int end = str.length();
        for (int i = start; i < end - 1; i++) {
            if (CheckIfPresent(str.substring(start, i + 1)) && generateCombinations(str.substring(i + 1),
                    i + begIndex + 1, i + lastIndex, hashMap)) {
                hashMap.put(n, true);
                return true;
            }
        }

        hashMap.put(n, false);
        return false;
    }

    static class myComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return Integer.compare(str2.length(), str1.length());
        }
    }
}

class Node {
    int start;
    int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
