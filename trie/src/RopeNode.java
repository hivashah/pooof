import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
public class RopeNode {
    int value;
    String data;
    RopeNode left, right;
    public  int countSuggestions=0;

    public RopeNode(int key, String data, RopeNode left, RopeNode right) {
        this.value = key;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public RopeNode(String data) {
        this.data = data;
        left = null;
        right = null;
        value = data.length();
    }

    boolean isLeaf() {
        return left == null ? right == null : false;
    }


    public RopeNode() {
        data = null;
        left = null;
        right = null;
        value = 0;
    }


}


//serfan vase git