package com.gjkf.calc.gui;

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

public class ColorPicker{

    private Color color;

    private static Shell shell;

    private Display display;

    public void run(Display display){

        this.display = display;

        shell = new Shell(display);
        shell.setText("Color Chooser");
        shell.setSize(500, 500);

        createContents(shell);

        center();
        open();

        // Dispose the color we created for the Label
        if (color != null) {
            color.dispose();
        }

    }

    /**
     * Creates the window contents
     *
     * @param shell the parent shell
     */

    private void createContents(final Shell shell){
//        shell.setLayout(new GridLayout(2, false));

        // Start with Celtics green
        color = new Color(shell.getDisplay(), new RGB(0, 255, 0));

        // Use a label full of spaces to show the color
        final CLabel colorLabel = new CLabel(shell, SWT.NONE);
        colorLabel.setText("                              ");
        colorLabel.setBounds(shell.getBounds().width/2 - 100, 30, 200, 30);
        colorLabel.setBackground(color);

        Button button = new Button(shell, SWT.PUSH);
        button.setText("Color...");
        button.setBounds(shell.getBounds().width/2 + 130, 30, 80, 30);
        button.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                // Create the color-change dialog
                ColorDialog dlg = new ColorDialog(shell);

                // Set the selected color in the dialog from user's selected color
                dlg.setRGB(colorLabel.getBackground().getRGB());

                // Change the title bar text
                dlg.setText("Choose a Color");

                // Open the dialog and retrieve the selected color
                RGB rgb = dlg.open();
                if (rgb != null) {
                    // Dispose the old color, create the new one, and set into the label
                    color.dispose();
                    color = new Color(shell.getDisplay(), rgb);
                    colorLabel.setBackground(color);
                }
            }
        });

        Button doneButton = new Button(shell, SWT.PUSH);
        doneButton.setText("Done");
        doneButton.setBounds(shell.getBounds().x - 20, shell.getBounds().height - 60, 80, 30);
        doneButton.addSelectionListener(new SelectionAdapter(){
            @Override
            public void widgetSelected(SelectionEvent e){

                shell.close();
                shell.dispose();

            }
        });
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