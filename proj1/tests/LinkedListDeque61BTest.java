import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /* In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /* In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /* This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    @Test// Below, you'll write your own tests for LinkedListDeque61B.
    public void isEmptyAndSizeTesr() {
        Deque61B<Integer> real1 = new LinkedListDeque61B<>();
        real1.addFirst(1);
        Deque61B<Integer> real2 = new LinkedListDeque61B<>();

        assertThat(real1.isEmpty()).isFalse();
        assertThat(real2.isEmpty()).isTrue();

        assertThat(real1.size()).isEqualTo(1);
        assertThat(real2.size()).isEqualTo(0);
    }
    @Test
     public void getFirstAndgetLastTest(){
         Deque61B<Integer> real=new LinkedListDeque61B<>();
         real.addFirst(5);
         real.addFirst(19);
         real.addLast(20);//最终为【19，5，20】
         assertThat(real.getFirst()).isEqualTo(19);
         assertThat(real.getLast()).isEqualTo(20);

        }

    @Test
    public void getAndgetRecursiveTest()
    {
        Deque61B<Integer> real=new LinkedListDeque61B<>();
        real.addFirst(19);
        real.addLast(12);
        real.addLast(12);//结果是【9，12，12】
        assertThat(real.get(2)).isEqualTo(12);
        assertThat(real.getRecursive(2)).isEqualTo(12);

    }
    @Test
    public void removeFirstAndremoveLastTest(){
         Deque61B<Integer> real=new LinkedListDeque61B<>();
         real.addFirst(19);
         real.addLast(13);
         real.addLast(13);//[19,13,13]
        assertThat(real.removeFirst()).isEqualTo(19);
        assertThat(real.removeLast()).isEqualTo(13);
        assertThat(real.getFirst()).isEqualTo(13);//原因：这里Java本质上是将real的引用传进去了，所以修改订的是原先的LLD！

    }

    }
