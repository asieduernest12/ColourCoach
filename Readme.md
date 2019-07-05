# What is this Project
ColourCoach was created to help me study colours
I imagined using the little knowledge I had about jave to build a an application with Frames and Ui components that will help me practice some 256 css colour reference i found online


# How to run the project
1. Import the project into Eclipse by clicking 
File > Import > Expand General > Import existing project into eclipse
2. Run the project
	a. Run the project as a java application 
		1. Navigate to *src > interfaze* 
		2. Right click on ColourCoachFrame.java
		3. Click "run as > Java Application "
	b. Run the project as a java applet
		1. Navigate to *src > interfaze*
		2. Right click on ColourCoachApplet.java
		3. Click "run as > Java Applet "

# How to interact with the application

The main application home screen is organised like a bottom based tab

<img src="home-tab.PNG" alt="Picture showing the home screen of the application">

# Show Distinct View
In this view, the most distinct colours are shown rather than variations of one colour. 
Thats is based on the various names that may contain the keyword brown  only one 
is picked and displayed. This logic applies to all of the colours 

# Random Colours
As the name indicates, colours of 20 are chosing randomly from the 256 colours obtained from the css colour text file
These colours are painted and labeled on the screen

# Basic Colours
This view shows the variation of colours that contain in their name any of the basic colours such as blue, yellow and green. Black and a few odd inclusion can be found in here.

# Basic + Grey Colours
This view shows the basic colours along with other grey colours and their labels


# How the application works 

## Loading Colours
Colours are loaded on start up inside *src > interfaze > ColorUtil.java* from a text file of colour names and corresponding
css colour values into an array list

## How views are constructed
Each of the corresponding tab-like views makes a selection from the colours obtained from ColorUtil.java and makes a panel with a label and coloured aread to show the colour visually based on the css colour value.

## How to Interact with application

### Randomize
Left clicking in any of the tab views will attempt to re initailize the view with random colours every 12 seconds for 4 times

### Training 
Right click on any of the colours to open the training/guessing child frame. In this frame colours the colours from the parent view are randomly shown, one at a time and you may try to recall the name of the colour. Presently there is no way to stop this process. It is a threaded process that has a button to start or stop the thread completely. Clicking on the start button starts a stopped process or stops a running proccess

