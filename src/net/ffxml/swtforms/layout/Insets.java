/*
 * Copyright (c) 2003 Florian Fankhauser. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of Florian Fankhauser nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

package net.ffxml.swtforms.layout;

import java.io.Serializable;

/**
 * Defines insets of a control.
 * 
 * @author Flo
 */
public class Insets implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private int top;

	private int left;

	private int bottom;

	private int right;

	/**
	 * @param top
	 *            the top inset
	 * @param left
	 *            the left inset
	 * @param bottom
	 *            the bottom inset
	 * @param right
	 *            the right inset
	 */
	public Insets(int top, int left, int bottom, int right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	/**
	 * @param top
	 *            The top inset to set.
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * @return Returns the left inset.
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            The left inset to set.
	 */
	public void setLeft(int left) {
		this.left = left;
	}

	/**
	 * @return Returns the bottom inset.
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * @param bottom
	 *            The bottom inset to set.
	 */
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	/**
	 * @return Returns the right inset.
	 */
	public int getRight() {
		return right;
	}

	/**
	 * @param right
	 *            The right inset to set.
	 */
	public void setRight(int right) {
		this.right = right;
	}

	/**
	 * @return Returns the top inset.
	 */
	public int getTop() {
		return top;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return new Insets(top, left, bottom, right);
	}
}