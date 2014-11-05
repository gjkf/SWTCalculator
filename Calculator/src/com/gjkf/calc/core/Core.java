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

	public static double getMoltiplication(String expression){

		if(expression != null){

			if(expression.contains("x")){

				double xValue = Double.parseDouble(expression.split("x")[0].substring(0, expression.split("x")[0].length()));
				double yValue = Double.parseDouble(expression.split("x")[1].substring(0, expression.split("x")[1].length()));

				System.err.println(xValue * yValue);

				return xValue * yValue;

			}

			return Integer.MIN_VALUE;

		}

		return Integer.MAX_VALUE;

	}

	public static double getDivision(String expression){

		if(expression != null){

			if(expression.contains(":")){

				double xValue = Double.parseDouble(expression.split(":")[0].substring(0, expression.split(":")[0].length()));
				double yValue = Double.parseDouble(expression.split(":")[1].substring(0, expression.split(":")[1].length()));

				System.err.println(xValue / yValue);

				return xValue / yValue;

			}

			return Integer.MIN_VALUE;

		}

		return Integer.MAX_VALUE;

	}
	
	/*
	 * This does not work, regex doesn't accept '+'...
	 */

	public static double getAddition(String expression){

		if(expression != null){

			if(expression.contains("+")){
				
				double xValue = Double.parseDouble(expression.split("+")[0].substring(0, expression.split("+")[0].length()));
				double yValue = Double.parseDouble(expression.split("+")[1].substring(0, expression.split("+")[1].length()));

				System.err.println(xValue + yValue);

				return xValue + yValue;

			}

			return Double.MIN_VALUE;

		}

		return Double.MAX_VALUE;

	}

	public static double getSubtraction(String expression){

		if(expression != null){

			if(expression.contains("-")){

				double xValue = Double.parseDouble(expression.split("-")[0].substring(0, expression.split("-")[0].length()));
				double yValue = Double.parseDouble(expression.split("-")[1].substring(0, expression.split("-")[1].length()));

				System.err.println(xValue - yValue);

				return xValue - yValue;

			}

			return Integer.MIN_VALUE;

		}

		return Integer.MAX_VALUE;

	}

}