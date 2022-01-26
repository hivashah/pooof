import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Rope {
    static int c=0;
    static ArrayList<RopeNode> ropes = new ArrayList<>();
//    static ArrayList<RopeNode>ropesTrie=new ArrayList<>();
    RopeNode root = new RopeNode();

    public void structRoop(String str) {

        RopeNode nptr = new RopeNode(str);
        RopeNode newRoot = new RopeNode();
        newRoot.left = root;
        newRoot.right = nptr;
        newRoot.value = newRoot.left.value;
        if (newRoot.left.right != null)
            newRoot.value += newRoot.left.right.value;
        root = newRoot;
    }

    public void add(String newString) {
        while (newString != null) {
            if (newString.contains(" ")) {
                structRoop(newString.substring(0, newString.indexOf(' ')) + '_');
                newString = newString.substring(newString.indexOf(' ') + 1, newString.length());
            } else {
                structRoop(newString.substring(0, newString.length()));
                break;
            }
        }
        ropes.add(root);
    }

    public static void status() {
        for (int i = 0; i < ropes.size(); i++) {
            printLeafNodes(ropes.get(i));
            System.out.println("\n");
        }

    }

    static void printLeafNodes(RopeNode root) {

        // If node is null, return
        if (root == null)
            return;

        // If node is leaf node, print its data


        if (root.left == null && root.right == null) {
            if (root.data != null) {  //ke null print nakone hey
                System.out.print(root.data);
                return;
            }
        }

        // If left child exists, check for leaf
        // recursively
        if (root.left != null) {
            printLeafNodes(root.left);
        }

        // If right child exists, check for leaf
        // recursively
        if (root.right != null) {
            printLeafNodes(root.right);
        }
    }

    public static int getIndexInANode(RopeNode root, int i) {
        int count = 0;
        int res = 0;
        ArrayList<Integer> saveNodeValue = new ArrayList<>();
        Stack<RopeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            RopeNode node = stack.pop();
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.isLeaf()) {
//                System.out.print(node.value);
                saveNodeValue.add(node.value);
            }
        }

        Collections.reverse(saveNodeValue);
        for (int j = 0; j < saveNodeValue.size(); j++) {
            if (count + saveNodeValue.get(j) < i) {
                count += saveNodeValue.get(j);
            } else {
                break;
            }
        }
        res = i - count;
        return res;
    }

    public static ArrayList<RopeNode> split(RopeNode root, int i, RopeNode realRoot) {
        ArrayList<RopeNode> nodes = new ArrayList<>();
        RopeNode firstStringRoot = new RopeNode();
        int positionInNode = getIndexInANode(realRoot, i);
        RopeNode temp = realRoot;
        if (positionInNode == root.data.length()) {
            if (realRoot.right == root) {
                firstStringRoot = temp.left;
                temp.left = null;
                ropes.add(firstStringRoot);
                temp.value -= firstStringRoot.value;
                ropes.remove(realRoot);
                ropes.add(realRoot);
            }
            System.out.println("akhareshe");
            while (temp.left != null) {

                if (temp.left.right == root) {
                    firstStringRoot = temp.left;
                    temp.left = null;

                    ropes.add(firstStringRoot);

                    temp.value -= firstStringRoot.value;

                    ropes.remove(realRoot);
                    ropes.add(realRoot);
                    break;
                } else {
                    temp = temp.left;
                }
            }
        } else {
            if (realRoot.right == root) {
                RopeNode ropeNodeL = new RopeNode();
                RopeNode ropeNodeR = new RopeNode();
                System.out.println(root.data + "shah");
                ropeNodeL.data = root.data.substring(0, positionInNode);
                ropeNodeL.value = root.data.substring(0, positionInNode).length();
                ropeNodeR.data = root.data.substring(positionInNode, root.data.length());
                ropeNodeR.value = root.data.substring(positionInNode, root.data.length()).length();
                firstStringRoot = temp.left;
                temp.left = null;
                temp.right = null;
                firstStringRoot = concatInMethod(firstStringRoot, ropeNodeL);

                ropes.add(firstStringRoot);
                temp.value -= firstStringRoot.value;
                ropes.remove(realRoot);
                realRoot = concatInMethod(ropeNodeR, realRoot);
                ropes.add(realRoot);

            }

            RopeNode ropeNodeL = new RopeNode();
            RopeNode ropeNodeR = new RopeNode();

            System.out.println(positionInNode + "adad");
            ropeNodeL.data = root.data.substring(0, positionInNode);
            ropeNodeL.value = root.data.substring(0, positionInNode).length();

            ropeNodeR.data = root.data.substring(positionInNode, root.data.length());
            ropeNodeR.value = root.data.substring(positionInNode, root.data.length()).length();


            while (temp.left != null) {

                if (temp.left.right == root) {
                    firstStringRoot = temp.left;
                    temp.left = null;
                    firstStringRoot.right = null;
                    firstStringRoot = concatInMethod(firstStringRoot, ropeNodeL);
                    ropes.add(firstStringRoot);

                    temp.value -= firstStringRoot.value;

                    ropes.remove(realRoot);
                    realRoot = concatInMethod(ropeNodeR, realRoot);
                    ropes.add(realRoot);
                    break;
                } else {
                    temp = temp.left;
                }
            }
        }
        nodes.add(firstStringRoot);
        nodes.add(realRoot);
        return nodes;
    }

    public static ArrayList<RopeNode> splitInMethod(RopeNode root, int i, RopeNode realRoot) {
        ArrayList<RopeNode> nodes = new ArrayList<>();
        RopeNode firstStringRoot = new RopeNode();

        int positionInNode = getIndexInANode(realRoot, i);

        RopeNode temp = realRoot;
        if (positionInNode == root.data.length()) {
            if (realRoot.right == root) {
                firstStringRoot = temp.left;
                temp.left = null;
                temp.value -= firstStringRoot.value;
            }
//            System.out.println("akhareshe");
            while (temp.left != null) {

                if (temp.left.right == root) {
                    firstStringRoot = temp.left;
                    temp.left = null;
                    temp.value -= firstStringRoot.value;
                    break;
                } else {
                    temp = temp.left;
                }
            }
        } else {
            if (realRoot.right == root) {
                RopeNode ropeNodeL = new RopeNode();
                RopeNode ropeNodeR = new RopeNode();
//                System.out.println(root.data+"shah");
                ropeNodeL.data = root.data.substring(0, positionInNode);
                ropeNodeL.value = root.data.substring(0, positionInNode).length();
                ropeNodeR.data = root.data.substring(positionInNode, root.data.length());
                ropeNodeR.value = root.data.substring(positionInNode, root.data.length()).length();

                firstStringRoot = temp.left;
                temp.left = null;
                temp.right = null;
                firstStringRoot = concatInMethod(firstStringRoot, ropeNodeL);
                temp.value -= firstStringRoot.value;
                realRoot = concatInMethod(ropeNodeR, realRoot);

            }
//            System.out.println("injam seyed");

            RopeNode ropeNodeL = new RopeNode();
            RopeNode ropeNodeR = new RopeNode();

//            System.out.println(positionInNode+"adad");
            ropeNodeL.data = root.data.substring(0, positionInNode);
            ropeNodeL.value = root.data.substring(0, positionInNode).length();

            ropeNodeR.data = root.data.substring(positionInNode, root.data.length());
            ropeNodeR.value = root.data.substring(positionInNode, root.data.length()).length();


            while (temp.left != null) {

                if (temp.left.right == root) {
                    firstStringRoot = temp.left;
                    temp.left = null;
                    firstStringRoot.right = null;
                    firstStringRoot = concatInMethod(firstStringRoot, ropeNodeL);
                    temp.value -= firstStringRoot.value;

                    realRoot = concatInMethod(ropeNodeR, realRoot);
                    break;
                } else {
                    temp = temp.left;
                }
            }
        }

//        System.out.println("++++++++++++");
//        status();
//        System.out.println("+++++++++++");
        try {
            if (c == 0) {
                ropes.remove(ropes.indexOf(realRoot));
//                ropes.remove(0);
            }
        } catch (Exception e) {
//            System.out.println("");
        }

        nodes.add(firstStringRoot);
        nodes.add(realRoot);
        return nodes;
    }

    public static void delete(int i, int j, RopeNode root, int index) {

        ArrayList<RopeNode> nodes = new ArrayList<>();
        ArrayList<RopeNode> nodes1 = new ArrayList<>();

        nodes = splitInMethod(search(root, j), j, root);
        c++;
        System.out.println(i + " :i");
        nodes1 = splitInMethod(search(nodes.get(0), i), i, nodes.get(0));
        ropes.add(index, concatInMethod(nodes1.get(0), nodes.get(1))); //vase in ke har string baade delete, biad sar jay khodesh, ta ghabl in harekat, miraft akhar arrayList.

        c--;

    }

    //    public static RopeNode concat(RopeNode ropeNode1 , RopeNode ropeNode2){
