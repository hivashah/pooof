import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Rope {
    public static int LEAF_LEN = 2;
    static ArrayList<RopeNode> ropes = new ArrayList<>();
    RopeNode root = new RopeNode();

    public void structRoop(String str) {

        RopeNode nptr = new RopeNode(str);
        RopeNode newRoot = new RopeNode();
        newRoot.left = root;
        newRoot.right = nptr;
        newRoot.value = newRoot.left.value ;
        if (newRoot.left.right != null)
            newRoot.value += newRoot.left.right.value;
        root = newRoot;
    }

    public void add(String newString){
        while (newString!=null){
            if (newString.contains(" ")) {
                structRoop(newString.substring(0, newString.indexOf(' '))+'_');
                newString = newString.substring(newString.indexOf(' ') + 1, newString.length());
            }else {
                structRoop(newString.substring(0, newString.length()));
                break;
            }
        }
        ropes.add(root);
    }

    public static void status(){
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
        if (root.left == null &&
                root.right == null)
        {
            System.out.print(root.data);
            return;
        }

        // If left child exists, check for leaf
        // recursively
        if (root.left != null&&root.right != null){
            printLeafNodes(root.left);}

        // If right child exists, check for leaf
        // recursively
        if (root.right != null&&root.left != null){
            printLeafNodes(root.right);}
    }

    public static int getIndexInANode(RopeNode root, int i ) {
        int count = 0;
        int res=0;
        ArrayList<Integer> saveNodeValue = new ArrayList<>();
        Stack<RopeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty() ) {
            RopeNode node = stack.pop();
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            } if (node.isLeaf()) {
//                System.out.print(node.value);
                saveNodeValue.add(node.value);
            }
        }

        Collections.reverse(saveNodeValue);
        for (int j = 0; j < saveNodeValue.size() ; j++) {
            if (count+saveNodeValue.get(j)<i) {
                count += saveNodeValue.get(j);
            }else {
                break;
            }
        }
        res = i-count ;
        return res;
    }

    public static ArrayList<RopeNode> split(RopeNode root , int i , RopeNode realRoot){
//        System.out.println(i + " i");
        ArrayList<RopeNode>nodes=new ArrayList<>();
        int positionInNode=getIndexInANode(realRoot,i);
        RopeNode  firstStringRoot=new RopeNode();
//        System.out.println(positionInNode + " positionIndex");
//        RopeNode root = search(root, i);
//        System.out.println(root.data + " root.data");

        RopeNode temp = realRoot ;
//        System.out.println(temp.right.data+"poooooooof");
//        System.out.println(temp.value);
        if (positionInNode==root.data.length()){
            if(realRoot.right==root){
                firstStringRoot = temp.left ;
                temp.left = null ;

                ropes.add(firstStringRoot);
//                    System.out.println(firstStringRoot.value + " fitstString.value");
                temp.value -= firstStringRoot.value;
//                    System.out.println(temp.value + " temp.value");
                ropes.remove(realRoot);
                ropes.add(realRoot);
            }
            System.out.println("akhareshe");
            while (temp.left != null){
//                System.out.println("akhareshe2");
                if (temp.left.right==root){
                     firstStringRoot = temp.left ;
                    temp.left = null ;

                    ropes.add(firstStringRoot);
                    nodes.add(firstStringRoot);
//                    System.out.println(firstStringRoot.value + " fitstString.value");
                    temp.value -= firstStringRoot.value;
//                    System.out.println(temp.value + " temp.value");
                    ropes.remove(realRoot);
                    nodes.add(realRoot);
                    ropes.add(realRoot);
                    break;
                }else {
                    temp = temp.left;
                }
            }
        }else {
            if(realRoot.right==root){
                RopeNode ropeNodeL = new RopeNode();
                RopeNode ropeNodeR = new RopeNode();
//            root = mainRope.left;
//            mainRope.left = ropeNodeL;
//            mainRope.right = ropeNodeR ;
                ropeNodeL.data = root.data.substring(0,positionInNode);
                ropeNodeL.value = root.data.substring(0,positionInNode).length();
//            mainRope.value = ropeNodeL.value;
                ropeNodeR.data = root.data.substring(positionInNode,root.data.length());
                ropeNodeR.value = root.data.substring(positionInNode,root.data.length()).length();
//            System.out.println(mainRope.left.data + "left"); //taghriban dorost mide
//            System.out.println(mainRope.right.data + "right"); // nemide hichi
//            System.out.println(mainRope.value);
                 firstStringRoot = temp.left ;
                temp.left = null ;
//                firstStringRoot.right=null;
                temp.right=null;
                firstStringRoot = concatInMethod(firstStringRoot,ropeNodeL);

                ropes.add(firstStringRoot);
//                    System.out.println(firstStringRoot.value + " fitstString.value");
                temp.value -= firstStringRoot.value;
//                    System.out.println(temp.value + " temp.value");
                ropes.remove(realRoot);
                realRoot = concatInMethod(ropeNodeR , realRoot);
                ropes.add(realRoot);

            }

//            RopeNode mainRope = new RopeNode();
            RopeNode ropeNodeL = new RopeNode();
            RopeNode ropeNodeR = new RopeNode();
//            root = mainRope.left;
//            mainRope.left = ropeNodeL;
//            mainRope.right = ropeNodeR ;
            ropeNodeL.data = root.data.substring(0,positionInNode);
            ropeNodeL.value = root.data.substring(0,positionInNode).length();
//            mainRope.value = ropeNodeL.value;
            ropeNodeR.data = root.data.substring(positionInNode,root.data.length());
            ropeNodeR.value = root.data.substring(positionInNode,root.data.length()).length();
//            System.out.println(mainRope.left.data + "left"); //taghriban dorost mide
//            System.out.println(mainRope.right.data + "right"); // nemide hichi
//            System.out.println(mainRope.value);

            while (temp.left != null){
//                System.out.println("akhareshe2");
                if (temp.left.right==root){
                     firstStringRoot = temp.left ;
                    temp.left = null ;
                    firstStringRoot.right=null;
                    firstStringRoot = concatInMethod(firstStringRoot,ropeNodeL);

                    ropes.add(firstStringRoot);
//                    System.out.println(firstStringRoot.value + " fitstString.value");
                    temp.value -= firstStringRoot.value;
//                    System.out.println(temp.value + " temp.value");
                    ropes.remove(realRoot);
                    realRoot = concatInMethod(ropeNodeR , realRoot);
                    ropes.add(realRoot);
                    break;
                }else {
                    temp = temp.left;
                }
            }
        }
        nodes.add(firstStringRoot);
        nodes.add(realRoot);
        return nodes;
    }


    public static RopeNode concat(RopeNode ropeNode1 , RopeNode ropeNode2){
        RopeNode newRoot = new RopeNode();
        newRoot.value = ropeNode1.value;
        newRoot.left = ropeNode1;
        newRoot.right = ropeNode2;
        int x = ropes.indexOf(ropeNode1);
        ropes.add(x,newRoot);
        System.out.println(ropes.get(x+1).value);
        ropes.remove(x+1);
        ropes.remove(ropeNode2);
        return newRoot;
    }

    public static RopeNode concatInMethod(RopeNode ropeNode1 , RopeNode ropeNode2){
        RopeNode newRoot = new RopeNode();
        newRoot.value = ropeNode1.value;
        newRoot.left = ropeNode1;
        newRoot.right = ropeNode2;
        return newRoot;
    }

    public static RopeNode search(RopeNode node,int i){

        if (node.value < i && node.right!=null){
//            System.out.println("x");
            return search(node.right, i - node.value);

        }
        if (node.left!=null) {
//            System.out.println("y");
            return search(node.left, i);
        }

        System.out.println(node.data);



        return node;

    }


    public static char index(RopeNode node,int i){
        if (node.value <= i && node.right!=null){
            return index(node.right, i - node.value);
        }
        if (node.left!=null) {
            return index(node.left, i);
        }
        return node.data.charAt(i);
    }
    public static void insert(int i,RopeNode root1,RopeNode root2){
        ArrayList<RopeNode>nodes=new ArrayList<>();
       nodes=split(search(root1,i),i,root1);
       concat(concat(nodes.get(0),root2),nodes.get(1));

    }





    public static void main(String[] args) {
        Rope rope =new Rope();
        rope.add("i am hiva shahrokh");
//        System.out.println(rope.root.right.data);

        Rope rope1 =new Rope();
        rope1.add("i am seyfi");




//        System.out.println(ropes.size());
//        System.out.println(ropes.get(0).value);
//        System.out.println(ropes.get(1).value);
//        status();

        RopeNode newRopeNode;
//        newRopeNode = concat(rope.root , rope1.root);
//        System.out.println(ropes.get(0).value + "pooooooof");


//        status();
//        System.out.println(index(rope1.root,3));
//        split(ropes.get(0),5);

//        System.out.println(getIndexInANode(ropes.get(0),13) );
//        System.out.println(search(ropes.get(0), 13).value );
//        concat(ropes.get(0),ropes.get(1));
//        split(search(ropes.get(1), 7),7  ,ropes.get(1) );
        insert(5, ropes.get(0),ropes.get(1) );
//        System.out.println(ropes.get(2).right.data);
        status();


    }
    //ba jam va printleaf lenght jaye node peyda mishe ba tafrigh jaye harf badam algorithm ye site to history mrhan
}