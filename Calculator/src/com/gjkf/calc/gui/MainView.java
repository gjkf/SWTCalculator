/*
 * Copyright (c) 2014 Davide Cossu.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.gjkf.calc.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainView{

	private Display display;
	private static Shell shell;

	private Button[] numberButtons = new Button[10];
	private Button plusButton, minusButton, timesButton, divideButton;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void init(Display d){

		this.display = d;

		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setMinimumSize(600, 200);
		shell.setSize(1000, 600);
		shell.setLayout(null);
		shell.setText("Scientific Calculator");

		initButtons();
		center();
		open();
	}

	public void initButtons(){

		/*
		 * Better way of handling 10 different digits
		 */

		for(int i = 0; i < numberButtons.length; i++){
			numberButtons[i] = new Button(shell, SWT.PUSH);
			numberButtons[i].setBounds(25 + 55*i, 250, 50, 50);
			numberButtons[i].setText(Integer.toString(i));
		}

		plusButton = new Button(shell, SWT.PUSH);
		minusButton = new Button(shell, SWT.PUSH);
		timesButton = new Button(shell, SWT.PUSH);
		divideButton = new Button(shell, SWT.PUSH);

		setOperationButtons(plusButton, "+", 25, 320, 45, 45);
		setOperationButtons(minusButton, "-", 80, 320, 45, 45);
		setOperationButtons(timesButton, "x", 135, 320, 45, 45);
		setOperationButtons(divideButton, "/", 190, 320, 45, 45);

	}

	/*
	 * This ensures that the shell stays open until it's closed
	 */

	public void open(){
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void setOperationButtons(Button btn, String text, int x, int y, int width, int height){
		if(btn != null){
			btn.setBounds(x, y, width, height);
			btn.setText(text);
		}
	}

	public void setListeners(Button btn, String ident){

		if(ident.equals("+")){
			plusButton.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("+");
				}

			});
		}else if(ident.equals("-")){
			minusButton.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("-");
				}
				
			});
		}

	}

	/*
	 * Puts the shell at the center of the screen, just aestethical
	 */

	private static void center(){
		org.eclipse.swt.graphics.Rectangle bds = shell.getDisplay().getBounds();
		org.eclipse.swt.graphics.Point p = shell.getSize();
		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

}