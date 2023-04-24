package Java.DataStructure.List;

/**
 * @author: MerelyThis
 * @since: 2023/4/24 周一
 * @description:
 */
public class Asserts {
    public static void test(boolean value) {
        try {
            if (!value) throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
