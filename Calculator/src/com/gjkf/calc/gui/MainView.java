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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.gjkf.calc.core.Core;

public class MainView{

	private Display display;
	private static Shell shell;

	private Button extendButton;

	private KeyBoard keyBoard;

	private static CLabel expressionLabel, xStackLabel, yStackLabel, zStackLabel, tStackLabel, yLabel;

	private Text formulaField;

	private boolean extended = false;
	private static boolean calculated = false;

	// Time in ms
	private int time = 10;

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
		
		yLabel = new CLabel(shell, SWT.SHADOW_NONE);
		yLabel.setBounds(300, 490, 30, 40);
		yLabel.setText("y = ");
		
		initStackLabels();

	}
	
	private void initStackLabels(){
		
		if(extended){
			xStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
			xStackLabel.setBounds(25, 170, 250, 30);
			yStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
			yStackLabel.setBounds(25, 200, 250, 30);
			zStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
			zStackLabel.setBounds(25, 230, 250, 30);
			tStackLabel = new CLabel(shell, SWT.SHADOW_NONE);
			tStackLabel.setBounds(25, 260, 250, 30);
		}
		
	}

	private void initTextField(){

		formulaField = new Text(shell, SWT.CENTER);
		formulaField.setBounds(300, 500, 650, 20);
		formulaField.setMessage("Insert Here the Formula to Graph");

		formulaField.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e){}

			@Override
			public void keyPressed(KeyEvent e){

				if(e.keyCode == 13){

					System.err.println(formula);

					Core.getY(formula);
					
				}

			}

		});

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

				if(extended){
					keyBoard = new KeyBoard(25, 300, shell);
					keyBoard.initKeyBoard();

					formulaField.dispose();
					
					initStackLabels();

					shell.layout();
				}else{
					keyBoard.dispose();
					
					xStackLabel.dispose();
					yStackLabel.dispose();
					zStackLabel.dispose();
					tStackLabel.dispose();

					formulaField = new Text(shell, SWT.CENTER);
					formulaField.setBounds(300, 500, 650, 30);
					formulaField.setMessage("Insert Here the Formula");
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

				if(!formulaField.isDisposed()){

					if(!isCalculated()){
						formula = formulaField.getText();
					}

					if(!extended && !isCalculated())
						expressionLabel.setText(formula);

					if(!formulaField.getText().equals(formula))
						setCalculated(false);
				}

				if(extended && xStackLabel != null && yStackLabel != null && zStackLabel != null && tStackLabel != null){
					xStackLabel.setText("X: " + Core.x);
					yStackLabel.setText("Y: " + Core.y);
					zStackLabel.setText("Z: " + Core.z);
					tStackLabel.setText("T: " + Core.t);
				}

				shell.redraw();

			}
		};

		display.timerExec(time, timer);

	}

	public static CLabel getLabel(){
		return expressionLabel;
	}

	public static CLabel getXStackLabel(){
		return xStackLabel;
	}
	
	public static CLabel getYStackLabel(){
		return yStackLabel;
	}
	
	public static CLabel getZStackLabel(){
		return zStackLabel;
	}
	
	public static CLabel getTStackLabel(){
		return tStackLabel;
	}

	public static boolean isCalculated(){
		return calculated;
	}

	public static void setCalculated(boolean calc){
		calculated = calc;
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