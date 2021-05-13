# HelloWorld2
The long-anticipated sequel to the classic "Hello, World!" program.
Meant as a joke program in which I practice programming concepts I've learned.

When ran, opens a JFrame with some components:
-A JButton "Hello World"
-Two Radio Buttons
-A JLabel with 2 buttons on each side

The "Hello World" button will, as the name suggests, print "Hello, World!" in a certain manner based on the values selected.
Selecting the "Line Print" radio button will print the message a number of times equal to the number displayed in the JLabel.
Selecting "Square Print" will instead print an n x n square of the message where n is the value in the JLabel.
The buttons surrounding the JLabel adjust the value it displays. This value ranges from 1 to 6.

Should the value reach a certain amount, certain JButtons will become disabled.
For example, when the value is 1, min and subtract become disabled.
Additionally, having a value of 6 will disable the add and max buttons.

Upon closing the JFrame, the save file system will create a simple file "save.txt" which stores values.
The first value stored within the save file relates to the radio button selection:
- 0 = "Line Print" is selected.
- 1 = "Square Print" is selected.
The second line relates to the starting value for the JLabel, with only values between/including 1 and 6 being accepted.
Should either value fall outside the accepted range, the program will override the read file, and set the value to its default, which is:
- 0 for the first line ("Line Print" button selected)
- 1 for the second line (Starting value set to 1)

This "save.txt" file is not included in the repository, as it is generated by the program when you close the JFrame for the first time.
