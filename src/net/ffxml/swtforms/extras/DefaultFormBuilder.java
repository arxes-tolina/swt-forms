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

package net.ffxml.swtforms.extras;

import java.util.ResourceBundle;

import net.ffxml.swtforms.factories.FormFactory;
import net.ffxml.swtforms.layout.ConstantSize;
import net.ffxml.swtforms.layout.FormLayout;
import net.ffxml.swtforms.layout.RowSpec;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * Provides a means to build consistent form-oriented panels quickly using the
 * {@link FormLayout}. This builder combines frequently used panel building
 * steps: add a new row, add a label, proceed to the next data column, then add
 * a component.
 * <p>
 * This builder can map resource keys to internationalized (i15d) texts when
 * creating text labels, titles and titled separators. Therefore you must
 * specify a <code>ResourceBundle</code> in the constructor. The builder
 * methods throw an <code>IllegalStateException</code> if one of the mapping
 * builder methods is invoked and no bundle has been set.
 * <p>
 * This class is not yet part of the binary Forms library; it comes with the
 * Forms distributions as an extra. <b>The API is work in progress and may
 * change without notice.</b> If you want to use this class, you may consider
 * copying it into your codebase.
 * <p>
 * <b>Example:</b>
 * 
 * <pre>
 * protected void createContents(Shell shell) {
 * 	FormLayout layout = new FormLayout(
 * 			&quot;right:max(40dlu;pref), 3dlu, 80dlu, 7dlu, &quot;
 * 					+ &quot;right:max(40dlu;pref), 3dlu, 80dlu&quot;, &quot;&quot;);
 * 	// add rows dynamically
 * 	DefaultFormBuilder builder = new DefaultFormBuilder(shell, layout);
 * 	builder.setDefaultDialogBorder();
 * 
 * 	builder.appendSeparator(&quot;Flange&quot;);
 * 
 * 	builder.append(&quot;Identifier&quot;, new Text(shell, SWT.BORDER));
 * 	builder.nextLine();
 * 
 * 	builder.append(&quot;PTI [kW]&quot;, new Text(shell, SWT.BORDER));
 * 	builder.append(&quot;Power [kW]&quot;, new Text(shell, SWT.BORDER));
 * 
 * 	builder.append(&quot;s [mm]&quot;, new Text(shell, SWT.BORDER));
 * 	builder.nextLine();
 * 
 * 	builder.appendSeparator(&quot;Diameters&quot;);
 * 
 * 	builder.append(&quot;da [mm]&quot;, new Text(shell, SWT.BORDER));
 * 	builder.append(&quot;di [mm]&quot;, new Text(shell, SWT.BORDER));
 * 
 * 	builder.append(&quot;da2 [mm]&quot;, new Text(shell, SWT.BORDER));
 * 	builder.append(&quot;di2 [mm]&quot;, new Text(shell, SWT.BORDER));
 * 
 * 	builder.append(&quot;R [mm]&quot;, new Text(shell, SWT.BORDER));
 * 	builder.append(&quot;D [mm]&quot;, new Text(shell, SWT.BORDER));
 * 
 * 	builder.appendSeparator(&quot;Criteria&quot;);
 * 
 * 	builder.append(&quot;Location&quot;, buildLocationComboBox(shell));
 * 	builder.append(&quot;k-factor&quot;, new Text(shell, SWT.BORDER));
 * 
 * 	builder.appendSeparator(&quot;Bolts&quot;);
 * 
 * 	builder.append(&quot;Material&quot;, buildMaterialComboBox(shell));
 * 	builder.nextLine();
 * 
 * 	builder.append(&quot;Numbers&quot;, new Text(shell, SWT.BORDER));
 * 	builder.nextLine();
 * 
 * 	builder.append(&quot;ds [mm]&quot;, new Text(shell, SWT.BORDER));
 * }
 * </pre>
 * 
 * @author Karsten Lentzsch
 * @see com.jgoodies.forms.builder.AbstractFormBuilder
 * @see com.jgoodies.forms.factories.FormFactory
 * @see com.jgoodies.forms.layout.FormLayout
 * @deprecated Moved to {@link net.ffxml.swtforms.builder.DefaultFormBuilder}.
 */
public final class DefaultFormBuilder extends I15dPanelBuilder {

	/**
	 * Holds the row specification that is reused to describe the constant gaps
	 * between component lines.
	 */
	private RowSpec lineGapSpec = FormFactory.LINE_GAP_ROWSPEC;

	/**
	 * Holds the row specification that describes the constant gaps between
	 * paragraphs.
	 */
	private RowSpec paragraphGapSpec = FormFactory.PARAGRAPH_GAP_ROWSPEC;

