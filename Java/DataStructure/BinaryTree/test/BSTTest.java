package Java.DataStructure.BinaryTree.test;

import Java.DataStructure.BinaryTree.IBinaryTree;
import Java.DataStructure.BinaryTree.bst.BinarySearchTree;
import Java.DataStructure.BinaryTree.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author: MerelyThis
 * @since: 2023/4/26 周三
 * @description:
 */
public class BSTTest {

    private static void testAdd() {
        System.out.println("testAdd");
        Integer date[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
    }

    private static void testObjectAdd() {
        System.out.println("testObjectAdd");
        BinarySearchTree<Person> bst = new BinarySearchTree<>(Comparator.comparingInt(Person::getAge));
        Integer date[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        for (int i = 0; i < date.length; i++) {
            bst.add(new Person(date[i]));
        }
        BinaryTrees.println(bst);
    }

    private static void testAddSame() {
        System.out.println("testAddSame");
        BinarySearchTree<Person> bst = new BinarySearchTree<>(Comparator.comparingInt(Person::getAge));
        bst.add(new Person(15, "jack"));
        bst.add(new Person(16, "rose"));
        bst.add(new Person(10, "jerry"));
        bst.add(new Person(10, "kali")); // add()时值相等最好覆盖，否则则不会替换
        BinaryTrees.println(bst);
    }

    private static void testLevelOrder() {
        System.out.println("testLevelOrder");
        Integer date[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        Person persons[] = new Person[10];
        BinarySearchTree<Person> bst = new BinarySearchTree<>((o1, o2) -> o2.getAge() - o1.getAge());

        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person(date[i]);
            bst.add(persons[i]);
        }
        BinaryTrees.println(bst);
        System.out.print("层次遍历:");
        bst.levelOrder(new IBinaryTree.Visitor<Person>() {
            @Override
            public boolean visit(Person element) {
                System.out.print(element + " ");
                return false;
            }
        });
    }

    private static void testOrder() {
        System.out.println("testOrder");
        Integer date[] = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.print(bst);

        System.out.print("\n层次遍历：");
        bst.levelOrder(new IBinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();

        System.out.print("前序遍历：");
        bst.preorder(new IBinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
//				return element == 2 ? true : false;
                return false;
            }
        });
        System.out.println();

        System.out.print("中序遍历：");
        bst.inorder(new IBinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();

        System.out.print("后序遍历：");
        bst.postorder(null);
        bst.postorder(new IBinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
    }

    private static void testHeight() {
        System.out.println("testHeight");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 20; i++) {
            bst.add((int) (Math.random() * 100));
        }
        BinaryTrees.print(bst);
        System.out.println();
        System.out.println(bst.height());
    }

    private static void testComplete() {
        System.out.println("testComplete");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer date[] = new Integer[]{7, 4, 8, 1, 5};
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
    }

    private static void testContains() {
        System.out.println("testContains");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer date[] = new Integer[]{7, 4, 8, 1, 5};
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst.contains(6));
    }

    private static void testRemove() {
        System.out.println("testRemove");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Integer date[] = new Integer[]{7, 4, 8, 1, 5};
        for (int i = 0; i < date.length; i++) {
            bst.add(date[i]);
        }
        BinaryTrees.println(bst);
        bst.remove(8);
        BinaryTrees.println(bst);
    }
}
