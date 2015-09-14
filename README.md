# ACSL-ACSLland
This program was written for Contest #1 of the Senior Division of the [American Computer Science League](http://www.acsl.org/) 2014-2015 competition.  It was written in Java.  As per competition rules, I had three days to complete this program.  

Please note, though not a stipulation of the competition, I was challenged to write this program without converting time values into military time.  I understand that the conversion into military time would simplify my current algorithm. 

#Program Description
The purpose of this program is to aid two hypothetical travelers in ACSLland.  Each traveler starts at a specific city at a specific time and moves towards the other.  Ultimately, this program outputs how long it will take the first traveler to meet up with the second.  This output is printed in either the hh:mm or h:mm format (h stands for hours and m stands for minutes) rounded to the nearest minute.  

It is safe to assume that the shortest time should be computed.  For example, the difference between 3 PM and 5 PM is always 2 hours, never 22 hours. 

The different starting cities are represented by letters.  The distance between cities is below.  

A-B : 450
B-C : 140
C-D : 125
D-E : 365
E-F : 250
F-G : 160
G-H : 380
H-J : 235
J-K : 320

#Input
Each input has 8 lines.  The first two lines contain letters representing the starting cities of two travelers.  The next two lines contain the starting time of the first traveler and either AM or PM.  The next two lines contain the starting time of the second traveler and either AM or PM.  The last two lines contain the average speed of the first traveler, followed by the average speed of the second traveler. 

For the contest, there are 5 inputs.

#Sample
Sample Input (each comma represents a hard enter):
A, C, 1, PM, 2, PM, 50, 60
