import java.util.Scanner;

public class LineReader {
    public static Stack stack = new Stack();
    public static void main(String[] args) {
//        Rope rope =new Rope();
//        rope.add("i am hiva shahrokh");
//        rope.splitInMethod(rope.root , 2);
//        Rope.status();
//        Rope rope1 =new Rope();
//        rope1.add("i am mehran seyfi");
//        Rope rope2 =new Rope();
//        rope2.add("This data structure");

//        RopeNode ropeNode = concat(rope.root,rope1.root);
//        int whichString = 0;
//        delete(2,9,ropes.get(0) ,whichString);
//        splitInMethod(ropes.get(0),10 );


//        Rope.insert(rope.root,rope2.root,8);
//        Rope.delete(13,20, Rope.ropes.get(0),0 );
//        Rope.status();


        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("+")) {
            String[] strings;
            strings = command.trim().split("[ ]+");
            if (command.equals("status")) {
                Rope.status();
            } else if (command.startsWith("new")) {
                stack.push(command);
                String string = command.substring(5, command.length() - 1);
                Rope rope = new Rope();
                rope.add(string);
            } else if (strings[0].equals("index")) {
                int s = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                System.out.println(Rope.index(Rope.ropes.get(s), i));
            } else if (strings[0].equals("concat")) {
                stack.push(command);
                int i = Integer.parseInt(strings[1]);
                int j = Integer.parseInt(strings[2]);
                Rope.concat(Rope.ropes.get(i), Rope.ropes.get(j));
            } else if (strings[0].equals("insert")) {
                stack.push(command);
                int s1 = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                int s2 = Integer.parseInt(strings[3]);
                Rope.insert(Rope.ropes.get(s1), Rope.ropes.get(s2), i);
            } else if (strings[0].equals("split")) {
                stack.push(command);
                int s = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                Rope.splitInMethod(Rope.ropes.get(s),i);
            } else if (strings[0].equals("delete")){
                stack.push(command);
                int s = Integer.parseInt(strings[1]);
                int i = Integer.parseInt(strings[2]);
                int j = Integer.parseInt(strings[3]);
                Rope.delete(i,j,Rope.ropes.get(s),s);
            }else if (command.equals("undo")){
                Undo.undo();
            }
            command = scanner.nextLine();
        }
    }
}
