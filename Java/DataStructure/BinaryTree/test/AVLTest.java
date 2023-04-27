package Java.DataStructure.BinaryTree.test;

import Java.DataStructure.BinaryTree.bbst.AVLTree;
import Java.DataStructure.BinaryTree.printer.BinaryTrees;

/**
 * @author: MerelyThis
 * @since: 2023/4/26 周三
 * @description:
 */
public class AVLTest {

    public static void testInteger() {
        Integer date[] = new Integer[]{
                75, 94, 21, 7, 93, 31, 83, 65, 43, 50, 57, 56
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < date.length; i++) {
            avl.add(date[i]);
            System.out.println("【" + date[i] + "】");
            BinaryTrees.println(avl);
            System.out.println("-----------------------------------------");
        }
    }

    public static void testRemove() {
        Integer data[] = new Integer[]{
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
        }

        for (int i = 0; i < data.length; i++) {
            avl.remove(data[i]);
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(avl);
            System.out.println("---------------------------------------");
        }


        BinaryTrees.println(avl);
    }
}
