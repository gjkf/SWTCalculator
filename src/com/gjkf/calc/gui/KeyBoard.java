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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

public class KeyBoard{

	private int x, y;

	private Shell shell;

	private CLabel label;

	public static boolean calculated = false;

	private Button[] firstRowNumberButtons = new Button[5];
	private Button[] secondRowNumberButtons = new Button[4];
	private Button[] thirdRowNumberButtons = new Button[8];
	private Button[] fourthRowButtons = new Button[8];

	public KeyBoard(int x, int y, CLabel cLabel, Shell shell){

		this.x = x;
		this.y = y;
		this.shell = shell;
		this.label = cLabel;

	}

	public void initKeyBoard(){

		if(label == null){
			label = new CLabel(shell, SWT.SHADOW_IN);
			label.setBounds(300, 300, 650, 100);
		}

		for(int i = 0; i < firstRowNumberButtons.length; i++){
			firstRowNumberButtons[i] = new Button(shell, SWT.PUSH);
			firstRowNumberButtons[i].setBounds(x + 55*i, y, 50, 50);

			if(i == 0)
				firstRowNumberButtons[i].setText("7");
			else if(i == 1)
				firstRowNumberButtons[i].setText("8");
			else if(i == 2)
				firstRowNumberButtons[i].setText("9");
			else if(i == 3)
				firstRowNumberButtons[i].setText("x");
			else if(i == 4){
				firstRowNumberButtons[i].setText("ENT");
				firstRowNumberButtons[i].setBounds(x + 55*i, y, 50, 110);
			}
		}

		for(int i = 0; i < secondRowNumberButtons.length; i++){
			secondRowNumberButtons[i] = new Button(shell, SWT.PUSH);
			secondRowNumberButtons[i].setBounds(x + 55*i, y + 60, 50, 50);

			if(i == 0)
				secondRowNumberButtons[i].setText("4");
			else if(i == 1)
				secondRowNumberButtons[i].setText("5");
			else if(i == 2)
				secondRowNumberButtons[i].setText("6");
			else if(i == 3)
				secondRowNumberButtons[i].setText(":");
		}

		for(int i = 0; i < thirdRowNumberButtons.length; i++){
			thirdRowNumberButtons[i] = new Button(shell, SWT.PUSH);
			thirdRowNumberButtons[i].setBounds(x +55*i, y + 120, 50, 50);

			if(i == 0)
				thirdRowNumberButtons[i].setText("1");
			else if(i == 1)
				thirdRowNumberButtons[i].setText("2");
			else if(i == 2)
				thirdRowNumberButtons[i].setText("3");
			else if(i == 3)
				thirdRowNumberButtons[i].setText("+");
			else if(i == 4)
				thirdRowNumberButtons[i].setText("Del");
			else if(i == 5)
				thirdRowNumberButtons[i].setText("sin");
			else if(i == 6)
				thirdRowNumberButtons[i].setText("tan");
			else if(i == 7)
				thirdRowNumberButtons[i].setText("pow");
		}

		for(int i = 0; i < fourthRowButtons.length; i++){
			fourthRowButtons[i] = new Button(shell, SWT.PUSH);
			fourthRowButtons[i].setBounds(x + 55*i, y + 180, 50, 50);

			if(i == 0)
				fourthRowButtons[i].setText("0");
			else if(i == 1)
				fourthRowButtons[i].setText(".");
			else if(i == 3)
				fourthRowButtons[i].setText("-");
			else if(i == 4)
				fourthRowButtons[i].setText("AC");
			else if(i == 5)
				fourthRowButtons[i].setText("cos");
			else if(i == 6)
				fourthRowButtons[i].setText("sqrt");
			else if(i == 7)
				fourthRowButtons[i].setText("rt");
		}

		setListeners("1");
		setListeners("2");
		setListeners("3");
		setListeners("4");
		setListeners("5");
		setListeners("6");
		setListeners("7");
		setListeners("8");
		setListeners("9");
		setListeners("0");
		setListeners(".");
		setListeners("=");
		setListeners("x");
		setListeners(":");
		setListeners("+");
		setListeners("-");
		setListeners("del");
		setListeners("ac");
		setListeners("ent");
		setListeners("sin");
		setListeners("cos");
		setListeners("tan");
		setListeners("sqrt");
		setListeners("rt");
		setListeners("pow");

	}

	public void dispose(){

		for(Button firstRowNumberButton : firstRowNumberButtons){
			firstRowNumberButton.dispose();
		}

		for(Button secondRowNumberButton : secondRowNumberButtons){
			secondRowNumberButton.dispose();
		}

		for(Button thirdRowNumberButton : thirdRowNumberButtons){
			thirdRowNumberButton.dispose();
		}

		for(Button fourthRowButton : fourthRowButtons){
			fourthRowButton.dispose();
		}

	}

