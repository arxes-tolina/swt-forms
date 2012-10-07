/*
 * Copyright (c) 2003 JGoodies Karsten Lentzsch. All Rights Reserved.
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
 *  o Neither the name of JGoodies Karsten Lentzsch nor the names of 
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

package net.ffxml.swtforms.debug;

import net.ffxml.swtforms.layout.CellConstraints;
import net.ffxml.swtforms.layout.ColumnSpec;
import net.ffxml.swtforms.layout.FormLayout;
import net.ffxml.swtforms.layout.RowSpec;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * Provides static methods that help you understand and fix layout problems when
 * using the {@link FormLayout}. Dumps information about the layout grid,
 * layout groups and cell constraints to the console.
 * <p>
 * 
 * Implicit values are mapped to concrete. For example, implicit alignments in
 * column and row specifications will be visible. And cell constraint alignments
 * that use or override the column and row defaults are visible too.
 * 
 * <pre>
 * ColumnSpec(&quot;p&quot;)   -&gt; ColumnSpec(&quot;fill:pref:0&quot;);
 * ColumnSpec(&quot;p:1&quot;) -&gt; ColumnSpec(&quot;fill:pref:1&quot;);
 * 
 * RowSpec(&quot;p&quot;)      -&gt; RowSpec(&quot;center:pref:0&quot;);
 * RowSpec(&quot;p:1&quot;)    -&gt; RowSpec(&quot;center:pref:1&quot;);
 * </pre>
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.2 $
 */
public final class FormDebugUtils {

	private FormDebugUtils() {
		// Overrides default constructor; prevents instantiation.
	}

	// Console Dump *********************************************************

	/**
	 * Dumps all layout state to the console: column and row specifications,
	 * column and row groups, grid bounds and cell constraints.
	 * 
	 * @param container
	 *            the layout container
	 */
	public static void dumpAll(Composite composite) {
		if (!(composite.getLayout() instanceof FormLayout)) {
			System.out.println("The container's layout is not a FormLayout.");
			return;
		}
		FormLayout layout = (FormLayout) composite.getLayout();
		dumpColumnSpecs(layout);
		dumpRowSpecs(layout);
		System.out.println();
		dumpColumnGroups(layout);
		dumpRowGroups(layout);
		System.out.println();
		dumpConstraints(composite);
		dumpGridBounds(composite);
	}

	/**
	 * Dumps the layout's column specifications to the console.
	 * 
	 * @param layout
	 *            the <code>FormLayout</code> to inspect
	 */
	public static void dumpColumnSpecs(FormLayout layout) {
		System.out.print("COLUMN SPECS:");
		for (int col = 1; col <= layout.getColumnCount(); col++) {
			ColumnSpec colSpec = layout.getColumnSpec(col);
			System.out.print(colSpec.toShortString());
			if (col < layout.getColumnCount())
				System.out.print(", ");
		}
		System.out.println();
	}

	/**
	 * Dumps the layout's row specifications to the console.
	 * 
	 * @param layout
	 *            the <code>FormLayout</code> to inspect
	 */
	public static void dumpRowSpecs(FormLayout layout) {
		System.out.print("ROW SPECS:   ");
		for (int row = 1; row <= layout.getRowCount(); row++) {
			RowSpec rowSpec = layout.getRowSpec(row);
			System.out.print(rowSpec.toShortString());
			if (row < layout.getRowCount())
				System.out.print(", ");
		}
		System.out.println();
	}

	/**
	 * Dumps the layout's column groups to the console.
	 * 
	 * @param layout
	 *            the <code>FormLayout</code> to inspect
	 */
	public static void dumpColumnGroups(FormLayout layout) {
		dumpGroups("COLUMN GROUPS: ", layout.getColumnGroups());
	}

	/**
	 * Dumps the layout's row groups to the console.
	 * 
	 * @param layout
	 *            the <code>FormLayout</code> to inspect
	 */
	public static void dumpRowGroups(FormLayout layout) {
		dumpGroups("ROW GROUPS:    ", layout.getRowGroups());
	}

	/**
	 * Dumps the container's grid info to the console if and only if the
	 * container's layout is a <code>FormLayout</code>.
	 * 
	 * @param composite
	 *            the composite to inspect
	 * @throws IllegalArgumentException
	 *             if the layout is not FormLayout
	 */
	public static void dumpGridBounds(Composite composite) {
		System.out.println("GRID BOUNDS");
		dumpGridBounds(getLayoutInfo(composite));
	}

	/**
	 * Dumps the grid layout info to the console.
	 * 
	 * @param layoutInfo
	 *            provides the column and row origins
	 */
	public static void dumpGridBounds(FormLayout.LayoutInfo layoutInfo) {
		System.out.print("COLUMN ORIGINS: ");
		for (int col = 0; col < layoutInfo.columnOrigins.length; col++) {
			System.out.print(layoutInfo.columnOrigins[col] + " ");
		}
		System.out.println();

		System.out.print("ROW ORIGINS:    ");
		for (int row = 0; row < layoutInfo.rowOrigins.length; row++) {
			System.out.print(layoutInfo.rowOrigins[row] + " ");
		}
		System.out.println();
	}

