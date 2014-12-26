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

package com.gjkf.calc;

import com.gjkf.calc.gui.MainView;
import com.gjkf.calc.updater.Updater;
import org.eclipse.swt.widgets.Display;

import java.io.InputStream;
import java.util.Scanner;

public class Main{

	public static void main(String[] args){

		InputStream inputStream = Main.class.getResourceAsStream("/version/version.txt");

		Scanner scanner = new Scanner(inputStream);

		String version = scanner.nextLine().substring(9);

		System.out.println("VersionMain: " + version);

		//TODO: make an actual server hosting my stuff

		Updater updater = new Updater("http://update.skcraft.com/quark/launcher/latest.json", version);

		updater.run();

		Display d = new Display();
		MainView view = new MainView();
		view.init(d);

	}

}