//        RopeNode newRoot = new RopeNode();
//        newRoot.value = ropeNode1.value;
//        newRoot.left = ropeNode1;
//        newRoot.right = ropeNode2;
//        int x = ropes.indexOf(ropeNode1);
//        ropes.add(x,newRoot);
//        System.out.println(ropes.get(x+1).value);
//        ropes.remove(x+1);
//        ropes.remove(ropeNode2);
//        return newRoot;
//    }
    public static RopeNode concat(RopeNode root2, RopeNode root1)//concat ro barax varedkon
    {
        RopeNode newRoot = new RopeNode();
        RopeNode tmp = root1;
        while (tmp.left != null)
            tmp = tmp.left;
        tmp.left = root2;
        root1.value += (root2.value + root2.right.value);
        ropes.remove(root2);
        return root1;
    }

    public static RopeNode concatInMethod(RopeNode ropeNode1, RopeNode ropeNode2) {
        RopeNode newRoot = new RopeNode();
        newRoot.value = ropeNode1.value + ropeNode1.right.value;
        newRoot.left = ropeNode1;
        newRoot.right = ropeNode2;
        return newRoot;
    }

    public static RopeNode search(RopeNode node, int i) { //az sefr shro mishe

        if (node.value < i && node.right != null) {
//            System.out.println("x");
            return search(node.right, i - node.value);

        }
        if (node.left != null) {
//            System.out.println("y");
            return search(node.left, i);
        }

//        System.out.println(node.data+"shih");


        return node;

    }


    public static char index(RopeNode node, int i) { // index az sefr mide
        if (node.value <= i && node.right != null) {
            return index(node.right, i - node.value);
        }
        if (node.left != null) {
            return index(node.left, i);
        }
        return node.data.charAt(i);
    }


    public static void main(String[] args) {
        Rope rope = new Rope();
        rope.add("i am hiva shahrokh");
        Rope rope1 = new Rope();
        rope1.add("i am seyfi");

        Rope rope2 = new Rope();
        rope2.add("i am mehran");

        Rope rope3 = new Rope();
        rope3.add("nakhondam mm mmm");


        status();
        RopeNode newRopeNode;

        int index = 0;
        index = 0;
        concat(ropes.get(0), ropes.get(1));
        status();
        index(ropes.get(0), 10);
//        delete(3,5,ropes.get(0),0);


//        status();
//        concat(ropes.get(0),ropes.get(1));

        status();


    }
}