	/**
	 * Dumps the component constraints to the console.
	 * 
	 * @param composite
	 *            the layout composite to inspect
	 */
	public static void dumpConstraints(Composite composite) {
		System.out.println("COMPONENT CONSTRAINTS");
		if (!(composite.getLayout() instanceof FormLayout)) {
			System.out.println("The container's layout is not a FormLayout.");
			return;
		}
		FormLayout layout = (FormLayout) composite.getLayout();
		int childCount = composite.getChildren().length;
		for (int i = 0; i < childCount; i++) {
			Control child = composite.getChildren()[i];
			CellConstraints cc = layout.getConstraints(child);
			String ccString = cc == null ? "no constraints" : cc
					.toShortString(layout);
			System.out.print(ccString);
			System.out.print("; ");
			String childType = child.getClass().getName();
			System.out.print(childType);
			if (child instanceof Label) {
				Label label = (Label) child;
				System.out.print("      \"" + label.getText() + "\"");
			}

			System.out.println();
		}
		System.out.println();
	}

	// Helper Code **********************************************************

	/**
	 * Dumps the given groups to the console.
	 * 
	 * @param title
	 *            a string title for the dump
	 * @param allGroups
	 *            a two-dimensional array with all groups
	 */
	private static void dumpGroups(String title, int[][] allGroups) {
		System.out.print(title + " {");
		for (int group = 0; group < allGroups.length; group++) {
			int[] groupIndices = allGroups[group];
			System.out.print(" {");
			for (int i = 0; i < groupIndices.length; i++) {
				System.out.print(groupIndices[i]);
				if (i < groupIndices.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.print("} ");
			if (group < allGroups.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}

	/**
	 * Computes and returns the layout's grid origins.
	 * 
	 * @param composite
	 *            the layout composite to inspect
	 * @return an object that comprises the cell origins and extents
	 * @throws IllegalArgumentException
	 *             if the layout is not FormLayout
	 */
	public static FormLayout.LayoutInfo getLayoutInfo(Composite composite) {
		if (!(composite.getLayout() instanceof FormLayout)) {
			throw new IllegalArgumentException(
					"The container must use an instance of FormLayout.");
		}
		FormLayout layout = (FormLayout) composite.getLayout();
		return layout.getLayoutInfo(composite);
	}

	public static void debugLayout(Composite composite) {
		debugLayout(composite, false, null);
	}

	/**
	 * Turns on the debug mode on the given composite.
	 * 
	 * @param composite
	 *            the composite to debug
	 * @param gridColor
	 *            the color used to paint the grid
	 */
	public static void debugLayout(Composite composite, Color gridColor) {
		debugLayout(composite, false, gridColor);
	}

	/**
	 * Turns on the debug mode on the given composite.
	 * 
	 * @param composite
	 *            the composite to debug
	 * @param paintDiagonals
	 *            true to paint diagonals, false to not paint them
	 */
	public static void debugLayout(Composite composite, boolean paintDiagonals) {
		debugLayout(composite, paintDiagonals, null);
	}

	/**
	 * Turns on the debug mode on the given composite.
	 * 
	 * @param composite
	 *            the composite to debug
	 * @param paintDiagonals
	 *            true to paint diagonals, false to not paint them
	 * @param gridColor
	 *            the color used to paint the grid
	 */
	public static void debugLayout(Composite composite, boolean paintDiagonals,
			Color gridColor) {
		FormDebug debug = new FormDebug();

		if (gridColor == null) {
			gridColor = new Color(Display.getCurrent(), 255, 0, 0);
			composite.addDisposeListener(debug);
		}
		debug.color = gridColor;
		debug.paintDiagonals = paintDiagonals;
		debug.composite = composite;

		composite.addPaintListener(debug);

	}

	/**
	 * Helper class which implements the grid painter.
	 */
	static class FormDebug implements DisposeListener, PaintListener {
		Composite composite = null;
		Color color = null;
		boolean paintDiagonals = false;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
		 */
		public void paintControl(PaintEvent paintEvent) {
			if (!(composite.getLayout() instanceof FormLayout)) {
				return;
			}
			FormLayout.LayoutInfo layoutInfo = FormDebugUtils
					.getLayoutInfo(composite);
			int left = layoutInfo.getX();
			int top = layoutInfo.getY();
			int width = layoutInfo.getWidth();
			int height = layoutInfo.getHeight();

			GC gc = paintEvent.gc;
			gc.setForeground(color);

			// Paint the column bounds.
			for (int col = 0; col < layoutInfo.columnOrigins.length; col++) {
				gc.drawLine(layoutInfo.columnOrigins[col], top,
						layoutInfo.columnOrigins[col], top + height);
			}

			// Paint the row bounds.
			for (int row = 0; row < layoutInfo.rowOrigins.length; row++) {
				gc.drawLine(left, layoutInfo.rowOrigins[row], left + width,
						layoutInfo.rowOrigins[row]);
			}

			if (paintDiagonals) {
				gc.drawLine(left, top, left + width, top + height);
				gc.drawLine(left, top + height, left + width, top);
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
		 */
		public void widgetDisposed(DisposeEvent arg0) {
			color.dispose();
		}

	}

}