	/**
	 * Holds the offset of the leading column - often 0 or 1.
	 */
	private int leadingColumnOffset = 0;

	/**
	 * Determines wether new data rows are being grouped or not.
	 */
	private boolean rowGroupingEnabled = false;

	private Composite panel;

	// Instance Creation ****************************************************

	/**
	 * Constructs an instance of <code>DefaultFormBuilder</code> for the given
	 * panel and layout.
	 * 
	 * @param panel
	 *            the layout container
	 * @param layout
	 *            the <code>FormLayout</code> to be used
	 */
	public DefaultFormBuilder(Composite panel, FormLayout layout) {
		this(panel, layout, null);
		this.panel = panel;
	}

	/**
	 * Constructs an instance of <code>DefaultFormBuilder</code> for the given
	 * panel, layout and resource bundle.
	 * 
	 * @param panel
	 *            the layout container
	 * @param layout
	 *            the <code>FormLayout</code> to be used
	 * @param bundle
	 *            the <code>ResourceBundle</code> used to lookup i15d strings
	 */
	public DefaultFormBuilder(Composite panel, FormLayout layout,
			ResourceBundle bundle) {
		super(panel, layout, bundle);
	}

	// Settings Gap Sizes ***************************************************

	/**
	 * Sets the size of gaps between component lines using the given constant
	 * size.
	 * 
	 * @param lineGapSize
	 *            the <coide>ConstantSize</code> that describes the size of the
	 *            gaps between component lines
	 */
	public void setLineGapSize(ConstantSize lineGapSize) {
		RowSpec rowSpec = FormFactory.createGapRowSpec(lineGapSize);
		this.lineGapSpec = rowSpec;
	}

	/**
	 * Returns the row specification that is used to separate component lines.
	 * 
	 * @return the <code>RowSpec</code> that is used to separate lines
	 */
	public RowSpec getLineGapSpec() {
		return lineGapSpec;
	}

	/**
	 * Sets the size of gaps between paragraphs using the given constant size.
	 * 
	 * @param paragraphGapSize
	 *            the <coide>ConstantSize</code> that describes the size of the
	 *            gaps between paragraphs
	 */
	public void setParagraphGapSize(ConstantSize paragraphGapSize) {
		RowSpec rowSpec = FormFactory.createGapRowSpec(paragraphGapSize);
		this.paragraphGapSpec = rowSpec;
	}

	/**
	 * Returns the offset of the leading column, often 0 or 1.
	 * 
	 * @return the offset of the leading column
	 */
	public int getLeadingColumnOffset() {
		return leadingColumnOffset;
	}

	/**
	 * Sets the offset of the leading column, often 0 or 1.
	 * 
	 * @param columnOffset
	 *            the new offset of the leading column
	 */
	public void setLeadingColumnOffset(int columnOffset) {
		this.leadingColumnOffset = columnOffset;
	}

	/**
	 * Returns whether new data rows are being grouped or not.
	 * 
	 * @return true indicates grouping enabled, false disabled
	 */
	public boolean isRowGroupingEnabled() {
		return rowGroupingEnabled;
	}

	/**
	 * Enables or disables the grouping of new data rows.
	 * 
	 * @param enabled
	 *            indicates grouping enabled, false disabled
	 */
	public void setRowGroupingEnabled(boolean enabled) {
		rowGroupingEnabled = enabled;
	}

	// Filling Columns ******************************************************

	/**
	 * Adds a component to the panel using the default constraints. Proceeds to
	 * the next data column.
	 * 
	 * @param component
	 *            the component to add
	 */
	public void append(Control component) {
		append(component, 1);
	}

	/**
	 * Adds a component to the panel using the default constraints with the
	 * given columnSpan. Proceeds to the next data column.
	 * 
	 * @param component
	 *            the component to append
	 * @param columnSpan
	 *            the column span used to add
	 */
	public void append(Control component, int columnSpan) {
		ensureCursorColumnInGrid();
		ensureHasGapRow(lineGapSpec);
		ensureHasComponentLine();

		setColumnSpan(columnSpan);
		add(component);
		setColumnSpan(1);
		nextColumn(columnSpan + 1);
	}

	/**
	 * Adds two components to the panel; each component will span a single data
	 * column. Proceeds to the next data column.
	 * 
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 */
	public void append(Control c1, Control c2) {
		append(c1);
		append(c2);
	}

	/**
	 * Adds three components to the panel; each component will span a single
	 * data column. Proceeds to the next data column.
	 * 
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param c3
	 *            the third component to add
	 */
	public void append(Control c1, Control c2, Control c3) {
		append(c1);
		append(c2);
		append(c3);
	}

