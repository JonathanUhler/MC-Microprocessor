package parser;


import java.util.List;
import java.util.ArrayList;


public class ASTNode<T> {

    private T data;
    private ASTNode<T> parent;
    private List<ASTNode<T>> children;


    public ASTNode(T data) {
        this.data = data;
        this.parent = null;
        this.children = new ArrayList<>();
    }


    public ASTNode<T> addChild(T childData) {
        if (childData == null) {
            return null;
        }

        ASTNode<T> childNode = new ASTNode<>(childData);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }


    public ASTNode<T> addChild(ASTNode<T> child) {
        if (child == null) {
            return null;
        }

        child.parent = this;
        this.children.add(child);
        return child;
    }


    public ASTNode<T> parent() {
        return this.parent;
    }


    public List<ASTNode<T>> children() {
        return this.children;
    }


    public int getDepth() {
        if (this.parent == null) {
            return 0;
        }
        return this.parent.getDepth() + 1;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int depth = this.getDepth();
        for (int i = 0; i < depth; i++) {
            str.append(" ");
        }
        str.append(this.data != null ? this.data.toString() : "null");
        str.append("\n");

        for (ASTNode<T> child : this.children) {
            str.append(child.toString());
        }

        return str.toString();
    }

}
