package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all generators
 * <p>
 * This is a library written on Java to resolve some combinatorics issues such
 * as generating combinatorial objects (permutations, partitions, compositions,
 * subsets, combinations and etc).
 * <p>
 * Type of the items should be specified as type parameter of generator and
 * vector.
 * <p>
 * There is a general pattern how to use the generators <blockquote>
 * 
 * <pre>
 * // create combinatorics vector
 * CombinatoricsVector&lt;T&gt; vector = new CombinatoricsVector&lt;T&gt;(array);
 * 
 * // create a concrete generator
 * Generator&lt;T&gt; generator = new&lt;Concrete&gt; Generator&lt;T&gt;(vector);
 * 
 * // create an iterator
 * Iterator&lt;CombinatoricsVector&lt;T&gt;&gt; iterator = generator.createIterator();
 * 
 * // iterate the generated objects
 * while (iterator.hasNext()) {
 * 	CombinatoricsVector&lt;T&gt; item = iterator.next();
 * 	System.out.println(item);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro Paukov
 * @see ICombinatoricsVector
 * @see Iterator
 * @param <T>
 *            Type of the elements in the generated vectors
 */
public abstract class Generator<T> {

	/**
	 * Returns core/original vector. This is a main vector which is used for
	 * generating other objects
	 * 
	 * @return The original vector which was passed into a constructor as an
	 *         initializing parameter
	 */
	public abstract ICombinatoricsVector<T> getOriginalVector();

	/**
	 * Returns the number of the the generated objects/vectors
	 * 
	 * @return Number of the generated vectors
	 */
	public abstract long getNumberOfGeneratedObjects();

	/**
	 * Creates an iterator for enumerating all generated objects/vectors
	 * 
	 * @return The iterator over the generated objects/vectors
	 */
	public abstract Iterator<ICombinatoricsVector<T>> createIterator();

	/**
	 * Returns all generated vectors as a list
	 * 
	 * @return List of all generated objects/vectors
	 */
	public List<ICombinatoricsVector<T>> generateAllObjects() {
		List<ICombinatoricsVector<T>> list = new ArrayList<ICombinatoricsVector<T>>();
		Iterator<ICombinatoricsVector<T>> iterator = createIterator();
		while (iterator.hasNext()) {
			ICombinatoricsVector<T> vector = iterator.next();
			list.add(vector);
		}
		return list;
	}

	/**
	 * Returns vectors as a list for specified range of indexes (from the
	 * <code>startIndex</code> to <code>stopIndex</code>)
	 * 
	 * @return List of the generated objects/vectors
	 */
	public List<ICombinatoricsVector<T>> generateObjectsRange(int startIndex,
			int stopIndex) {
		assert (startIndex <= stopIndex);
		List<ICombinatoricsVector<T>> list = new ArrayList<ICombinatoricsVector<T>>();
		Iterator<ICombinatoricsVector<T>> iterator = createIterator();
		int index = 1;
		while (iterator.hasNext()) {
			if (index >= startIndex && index <= stopIndex) {
				list.add(iterator.next());
			} else if (index > stopIndex) {
				return list;
			} else {
				iterator.next();
			}
			index++;
		}
		return list;
	}

}