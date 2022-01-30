import java.io.IOException;
import java.util.Scanner;

public class LineReader {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("+")) {
            String[] strings;
            strings = command.trim().split("[ ]+");

            if (command.equals("status")) {
                Rope.status();
            } else if (command.startsWith("new")) {
                String string = command.substring(5, command.length() - 1);
                Rope rope = new Rope();
                rope.add(string);
            } else if (strings[0].equals("index")) {
                int s = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                System.out.println(Rope.index(Rope.ropes.get(s), i));
            } else if (strings[0].equals("concat")) {
                int i = Integer.parseInt(strings[1]);
                int j = Integer.parseInt(strings[2]);
                Rope.concat(Rope.ropes.get(i), Rope.ropes.get(j));
            } else if (strings[0].equals("insert")) {
                int s1 = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                int s2 = Integer.parseInt(strings[3]);
                Rope.insert(Rope.ropes.get(s1), Rope.ropes.get(s2), i);
            } else if (strings[0].equals("split")) {
                int s = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                Rope.splitInMethod(Rope.ropes.get(s),i);
            } else if (strings[0].equals("delete")){
                int s = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                int j = Integer.parseInt(strings[3]);
                Rope.delete(i,j,Rope.ropes.get(s),s);
            }
            else if (strings[0].equals("autocomplete")){
                Trie.autocomplete(strings[1]);
            }
            command = scanner.nextLine();
        }
    }
}
