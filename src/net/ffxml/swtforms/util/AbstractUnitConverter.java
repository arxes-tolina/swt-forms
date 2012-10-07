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

package net.ffxml.swtforms.util;

import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * An abstract implementation of the {@link UnitConverter} that minimizes the
 * effort required to convert font-dependent sizes to pixels.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.2 $
 * @see DefaultUnitConverter
 * @see com.jgoodies.forms.layout.Size
 * @see com.jgoodies.forms.layout.Sizes
 */
abstract public class AbstractUnitConverter implements UnitConverter {

	private static final int DTP_RESOLUTION = 72;

	// Unit Converter Implementation
	// *********************************************

	/**
	 * Converts Inches and returns pixels using the current resolution.
	 * 
	 * @param in
	 *            the Inches
	 * @return the given Inches as pixels
	 */
	public int inchAsPixel(double in) {
		return inchAsPixel(in, getScreenResolution());
	}

	/**
	 * Converts Millimeters and returns pixels using the current resolution.
	 * 
	 * @param mm
	 *            Millimeters
	 * @return the given Millimeters as pixels
	 */
	public int millimeterAsPixel(double mm) {
		return millimeterAsPixel(mm, getScreenResolution());
	}

	/**
	 * Converts Centimeters and returns pixels using the current resolution.
	 * 
	 * @param cm
	 *            Centimeters
	 * @return the given Centimeters as pixels
	 */
	public int centimeterAsPixel(double cm) {
		return centimeterAsPixel(cm, getScreenResolution());
	}

	/**
	 * Converts DTP Points and return pixels using the current resolution.
	 * 
	 * @param pt
	 *            DTP Points
	 * @return the given Points as pixels
	 */
	public int pointAsPixel(int pt) {
		return pointAsPixel(pt, getScreenResolution());
	}

	/**
	 * Converts horizontal dialog units and returns pixels. Honors the
	 * resolution and dialog font size.
	 * 
	 * @param dluX
	 *            the horizontal dialog units
	 * @param c
	 *            a control that provides the fontmetrics
	 * @return the given horizontal dialog units as pixels
	 */
	public int dialogUnitXAsPixel(int dluX, Control c) {
		return dialogUnitXAsPixel(dluX, getDialogBaseUnitsX(c));
	}

	/**
	 * Converts vertical dialog units and returns pixels. Honors the resolution
	 * and dialog font size.
	 * 
	 * @param dluY
	 *            the vertical dialog units
	 * @param c
	 *            a control that provides the fontmetrics
	 * @return the given vertical dialog units as pixels
	 */
	public int dialogUnitYAsPixel(int dluY, Control c) {
		return dialogUnitYAsPixel(dluY, getDialogBaseUnitsY(c));
	}

	// Abstract Behavior *****************************************************

	/**
	 * Gets and returns the horizontal dialog base units. Implementations are
	 * encouraged to cache previously computed dialog base units.
	 * 
	 * @param control
	 *            a control that provides the fontmetrics
	 * @return the horizontal dialog base units
	 */
	abstract protected double getDialogBaseUnitsX(Control control);

	/**
	 * Gets and returns the vertical dialog base units. Implementations are
	 * encouraged to cache previously computed dialog base units.
	 * 
	 * @param control
	 *            a control that provides the fontmetrics
	 * @return the vertical dialog base units
	 */
	abstract protected double getDialogBaseUnitsY(Control control);

	// Convenience Methods ***************************************************

	/**
	 * Converts Inches and returns pixels using the specified resolution.
	 * 
	 * @param in
	 *            the Inches
	 * @param dpi
	 *            the resolution
	 * @return the given Inches as pixels
	 */
	protected final int inchAsPixel(double in, int dpi) {
		return (int) Math.round(dpi * in);
	}

	/**
	 * Converts Millimeters and returns pixels using the specified resolution.
	 * 
	 * @param mm
	 *            Millimeters
	 * @param dpi
	 *            the resolution
	 * @return the given Millimeters as pixels
	 */
	protected final int millimeterAsPixel(double mm, int dpi) {
		return (int) Math.round(dpi * mm * 10 / 254);
	}

	/**
	 * Converts Centimeters and returns pixels using the specified resolution.
	 * 
	 * @param cm
	 *            Centimeters
	 * @param dpi
	 *            the resolution
	 * @return the given Centimeters as pixels
	 */
	protected final int centimeterAsPixel(double cm, int dpi) {
		return (int) Math.round(dpi * cm * 100 / 254);
	}

	/**
	 * Converts DTP Points and returns pixels using the specified resolution.
	 * 
	 * @param pt
	 *            DTP Points
	 * @param dpi
	 *            the resolution in dpi
	 * @return the given Points as pixels
	 */
	protected final int pointAsPixel(int pt, int dpi) {
		return Math.round(dpi * pt / DTP_RESOLUTION);
	}

	/**
	 * Converts horizontal dialog units and returns pixels.
	 * 
	 * @param dluX
	 *            the horizontal dialog units
	 * @param dialogBaseUnitsX
	 *            the horizontal dialog base units
	 * @return the given dialog base units as pixels
	 */
	protected int dialogUnitXAsPixel(int dluX, double dialogBaseUnitsX) {
		return (int) Math.round(dluX * dialogBaseUnitsX / 4);
	}

	/**
	 * Converts vertical dialog units and returns pixels.
	 * 
	 * @param dluY
	 *            the vertical dialog units
	 * @param dialogBaseUnitsY
	 *            the vertical dialog base units
	 * @return the given dialog base units as pixels
	 */
	protected int dialogUnitYAsPixel(int dluY, double dialogBaseUnitsY) {
		return (int) Math.round(dluY * dialogBaseUnitsY / 8);
	}

	// Helper Code ************************************************************

	protected double computeAverageCharWidth(FontMetrics metrics) {
		return metrics.getAverageCharWidth();
	}

	/**
	 * Returns the current screen resolution.
	 * 
	 * Note: Is equal to {@link getDefaultScreenResolution} in the current
	 * implementation.
	 * 
	 * @return the current screen resolution
	 */
	protected int getScreenResolution() {
		return getDefaultScreenResolution();
	}

	private static int defaultScreenResolution = -1;

	/**
	 * Computes and returns the default resolution.
	 * 
	 * @return the default screen resolution
	 */
	protected int getDefaultScreenResolution() {
		if (defaultScreenResolution == -1) {
			defaultScreenResolution = Display.getCurrent().getDPI().x;
		}
		return defaultScreenResolution;
	}

}