	// Appending Labels with optional components ------------------------------

	/**
	 * Adds a text label to the panel and proceeds to the next column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @return the added label
	 */
	public Label append(String textWithMnemonic) {
		Label label = getComponentFactory()
				.createLabel(panel, textWithMnemonic);
		append(label);
		return label;
	}

	/**
	 * Adds a text label and component to the panel. Then proceeds to the next
	 * data column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @param component
	 *            the component to add
	 * @return the added label
	 */
	public Label append(String textWithMnemonic, Control component) {
		return append(textWithMnemonic, component, 1);
	}

	/**
	 * Adds a text label and component to the panel; the component will span the
	 * specified number columns. Proceeds to the next data column.
	 * <p>
	 * The created label is labelling the given component; so the component gets
	 * the focus if the (optional) label mnemonic is pressed.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @param c
	 *            the component to add
	 * @param columnSpan
	 *            number of columns the component shall span
	 * @return the added label
	 * @see Label#setLabelFor
	 */
	public Label append(String textWithMnemonic, Control c, int columnSpan) {
		Label label = append(textWithMnemonic);
		// hmm... label.setLabelFor(c);
		append(c, columnSpan);
		return label;
	}

	/**
	 * Adds a text label and two components to the panel; each component will
	 * span a single column. Proceeds to the next data column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @return the added label
	 */
	public Label append(String textWithMnemonic, Control c1, Control c2) {
		Label label = append(textWithMnemonic, c1);
		append(c2);
		return label;
	}

	/**
	 * Adds a text label and two components to the panel; each component will
	 * span a single column. Proceeds to the next data column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param colSpan
	 *            the column span for the second component
	 */
	public void append(String textWithMnemonic, Control c1, Control c2,
			int colSpan) {
		append(textWithMnemonic, c1);
		append(c2, colSpan);
	}

	/**
	 * Adds a text label and three components to the panel; each component will
	 * span a single column. Proceeds to the next data column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param c3
	 *            the third component to add
	 * @return the added label
	 */
	public Label append(String textWithMnemonic, Control c1, Control c2,
			Control c3) {
		Label label = append(textWithMnemonic, c1, c2);
		append(c3);
		return label;
	}

	/**
	 * Adds a text label and four components to the panel; each component will
	 * span a single column. Proceeds to the next data column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param c3
	 *            the third component to add
	 * @param c4
	 *            the fourth component to add
	 * @return the added label
	 */
	public Label append(String textWithMnemonic, Control c1, Control c2,
			Control c3, Control c4) {
		Label label = append(textWithMnemonic, c1, c2, c3);
		append(c4);
		return label;
	}

	// Appending internationalized labels with optional components ------------

	/**
	 * Adds an internationalized (i15d) text label to the panel using the given
	 * resource key and proceeds to the next column.
	 * 
	 * @param resourceKey
	 *            the resource key for the the label's text
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey) {
		return append(getI15dString(resourceKey));
	}

	/**
	 * Adds an internationalized (i15d) text label to the panel using the given
	 * resource key; then proceeds to the next data column and adds a component
	 * with the given column span. Proceeds to the next data column.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param c
	 *            the component to add
	 * @param columnSpan
	 *            number of columns the component shall span
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control c, int columnSpan) {
		Label label = appendI15d(resourceKey);
		append(c, columnSpan);
		return label;
	}

	/**
	 * Adds an internationalized (i15d) text label and component to the panel.
	 * Then proceeds to the next data column.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param component
	 *            the component to add
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control component) {
		return appendI15d(resourceKey, component, 1);
	}

	/**
	 * Adds an internationalized (i15d) text label and component to the panel.
	 * Then proceeds to the next data column. Goes to the next line if the
	 * boolean flag is set.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param component
	 *            the component to add
	 * @param nextLine
	 *            true forces a next line
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control component,
			boolean nextLine) {
		Label label = appendI15d(resourceKey, component, 1);
		if (nextLine) {
			nextLine();
		}
		return label;
	}

	/**
	 * Adds an internationalized (i15d) text label and two components to the
	 * panel; each component will span a single column. Proceeds to the next
	 * data column.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control c1, Control c2) {
		Label label = appendI15d(resourceKey, c1);
		append(c2);
		return label;
	}

	/**
	 * Adds an internationalized (i15d) text label and two components to the
	 * panel; each component will span a single column. Proceeds to the next
	 * data column.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param colSpan
	 *            the column span for the second component
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control c1, Control c2,
			int colSpan) {
		Label label = appendI15d(resourceKey, c1);
		append(c2, colSpan);
		return label;
	}

	/**
	 * Adds an internationalized (i15d) text label and three components to the
	 * panel; each component will span a single column. Proceeds to the next
	 * data column.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param c3
	 *            the third component to add
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control c1, Control c2,
			Control c3) {
		Label label = appendI15d(resourceKey, c1, c2);
		append(c3);
		return label;
	}

	/**
	 * Adds an internationalized (i15d) text label and four components to the
	 * panel; each component will span a single column. Proceeds to the next
	 * data column.
	 * 
	 * @param resourceKey
	 *            the resource key for the text to add
	 * @param c1
	 *            the first component to add
	 * @param c2
	 *            the second component to add
	 * @param c3
	 *            the third component to add
	 * @param c4
	 *            the third component to add
	 * @return the added label
	 */
	public Label appendI15d(String resourceKey, Control c1, Control c2,
			Control c3, Control c4) {
		Label label = appendI15d(resourceKey, c1, c2, c3);
		append(c4);
		return label;
	}

