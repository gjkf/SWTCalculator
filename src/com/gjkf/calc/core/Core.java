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

	public static boolean error = false;

	/**
	 * It uses the <code>JavaScript</code>'s engine to calculate the value of the formula
	 *
	 * @param canvas The canvas used get the bounds in which draw
	 * @param formula The given formula to parse
	 * @param multiplier The multiplier, used to zoom
	 * @param cycle The current cycle, used to change the line colour
	 *
	 * @throws java.lang.NullPointerException If the <code>formula</code> is null
	 *
	 * @see com.gjkf.calc.gui.MainView
	 */

	public static void calculateAndDraw(Canvas canvas, String formula, double multiplier, int cycle){

		error = false;

		int var = (int) ((canvas.getBounds().width/2) / multiplier);

		double increases = 1 / multiplier;

		double currX;

		double yValue = 0., oldY = 0., oldX = 0.;

		String tempFormula1 = formula;

		String tempFormula;

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		/*
		 * Substituting functions to make sure that the JavaScript Engine can understand them
		 */

		tempFormula1 = tempFormula1.replaceAll("sin", "Math.sin");
		tempFormula1 = tempFormula1.replaceAll("cos", "Math.cos");
		tempFormula1 = tempFormula1.replaceAll("tan", "Math.tan");
		tempFormula1 = tempFormula1.replaceAll("sqrt", "Math.sqrt");
		tempFormula1 = tempFormula1.replaceAll("cosh", "Math.cosh");
		tempFormula1 = tempFormula1.replaceAll("sinh", "Math.sinh");
		tempFormula1 = tempFormula1.replaceAll("tanh", "Math.tanh");


		tempFormula = tempFormula1;


		for(currX = -var; currX < var; currX += increases){

//			System.out.println("CurrX: " + currX);

			if(currX == 0.){
				continue;
			}

			mgr = new ScriptEngineManager();
			engine = mgr.getEngineByName("JavaScript");

			tempFormula = tempFormula.replaceAll("x", Double.toString(currX));

//			System.err.println(tempFormula);

			/*
			 * Using the JavaScript Engine for the calculations
			 */

			try{
				yValue = Double.parseDouble(engine.eval(tempFormula).toString());
			}catch(ScriptException e){

				if(MainView.errorLabel == null){
					MainView.initErrorLabel();
				}

				if(MainView.errorLabel.isDisposed()){
					MainView.initErrorLabel();
				}

				MainView.errorLabel.setText("");
				MainView.errorLabel.redraw();

				error = true;
				String error = e.getMessage();//.substring(e.getMessage().indexOf(": \"") + 2, e.getMessage().indexOf("defined.") + "defined.".length());
//				System.out.println(e.getMessage());

				if(error.contains(": \""))
					error = e.getMessage().substring(e.getMessage().indexOf(": \"") + 2, e.getMessage().indexOf("defined.") + "defined.".length());
				else if(error.contains("syntax"))
					error = "Syntax error.";
				else if(error.contains("["))
					error = "Missing element after list.";
				else
					error = "Unexpected end of file.";

				MainView.errorLabel.setText(error);

				MainView.resetCycle();

				break;

			}

//			System.out.println(yValue);

			tempFormula = tempFormula1;

			if(currX != -var)
				MainView.draw((int) (canvas.getBounds().width/2 + oldX * multiplier), (int) (canvas.getBounds().height/2 - oldY * multiplier),
						      (int) (canvas.getBounds().width/2 + currX * multiplier), (int) (canvas.getBounds().height/2 - yValue * multiplier), cycle);

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