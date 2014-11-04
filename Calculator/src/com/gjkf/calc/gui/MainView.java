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

	private Button extendButton;

	private KeyBoard keyBoard;

	private boolean extended = false;

	public void init(Display d){

		this.display = d;

		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setMinimumSize(600, 200);
		shell.setSize(1000, 600);
		shell.setLayout(null);
		shell.setText("Scientific Calculator");

		extendButton = new Button(shell, SWT.PUSH);
		extendButton.setBounds(0, 10, 40, 25);
		extendButton.setText("...");
		extendButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseDown(MouseEvent e){
				System.out.println("...");
				extended = !extended;
				if(extended){
					keyBoard = new KeyBoard(25, 300, shell);
					keyBoard.initKeyBoard();
					shell.layout();
				}else{
					keyBoard.dispose();
				}
			}

		});

		center();
		open();
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