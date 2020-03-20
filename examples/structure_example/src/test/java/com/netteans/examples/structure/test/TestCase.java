package com.netteans.exampless.structure.test;

import com.netteans.examples.structure.IValue;
import com.netteans.examples.structure.bst.BinaryTree;
import com.netteans.examples.structure.bst.IntValue;
import com.netteans.examples.structure.bst.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class TestCase {

    @Test
    public void bst() {
        TreeSet<String> strings = new TreeSet<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");
        strings.add("8");
        strings.add("9");
        strings.add("10");
        strings.add("11");

        List<IValue<Integer>> comparables = new ArrayList<>();
        comparables.add(new IntValue(5));
        comparables.add(new IntValue(8));
        comparables.add(new IntValue(9));
        comparables.add(new IntValue(2));
        comparables.add(new IntValue(7));
        comparables.add(new IntValue(6));
        comparables.add(new IntValue(1));
        comparables.add(new IntValue(3));

        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < comparables.size(); i++) {
            binaryTree.insert(new Node<>(comparables.get(i)));
        }

        System.out.println("");
    }
}
