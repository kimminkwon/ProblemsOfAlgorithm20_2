package Others;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    private int value;
    private List<TreeNode> child;

    public TreeNode(int value) {
        child = new ArrayList<>();
        this.value = value;
    }

    public void addChild(TreeNode child) {
        this.child.add(child);
    }

    public String getValue() {
        String str = String.valueOf(this.value);
        switch (str.length()) {
            case 1:
                str = "00" + str;
                break;
            case 2:
                str = "0" + str;
                break;
            default:
                break;
        }
        return "[" + str + "]";
    }

    public List<TreeNode> getChild() {
        return this.child;
    }

    public TreeNode getChildIndex(int index) {
        return child.get(index);
    }
}

public class ssafyProblem9 {
    private static TreeNode root;

    public static void main(String[] args) {
        makeTree();
        printTree(root);
    }

    private static void printTree(TreeNode node) {
        System.out.print(node.getValue());
        if(node.getChild().size() == 1) {
            System.out.println("-----" + node.getChildIndex(0).getValue());
            return;
        } else {
            for(int i = 0; i < node.getChild().size(); i++) {
                if(i == 0) System.out.print("--+--");
                else if(i == node.getChild().size() - 1) System.out.print("       L--");
                else System.out.print("       +--");

                printTree(node.getChildIndex(i));
            }
            System.out.println();
        }
    }

    private static void makeTree() {
        root = new TreeNode(30);
        root.addChild(new TreeNode(54));
        root.addChild(new TreeNode(2));
        root.addChild(new TreeNode(45));
        root.getChildIndex(0).addChild(new TreeNode(1));
        root.getChildIndex(2).addChild(new TreeNode(123));
    }
}
