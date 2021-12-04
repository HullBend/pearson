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

final class Swap {

    /**
     * Swap two positions.
     * 
     * @param x the array
     * @param i the index of array element
     * @param j the index of other element
     */
    static void swap(double[] x, int i, int j) {
        double a;
        a = x[i];
        x[i] = x[j];
        x[j] = a;
    }

    /**
     * Swap two positions.
     * 
     * @param x the array
     * @param i the index of array element
     * @param j the index of other element
     */
    static void swap(Object[] x, int i, int j) {
        Object a;
        a = x[i];
        x[i] = x[j];
        x[j] = a;
    }

    private Swap() {
    }
}
