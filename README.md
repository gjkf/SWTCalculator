#SWT Scientific Calculator

This is a Java application I made. It reproduces a RPN calculator with the addition of another calculator that can graph functions for you.

For further information visit the wiki: https://github.com/gjkf/SWTCalculator/wiki/

[Requirements] (#requirements)

[Downloads] (#download)

[Launching] (#launching)

[Licensing] (#licensing)

###Requirements

This application requires Java to run. Make sure you have it installed before launching this application.

The Java JDK is used to compile this project.

1. Download and install the Java JDK.
	* [Windows/Mac download link](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download it (if you have a 64-bit OS, please download the 64-bit version).
	* Linux: Installation methods for certain popular flavors of Linux are listed below.  If your distribution is not listed, follow the instructions specific to your package manager or install it manually [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
		* Gentoo: `emerge dev-java/oracle-jdk-bin`
		* Archlinux: `pacman -S jdk7-openjdk`
		* Ubuntu/Debian: `apt-get install openjdk-7-jdk`
		* Fedora: `yum install java-1.7.0-openjdk`
2. Windows: Set environment variables for the JDK.
    * Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
    * Click on `Environment Variables`.
    * Under `System Variables`, click `New`.
    * For `Variable Name`, input `JAVA_HOME`.
    * For `Variable Value`, input something similar to `C:\Program Files\Java\jdk1.7.0_51` EXACTLY AS SHOWN (*or wherever your Java JDK installation is*), and click `Ok`.
    * Scroll down to a variable named `Path`, and double-click on it.
    * Append `;%JAVA_HOME%\bin` EXACTLY AS SHOWN and click `Ok`.  Make sure the location is correct; double-check just to make sure.
3. Open up your command line and run `javac`.  If it spews out a bunch of possible options and the usage, then you're good to go.

###Downloads

* Linux:
  * [32bit] (https://www.dropbox.com/sh/gq3zgscxsjmor3i/AAAUynfkvI7AzS_-ba4wYS7ia?dl=0)
  * [64bit] (https://www.dropbox.com/sh/1cqntxgz9ogjxmx/AAD2ePQzeABg6lPDo1LBqfrSa?dl=0)
* Windows
  [32bit] (https://www.dropbox.com/sh/ubfn89q4jgimsmi/AADEp9V-Ma0wYleQYb7o0yYea?dl=0)
  [64bit] (https://www.dropbox.com/sh/1c26vdcqmt18l6o/AAAsmtkA0kj2OC6gWPAB3QGBa?dl=0)
* MacOSX
  [32bit] (https://www.dropbox.com/sh/mh30u9vgkztjkes/AAD4WH1Npc2VW5B7krC6AR5Za?dl=0)
  [64bit] (https://www.dropbox.com/sh/50tj7954xj3fy20/AACzvEQHFletpaSJorq8SWCTa?dl=0)

###Launching

Make sure you have the `SWTCalculator.jar` in the same directory as the `swt.jar`, otherwise it won't work.

If you are on Mac, you need to launch this application from the command line. You need to type in this `java -jar -XstartOnFirstThread SWTCalculator.jar`.

### Licensing

- Source code Copyright &copy; 2014 Davide Cossu.

  ![GPL3](https://www.gnu.org/graphics/lgplv3-147x51.png)

  This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses>.