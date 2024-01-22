package src;

public class LabStack implements IStack {
    private LabStackNode topNode;
    private int size;

    public LabStack() {
        this.topNode = null;
        this.size = 1;
    }

    public int pop() {
        if (this.topNode == null || this.topNode.getNext() == null) {
            throw new IllegalStateException("Cannot pop from an empty stack!");
        }
        int retVal = this.topNode.getValue();
        this.topNode = this.topNode.getNext();
        return retVal;
    }

    public int peek() {
        if (this.topNode == null) {
            return 0;
        }
        return this.topNode.getValue();
    }

    public void push(int i) {
        this.topNode = new LabStackNode(i, this.topNode);
        this.calculateSize();
    }

    private void calculateSize() {
        int s = 0;
        LabStackNode curNode = this.topNode;
        while (curNode != null) {
            s++;
            curNode = curNode.getNext();
        }
        this.size = s;
    }

    public void clear() {
        if (this.topNode != null) {
            while (this.topNode.getNext() != null) {
                this.topNode = this.topNode.getNext();
            }
        }
        this.size = 0;
    }

    public int size() {
        return this.size;
    }
}
