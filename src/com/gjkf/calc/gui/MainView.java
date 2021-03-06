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

import com.gjkf.calc.core.Core;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.*;

public class MainView{

	private static Display display;
	private static Shell shell;

	private static Canvas canvas;

	private static CLabel expressionLabel, xStackLabel, yStackLabel, zStackLabel, tStackLabel, yLabel, multLabel;

	public static CLabel errorLabel;

	private static Text formulaField, multiplierTextField;

	private static boolean extended = false, changed = false;

	private KeyBoard keyBoard;

	// Time in ms
	private int time = 10;
	public static int drawCycle;

	private String formula = "", multiplier = "";

	@SuppressWarnings("static-access")
	public void init(Display d){

		this.display = d;

		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setMinimumSize(1000, 600);
		shell.setSize(1000, 600);
		shell.setLayout(null);
		shell.setText("Scientific Calculator");

		initMenu();

		ColorPicker.initColor(shell);

		initButtons();
		initTextField();

		drawAxis();

		initTimer();

		center();
		open();

	}

	/**
	 * It draws a line between the 2 coordinates, with the colour based on the current cycle
	 *
	 * @param x1 The first X coordinate
	 * @param y1 The second X coordinate
	 * @param x2 The first Y coordinate
	 * @param y2 The second X coordinate
	 * @param cycle The current cycle we're in, used to change colour of the line we draw
	 *
	 * @see com.gjkf.calc.core.Core
	 * @see com.gjkf.calc.gui.ColorPicker
	 */

	public static void draw(int x1, int y1, int x2, int y2, int cycle){

		GC gc = new GC(canvas);

		if(cycle > 7)
			cycle = 0;

		Color[] cArray = ColorPicker.getColorArray();

//		gc.drawPolyline(new int[] { x1, y1, x2, y2 });

//		System.out.println(cArray[cycle]);

		gc.setForeground(cArray[cycle]);

		gc.drawLine(x1, y1, x2, y2);

		gc.dispose();

	}