	// Adding Titles ----------------------------------------------------------

	/**
	 * Adds a title label to the panel and proceeds to the next column.
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may mark a mnemonic
	 * @return the added title label
	 */
	public Label appendTitle(String textWithMnemonic) {
		Label titleLabel = getComponentFactory().createTitle(panel,
				textWithMnemonic);
		append(titleLabel);
		return titleLabel;
	}

	/**
	 * Adds an internationalized title label to the panel and proceeds to the
	 * next column.
	 * 
	 * @param resourceKey
	 *            the resource key for the title's text
	 * @return the added title label
	 */
	public Label appendI15dTitle(String resourceKey) {
		return appendTitle(getI15dString(resourceKey));
	}

	// Appending Separators ---------------------------------------------------

	/**
	 * Adds a separator without text that spans all columns.
	 * 
	 * @return the added titled separator
	 */
	public Control appendSeparator() {
		return appendSeparator("");
	}

	/**
	 * Adds a separator with the given text that spans all columns.
	 * 
	 * @param text
	 *            the separator title text
	 * @return the added titled separator
	 */
	public Control appendSeparator(String text) {
		ensureCursorColumnInGrid();
		ensureHasGapRow(paragraphGapSpec);
		ensureHasComponentLine();

		setColumn(super.getLeadingColumn());
		int columnSpan = getColumnCount();
		setColumnSpan(getColumnCount());
		Control titledSeparator = addSeparator(text);
		setColumnSpan(1);
		nextColumn(columnSpan);
		return titledSeparator;
	}

	/**
	 * Appends an internationalized titled separator for the given resource key
	 * that spans all columns.
	 * 
	 * @param resourceKey
	 *            the resource key for the separator title's text
	 */
	public void appendI15dSeparator(String resourceKey) {
		appendSeparator(getI15dString(resourceKey));
	}

	// Overriding Superclass Behavior ***************************************

	/**
	 * Returns the leading column. Unlike the superclass we take a column offset
	 * into account.
	 * 
	 * @return the leading column
	 */
	protected int getLeadingColumn() {
		int column = super.getLeadingColumn();
		return column + getLeadingColumnOffset() * getColumnIncrementSign();
	}

	// Adding Rows **********************************************************

	/**
	 * Ensures that the cursor is in the grid. In case it's beyond the form's
	 * right hand side, the cursor is moved to the leading column of the next
	 * line.
	 */
	private void ensureCursorColumnInGrid() {
		if (getColumn() > getColumnCount()) {
			nextLine();
		}
	}

	/**
	 * Ensures that we have a gap row before the next component row. Checks if
	 * the current row is the given <code>RowSpec</code> and appends this row
	 * spec if necessary.
	 * 
	 * @param gapRowSpec
	 *            the row specification to check for
	 */
	private void ensureHasGapRow(RowSpec gapRowSpec) {
		if ((getRow() == 1) || (getRow() <= getRowCount()))
			return;

		if (getRow() <= getRowCount()) {
			RowSpec rowSpec = getCursorRowSpec();
			if ((rowSpec == gapRowSpec))
				return;
		}
		appendRow(gapRowSpec);
		nextLine();
	}

	/**
	 * Ensures that the form has a component row. Adds a component row if the
	 * cursor is beyond the form's bottom.
	 */
	private void ensureHasComponentLine() {
		if (getRow() <= getRowCount())
			return;
		appendRow(FormFactory.PREF_ROWSPEC);
		if (isRowGroupingEnabled()) {
			getLayout().addGroupedRow(getRow());
		}
	}

	/**
	 * Looks up and answers the row specification of the current row.
	 * 
	 * @return the row specification of the current row
	 */
	private RowSpec getCursorRowSpec() {
		return getLayout().getRowSpec(getRow());
	}

}
