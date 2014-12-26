/**
 * Copyright (c) 26/12/14 Davide Cossu.
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

package com.gjkf.calc.updater;

import com.gjkf.calc.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater implements Runnable{

    private String updateUrl, urlVersion, currentVersion;

    public Updater(String checkUrl, String currentVersion){

        this.updateUrl = checkUrl;
        this.currentVersion = currentVersion;

    }

    @Override
    public void run(){

        getVariables();

        System.out.println(isUpdated(currentVersion, urlVersion));

        if(!isUpdated(currentVersion, urlVersion)){
            try{
                download(updateUrl, new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile(), "SWTCalculator.jar");
            }catch(IOException | URISyntaxException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * Reads the url from the constructor, it looks for the version and the update url
     */

    public void getVariables(){

        try{
            URL url = new URL(updateUrl);

            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(url.openStream())));

            String line;

            String[] values;
            while((line = bufferedReader.readLine()) != null){

                values = line.split(": ");

                for(String value : values){

					/*
                     * Gets the version
					 */

                    if(value.length() == 8){
                        urlVersion = value.substring(1, 6);
                        System.out.println("Version: " + urlVersion);
                    }

					/*
					 * Gets the url
					 */

                    if(value.length() > 8 && value.startsWith("http", 1)){
                        updateUrl = value.substring(1, value.length() - 1);
                        System.out.println("ApplicationUrl: " + updateUrl);
                    }

                }

            }

            bufferedReader.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
	 * Checks if the 1st version is bigger then the 2nd
	 * @param currVersion The current version
	 * @param urlVersion The next version
	 * @return True if the 1st is older than the 2nd, false otherwise
	 */

    public boolean isUpdated(String currVersion, String urlVersion){

        currVersion = currVersion.replaceAll("\\.", "");
        urlVersion = urlVersion.replaceAll("\\.", "");

        return Integer.parseInt(currVersion) >= Integer.parseInt(urlVersion);

    }

    /**
     * Downloads the file from the given url, it's put in the given folder with the given name
     *
     * @param link The link from where to download
     * @param folder The folder to save the file in
     * @param name The name of the downloaded file
     * @throws IOException
     */

    public static void download(String link, File folder, String name) throws IOException{
        URL url = new URL(link);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(folder + "/" + name);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        System.out.println("Successfully downloaded file at: " + folder);
    }
}