	private void initMenu(){

//		Main menu

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem settings = new MenuItem(menu, SWT.CASCADE);
		settings.setText("Settings");

//		Sub-Menu

		Menu menu_1 = new Menu(settings);
		settings.setMenu(menu_1);

		MenuItem colors = new MenuItem(menu_1, SWT.NONE);
		colors.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorPicker colorPicker = new ColorPicker();
				colorPicker.run(display);
			}
		});
		colors.setText("Colors");


	}

	private void initLabels(){

		expressionLabel = new CLabel(shell, SWT.SHADOW_IN);
		expressionLabel.setBounds(300, 300, 650, 100);

		xStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
		xStackLabel.setBounds(25, 170, 250, 30);
		yStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
		yStackLabel.setBounds(25, 200, 250, 30);
		zStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
		zStackLabel.setBounds(25, 230, 250, 30);
		tStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
		tStackLabel.setBounds(25, 260, 250, 30);

	}

	private void disposeLabels(){

		expressionLabel.dispose();

		xStackLabel.dispose();
		yStackLabel.dispose();
		zStackLabel.dispose();
		tStackLabel.dispose();

	}

	/**
	 * Inits all the text fields with their listeners
	 */

	private void initTextField(){

		multLabel = new CLabel(shell, SWT.SHADOW_NONE);
		multLabel.setBounds(55, 490, 100, 40);
		multLabel.setText("Multiplier = ");

		multiplierTextField = new Text(shell, SWT.CENTER);
		multiplierTextField.setBounds(150, 500, 70, 20);
		multiplierTextField.setMessage("Multiplier");
		multiplierTextField.setText("1");

		yLabel = new CLabel(shell, SWT.SHADOW_NONE);
		yLabel.setBounds(300, 490, 30, 40);
		yLabel.setText("y = ");

		formulaField = new Text(shell, SWT.CENTER);
		formulaField.setBounds(300, 500, 650, 20);
		formulaField.setMessage("Insert Here the Formula to Graph");

		formulaField.addKeyListener(new KeyListener(){

			@Override
			public void keyReleased(KeyEvent e){
			}

			@Override
			public void keyPressed(KeyEvent e){

				if(e.keyCode == 13){

//					System.out.println(formula);

					if(! multiplierTextField.getText().equals("")){
//						if(errorLabel != null && !errorLabel.isDisposed()){
//							errorLabel.setText("");
//							errorLabel.redraw();
//						}
						Core.calculateAndDraw(canvas, formula, Double.parseDouble(multiplierTextField.getText()), drawCycle);
//						Core.draw(formula, Double.parseDouble(multiplierTextField.getText()), drawCycle);s
					}else
						formulaField.setText("Insert a Multiplier into the text field to the left");

				}

			}

		});

	}

	/**
	 * Inits all the buttons with their listeners
	 */

	private void initButtons(){

		Button extendButton = new Button(shell, SWT.PUSH);
		extendButton.setBounds(0, 10, 40, 25);
		extendButton.setText("...");
		extendButton.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseDown(MouseEvent e){
//				System.out.println("...");
				extended = ! extended;

				if(extended){

					multiplierTextField.dispose();
					formulaField.dispose();

					yLabel.dispose();
					multLabel.dispose();

					canvas.dispose();

					initLabels();

					keyBoard = new KeyBoard(25, 300, expressionLabel, shell);
					keyBoard.initKeyBoard();

					shell.layout();

				}else{

					keyBoard.dispose();

					disposeLabels();

					shell.layout();

					initTextField();

					drawAxis();

				}
			}

		});

	}

	/**
	 * Timer to update the Label and do some other cool stuff
	 */

	private void initTimer(){

		Runnable timer = new Runnable() {
			@Override
			public void run() {

				display.timerExec(time, this);

				if((!Core.error && errorLabel != null )&& !errorLabel.isDisposed())
					errorLabel.dispose();

				if((Core.error && errorLabel != null) && errorLabel.isDisposed())
					initErrorLabel();

				if(!formulaField.isDisposed()){

					if(!multiplier.equals(multiplierTextField.getText())){
						shell.redraw();
						changed = true;
						resetCycle();
					}

					multiplier = multiplierTextField.getText();
					formula = formulaField.getText();

					changed = false;

				}

				if(extended && xStackLabel != null && yStackLabel != null && zStackLabel != null && tStackLabel != null){
					xStackLabel.setText("X: " + KeyBoard.x);
					yStackLabel.setText("Y: " + KeyBoard.y);
					zStackLabel.setText("Z: " + KeyBoard.z);
					tStackLabel.setText("T: " + KeyBoard.t);
				}

				if(expressionLabel != null){

					if(!expressionLabel.isDisposed()){

						if(expressionLabel.getText() == null){
							expressionLabel.setText("");
						}

					}

				}

			}
		};

		display.timerExec(time, timer);

	}

	/**
	 * Draws the axis
	 */

	private void drawAxis(){

		canvas = new Canvas(shell, SWT.NO_REDRAW_RESIZE);

		canvas.setBounds(0, 40, shell.getBounds().width, shell.getBounds().height / 2 + 150);
		canvas.layout();

		canvas.addPaintListener(new PaintListener(){
			public void paintControl(PaintEvent e){

				int size = 10;

				// Axis
				e.gc.drawLine(canvas.getBounds().width / 2, 0, canvas.getBounds().width / 2, canvas.getBounds().height);
				e.gc.drawLine(0, canvas.getBounds().height / 2, canvas.getBounds().width, canvas.getBounds().height / 2);

				// Arrows
				e.gc.drawLine(canvas.getBounds().width / 2 - size, size, canvas.getBounds().width / 2, 0);
				e.gc.drawLine(canvas.getBounds().width / 2, 0, canvas.getBounds().width / 2 + size, size);
				e.gc.drawLine(canvas.getBounds().width - size, canvas.getBounds().height / 2 - size, canvas.getBounds().width, canvas.getBounds().height / 2);
				e.gc.drawLine(canvas.getBounds().width, canvas.getBounds().height / 2, canvas.getBounds().width - size, canvas.getBounds().height / 2 + size);

			}
		});

	}

	/**
	 * Init the label used when an error occurs
	 */

	public static void initErrorLabel(){
			errorLabel = new CLabel(shell, SWT.SHADOW_NONE);

			errorLabel.setBounds(formulaField.getBounds().x, formulaField.getBounds().y + 30, formulaField.getBounds().width, formulaField.getBounds().height);
			errorLabel.setBackground(display.getSystemColor(SWT.COLOR_RED));
			errorLabel.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
	}

	public static boolean isChanged(){ return changed;}

	public static void augmentCycle(){
		drawCycle++;
	}

	public static void resetCycle(){
		drawCycle = 0;
	}

	/**
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

	/**
	 * Puts the shell at the center of the screen, just aesthetic
	 */

	private static void center(){
		org.eclipse.swt.graphics.Rectangle bds = shell.getDisplay().getBounds();
		org.eclipse.swt.graphics.Point p = shell.getSize();
		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

}