	private void setListeners(final String ident){

		if(label != null){

			if(ident.equals("1")){
				thirdRowNumberButtons[0].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("1");
						label.setText(label.getText() + "1");
					}

				});
			}else if(ident.equals("2")){
				thirdRowNumberButtons[1].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("2");
						label.setText(label.getText() + "2");
					}

				});
			}else if(ident.equals("3")){
				thirdRowNumberButtons[2].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("3");
						label.setText(label.getText() + "3");
					}

				});
			}else if(ident.equals("4")){
				secondRowNumberButtons[0].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("4");
						label.setText(label.getText() + "4");
					}

				});
			}else if(ident.equals("5")){
				secondRowNumberButtons[1].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("5");
						label.setText(label.getText() + "5");
					}

				});
			}else if(ident.equals("6")){
				secondRowNumberButtons[2].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("6");
						label.setText(label.getText() + "6");
					}

				});
			}else if(ident.equals("7")){
				firstRowNumberButtons[0].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("7");
						label.setText(label.getText() + "7");
					}

				});
			}else if(ident.equals("8")){
				firstRowNumberButtons[1].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("8");
						label.setText(label.getText() + "8");
					}

				});
			}else if(ident.equals("9")){
				firstRowNumberButtons[2].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("9");
						label.setText(label.getText() + "9");
					}

				});
			}else if(ident.equals("0")){
				fourthRowButtons[0].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("0");
						label.setText(label.getText() + "0");
					}

				});
			}else if(ident.equals(".")){
				fourthRowButtons[1].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
						System.out.println(".");
						label.setText(label.getText() + ".");
					}

				});
			}else if(ident.equals("x")){
				firstRowNumberButtons[3].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("x");
						String result = null;

						if(label.getText() != null && Core.x != 0 && Core.y != 0){

							Core.x = Double.parseDouble(label.getText());

							Core.x = Core.x * Core.y;

							pullDown();

							result = Double.toString(Core.x);

							label.setText(result);

							calculated = true;

						}

					}

				});
			}else if(ident.equals(":")){
				secondRowNumberButtons[3].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println(":");

						String result = null;

						if(label.getText() != null && Core.x != 0 && Core.y != 0){

							Core.x = Double.parseDouble(label.getText());

							if(Core.x != 0)
								Core.x = Core.x / Core.y;

							pullDown();

							result = Double.toString(Core.x);

							label.setText(result);

							calculated = true;

						}

					}

				});
			}else if(ident.equals("+")){
				thirdRowNumberButtons[3].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("+");

						String result = null;

						if(label.getText() != null && Core.x != 0 && Core.y != 0){

							Core.x = Double.parseDouble(label.getText());

							Core.x = Core.x + Core.y;

							pullDown();

							result = Double.toString(Core.x);

							label.setText(result);

							calculated = true;

						}

					}

				});
			}else if(ident.equals("-")){
				fourthRowButtons[3].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("-");

						String result = null;

						if(label.getText() != null && Core.x != 0 && Core.y != 0){

							Core.x = Double.parseDouble(label.getText());

							Core.x = Core.x - Core.y;

							pullDown();

							result = Double.toString(Core.x);

							label.setText(result);

							calculated = true;

						}

					}

				});
			}else if(ident.equals("del")){
				thirdRowNumberButtons[4].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("Del");
						if(label.getText() != null){
							if(!label.getText().equals("")){

								if(label.getText().endsWith(" "))
									label.setText(label.getText().substring(0, label.getText().length() - 2));
								else
									label.setText(label.getText().substring(0, label.getText().length() - 1));

							}
						}

					}

				});
			}else if(ident.equals("ac")){
				fourthRowButtons[4].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("AC");
						label.setText("");
//						System.out.println(Core.x + ":X " + Core.y + ":Y " + Core.z + ":Z " + Core.t + ":T");
						Core.clearStack();
//						System.out.println(Core.x + ":X " + Core.y + ":Y " + Core.z + ":Z " + Core.t + ":T");
					}

				});
			}else if(ident.equals("ent")){
				firstRowNumberButtons[4].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("Ent");

						Core.x = Double.parseDouble(label.getText());

						pushUp();

//						System.err.println("TEXT: " + label.getText());

						label.setText("");

					}

				});
			}else if(ident.contains("sin")){
				thirdRowNumberButtons[5].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("sin");

						Core.x = Math.sin(Core.x);

						pullDown();
						pushUp();

//						System.out.println("X: " + Core.x + " Sin(x): " + Math.sin(Core.x));

						label.setText(Double.toString(Core.x));

					}

				});
			}else if(ident.equals("cos")){
				fourthRowButtons[5].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("cos");

//						System.out.println("X: " + Core.x + " Cos(x): " + Math.cos(Core.x));

						Core.x = Math.cos(Core.x);

						pullDown();
						pushUp();

						label.setText(Double.toString(Core.x));

					}

				});
			}else if(ident.equals("tan")){
				thirdRowNumberButtons[6].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("tan");

//						System.out.println("X: " + Core.x + " Tan(x): " + Math.tan(Core.x));

						Core.x = Math.tan(Core.x);

						pullDown();
						pushUp();

						label.setText(Double.toString(Core.x));

					}

				});
			}else if(ident.equals("sqrt")){
				fourthRowButtons[6].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("sqrt");

						Core.x = Math.sqrt(Core.x);

						pullDown();
						pushUp();

						label.setText(Double.toString(Core.x));

					}

				});
			}else if(ident.equals("rt")){
				fourthRowButtons[7].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("rt");

						Core.x = Math.pow(Core.y, 1 / Double.parseDouble(label.getText()));

						pullDown();
						pushUp();

						label.setText(Double.toString(Core.x));

					}

				});
			}else if(ident.equals("pow")){
				thirdRowNumberButtons[7].addMouseListener(new MouseAdapter(){

					@Override
					public void mouseDown(MouseEvent e){
//						System.out.println("pow");

						Core.x = Math.pow(Core.y, Double.parseDouble(label.getText()));

						pullDown();
						pushUp();

						label.setText(Double.toString(Core.x));

					}

				});
			}

		}
	}

	private void pullDown(){

		Core.y = Core.z;
		Core.z = Core.t;

	}

	private void pushUp(){

		Core.t = Core.z;
		Core.z = Core.y;
		Core.y = Core.x;

	}

}