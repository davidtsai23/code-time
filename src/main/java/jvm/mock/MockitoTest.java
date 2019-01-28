package jvm.mock;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoTest extends TestCase {

    public void testMock(){
        List mockList = mock(List.class);
        //使用 mock 对象，mock 对象调用过的方法都会被记录下来
        mockList.add("one");
        mockList.clear();

        //验证，mock 对象调用了 add("one") 和 clear()
        verify(mockList).add("one");
        verify(mockList).clear();

    }

    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);
        // optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);
        // using the spy calls real methods
        spy.add("one");
        spy.add("two");
        // prints "one" - the first element of a list
        System.out.println(spy.get(0));
        // size() method was stubbed - 100 is printed
        System.out.println(spy.size());
        // optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }
}
