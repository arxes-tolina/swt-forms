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

package net.ffxml.swtforms.test;

import net.ffxml.swtforms.builder.DefaultFormBuilder;
import net.ffxml.swtforms.layout.FormLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Based on SWTTest7.<br>
 * Test behaviour of default-size. Text's have to scale to their minimum-sizes
 * (which is the size of the margins for Text's, and the size of the
 * arrow-button for the combos).<br>
 * As we use column-groups, the Text's in the left and right column nevertheless
 * get the same widths.<br>
 * Without columngroups the right column will be squeezed more, as there are
 * only Text-widgets.
 * 
 * @author stefan.hansel@tolina.de
 * 
 * 
 */
public class SWTTest8 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		createContents(shell);
		shell.pack();
		shell.setText("SWTTest8");
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	protected static void createContents(Shell shell) {
		FormLayout layout = new FormLayout(
				"right:max(40dlu;pref), 3dlu, default:grow, 7dlu, "
						+ "right:max(40dlu;pref), 3dlu, default:grow(2)", "");
		layout.setColumnGroups(new int[][] { { 3, 7 } });
		// add rows dynamically
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, shell);
		builder.setDefaultDialogBorder();

		builder.appendSeparator("Flange");

		builder.append("Identifier", new Text(shell, SWT.BORDER));
		builder.nextLine();

		builder.append("PTI [kW]", new Text(shell, SWT.BORDER));
		builder.append("Power [kW]", new Text(shell, SWT.BORDER));

		builder.append("s [mm]", new Text(shell, SWT.BORDER));
		builder.nextLine();

		builder.appendSeparator("Diameters");

		builder.append("da [mm]", new Text(shell, SWT.BORDER));
		builder.append("di [mm]", new Text(shell, SWT.BORDER));

		builder.append("da2 [mm]", new Text(shell, SWT.BORDER));
		builder.append("di2 [mm]", new Text(shell, SWT.BORDER));

		builder.append("R [mm]", new Text(shell, SWT.BORDER));
		builder.append("D [mm]", new Text(shell, SWT.BORDER));

		builder.appendSeparator("Criteria");

		builder.append("Location", buildLocationComboBox(shell));
		builder.append("k-factor", new Text(shell, SWT.BORDER));

		builder.appendSeparator("Bolts");

		builder.append("Material", buildMaterialComboBox(shell));
		builder.nextLine();

		builder.append("Numbers", new Text(shell, SWT.BORDER));
		builder.nextLine();

		builder.append("ds [mm]", new Text(shell, SWT.BORDER));
	}

	protected static Combo buildLocationComboBox(Shell shell) {
		Combo locations = new Combo(shell, SWT.BORDER);
		locations.setItems(new String[] { "Location 1", "Location 2",
				"Location 3" });
		return locations;
	}

	protected static Combo buildMaterialComboBox(Shell shell) {
		Combo materials = new Combo(shell, SWT.BORDER);
		materials.setItems(new String[] { "Material 1", "Material 2",
				"Material 3" });
		return materials;
	}
}