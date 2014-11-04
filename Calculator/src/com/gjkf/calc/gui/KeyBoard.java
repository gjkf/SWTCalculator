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
import org.eclipse.swt.widgets.Shell;

public class KeyBoard{

	private int x, y;

	private Shell shell;

	private Button[] firstRowNumberButtons = new Button[4];
	private Button[] secondRowNumberButtons = new Button[4];
	private Button[] thirdRowNumberButtons = new Button[4];
	private Button[] fourthRowButtons = new Button[4];

	public KeyBoard(int x, int y, Shell shell){

		this.x = x;
		this.y = y;
		this.shell = shell;

	}

	public void initKeyBoard(){

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
		}

		for(int i = 0; i < fourthRowButtons.length; i++){
			fourthRowButtons[i] = new Button(shell, SWT.PUSH);
			fourthRowButtons[i].setBounds(x + 55*i, y + 180, 50, 50);

			if(i == 0)
				fourthRowButtons[i].setText("0");
			else if(i == 1)
				fourthRowButtons[i].setText(".");
			else if(i == 2)
				fourthRowButtons[i].setText("=");
			else if(i == 3)
				fourthRowButtons[i].setText("-");
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
		setListeners("X");
		setListeners(":");
		setListeners("+");
		setListeners("-");

	}

	public void dispose(){

		for(int i = 0; i < firstRowNumberButtons.length; i++){
			firstRowNumberButtons[i].dispose();
		}

		for(int i = 0; i < secondRowNumberButtons.length; i++){
			secondRowNumberButtons[i].dispose();
		}

		for(int i = 0; i < thirdRowNumberButtons.length; i++){
			thirdRowNumberButtons[i].dispose();
		}

		for(int i = 0; i < fourthRowButtons.length; i++){
			fourthRowButtons[i].dispose();
		}

	}

	private void setListeners(String ident){

		if(ident.equals("1")){
			thirdRowNumberButtons[0].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("1");
				}

			});
		}else if(ident.equals("2")){
			thirdRowNumberButtons[1].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("2");
				}

			});
		}else if(ident.equals("3")){
			thirdRowNumberButtons[2].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("3");
				}

			});
		}else if(ident.equals("4")){
			secondRowNumberButtons[0].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("4");
				}

			});
		}else if(ident.equals("5")){
			secondRowNumberButtons[1].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("5");
				}

			});
		}else if(ident.equals("6")){
			secondRowNumberButtons[2].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("6");
				}

			});
		}else if(ident.equals("7")){
			firstRowNumberButtons[0].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("7");
				}

			});
		}else if(ident.equals("8")){
			firstRowNumberButtons[1].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("8");
				}

			});
		}else if(ident.equals("9")){
			firstRowNumberButtons[2].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("9");
				}

			});
		}else if(ident.equals("0")){
			fourthRowButtons[0].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("0");
				}

			});
		}else if(ident.equals(".")){
			fourthRowButtons[1].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println(".");
				}

			});
		}else if(ident.equals("=")){
			fourthRowButtons[2].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("=");
				}

			});
		}else if(ident.equals("X")){
			firstRowNumberButtons[3].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("x");
				}

			});
		}else if(ident.equals(":")){
			secondRowNumberButtons[3].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println(":");
				}

			});
		}else if(ident.equals("+")){
			thirdRowNumberButtons[3].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("+");
				}

			});
		}else if(ident.equals("-")){
			fourthRowButtons[3].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseDown(MouseEvent e){
					System.out.println("-");
				}

			});
		}

	}

}