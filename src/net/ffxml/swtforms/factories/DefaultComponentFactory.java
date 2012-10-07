/*
 * Copyright (c) 2002-2006 JGoodies Karsten Lentzsch. All Rights Reserved.
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

package net.ffxml.swtforms.factories;

import javax.swing.SwingConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * A singleton implementaton of the {@link ComponentFactory} interface that
 * creates UI components as required by the
 * {@link com.jgoodies.forms.builder.PanelBuilder}.
 * 
 * The texts used in methods <code>#createLabel(String)</code> and
 * <code>#createTitle(String)</code> can contain an optional mnemonic marker.
 * The mnemonic and mnemonic index are indicated by a single ampersand (<tt>&amp;</tt>).
 * For example <tt>&quot;&amp;Save&quot</tt>, or
 * <tt>&quot;Save&nbsp;&amp;as&quot</tt>. To use the ampersand itself
 * duplicate it, for example <tt>&quot;Look&amp;&amp;Feel&quot</tt>.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.2 $
 */

public final class DefaultComponentFactory implements ComponentFactory {

	/**
	 * Holds the single instance of this class.
	 */
	private static final DefaultComponentFactory INSTANCE = new DefaultComponentFactory();

	// Instance *************************************************************

	private DefaultComponentFactory() {
		// Suppresses default constructor, ensuring non-instantiability.
	}

	/**
	 * Returns the sole instance of this factory class.
	 * 
	 * @return the sole instance of this factory class
	 */
	public static DefaultComponentFactory getInstance() {
		return INSTANCE;
	}

	// Component Creation ***************************************************

	/**
	 * Creates and returns a label with an optional mnemonic.
	 * <p>
	 * 
	 * <pre>
	 * createLabel(&quot;Name&quot;); // No mnemonic
	 * createLabel(&quot;N&amp;ame&quot;); // Mnemonic is 'a'
	 * createLabel(&quot;Save &amp;as&quot;); // Mnemonic is the second 'a'
	 * createLabel(&quot;Look&amp;&amp;Feel&quot;); // No mnemonic, text is Look&amp;Feel
	 * </pre>
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may contain an ampersand (<tt>&amp;</tt>)
	 *            to mark a mnemonic
	 * @return an label with optional mnemonic
	 */
	public Label createLabel(Composite parent, String textWithMnemonic) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(textWithMnemonic);
		return label;
	}

	/**
	 * Creates and returns a title label that uses the foreground color and font
	 * of a <code>TitledBorder</code>.
	 * <p>
	 * 
	 * <pre>
	 * createTitle(&quot;Name&quot;); // No mnemonic
	 * createTitle(&quot;N&amp;ame&quot;); // Mnemonic is 'a'
	 * createTitle(&quot;Save &amp;as&quot;); // Mnemonic is the second 'a'
	 * createTitle(&quot;Look&amp;&amp;Feel&quot;); // No mnemonic, text is Look&amp;Feel
	 * </pre>
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may contain an ampersand (<tt>&amp;</tt>)
	 *            to mark a mnemonic
	 * @return an emphasized title label
	 */
	public Label createTitle(Composite parent, String textWithMnemonic) {
		return createTitle(parent, textWithMnemonic, 0);
	}

	/**
	 * Creates and answers a label that uses the foreground color and font of a
	 * <code>TitledBorder</code>.
	 * 
	 * @param textWithMnemonic
	 *            the title's text - may contain a mnemonic
	 * @param gap
	 *            the right-hand side gap
	 * @return an emphasized title label
	 */
	private Label createTitle(Composite parent, String textWithMnemonic, int gap) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(textWithMnemonic);
		label.setForeground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND));

		try {
			// use bold font, if not already bold
			Font currentFont = label.getFont();
			FontData fd = currentFont.getFontData()[0];
			if ((fd.getStyle() & SWT.BOLD) == 0) {
				Font newFont = new Font(Display.getCurrent(), fd.getName(), fd
						.getHeight(), SWT.BOLD);
				label.setFont(newFont);

				label.addDisposeListener(new DisposeListener() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
					 */
					public void widgetDisposed(DisposeEvent arg0) {
						((Label) arg0.widget).getFont().dispose();
					}

				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return label;
	}

	/**
	 * Creates and returns a labeled separator with the label in the left-hand
	 * side. Useful to separate paragraphs in a panel; often a better choice
	 * than a <code>TitledBorder</code>.
	 * <p>
	 * 
	 * <pre>
	 * createSeparator(&quot;Name&quot;); // No mnemonic
	 * createSeparator(&quot;N&amp;ame&quot;); // Mnemonic is 'a'
	 * createSeparator(&quot;Save &amp;as&quot;); // Mnemonic is the second 'a'
	 * createSeparator(&quot;Look&amp;&amp;Feel&quot;); // No mnemonic, text is Look&amp;Feel
	 * </pre>
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may contain an ampersand (<tt>&amp;</tt>)
	 *            to mark a mnemonic
	 * @return a title label with separator on the side
	 */
	public Control createSeparator(Composite parent, String text) {
		return createSeparator(parent, text, SwingConstants.LEFT);
	}

	/**
	 * Creates and returns a labeled separator. Useful to separate paragraphs in
	 * a panel, which is often a better choice than a <code>TitledBorder</code>.
	 * <p>
	 * 
	 * <pre>
	 * final int LEFT = SwingConstants.LEFT;
	 * createSeparator(&quot;Name&quot;, LEFT); // No mnemonic
	 * createSeparator(&quot;N&amp;ame&quot;, LEFT); // Mnemonic is 'a'
	 * createSeparator(&quot;Save &amp;as&quot;, LEFT); // Mnemonic is the second 'a'
	 * createSeparator(&quot;Look&amp;&amp;Feel&quot;, LEFT); // No mnemonic, text is Look&amp;Feel
	 * </pre>
	 * 
	 * @param textWithMnemonic
	 *            the label's text - may contain an ampersand (<tt>&amp;</tt>)
	 *            to mark a mnemonic
	 * @param alignment
	 *            text alignment, one of <code>SwingConstants.LEFT</code>,
	 *            <code>SwingConstants.CENTER</code>,
	 *            <code>SwingConstants.RIGHT</code>
	 * @return a separator with title label
	 */
	public Control createSeparator(Composite parent, String text, int alignment) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		composite.setLayout(layout);

		Label label = createTitle(composite, text);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		label.setLayoutData(gd);

		Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = GridData.FILL;
		separator.setLayoutData(gd);

		return composite;
	}

}