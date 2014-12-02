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

package com.gjkf.calc.core;

import com.gjkf.calc.gui.MainView;

public class Core{

	public static double x = 0, y = 0, z = 0, t = 0;

	public static void clearStack(){

		x = 0; y = 0; z = 0; t = 0;

	}

	private void calculate(String formula){

		String[] parsedString, inBraceFormulaArray;

		String operator = "";

		double yValue = 0., tempY = 0., tempX = 0., oldX = 0., oldY = 0., currX = 0.;

		boolean hasSomething = false;

		// Splits the formula at each space
		parsedString = formula.split(" ");

		for(int i = 0; i < parsedString.length; i++){

			System.out.println("ParsedString: " + parsedString[i]);

			if(parsedString[i].equalsIgnoreCase("x")){

				if(hasSomething){

					tempX = currX;

				}else{

					tempY = currX;

				}

			}else{

				if(parsedString[i].equals("+") || parsedString[i].equals("-") || parsedString[i].equals("*") || parsedString[i].equals("/")){

					operator = parsedString[i];

					hasSomething = true;

					continue;

				}

			}

			if(hasSomething)
				tempX = Double.parseDouble(parsedString[i]);
			else
				tempY = Double.parseDouble(parsedString[i]);

			if(hasSomething){

				hasSomething = false;

				if(operator.equals("+")){

					yValue = tempX + tempY;

					tempY = yValue;

					System.out.println("X: " + currX);
					System.out.println("Y: " + yValue + "\n");

				}

				if(operator.equals("*")){

					yValue = tempX * tempY;

					tempY = yValue;

					System.out.println("X: " + currX);
					System.out.println("Y: " + yValue + "\n");

				}

				if(operator.equals("-")){

					yValue = tempY - tempX;

					tempY = yValue;

					System.out.println("X: " + currX);
					System.out.println("Y: " + yValue + "\n");

				}

				if(operator.equals("/")){

					if(tempX != 0){

						yValue = tempY / tempX;

						tempY = yValue;

						System.out.println("X: " + currX);
						System.out.println("Y: " + yValue + "\n");

					}else{

						System.err.println("Exception Occurred: Can't divide by '0'");

					}

				}

			}

		}

	}

	/**
	 * It parses the given formula and it calculates the <code>y</code> coordinate and then it draws
	 * The <code>x</code> coordinate is inside the code itself
	 *
	 * @param formula The given formula to parse
	 * @param multiplier The multiplier, used to zoom
	 * @param cycle The current cycle, used to change the line colour
	 *
	 * @see com.gjkf.calc.gui.MainView
	 */

	public static void calculateAndDraw(String formula, double multiplier, int cycle){

		String[] parsedString;

		double yValue = 0., tempY = 0., tempX = 0., oldX = 0., oldY = 0., currX = 0.;

		boolean hasSomething = false, isInBrace = false;

		String operator = "", inBraceFormula = "";

		int var = (int) (-100 * multiplier);

		parsedString = formula.split(" ");

		if(formula != null){

			for(int i = 0; i < parsedString.length; i++){

				System.out.println("ParsedString: " + parsedString[i] + " IsInBrace: " + isInBrace);

				if(parsedString[i].equalsIgnoreCase("x")){

				}else{

					if(parsedString[i].equals("(")){

						isInBrace = true;

					}

					if(parsedString[i].equals(")")){

						isInBrace = false;

					}

				}

			}

		}

	}

	/**
	 * It calculates <code>y</code> with the given formula
	 *
	 * @param formula The given formula
	 * @param multiplier The multiplier, used to zoom
	 * @param cycle The current cycle
	 *
	 * @see com.gjkf.calc.gui.MainView
	 */

