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
import org.eclipse.swt.widgets.Canvas;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Core{

	public static double x = 0, y = 0, z = 0, t = 0;

	/**
	 * Clears the stack, used in the RPN
	 *
	 * @see com.gjkf.calc.gui.KeyBoard @400
	 */

	public static void clearStack(){

		x = 0; y = 0; z = 0; t = 0;

	}

	/**
	 * It uses the <code>JavaScript</code>'s engine to calculate the value of the formula
	 *
	 * @param formula The given formula to parse
	 * @param multiplier The multiplier, used to zoom
	 * @param cycle The current cycle, used to change the line colour
	 *
	 * @throws java.lang.NullPointerException If the <code>formula</code> is null
	 * @throws javax.script.ScriptException If the engine can't resolve the <code>formula</code>
	 *
	 * @see com.gjkf.calc.gui.MainView
	 */

	public static void calculateAndDraw(Canvas canvas, String formula, double multiplier, int cycle) throws ScriptException{

		//TODO: fix the infinite loop when doing a second run with changed multiplier

		int var = (int) (100 / multiplier);

		double increases = 1 / multiplier;

		double currX;

		double yValue = 0., oldY = 0., oldX = 0.;

		String tempFormula1 = formula;

		String tempFormula;

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		tempFormula1 = tempFormula1.replaceAll("sin", "Math.sin");
		tempFormula1 = tempFormula1.replaceAll("cos", "Math.cos");
		tempFormula1 = tempFormula1.replaceAll("tan", "Math.tan");
		tempFormula1 = tempFormula1.replaceAll("sqrt", "Math.sqrt");
		tempFormula1 = tempFormula1.replaceAll("cosh", "Math.cosh");
		tempFormula1 = tempFormula1.replaceAll("sinh", "Math.sinh");
		tempFormula1 = tempFormula1.replaceAll("tanh", "Math.tanh");


		tempFormula = tempFormula1;
//		System.out.println(tempFormula);

//		var = 5;

		for(currX = -var; currX < var; currX += increases){

//			System.out.println("CurrX: " + currX);

			if(currX == 0.){
				continue;
			}

			mgr = new ScriptEngineManager();
			engine = mgr.getEngineByName("JavaScript");

			tempFormula = tempFormula.replaceAll("x", Double.toString(currX));

//			System.err.println(tempFormula);

			yValue = Double.parseDouble(engine.eval(tempFormula).toString());

//			System.out.println(yValue);

			tempFormula = tempFormula1;

			if(currX != -var)
				MainView.draw(canvas.getBounds().width/2 + (int) oldX * (int) multiplier, canvas.getBounds().height/2 - (int) oldY * (int) multiplier, canvas.getBounds().width/2 + (int) currX * (int) multiplier, canvas.getBounds().height/2 - (int) yValue * (int) multiplier, cycle);

			oldX = currX;
			oldY = yValue;

			mgr = null;
			engine = null;

		}

		if(!MainView.isChanged())
			MainView.augmentCycle();

		mgr = null;
		engine = null;

	}

}