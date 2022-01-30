public class Undo {
    public static void undo() {
        String lastCommand = LineReader.stack.peek();
        String[] strings;

        strings = lastCommand.trim().split("[ ]+");
        LineReader.stack.pop();

        if (strings[0].equals("new")) {
            Rope.ropes.remove(Rope.ropes.size() - 1);
        } else if (strings[0].equals("split")) {
            int s = Integer.parseInt(strings[1]);
            Rope.concat(Rope.ropes.get(s), Rope.ropes.get(s + 1));
        } else if (strings[0].equals("concat")) {
            int length =Rope.ropes.get(Integer.parseInt(strings[1])).value;
            Rope.splitInMethod(Rope.ropes.get(Integer.parseInt(strings[1])), length);
        }
        else if (strings[0].equals("insert")){

        }
    }
    
}

