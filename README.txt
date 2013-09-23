Description:

A simple, single player pong clone for android. The game is converted to single player by having both paddles accessible to user input - they simply operate in opposing directions. The game keeps track of your current high score.

Setup:

1) Clone the git repository containing this file:  git clone git@github.com:saghevli/481hw2

2) Open the Android SDK and navigate to file->import->Existing Android code into workspace, selecting the top level 'pong' directory.

3) Create an AVD (Android Virtual Device) to demo the program. Use the Nexus 4 AVD - this project was tested with API level 15. In the Virtual Device Manager, click the device definitions tab and edit/create a new Nexus 4 that has the D-Pad enabled under the input category - this allows the user to manipulate the pong paddles with the keyboard arrow keys in the emulator.

4) Run the app and enjoy! If the directional arrows are not cooperating, I've found that hitting one direction followed by the other causes them to work.
