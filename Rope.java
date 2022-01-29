import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Rope {
    public static ArrayList<RopeNode> ropes = new ArrayList<>();
    public RopeNode root = new RopeNode();
    public static boolean splitNeed = true;

    public void structRope(String str) {
        RopeNode ropeNode = new RopeNode(str);
        RopeNode newRoot = new RopeNode();
        newRoot.left = root;
        newRoot.right = ropeNode;
        newRoot.value = newRoot.left.value;
        if (newRoot.left.right != null)
            newRoot.value += newRoot.left.right.value;
        root = newRoot;
    }

    public void add(String newString){
        while (newString!=null){
            if (newString.contains(" ")) {
                structRope(newString.substring(0, newString.indexOf(' '))+'_');
                newString = newString.substring(newString.indexOf(' ') + 1, newString.length());
            }else {
                structRope(newString.substring(0, newString.length()));
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

    public static void printLeafNodes(RopeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.data!=null){  //ke null print nakone hey
                System.out.print(root.data);
                return;
            }
        }
        if (root.left != null){
            printLeafNodes(root.left);}
        if (root.right != null){
            printLeafNodes(root.right);
        }
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

//    public static ArrayList<RopeNode> split(RopeNode root , int i , RopeNode realRoot){
//        ArrayList<RopeNode>nodes=new ArrayList<>();
//        RopeNode firstStringRoot=new RopeNode();
//
//        int positionInNode=getIndexInANode(realRoot,i);
//
//        RopeNode temp = realRoot ;
//        if (positionInNode==root.data.length()){
//            if(realRoot.right==root){
//                firstStringRoot = temp.left ;
//                temp.left = null ;
//                ropes.add(firstStringRoot);
//                temp.value -= firstStringRoot.value;
//                ropes.remove(realRoot);
//                ropes.add(realRoot);
//            }
//            System.out.println("akhareshe");
//            while (temp.left != null){
//
//                if (temp.left.right==root){
//                    firstStringRoot = temp.left ;
//                    temp.left = null ;
//
//                    ropes.add(firstStringRoot);
//
//                    temp.value -= firstStringRoot.value;
//
//                    ropes.remove(realRoot);
//                    ropes.add(realRoot);
//                    break;
//                }else {
//                    temp = temp.left;
//                }
//            }
//        }else {
//            if (realRoot.right == root) {
//                RopeNode ropeNodeL = new RopeNode();
//                RopeNode ropeNodeR = new RopeNode();
//                System.out.println(root.data+"shah");
//                ropeNodeL.data = root.data.substring(0, positionInNode);
//                ropeNodeL.value = root.data.substring(0, positionInNode).length();
//                ropeNodeR.data = root.data.substring(positionInNode, root.data.length());
//                ropeNodeR.value = root.data.substring(positionInNode, root.data.length()).length();
//                firstStringRoot = temp.left;
//                temp.left = null;
//                temp.right = null;
//                firstStringRoot = concatInMethod(firstStringRoot, ropeNodeL);
//
//                ropes.add(firstStringRoot);
//                temp.value -= firstStringRoot.value;
//                ropes.remove(realRoot);
//                realRoot = concatInMethod(ropeNodeR, realRoot);
//                ropes.add(realRoot);
//
//            }
//
//            RopeNode ropeNodeL = new RopeNode();
//            RopeNode ropeNodeR = new RopeNode();
//
//            System.out.println(positionInNode+"adad");
//            ropeNodeL.data = root.data.substring(0,positionInNode);
//            ropeNodeL.value = root.data.substring(0,positionInNode).length();
//
//            ropeNodeR.data = root.data.substring(positionInNode,root.data.length());
//            ropeNodeR.value = root.data.substring(positionInNode,root.data.length()).length();
//
//
//            while (temp.left != null){
//
//                if (temp.left.right==root){
//                    firstStringRoot = temp.left ;
//                    temp.left = null ;
//                    firstStringRoot.right=null;
//                    firstStringRoot = concatInMethod(firstStringRoot,ropeNodeL);
//                    ropes.add(firstStringRoot);
//
//                    temp.value -= firstStringRoot.value;
//
//                    ropes.remove(realRoot);
//                    realRoot = concatInMethod(ropeNodeR , realRoot);
//                    ropes.add(realRoot);
//                    break;
//                }else {
//                    temp = temp.left;
//                }
//            }
//        }
//        nodes.add(firstStringRoot);
//        nodes.add(realRoot);
//        return nodes;
//    }



    public static ArrayList<RopeNode> splitInMethod(RopeNode realRoot , int index){
        ArrayList<RopeNode>nodes = new ArrayList<>();
        RopeNode appropriateNode = search(realRoot , index);
        RopeNode rightSplitedRopeRoot = new RopeNode();
        RopeNode temp1 = new RopeNode();
        RopeNode rightProcessor = new RopeNode();
        RopeNode tmp = realRoot ;
        int x = ropes.indexOf(realRoot);
        ropes.remove(realRoot);
        int count = 0;
        int positionInNode = getIndexInANode(realRoot,index) ;
        boolean isTrue = false;
        temp1 = rightSplitedRopeRoot;
        while (tmp.right != search(realRoot, index)){
            if (realRoot.right.right==search(realRoot, index)){
                index = index - tmp.value;
                tmp=tmp.right;
                isTrue=true;
                rightSplitedRopeRoot.right = tmp.right;
                temp1 = rightSplitedRopeRoot;
                break;
            }
            if (index < tmp.value){
                if (rightSplitedRopeRoot.right == null) {
                    rightSplitedRopeRoot.right = tmp.right;
                    rightSplitedRopeRoot.left = new RopeNode();
                    temp1 = rightSplitedRopeRoot;
                    temp1=temp1.left;
                }
                else {
                    if (temp1.left!= null && temp1.left.right!=null) {
                        temp1.value = temp1.left.value + temp1.left.right.value;
                    }
                    temp1.right = tmp.right;
                    tmp.right=null;
                    temp1.left = new RopeNode();
                }
                if (tmp.left != null){
                    if (count==0) {
                        tmp = tmp.left;
                        realRoot = tmp;
                    }else {
                        rightProcessor=tmp ;
                        tmp.right = null;
                        rightProcessor.left.right=null;
                        rightProcessor=rightProcessor.left.left;
                        rightProcessor.left.right=null;
                        break;
                    }
                }
            }
            else {
                count++;
                index = index - tmp.value;
                if (tmp.right != null){
                    tmp = tmp.right;
                }else {
                    break;
                }

            }
        }
        RopeNode ropeNodeL = new RopeNode();
        RopeNode ropeNodeR = new RopeNode();
        if (positionInNode == appropriateNode.data.length()){
            realRoot = concatInMethod(realRoot , tmp.right);
            tmp.right = null;
        }else if(tmp.value - index==0){
            if (isTrue){
                rightSplitedRopeRoot.right = realRoot.right.right;
                tmp.right = null;
            }else {
                rightSplitedRopeRoot = concatInMethod(tmp.right, rightSplitedRopeRoot);
                tmp.right = null;
            }
        }
        else {
            ropeNodeL.data = appropriateNode.data.substring(0,positionInNode);
            ropeNodeL.value = appropriateNode.data.substring(0,positionInNode).length();
            tmp.right = ropeNodeL;
            ropeNodeR.data = appropriateNode.data.substring(positionInNode,appropriateNode.data.length());
            ropeNodeR.value = appropriateNode.data.substring(positionInNode,appropriateNode.data.length()).length();
            if (isTrue){
                rightProcessor.right = ropeNodeR;
                rightSplitedRopeRoot = rightProcessor;
            }else {
                temp1.left = ropeNodeR;
            }
        }
        nodes.add(realRoot);
        nodes.add(rightSplitedRopeRoot);

        if (splitNeed) {
            ropes.add(x, realRoot);
            ropes.add(x + 1, rightSplitedRopeRoot);
        }
        return nodes;
    }


    public static void delete(int i,int j,RopeNode root ,int whichString){
        splitNeed=false;
        ArrayList<RopeNode>nodes=new ArrayList<>();
        ArrayList<RopeNode>nodes1=new ArrayList<>();
        nodes=splitInMethod(root,j);
        nodes1=splitInMethod(nodes.get(0),i);
        ropes.add( whichString ,concatInMethod(nodes1.get(0),nodes.get(1))); //vase in ke har string baade delete, biad sar jay khodesh, ta ghabl in harekat, miraft akhar arrayList.
        splitNeed=true;
    }

    public static RopeNode concat(RopeNode ropeNode1 , RopeNode ropeNode2){
        RopeNode newRoot = new RopeNode();
        if (ropeNode1.right!=null) {
            newRoot.value = ropeNode1.value + ropeNode1.right.value;
        }
        newRoot.left = ropeNode1;
        newRoot.right = ropeNode2;
        int x = ropes.indexOf(ropeNode1);
        ropes.add(x,newRoot);
        ropes.remove(x+1);
        ropes.remove(ropeNode2);
        return newRoot;
    }

    public static RopeNode concatInMethod(RopeNode ropeNode1 , RopeNode ropeNode2){
        RopeNode newRoot = new RopeNode();
        if (ropeNode1.right!=null) {
            newRoot.value = ropeNode1.value + ropeNode1.right.value;
        }
        newRoot.left = ropeNode1;
        newRoot.right = ropeNode2;
        return newRoot;
    }

    public static RopeNode search(RopeNode node,int i){ //az sefr shro mishe

        if (node.value <= i && node.right!=null){
            return search(node.right, i - node.value);
        }
        if (node.left!=null) {
            return search(node.left, i);
        }
        return node;
    }

    public static char index(RopeNode node,int i){ // index az sefr mide
        if (node.value <= i && node.right!=null){
            return index(node.right, i - node.value);
        }
        if (node.left!=null) {
            return index(node.left, i);
        }
        return node.data.charAt(i);
    }

    public static void insert(RopeNode ropeNode1, RopeNode ropeNode2, int i){
        splitNeed=false;
        int x = ropes.indexOf(ropeNode1);
        int y = ropes.indexOf(ropeNode2);
        ArrayList<RopeNode> ropeNodes = splitInMethod(ropeNode1,i);
        ropes.add(x, concatInMethod(concatInMethod(ropeNodes.get(0),ropeNode2),ropeNodes.get(1)));
        ropes.remove(ropeNode2);//////////////////
        splitNeed=true;
    }



}