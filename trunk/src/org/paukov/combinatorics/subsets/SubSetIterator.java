package org.paukov.combinatorics.subsets;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * Iterator over the all subsets
 * 
 * @author Dmytro.Paukov
 * @see ICombinatoricsVector
 * @see SubSetGenerator
 * 
 * @param <T>
 *            Type of elements of subset
 */
public class SubSetIterator<T> extends Iterator<ICombinatoricsVector<T>> {

	/**
	 * Subset generator
	 */
	protected final Generator<T> _generator;

	/**
	 * Current subset
	 */
	protected ICombinatoricsVector<T> _currentSubSet = null;

	/**
	 * Index of the current subset
	 */
	protected long _currentIndex = 0;

	/**
	 * Size of the subset
	 */
	protected final int _length;

	/**
	 * internal bit vector, representing the subset
	 */
	private int[] _bitVector = null;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            The subset generator
	 */
	public SubSetIterator(Generator<T> generator) {
		_generator = generator;
		_length = generator.getOriginalVector().getSize();
		_currentSubSet = Factory.<T>createVector();
		_bitVector = new int[_length + 2];
		init();
	}

	/**
	 * initialize the iterator
	 * 
	 * @see org.paukov.combinatorics.iterator.Iterator#first()
	 */
	private void init() {
		_currentIndex = 0;
		for (int i = 0; i <= _length + 1; i++) {
			_bitVector[i] = 0;
		}

	}

	/**
	 * Returns the current subset
	 * 
	 * @see org.paukov.combinatorics.Iterator#getCurrentItem()
	 */
	@Override
	public ICombinatoricsVector<T> getCurrentItem() {
		return Factory.createVector(_currentSubSet);
	}

	/**
	 * Returns true if iteration is done, otherwise false
	 * 
	 * @see org.paukov.combinatorics.Iterator#isDone()
	 */
	@Override
	public boolean isDone() {
		return _bitVector[_length + 1] == 1;
	}

	/**
	 * Returns the next subset if it is available
	 * 
	 * @see org.paukov.combinatorics.Iterator#next()
	 */
	@Override
	public ICombinatoricsVector<T> next() {
		_currentIndex++;
		_currentSubSet.getVector().clear();
		for (int index = 1; index <= _length; index++) {
			if (_bitVector[index] == 1) {
				T value = _generator.getOriginalVector().getValue(index - 1);
				_currentSubSet.getVector().add(value);
			}
		}
		int i = 1;
		while (_bitVector[i] == 1) {
			_bitVector[i] = 0;
			i++;
		}
		_bitVector[i] = 1;

		return getCurrentItem();
	}

	/**
	 * Convert
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubSetIterator=[#" + _currentIndex + ", " + _currentSubSet
				+ "]";
	}

}