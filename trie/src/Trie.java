
import java.io.*;
import java.util.*;

public class Trie {
//    public static int count = 0;
    static Queue queue=new Queue();
    public static TrieNode root;

    Trie() {
    }

    public class TrieNode {
        HashMap<Character, TrieNode> children;
        char c;
        boolean isWord;

        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
            isWord = false;
        }

        public TrieNode() {
            children = new HashMap<>();
        }
//        public void insert(String word)
//        //insert khodam
//        {
//            char ch = word.charAt(0);//getting the first char to new a trienode to traverse
//            TrieNode child = children.get(ch);
//            int i = 0;
//            for (i = 0; i < word.length(); i++) {
//                ch = word.charAt(i);
//                child = children.get(ch);
//                if (child == null) {
//                    child=new TrieNode(ch);
//                    children.put(ch, child);
//                    System.out.println(ch + "ch");
//                } else {
//                    System.out.println("falsenj");
//                }
//
//            }
//            System.out.println(word.charAt(word.length() - 1) + "bayad a");
//            child = children.get(word.charAt(word.length() - 1));
//            System.out.println(child);
//            child.isWord = true;
//        }

        public void insert(String word) {
            if (word == null || word.isEmpty())
                return;
            char firstChar = word.charAt(0);
            TrieNode child = children.get(firstChar);
            if (child == null) {
                child = new TrieNode(firstChar);
                children.put(firstChar, child);
            }
            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isWord = true;
        }
    }
        public boolean find(String prefix, boolean exact) {
            TrieNode lastNode = root;
            for (char c : prefix.toCharArray()) {
                lastNode = lastNode.children.get(c);
                if (lastNode == null)
                    return false;
            }
            return !exact || lastNode.isWord;
        }
        public boolean find(String prefix) {
            return find(prefix, false);
        }


        public void suggestHelper(TrieNode root, List<String> list, StringBuffer curr) {
            if (root.isWord) {
                list.add(curr.toString());
            }
            if (root.children == null || root.children.isEmpty())
                return;
            for (TrieNode child : root.children.values()) {
                suggestHelper(child, list, curr.append(child.c));
                curr.setLength(curr.length() - 1);

            }
        }

        public ArrayList<String> suggest(String prefix) {
            ArrayList<String> list = new ArrayList<>();
            TrieNode lastNode = root;
            StringBuffer curr = new StringBuffer();
            for (char c : prefix.toCharArray()) {
                lastNode = lastNode.children.get(c);
                if (lastNode == null)
                    return list;
                curr.append(c);
            }
            suggestHelper(lastNode, list, curr);
            return list;
        }

        //
//        public  boolean search(String word){
//            char firstChar = word.charAt(0);
//            TrieNode child = children.get(firstChar);
//            if (child == null) {
//                System.out.println("yek");
//                return false;
//            }
//
//            if (word.length() > 1){
//                System.out.println("do");
//                child.search(word.substring(1));}
//            else{
//                System.out.println("se");
//                return true;}
//            System.out.println("chaar");
//            return true;
//        }
        public Trie(List<String> words) {
            root = new TrieNode();
            for (String word : words)
                root.insert(word);

        }