	public static void draw(String formula, double multiplier, int cycle){

		String[] args;

		double yValue = 0., tempX = 0., tempY = 0., currX, oldX = 0., oldY = 0.;

		String oper = "";

		// This stores whether or not I've already put a value in the 'x'
		boolean flag = false;

		int var = (int) (-100 * multiplier);

		/*
		 * Checks if the given formula is not null
		 */

		if(formula != null){

			/*
			 * Puts the split String inside an array. It splits the formula at each space
			 */

			args = formula.split(" ");

			for(currX = var; currX < -var; currX += 1){

				for(String arg : args){

					/*
					 * If the element at the 'i' position is an 'x', otherwise I check if it's an operator
					 */

					//System.out.println("Args[i]: " + args[i] + " " + i);

					if(arg.equalsIgnoreCase("x")){

						if(flag)
							tempX = currX;
						else
							tempY = currX;

					}else{

						if(arg.equals("*") || arg.equals("/") || arg.equals("+") || arg.equals("-") || arg.equals("sin") || arg.equals("cos")){

							//System.out.println("ArgsOperation[i]: " + args[i] + " " + i);

							// I put the element inside the "oper" variable
							oper = arg;

							flag = true;

							//System.out.println("Flag: " + flag);

							continue;

						}

						//System.out.println("Flag: " + flag);

						/*
						 * If I already have a value stored, I put in the "tempX" varable the current element of the array, otherwise I put it in the 'Y' value
						 */

						if(flag)
							tempX = Double.parseDouble(arg);
						else
							tempY = Double.parseDouble(arg);

					}

					//System.out.println("TempX: " + tempX);
					//System.out.println("TempY: " + tempY);

					/*
					 * If I have a value, I must have another one ready to be calculated, so I check what operator I've stored before and do the proper calculation
					 */

					if(flag){

						flag = false;

						if(oper.equals("+")){

							yValue = tempX + tempY;

							tempY = yValue;

							System.out.println("X: " + currX);
							System.out.println("Y: " + yValue + "\n");

						}

						if(oper.equals("*")){

							yValue = tempX * tempY;

							tempY = yValue;

							System.out.println("X: " + currX);
							System.out.println("Y: " + yValue + "\n");

						}

						if(oper.equals("-")){

							yValue = tempY - tempX;

							tempY = yValue;

							System.out.println("X: " + currX);
							System.out.println("Y: " + yValue + "\n");

						}

						if(oper.equals("/")){

							if(tempX != 0){

								yValue = tempY / tempX;

								tempY = yValue;

								System.out.println("X: " + currX);
								System.out.println("Y: " + yValue + "\n");

							}else{

								System.err.println("Exception Occurred: Can't divide by '0'");

							}

						}

						if(oper.equals("sin")){

							yValue = Math.sin((tempX / 180) * Math.PI);

							tempY = yValue;

							//System.out.println("sin(45): " + Math.sin(Math.toDegrees(45)));

							System.out.println("X: " + currX);
							System.out.println("Y: " + yValue + "\n");

						}

						if(oper.equals("cos")){

							yValue = Math.cos((tempX / 180) * Math.PI);

							tempY = yValue;

							System.out.println("X: " + currX);
							System.out.println("Y: " + yValue + "\n");

						}

						/*
						 * If the currX (incremented in the for loop) is not equals to the var, so it's not the first time, I draw the point
						 */

						if(currX != var)
							MainView.draw(400 + (int) oldX * (int) multiplier, 185 - (int) oldY * (int) multiplier, 400 + (int) currX * (int) multiplier, 185 - (int) yValue * (int) multiplier, cycle);

						/*
						 * I make sure to store the "old" 'x' and 'y' values in other values for later use (see the draw line above)
						 */

						oldX = currX;
						oldY = yValue;

					}

				}
			}

			if(!MainView.isChanged())
				MainView.augmentCycle();

			//System.out.println("Changed: " + MainView.isChanged() + "\n");
			//System.err.println("Cicle: " + cicle + "\n");
			//System.err.println("MainViewCicle: " + MainView.drawCicle + "\n");

		}

	}

}