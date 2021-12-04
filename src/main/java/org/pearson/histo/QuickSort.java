/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pearson.histo;

final class QuickSort {

    private static final int M = 7;
    private static final int STACK_SIZE = 64;

    /**
     * Besides sorting the array x, the array y will be also rearranged as the same
     * order of x.
     * 
     * @param x the array to sort
     * @param y the associate array
     */
    static void sort(double[] x, Object[] y) {
        sort(x, y, x.length);
    }

    /**
     * This is an efficient implementation Quick Sort algorithm without recursion.
     * Besides sorting the first n elements of array x, the first n elements of
     * array y will be also rearranged as the same order of x.
     * 
     * @param x the array to sort
     * @param y the associate array
     * @param n the first n elements to sort
     */
    static void sort(double[] x, Object[] y, int n) {
        int jstack = -1;
        int l = 0;
        int[] istack = new int[STACK_SIZE];
        int ir = n - 1;

        int i, j, k;
        double a;
        Object b;
        for (;;) {
            if (ir - l < M) {
                for (j = l + 1; j <= ir; j++) {
                    a = x[j];
                    b = y[j];
                    for (i = j - 1; i >= l; i--) {
                        if (x[i] <= a) {
                            break;
                        }
                        x[i + 1] = x[i];
                        y[i + 1] = y[i];
                    }
                    x[i + 1] = a;
                    y[i + 1] = b;
                }
                if (jstack < 0) {
                    break;
                }
                ir = istack[jstack--];
                l = istack[jstack--];
            } else {
                k = (l + ir) >> 1;
                Swap.swap(x, k, l + 1);
                Swap.swap(y, k, l + 1);
                if (x[l] > x[ir]) {
                    Swap.swap(x, l, ir);
                    Swap.swap(y, l, ir);
                }
                if (x[l + 1] > x[ir]) {
                    Swap.swap(x, l + 1, ir);
                    Swap.swap(y, l + 1, ir);
                }
                if (x[l] > x[l + 1]) {
                    Swap.swap(x, l, l + 1);
                    Swap.swap(y, l, l + 1);
                }
                i = l + 1;
                j = ir;
                a = x[l + 1];
                b = y[l + 1];
                for (;;) {
                    do {
                        i++;
                    } while (x[i] < a);
                    do {
                        j--;
                    } while (x[j] > a);
                    if (j < i) {
                        break;
                    }
                    Swap.swap(x, i, j);
                    Swap.swap(y, i, j);
                }
                x[l + 1] = x[j];
                x[j] = a;
                y[l + 1] = y[j];
                y[j] = b;
                jstack += 2;

                if (jstack >= STACK_SIZE) {
                    throw new IllegalStateException("STACK_SIZE too small in sort");
                }

                if (ir - i + 1 >= j - l) {
                    istack[jstack] = ir;
                    istack[jstack - 1] = i;
                    ir = j - 1;
                } else {
                    istack[jstack] = j - 1;
                    istack[jstack - 1] = l;
                    l = i;
                }
            }
        }
    }

    private QuickSort() {
    }
}
