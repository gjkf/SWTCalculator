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

public class Core{

	public static double x = 0, y = 0, z = 0, t = 0;

	public static void clearStack(){

		x = 0; y = 0; z = 0; t = 0;

	}

	public static double getY(String formula){

		if(formula != null){

			String[] args;

			double yValue, tempX = 0., tempY = 0.,currX;

			String oper = "";

			boolean flag = false;

			args = formula.split(" ");

			for(currX = -2; currX < 2; currX += 0.5){

				for(int i = 0; i < args.length; i++){

					//System.out.println("Args[i]: " + args[i] + " " + i);

					//System.err.println("CurrX: " + currX);

					if(args[i].equalsIgnoreCase("x")){

						if(flag)
							tempX = currX;
						else
							tempY = currX;

					}else{

						if(args[i].equals("*") || args[i].equals("/") || args[i].equals("+") || args[i].equals("-")){

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

					}

				}



			}

		}

		return Double.NaN;

	}

}