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
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainView{

	private Display display;
	private static Shell shell;

	private Button extendButton;

	private KeyBoard keyBoard;

	private static CLabel expressionLabel;

	private Text formulaField;

	private boolean extended = false;

	// Time in ms
	private int time = 1000;

	private String formula; 

	public void init(Display d){

		this.display = d;

		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setMinimumSize(1000, 600);
		shell.setSize(1000, 600);
		shell.setLayout(null);
		shell.setText("Scientific Calculator");

		initButtons();
		initLabels();
		initTextField();

		initTimer();

		center();
		open();
	}

	private void initLabels(){

		expressionLabel = new CLabel(shell, SWT.SHADOW_IN);
		expressionLabel.setBounds(300, 300, 650, 100);

	}

	private void initTextField(){

		formulaField = new Text(shell, SWT.CENTER);
		formulaField.setBounds(300, 500, 650, 30);
		formulaField.setMessage("Insert Here the Formula");

	}

	private void initButtons(){

		extendButton = new Button(shell, SWT.PUSH);
		extendButton.setBounds(0, 10, 40, 25);
		extendButton.setText("...");
		extendButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseDown(MouseEvent e){
				System.out.println("...");
				extended = !extended;

				/*
				 * Open the KeyBoard in case it's closed and vice-versa 
				 */

				if(extended){
					keyBoard = new KeyBoard(25, 300, shell);
					keyBoard.initKeyBoard();
					shell.layout();
				}else{
					keyBoard.dispose();
				}
			}

		});

	}

	/*
	 * Timer to update the Label
	 */

	private void initTimer(){

		Runnable timer = new Runnable() {
			public void run() {
				display.timerExec(time, this);
				formula = formulaField.getText();
				if(!extended)
					expressionLabel.setText(formula);
				shell.redraw();
			}
		};

		display.timerExec(time, timer);

	}

	public static CLabel getLabel(){
		return expressionLabel;
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