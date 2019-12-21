# ObjectOrientedProgrammingClasses_Project1
This repository contains the first assignment project from object oriented programming classes.
How to use this application:
Running this application will result in apperaing of two windows, each of which will show an animation of the animals, and constantly
changing statistics.In the animation, red squares represent animals, green squares - grass, and black ones - desert.
Animation in each window can be stopped by clicking on a particular window (to set focus) and pressing 's' button.
Pressing 's' again will restart animation. When an animation is stopped and following actions are available:
- pressing G, will paint in blue every animal having genome identical with the dominationg one. This action can be undone by pessing
G again, or restarting animation by pressing S.
- Pressing F, will create a text file containg average statistics from the begining of the animation. This file will have the following 
format: "statistics_file_of_grass_" + ID number of an animation + "_" + number of time this operation is executed on this particular 
animation.
- clicking a red square, will start tracing an animal. Tracing will continue until an animal dies and will consist of the following actions:
              - traced animal will be painted in pink
              - three numbers will be shown: number of its children, number of its descendants, and the number of epoch the animal died. All these values 
              are set to -1, -1, -1 by default, and the third number (number of epoch) will be set to -1, until the animal dies. Changing the number of 
              epoch from -1 to something else will denote te fact, that the animal that was traced has already died
At first glance animations may seem slow, but in fact their speeds were set in such a way deliberatley. If the user wants to speed them up, this can be done by increasing value of the variable 'amountOfTicks' in the code of SimulationEngine.java class. 
