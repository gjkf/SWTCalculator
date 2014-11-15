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

	public static void draw(String formula, double multiplier){

		String[] args;

		double yValue = 0., tempX = 0., tempY = 0., currX, oldX = 0., oldY = 0.;

		String oper = "";

		boolean flag = false;
		
		int var = (int) (-100 * multiplier);

		if(formula != null){

			args = formula.split(" ");

			for(currX = var; currX < -var; currX += 1){

				for(int i = 0; i < args.length; i++){
					
					if(args[i].equalsIgnoreCase("x")){

						if(flag)
							tempX = currX;
						else
							tempY = currX;

					}else{

						if(args[i].equals("*") || args[i].equals("/") || args[i].equals("+") || args[i].equals("-") || args[i].equals("sin") || args[i].equals("cos")){

							//System.out.println("ArgsOperation[i]: " + args[i] + " " + i);

							oper = args[i];

							flag = true;

							//System.out.println("Flag: " + flag);

							continue;

						}

						//System.out.println("Flag: " + flag);

						if(flag)
							tempX = Double.parseDouble(args[i]);
						else
							tempY = Double.parseDouble(args[i]);

					}

					//System.out.println("TempX: " + tempX);
					//System.out.println("TempY: " + tempY);

					if(flag){

						flag  = false;

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
							
							yValue = Math.sin(currX);
							
							System.out.println(yValue);
							
						}
						
						if(oper.equals("cos")){
							
							yValue = Math.cos(currX);
							
						}

						if(currX != var)
							MainView.draw(400 + (int) oldX * (int) multiplier, 185 - (int) oldY * (int) multiplier, 400 + (int) currX * (int) multiplier, 185 - (int) yValue * (int) multiplier);
						
						oldX = currX;
						oldY = yValue;

					}

				}

			}

		}

	}

}