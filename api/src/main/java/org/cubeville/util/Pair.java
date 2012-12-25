/*
 * HawkEye Redux
 * Copyright (C) 2012 Cubeville <http://www.cubeville.org> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cubeville.util;

/**
 * Simple utility class to wrap two objects
 *
 * @param <L> Left element type
 * @param <R> Right element type
 */
public class Pair<L, R> {

	/**
	 * Left element
	 */
	L left;

	/**
	 * Right element
	 */
	R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Gets the left element of this pair
	 *
	 * @return Pair's left element
	 */
	public L getLeft() {
		return left;
	}

	/**
	 * Sets the left element of this pair
	 *
	 * @param left Object to set left to
	 */
	public void setLeft(L left) {
		this.left = left;
	}

	/**
	 * Gets the right element of this pair
	 *
	 * @return Pair's right element
	 */
	public R getRight() {
		return right;
	}

	/**
	 * Sets the right element of this pair
	 *
	 * @param right Object to set right to
	 */
	public void setRight(R right) {
		this.right = right;
	}

	@Override
	public int hashCode() {
		int l = (left == null) ? 0 : left.hashCode();
		int r = (right == null) ? 0 : right.hashCode();

		return l ^ r;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Pair<?, ?>)) return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;

		if (!other.getLeft().equals(getLeft())) return false;
		if (!other.getRight().equals(getRight())) return false;

		return true;
	}

	/**
	 * Creates a pair from two objects
	 *
	 * @param left Left object
	 * @param right Right object
	 * @return Pair of the specified objects
	 */
	public static <L, R> Pair<L, R> of(L left, R right) {
		return new Pair<L, R>(left, right);
	}

}
