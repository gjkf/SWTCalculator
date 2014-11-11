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

			args = formula.split(" ");

			for(int i = 0; i < args.length; i++){
				System.out.println(args[i]);

				if(args[i].equals("*") || args[i].equals("/") || args[i].equals("+") || args[i].equals("-")){

					System.err.println(args[i]);
					
				}

			}

		}
		
		return Double.NaN;

	}

}