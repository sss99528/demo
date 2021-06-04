package com.example.demo.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * todo list中遍历移除元素导致错误分析与拆解
 *
 */
public class Demo {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        System.out.println("开始添加元素 size:" + list.size());

        for (int i = 0; i < 100; i++) {
            list.add(i + 1);
        }

        System.out.println("元素添加结束 size:" + list.size());

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 5 == 0) {

                /**
                 *  remove()->调用了fastremove 方法，修改了modCount的值
                 *
                 *  private void fastRemove(Object[] es, int i) {
                 *         ++this.modCount;
                 *         int newSize;
                 *         if ((newSize = this.size - 1) > i) {
                 *             System.arraycopy(es, i + 1, es, i, newSize - i);
                 *         }
                 *
                 *         es[this.size = newSize] = null;
                 *     }
                 *
                 *
                 *     而在iterator，next（）方法中使用的checkForComodification 方法，
                 *     会校验iterator中的modCount 和  自身期待的expectedModCount 值是否相同，这里出现不同导致的错误。
                 *     public E next() {
                 *             this.checkForComodification();
                 *             int i = this.cursor;
                 *             if (i >= ArrayList.this.size) {
                 *                 throw new NoSuchElementException();
                 *             } else {
                 *                 Object[] elementData = ArrayList.this.elementData;
                 *                 if (i >= elementData.length) {
                 *                     throw new ConcurrentModificationException();
                 *                 } else {
                 *                     this.cursor = i + 1;
                 *                     return elementData[this.lastRet = i];
                 *                 }
                 *             }
                 *         }
                 *
                 *
                 *    final void checkForComodification() {
                 *             if (ArrayList.this.modCount != this.expectedModCount) {
                 *                 throw new ConcurrentModificationException();
                 *             }
                 *         }
                 *
                 *
                 *   而 interator 中的remove方法,会在修改list中的modCount 值后，重新赋值给expectedModCount 值，因此可以解决这个问题
                 *
                 *   public void remove() {
                 *             if (this.lastRet < 0) {
                 *                 throw new IllegalStateException();
                 *             } else {
                 *                 this.checkForComodification();
                 *
                 *                 try {
                 *                     ArrayList.this.remove(this.lastRet);
                 *                     this.cursor = this.lastRet;
                 *                     this.lastRet = -1;
                 *                     this.expectedModCount = ArrayList.this.modCount;
                 *                 } catch (IndexOutOfBoundsException var2) {
                 *                     throw new ConcurrentModificationException();
                 *                 }
                 *             }
                 *         }
                 */
                iterator.remove();

                list.remove(next);

            }
        }
        System.out.println("执行结束 size:" + list.size());



    }
}
