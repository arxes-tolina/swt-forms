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

import net.ffxml.swtforms.builder.ButtonBarBuilder;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A factory class that consists only of static methods to build frequently used
 * button bars. Utilizes the {@link com.jgoodies.forms.builder.ButtonBarBuilder}
 * that in turn uses the {@link com.jgoodies.forms.layout.FormLayout} to lay out
 * the bars.
 * <p>
 * 
 * The button bars returned by this builder comply with popular UI style guides.
 * 
 * <p>
 * In opposite to JGoodies Forms, you have to create the button bar's composite
 * and pass it to the constructor. This is, because you need the composite for
 * creating the buttons.
 * <p>
 * <strong>Example</strong><br>
 * 
 * <pre>
 * protected void createContents(Shell shell) {
 * 	FormLayout layout = new FormLayout(&quot;r:p, 3dlu, p:g&quot;, &quot;p, 3dlu, p&quot;);
 * 
 * 	CellConstraints cc = new CellConstraints();
 * 	PanelBuilder builder = new PanelBuilder(shell, layout);
 * 	builder.setDefaultDialogBorder();
 * 
 * 	builder.addLabel(&quot;Name:&quot;, cc.xy(1, 1));
 * 	builder.add(new Text(shell, SWT.BORDER), cc.xy(3, 1));
 * 
 * 	Composite myButtonBar = new Composite(shell, SWT.NONE);
 * 	Button okButton = new Button(myButtonBar, SWT.PUSH);
 * 	okButton.setText(&quot;OK&quot;);
 * 	Button cancelButton = new Button(myButtonBar, SWT.PUSH);
 * 	cancelButton.setText(&quot;Cancel&quot;);
 * 	Button helpButton = new Button(myButtonBar, SWT.PUSH);
 * 	helpButton.setText(&quot;Help&quot;);
 * 
 * 	builder.add(ButtonBarFactory.buildHelpOKCancelBar(myButtonBar, helpButton,
 * 			okButton, cancelButton), cc.xywh(3, 3, 1, 1));
 * }
 * </pre>
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.1 $
 * 
 * @see com.jgoodies.forms.builder.ButtonBarBuilder
 * @see com.jgoodies.forms.util.LayoutStyle
 */

public final class ButtonBarFactory {

	private ButtonBarFactory() {
		// Suppresses default constructor, ensuring non-instantiability.
	}

	// General Purpose Factory Methods: Left Aligned ************************

