package sol;

import src.IStack;
import src.LabStack;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LabStackTest {
    private IStack testStack;

    private void makeTestStack() {
        this.testStack = new LabStack();
    }

    @Test
    public void testLabStackConstructor() {
        this.makeTestStack();
        // TODO: finish this test
    }
}
