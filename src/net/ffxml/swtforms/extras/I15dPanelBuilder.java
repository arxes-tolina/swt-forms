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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.ffxml.swtforms.builder.PanelBuilder;
import net.ffxml.swtforms.layout.CellConstraints;
import net.ffxml.swtforms.layout.FormLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * A general purpose panel builder that uses the {@link FormLayout} to layout
 * <code>JPanel</code>s. In addition to its superclass {@link PanelBuilder}
 * this class provides convenience behavior to map resource keys to their
 * associated internationalized (i15d) strings when adding labels, titles and
 * titled separators.
 * <p>
 * This class is not yet part of the binary Forms library; it comes with the
 * Forms distributions as an extra. <b>The API is work in progress and may
 * change without notice.</b> If you want to use this class, you may consider
 * copying it into your codebase.
 * 
 * @author Karsten Lentzsch
 * @see ResourceBundle
 * @deprecated Moved to {@link net.ffxml.swtforms.builder.I15dPanelBuilder}.
 */
public class I15dPanelBuilder extends PanelBuilder {

	/**
	 * Holds the <code>ResourceBundle</code> used to lookup internationalized
	 * (i15d) String resources.
	 */
	private final ResourceBundle bundle;

	// Instance Creation ****************************************************

	/**
	 * Constructs an instance of <code>I15dPanelBuilder</code> for the given
	 * panel and layout.
	 * 
	 * @param panel
	 *            the layout container
	 * @param layout
	 *            the <code>FormLayout</code> used to layout the container
	 * @param bundle
	 *            the <code>ResourceBundle</code> used to lookup i15d strings
	 */
	public I15dPanelBuilder(Composite panel, FormLayout layout,
			ResourceBundle bundle) {
		super(layout, panel);
		this.bundle = bundle;
	}

	// Adding Labels and Separators *****************************************

	/**
	 * Adds an internationalized (i15d) textual label to the form using the
	 * specified constraints.
	 * 
	 * @param resourceKey
	 *            the resource key for the label's text
	 * @param constraints
	 *            the label's cell constraints
	 * @return the added label
	 */
	public final Label addI15dLabel(String resourceKey,
			CellConstraints constraints) {
		return addLabel(getI15dString(resourceKey), constraints);
	}

	/**
	 * Adds an internationalized (i15d) textual label to the form using the
	 * specified constraints.
	 * 
	 * @param resourceKey
	 *            the resource key for the label's text
	 * @param encodedConstraints
	 *            a string representation for the constraints
	 * @return the added label
	 */
	public final Label addI15dLabel(String resourceKey,
			String encodedConstraints) {
		return addI15dLabel(resourceKey,
				new CellConstraints(encodedConstraints));
	}

	/**
	 * Adds an internationalized (i15d) titled separator to the form using the
	 * specified constraints.
	 * 
	 * @param resourceKey
	 *            the resource key for the separator title
	 * @param constraints
	 *            the separator's cell constraints
	 * @return the added titled separator
	 */
	public final Control addI15dSeparator(String resourceKey,
			CellConstraints constraints) {
		return addSeparator(getI15dString(resourceKey), constraints);
	}

	/**
	 * Adds an internationalized (i15d) titled separator to the form using the
	 * specified constraints.
	 * 
	 * @param resourceKey
	 *            the resource key for the separator titel
	 * @param encodedConstraints
	 *            a string representation for the constraints
	 * @return the added titled separator
	 */
	public final Control addI15dSeparator(String resourceKey,
			String encodedConstraints) {
		return addI15dSeparator(resourceKey, new CellConstraints(
				encodedConstraints));
	}

	/**
	 * Adds a title to the form using the specified constraints.
	 * 
	 * @param resourceKey
	 *            the resource key for the separator title
	 * @param constraints
	 *            the separator's cell constraints
	 * @return the added title label
	 */
	public final Label addI15dTitle(String resourceKey,
			CellConstraints constraints) {
		return addTitle(getI15dString(resourceKey), constraints);
	}

	/**
	 * Adds a title to the form using the specified constraints.
	 * 
	 * @param resourceKey
	 *            the resource key for the separator titel
	 * @param encodedConstraints
	 *            a string representation for the constraints
	 * @return the added title label
	 */
	public final Label add15dTitle(String resourceKey, String encodedConstraints) {
		return addI15dTitle(resourceKey,
				new CellConstraints(encodedConstraints));
	}

	// Helper Code **********************************************************

	/**
	 * Looks up and answers the internationalized (i15d) string for the given
	 * resource key from the <code>ResourceBundle</code>.
	 * 
	 * @param resourceKey
	 *            the key to look for in the resource bundle
	 * @return the associated internationalized string, or the resource key
	 *         itself in case of a missing resource
	 * @throws IllegalStateException
	 *             if no <code>ResourceBundle</code> has been set
	 */
	protected String getI15dString(String resourceKey) {
		if (bundle == null)
			throw new IllegalStateException("You must specify a ResourceBundle"
					+ " before using the internationalization support.");
		try {
			return bundle.getString(resourceKey);
		} catch (MissingResourceException mre) {
			return resourceKey;
		}
	}

}