	/**
	 * Builds and returns a left aligned bar with one button.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @return a button bar with the given button
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button button1) {
		return buildLeftAlignedBar(composite, new Button[] { button1 });
	}

	/**
	 * Builds and returns a left aligned bar with two buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button button1, Button button2) {
		return buildLeftAlignedBar(composite,
				new Button[] { button1, button2 }, true);
	}

	/**
	 * Builds and returns a left aligned bar with three buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button button1, Button button2, Button button3) {
		return buildLeftAlignedBar(composite, new Button[] { button1, button2,
				button3 }, true);
	}

	/**
	 * Builds and returns a left aligned bar with four buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4) {
		return buildLeftAlignedBar(composite, new Button[] { button1, button2,
				button3, button4 }, true);
	}

	/**
	 * Builds and returns a left aligned bar with five buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @param button5
	 *            the fifth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4,
			Button button5) {
		return buildLeftAlignedBar(composite, new Button[] { button1, button2,
				button3, button4, button5 }, true);
	}

	/**
	 * Builds and returns a left aligned button bar with the given buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param buttons
	 *            an array of buttons to add
	 * @return a left aligned button bar with the given buttons
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button[] buttons) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.addGriddedButtons(buttons);
		builder.addGlue();
		return composite;
	}

	/**
	 * Builds and returns a left aligned button bar with the given buttons.
	 * 
	 * @param buttons
	 *            an array of buttons to add
	 * @param leftToRightButtonOrder
	 *            the order in which the buttons to add
	 * @return a left aligned button bar with the given buttons
	 */
	public static Composite buildLeftAlignedBar(Composite composite,
			Button[] buttons, boolean leftToRightButtonOrder) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.setLeftToRightButtonOrder(leftToRightButtonOrder);
		builder.addGriddedButtons(buttons);
		builder.addGlue();
		return composite;
	}

	// General Purpose Factory Methods: Centered ****************************

	/**
	 * Builds and returns a centered bar with one button.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @return a button bar with the given button
	 */
	public static Composite buildCenteredBar(Composite composite, Button button1) {
		return buildCenteredBar(composite, new Button[] { button1 });
	}

	/**
	 * Builds and returns a centered bar with two buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildCenteredBar(Composite composite,
			Button button1, Button button2) {
		return buildCenteredBar(composite, new Button[] { button1, button2 });
	}

	/**
	 * Builds and returns a centered bar with three buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildCenteredBar(Composite composite,
			Button button1, Button button2, Button button3) {
		return buildCenteredBar(composite, new Button[] { button1, button2,
				button3 });
	}

	/**
	 * Builds and returns a centered bar with four buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildCenteredBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4) {
		return buildCenteredBar(composite, new Button[] { button1, button2,
				button3, button4 });
	}

	/**
	 * Builds and returns a centered bar with five buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @param button5
	 *            the fifth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildCenteredBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4,
			Button button5) {
		return buildCenteredBar(composite, new Button[] { button1, button2,
				button3, button4, button5 });
	}

	/**
	 * Builds and returns a centered button bar with the given buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param buttons
	 *            an array of buttons to add
	 * @return a centered button bar with the given buttons
	 */
	public static Composite buildCenteredBar(Composite composite,
			Button[] buttons) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.addGlue();
		builder.addGriddedButtons(buttons);
		builder.addGlue();
		return composite;
	}

	/**
	 * Builds and returns a filled bar with one button.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @return a button bar with the given button
	 */
	public static Composite buildGrowingBar(Composite composite, Button button1) {
		return buildGrowingBar(composite, new Button[] { button1 });
	}

	/**
	 * Builds and returns a filled button bar with two buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildGrowingBar(Composite composite,
			Button button1, Button button2) {
		return buildGrowingBar(composite, new Button[] { button1, button2 });
	}

	/**
	 * Builds and returns a filled bar with three buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildGrowingBar(Composite composite,
			Button button1, Button button2, Button button3) {
		return buildGrowingBar(composite, new Button[] { button1, button2,
				button3 });
	}

	/**
	 * Builds and returns a filled bar with four buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildGrowingBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4) {
		return buildGrowingBar(composite, new Button[] { button1, button2,
				button3, button4 });
	}

	/**
	 * Builds and returns a filled bar with five buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @param button5
	 *            the fifth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildGrowingBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4,
			Button button5) {
		return buildGrowingBar(composite, new Button[] { button1, button2,
				button3, button4, button5 });
	}

	/**
	 * Builds and returns a button bar with the given buttons. All button
	 * columns will grow with the bar.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param buttons
	 *            an array of buttons to add
	 * @return a filled button bar with the given buttons
	 */
	public static Composite buildGrowingBar(Composite composite,
			Button[] buttons) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.addGriddedGrowingButtons(buttons);
		return composite;
	}

	// General Purpose Factory Methods: Right Aligned ***********************

	/**
	 * Builds and returns a right aligned bar with one button.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @return a button bar with the given button
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button button1) {
		return buildRightAlignedBar(composite, new Button[] { button1 });
	}

	/**
	 * Builds and returns a right aligned bar with two buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button button1, Button button2) {
		return buildRightAlignedBar(composite,
				new Button[] { button1, button2 }, true);
	}

	/**
	 * Builds and returns a right aligned bar with three buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button button1, Button button2, Button button3) {
		return buildRightAlignedBar(composite, new Button[] { button1, button2,
				button3 }, true);
	}

	/**
	 * Builds and returns a right aligned bar with four buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4) {
		return buildRightAlignedBar(composite, new Button[] { button1, button2,
				button3, button4 }, true);
	}

	/**
	 * Builds and returns a right aligned bar with five buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @param button5
	 *            the fifth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button button1, Button button2, Button button3, Button button4,
			Button button5) {
		return buildRightAlignedBar(composite, new Button[] { button1, button2,
				button3, button4, button5 }, true);
	}

	/**
	 * Builds and returns a right aligned button bar with the given buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param buttons
	 *            an array of buttons to add
	 * @return a right aligned button bar with the given buttons
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button[] buttons) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.addGlue();
		builder.addGriddedButtons(buttons);
		return composite;
	}

	/**
	 * Builds and returns a right aligned button bar with the given buttons.
	 * 
	 * @param buttons
	 *            an array of buttons to add
	 * @param leftToRightButtonOrder
	 *            the order in which the buttons to add
	 * @return a right aligned button bar with the given buttons
	 */
	public static Composite buildRightAlignedBar(Composite composite,
			Button[] buttons, boolean leftToRightButtonOrder) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.setLeftToRightButtonOrder(leftToRightButtonOrder);
		builder.addGlue();
		builder.addGriddedButtons(buttons);
		return composite;
	}

	// Right Aligned Button Bars with Help in the Left **********************

	/**
	 * Builds and returns a right aligned bar with help and one button.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the help button to add on the left side
	 * @param button1
	 *            the first button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildHelpBar(Composite composite, Button help,
			Button button1) {
		return buildHelpBar(composite, help, new Button[] { button1 });
	}

	/**
	 * Builds and returns a right aligned bar with help and two buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the help button to add on the left side
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildHelpBar(Composite composite, Button help,
			Button button1, Button button2) {
		return buildHelpBar(composite, help, new Button[] { button1, button2 });
	}

	/**
	 * Builds and returns a right aligned bar with help and three buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the help button to add on the left side
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildHelpBar(Composite composite, Button help,
			Button button1, Button button2, Button button3) {
		return buildHelpBar(composite, help, new Button[] { button1, button2,
				button3 });
	}

	/**
	 * Builds and returns a right aligned bar with help and four buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the help button to add on the left side
	 * @param button1
	 *            the first button to add
	 * @param button2
	 *            the second button to add
	 * @param button3
	 *            the third button to add
	 * @param button4
	 *            the fourth button to add
	 * @return a button bar with the given buttons
	 */
	public static Composite buildHelpBar(Composite composite, Button help,
			Button button1, Button button2, Button button3, Button button4) {
		return buildHelpBar(composite, help, new Button[] { button1, button2,
				button3, button4 });
	}

	/**
	 * Builds and returns a right aligned bar with help and other buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the help button to add on the left side
	 * @param buttons
	 *            an array of buttons to add
	 * @return a right aligned button bar with the given buttons
	 */
	public static Composite buildHelpBar(Composite composite, Button help,
			Button[] buttons) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.addGridded(help);
		builder.addRelatedGap();
		builder.addGlue();
		builder.addGriddedButtons(buttons);
		return composite;
	}

	// Popular Dialog Button Bars: No Help **********************************

	/**
	 * Builds and returns a button bar with Close.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param close
	 *            the Close button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildCloseBar(Composite composite, Button close) {
		return buildRightAlignedBar(composite, close);
	}

	/**
	 * Builds and returns a button bar with OK.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param ok
	 *            the OK button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildOKBar(Composite composite, Button ok) {
		return buildRightAlignedBar(composite, ok);
	}

	/**
	 * Builds and returns a button bar with OK and Cancel.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param ok
	 *            the OK button
	 * @param cancel
	 *            the Cancel button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildOKCancelBar(Composite composite, Button ok,
			Button cancel) {
		return buildRightAlignedBar(composite, new Button[] { ok, cancel });
	}

	/**
	 * Builds and returns a button bar with OK, Cancel and Apply.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param ok
	 *            the OK button
	 * @param cancel
	 *            the Cancel button
	 * @param apply
	 *            the Apply button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildOKCancelApplyBar(Composite composite,
			Button ok, Button cancel, Button apply) {
		return buildRightAlignedBar(composite,
				new Button[] { ok, cancel, apply });
	}

	// Popular Dialog Button Bars: Help in the Left *************************

	/**
	 * Builds and returns a button bar with Help and Close.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the Help button
	 * @param close
	 *            the Close button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildHelpCloseBar(Composite composite, Button help,
			Button close) {
		return buildHelpBar(composite, help, close);
	}

	/**
	 * Builds and returns a button bar with Help and OK.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the Help button
	 * @param ok
	 *            the OK button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildHelpOKBar(Composite composite, Button help,
			Button ok) {
		return buildHelpBar(composite, help, ok);
	}

	/**
	 * Builds and returns a button bar with Help, OK and Cancel.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the Help button
	 * @param ok
	 *            the OK button
	 * @param cancel
	 *            the Cancel button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildHelpOKCancelBar(Composite composite,
			Button help, Button ok, Button cancel) {
		return buildHelpBar(composite, help, ok, cancel);
	}

	/**
	 * Builds and returns a button bar with Help, OK, Cancel and Apply.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the Help button
	 * @param ok
	 *            the OK button
	 * @param cancel
	 *            the Cancel button
	 * @param apply
	 *            the Apply button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildHelpOKCancelApplyBar(Composite composite,
			Button help, Button ok, Button cancel, Button apply) {
		return buildHelpBar(composite, help, ok, cancel, apply);
	}

	// Popular Dialog Button Bars: Help in the Right Hand Side **************

	/**
	 * Builds and returns a button bar with Close and Help.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param close
	 *            the Close button
	 * @param help
	 *            the Help button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildCloseHelpBar(Composite composite,
			Button close, Button help) {
		return buildRightAlignedBar(composite, new Button[] { close, help });
	}

	/**
	 * Builds and returns a button bar with OK and Help.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param ok
	 *            the OK button
	 * @param help
	 *            the Help button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildOKHelpBar(Composite composite, Button ok,
			Button help) {
		return buildRightAlignedBar(composite, new Button[] { ok, help });
	}

	/**
	 * Builds and returns a button bar with OK, Cancel, and Help.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param ok
	 *            the OK button
	 * @param cancel
	 *            the Cancel button
	 * @param help
	 *            the Help button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildOKCancelHelpBar(Composite composite,
			Button ok, Button cancel, Button help) {
		return buildRightAlignedBar(composite,
				new Button[] { ok, cancel, help });
	}

	/**
	 * Builds and returns a button bar with OK, Cancel, Apply and Help.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param ok
	 *            the OK button
	 * @param cancel
	 *            the Cancel button
	 * @param apply
	 *            the Apply button
	 * @param help
	 *            the Help button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildOKCancelApplyHelpBar(Composite composite,
			Button ok, Button cancel, Button apply, Button help) {
		return buildRightAlignedBar(composite, new Button[] { ok, cancel,
				apply, help });
	}

	// Add..., Remove *******************************************************

	/**
	 * Builds and returns a left aligned button bar with Add and Remove.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param add
	 *            the Add button
	 * @param remove
	 *            the Remove button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildAddRemoveLeftBar(Composite composite,
			Button add, Button remove) {
		return buildLeftAlignedBar(composite, add, remove);
	}

	/**
	 * Builds and returns a filled button bar with Add and Remove.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param add
	 *            the Add button
	 * @param remove
	 *            the Remove button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildAddRemoveBar(Composite composite, Button add,
			Button remove) {
		return buildGrowingBar(composite, add, remove);
	}

	/**
	 * Builds and returns a right aligned button bar with Add and Remove.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param add
	 *            the Add button
	 * @param remove
	 *            the Remove button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildAddRemoveRightBar(Composite composite,
			Button add, Button remove) {
		return buildRightAlignedBar(composite, add, remove);
	}

	// Add..., Remove, Properties... ****************************************

	/**
	 * Builds and returns a left aligned button bar with Add, Remove, and
	 * Properties.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param add
	 *            the Add button
	 * @param remove
	 *            the Remove button
	 * @param properties
	 *            the Properties button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildAddRemovePropertiesLeftBar(
			Composite composite, Button add, Button remove, Button properties) {
		return buildLeftAlignedBar(composite, add, remove, properties);
	}

	/**
	 * Builds and returns a filled button bar with Add, Remove, and Properties.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param add
	 *            the Add button
	 * @param remove
	 *            the Remove button
	 * @param properties
	 *            the Properties button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildAddRemovePropertiesBar(Composite composite,
			Button add, Button remove, Button properties) {
		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		builder.addGriddedGrowing(add);
		builder.addRelatedGap();
		builder.addGriddedGrowing(remove);
		builder.addRelatedGap();
		builder.addGriddedGrowing(properties);
		return composite;
	}

	/**
	 * Builds and returns a right aligned button bar with Add, Remove, and
	 * Properties.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param add
	 *            the Add button
	 * @param remove
	 *            the Remove button
	 * @param properties
	 *            the Properties button
	 * @return a panel that contains the button(s)
	 */
	public static Composite buildAddRemovePropertiesRightBar(
			Composite composite, Button add, Button remove, Button properties) {
		return buildRightAlignedBar(composite, add, remove, properties);
	}

	// Wizard Bars **********************************************************

	/**
	 * Builds and returns a wizard button bar with: Back, Next, Finish, Cancel.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param back
	 *            the Back button
	 * @param next
	 *            the Next button
	 * @param finish
	 *            the Finish button
	 * @param cancel
	 *            the Cancel button
	 * @return a wizard button bar for back, next, finish, cancel
	 */
	public static Composite buildWizardBar(Composite composite, Button back,
			Button next, Button finish, Button cancel) {
		return buildWizardBar(composite, back, next, new Button[] { finish,
				cancel });
	}

	/**
	 * Builds and returns a wizard button bar with: Help and Back, Next, Finish,
	 * Cancel.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param help
	 *            the Help button
	 * @param back
	 *            the Back button
	 * @param next
	 *            the Next button
	 * @param finish
	 *            the Finish button
	 * @param cancel
	 *            the Cancel button
	 * @return a wizard button bar for help, back, next, finish, cancel
	 */
	public static Composite buildWizardBar(Composite composite, Button help,
			Button back, Button next, Button finish, Button cancel) {
		return buildWizardBar(composite, new Button[] { help }, back, next,
				new Button[] { finish, cancel });
	}

	/**
	 * Builds and returns a wizard button bar that consists of the back and next
	 * buttons, and some right aligned buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param back
	 *            the mandatory back button
	 * @param next
	 *            the mandatory next button
	 * @param rightAlignedButtons
	 *            an optional array of buttons that will be located in the bar's
	 *            right hand side
	 * @return a wizard button bar with back, next and a bunch of buttons
	 */
	public static Composite buildWizardBar(Composite composite, Button back,
			Button next, Button[] rightAlignedButtons) {
		return buildWizardBar(null, back, next, rightAlignedButtons);
	}

	/**
	 * Builds and returns a wizard button bar. It consists of some left aligned
	 * buttons, the back and next buttons, and some right aligned buttons.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param leftAlignedButtons
	 *            an optional array of buttons that will be positioned in the
	 *            bar's left hand side
	 * @param back
	 *            the mandatory back button
	 * @param next
	 *            the mandatory next button
	 * @param rightAlignedButtons
	 *            an optional array of buttons that will be located in the bar's
	 *            right hand side
	 * @return a wizard button bar with back, next and a bunch of buttons
	 */
	public static Composite buildWizardBar(Composite composite,
			Button[] leftAlignedButtons, Button back, Button next,
			Button[] rightAlignedButtons) {
		return buildWizardBar(composite, leftAlignedButtons, back, next, null,
				rightAlignedButtons);
	}

	/**
	 * Builds and returns a wizard button bar. It consists of some left aligned
	 * buttons, the back, next group, and some right aligned buttons. To allow
	 * the finish button to overlay the next button, you can optionally provide
	 * the <code>overlayedFinish</code> parameter.
	 * 
	 * @param composite
	 *            the composite, used for the button bar
	 * @param leftAlignedButtons
	 *            an optional array of buttons that will be positioned in the
	 *            bar's left hand side
	 * @param back
	 *            the mandatory back button
	 * @param next
	 *            the mandatory next button
	 * @param overlayedFinish
	 *            the optional overlayed finish button
	 * @param rightAlignedButtons
	 *            an optional array of buttons that will be located in the bar's
	 *            right hand side
	 * @return a wizard button bar with back, next and a bunch of buttons
	 */
	public static Composite buildWizardBar(Composite composite,
			Button[] leftAlignedButtons, Button back, Button next,
			Button overlayedFinish, Button[] rightAlignedButtons) {

		ButtonBarBuilder builder = new ButtonBarBuilder(composite);
		if (leftAlignedButtons != null) {
			builder.addGriddedButtons(leftAlignedButtons);
			builder.addRelatedGap();
		}
		builder.addGlue();
		builder.addGridded(back);
		builder.addGridded(next);

		// Optionally overlay the finish and next button.
		if (overlayedFinish != null) {
			builder.nextColumn(-1);
			builder.add(overlayedFinish);
			builder.nextColumn();
		}

		if (rightAlignedButtons != null) {
			builder.addRelatedGap();
			builder.addGriddedButtons(rightAlignedButtons);
		}
		return composite;
	}

}