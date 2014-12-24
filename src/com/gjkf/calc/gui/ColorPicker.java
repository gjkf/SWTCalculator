/**
 * Copyright (c) 22/12/14 Davide Cossu.
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.gjkf.calc.gui;

import com.gjkf.calc.core.ColorHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ColorPicker{

    private static Shell shell;
    private Display display;

    private static CLabel[] colorLabels;

    private static CLabel[] typeLabels;

    private static Button[] chooseColors;

    private static Color[] colorArray;

    public void run(Display display){

        this.display = display;

        shell = new Shell(display);
        shell.setText("Color Chooser");
        shell.setSize(500, 500);

//        initColor(shell);
        initLabels();
        initButtons();

//        loadColorArray();

        center();
        open();

    }

    private void initLabels(){

        for(int i = 0; i < colorLabels.length; i++){

            colorLabels[i] = new CLabel(shell, SWT.NONE);
            colorLabels[i].setText("                              ");
            colorLabels[i].setBounds(shell.getBounds().width / 2 - 100, 30 + i * 50, 200, 30);

            if(colorArray[i] != null){
                colorLabels[i].setBackground(colorArray[i]);
            }else{
                initColor(shell);
            }

            typeLabels[i] = new CLabel(shell, SWT.NONE);
            typeLabels[i].setBounds(colorLabels[i].getBounds().x - 100, colorLabels[i].getBounds().y, colorLabels[i].getBounds().width, colorLabels[i].getBounds().height);

            typeLabels[i].setText("Cycle " + i);

        }

    }

    private void initButtons(){

        for(int i = 0; i < colorLabels.length; i++){

            final int var = i;

            chooseColors[i] = new Button(shell, SWT.PUSH);
            chooseColors[i].setBounds(colorLabels[i].getBounds().x + colorLabels[i].getBounds().width + 30, colorLabels[i].getBounds().y, colorLabels[i].getBounds().width / 2, colorLabels[i].getBounds().height);
            chooseColors[i].setText("Color...");

            chooseColors[i].addSelectionListener(new SelectionAdapter(){

                @Override
                public void widgetSelected(SelectionEvent e){

                    ColorDialog dlg = new ColorDialog(shell);
                    dlg.setRGB(colorLabels[var].getBackground().getRGB());
                    dlg.setText("Choose a Color");

                    RGB rgb = dlg.open();
                    if(rgb != null){
                        colorArray[var].dispose();
                        colorArray[var] = new Color(shell.getDisplay(), rgb);
                        colorLabels[var].setBackground(colorArray[var]);
                    }
                }
            });
        }

        Button doneButton = new Button(shell, SWT.PUSH);
        doneButton.setText("Done");
        doneButton.setBounds(shell.getBounds().x - 20, shell.getBounds().height - 60, 80, 30);
        doneButton.addSelectionListener(new SelectionAdapter(){
            @Override
            public void widgetSelected(SelectionEvent e){

                for(int i = 0; i < colorLabels.length; i++){

                    colorArray[i] = colorLabels[i].getBackground();

//                    System.out.println(colorArray[i]);

                }

                MainView.resetCycle();

                ColorHelper.clearFile();
                ColorHelper.setColors(colorArray);

                shell.close();
                shell.dispose();

            }
        });

    }

    public static void initColor(Shell shell){

        colorLabels = new CLabel[8];
        typeLabels = new CLabel[colorLabels.length];
        colorArray = new Color[colorLabels.length];
        chooseColors = new Button[typeLabels.length];

        for(int i = 0; i < colorLabels.length; i++){
            colorArray[i] = new Color(shell.getDisplay(), new RGB(i * 10 + 10, i * 10 + 10, i * 30 + 10));
        }
    }

    public static void loadColorArray(Shell shell){
        colorArray = ColorHelper.getColors(shell.getDisplay());
    }

    public static Color[] getColorArray(){
        return colorArray;
    }

    /**
     * This ensures that the shell stays open until it's closed
     */

    public void open(){
        shell.open();
        while(!shell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Puts the shell at the center of the screen, just aesthetic
     */

    private static void center(){
        org.eclipse.swt.graphics.Rectangle bds = shell.getDisplay().getBounds();
        org.eclipse.swt.graphics.Point p = shell.getSize();
        int nLeft = (bds.width - p.x) / 2;
        int nTop = (bds.height - p.y) / 2;
        shell.setBounds(nLeft, nTop, p.x, p.y);
    }

}