//
//    public boolean find(String prefix) {
//        TrieNode current = root;
//        for (char c : prefix.toCharArray()) {
//            current = current.children.get(c);
//            if (current == null)
//                return false;
//        }
//        return  current.isWord;
//    }

        public static ArrayList<String> insertFile(String name) throws IOException {
            ArrayList<String> words = new ArrayList<>();
            File file = new File(
                    "/Users/mac/Desktop/trie/" + name);
            BufferedReader br
                    = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                System.out.println("words" + st);
                words.add(st);
            }
            return words;
        }


        public void writeFile(String name) {
            try {
                File myObj = new File(name);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        public static ArrayList<String> suggestions(String prefix, Trie trie) {
            ArrayList<String> suggestions = new ArrayList<>();
//            System.out.println(trie.suggest(prefix) + "suggest");
            suggestions = trie.suggest(prefix);

            return suggestions;
        }

        public static RopeNode findingRope(String s) {
            for (int i = 0; i < Rope.ropes.size(); i++) {
                if (Rope.ropes.get(i).right.data.equals(s)){
                    return Rope.ropes.get(i);
                }
            }
            return null;
        }
        public static void sort(ArrayList<String> suggest) {
            if(findingRope(suggest.get(0)) !=null) {
                Node node = queue.newNode(suggest.get(0), findingRope(suggest.get(0)).countSuggestions);
                if (suggest.size() > 1) {
                    for (int i = 1; i < suggest.size(); i++) {
                        node = queue.newNode(suggest.get(i), findingRope(suggest.get(i)).countSuggestions);
                    }
                }
                while (Queue.isEmpty(node) == 0) {
                    System.out.println("queueg");
                    System.out.println( Queue.peek(node));
                    node = Queue.pop(node);
                }
            }
            else{
                Node node = queue.newNode(suggest.get(0),0);
                if (suggest.size() > 1) {
                    for (int i = 1; i < suggest.size(); i++) {
                        node = queue.newNode(suggest.get(i), 0);
                    }
                }
                while (Queue.isEmpty(node) == 0) {
                    System.out.println("queue");
                    System.out.println( Queue.peek(node));
                    node = Queue.pop(node);
                }
            }

        }
        public static void main(String[] args) throws IOException {

            ArrayList<String> words = new ArrayList<>();
            Scanner sc = new Scanner(System.in);


            words = insertFile("filename.txt");
            Trie trie = new Trie(words);
            while (true) {
                System.out.println("please enter the prefix: ");
                String input = sc.next();
                if(input.equals("Q"))
                    break;
                sort(suggestions(input,trie));
//                for (int j = 0; j < 3; j++)
//                    System.out.println(suggestions(input, trie).get(j));
                System.out.println("please pick one");
                int i = sc.nextInt();
                switch (i) {
                    case 1:
                        if(findingRope(suggestions(input,trie).get(0))!=null){
                            findingRope(suggestions(input,trie).get(0)).countSuggestions++;
                            System.out.println(findingRope(suggestions(input,trie).get(0)).countSuggestions);
                        }
                       else {
                            Rope rope = new Rope();
                            rope.add(suggestions(input, trie).get(0));
                            rope.root.countSuggestions++;
//                            Rope.ropes.add(rope.root);
//                          Rope.ropesTrie.add(rope.root);
                            System.out.println(rope.root.countSuggestions);
                        }
                        break;
                    case 2:
                        if(findingRope(suggestions(input,trie).get(1))!=null){
                            findingRope(suggestions(input,trie).get(1)).countSuggestions++;
                            System.out.println(findingRope(suggestions(input,trie).get(1)).countSuggestions);

                        }
                        else {
                            Rope rope = new Rope();
                            rope.add(suggestions(input, trie).get(1));
                            rope.root.countSuggestions++;
//                            Rope.ropes.add(rope.root);
//                          Rope.ropesTrie.add(rope.root);
                            System.out.println(rope.root.countSuggestions);
                        }
                        break;
                    case 3:
                        if(findingRope(suggestions(input,trie).get(2))!=null){
                            findingRope(suggestions(input,trie).get(2)).countSuggestions++;
                            System.out.println(findingRope(suggestions(input,trie).get(2)).countSuggestions);

                        }
                        else {
                            Rope rope = new Rope();
                            rope.add(suggestions(input, trie).get(2));
                            rope.root.countSuggestions++;
//                            Rope.ropes.add(rope.root);
//                          Rope.ropesTrie.add(rope.root);
                            System.out.println(rope.root.countSuggestions);
                        }
                        break;
                }
                Rope.status();
            }
        }
    }
