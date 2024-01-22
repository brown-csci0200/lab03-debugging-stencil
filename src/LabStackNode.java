package src;

public class LabStackNode {
    private int value;
    private LabStackNode next;

    public LabStackNode(int value, LabStackNode next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return this.value;
    }

    public LabStackNode getNext() {
        return this.next;
    }
}
