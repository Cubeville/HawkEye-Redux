/*
 * HawkEye
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
 * Simple utility class to wrap three objects
 *
 * @param <L> Left element type
 * @param <M> Middle element type
 * @param <R> Right element type
 */
public class Triplet<L, M, R> {

	/**
	 * Left element
	 */
	L left;

	/**
	 * Middle element
	 */
	M middle;

	/**
	 * Right element
	 */
	R right;

	public Triplet(L left, M middle, R right) {
		this.left = left;
		this.middle = middle;
		this.right = right;
	}

	/**
	 * Gets the left element of this triplet
	 *
	 * @return Triplet's left element
	 */
	public L getLeft() {
		return left;
	}

	/**
	 * Sets the left element of this triplet
	 *
	 * @param left Object to set left to
	 */
	public void setLeft(L left) {
		this.left = left;
	}

	/**
	 * Gets the middle element of this triplet
	 *
	 * @return Triplet's middle element
	 */
	public M getMiddle() {
		return middle;
	}

	/**
	 * Sets the middle element of this triplet
	 *
	 * @param middle Object to set middle to
	 */
	public void setMiddle(M middle) {
		this.middle = middle;
	}

	/**
	 * Gets the right element of this triplet
	 *
	 * @return Triplet's right element
	 */
	public R getRight() {
		return right;
	}

	/**
	 * Sets the right element of this triplet
	 *
	 * @param right Object to set right to
	 */
	public void setRight(R right) {
		this.right = right;
	}

	@Override
	public int hashCode() {
		int l = (left == null) ? 0 : left.hashCode();
		int m = (left == null) ? 0 : middle.hashCode();
		int r = (right == null) ? 0 : right.hashCode();

		return l ^ m ^ r;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Triplet<?, ?, ?>)) return false;
		Triplet<?, ?, ?> other = (Triplet<?, ?, ?>) obj;

		if (!other.getLeft().equals(getLeft())) return false;
		if (!other.getMiddle().equals(getMiddle())) return false;
		if (!other.getRight().equals(getRight())) return false;

		return true;
	}

	/**
	 * Creates a triplet from three objects
	 *
	 * @param left Left object
	 * @param middle Middle object
	 * @param right Right object
	 * @return Pair of the specified objects
	 */
	public static <L, M, R> Triplet<L, M, R> of(L left, M middle, R right) {
		return new Triplet<L, M, R>(left, middle, right);
	}
}
