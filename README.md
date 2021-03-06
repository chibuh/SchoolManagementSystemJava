# SchoolManagementSystemJava
This OOPS project
Link to Github
https://drive.google.com/drive/folders/1AfskNU1nYXqKtepwr5muc6q8Q84P_GpY

Additional softwares and libraries used:
mySQL has been used for managing the database of the school management system. This ensures that the data related to the school, including student records, teacher records, admin details and course details remains intact across different instances of the program. i.e. the data is not lost once the program is closed. This ensures that the past data remains accessible once the program is run again. 
Version of mySQL and java-sql connector required:  5.1.33v.
Java Swing 
Swing is a set of classes (a framework) providing various components like labels, buttons, check/unchecked boxes, scroll/tabbed panes, tables, etc. helping you Build a graphical user interface (GUI) written completely in Java. Few components we used in our project are JLabel, JButton, JComboBox (to make drop-drown list), JFrame (to create a window), JPanel,etc.
 
1.	Description Of Classes:
2.	User – is an abstract class the common general information of the Teacher and Student like name, loginID, password, contact details, gender, etc.
It also contains an abstract method, applyHoliday() which is implemented in Teacher and Student class so that a teacher and student can apply for holiday and get validation if they are eligible (both have different conditions for holiday).
3.	Admin – This class contains information of admin like adminID and password. It contains methods such as to add, modify and delete Teachers and Student from List and Databases.
4.	Student – class extends the User class and contains additional information such as studentID, courses Opted, section and class in which the student is studying. It also implements applyHoliday() method which returns a Boolean.
5.	Teacher- also class extends and contains additional information such as TeacherId, courses enrolled. It also implements applyHoliday() method which returns a Boolean.
6.	Courses- It contains attributes of a course such as Course name and CourseID. We defined a constructor of this class where we crea  
For GUI
1.	SchoolManagement


2.	LoginGUI
 





3.	AdminGUI

 
















4.	TeacherGUI

 


5.	StudentGUI
 
6.	AddStudentGUI

 
For SQL
DBConnection Class
The DBConnection class is a custom class used to access the different mySQL queries used in the program, like SELECT, DELETE, INSERT and UPDATE. Different methods bearing the same names as the queries are provided within the DBConnection class. This reduces the load on the programmer as he/she does not need to define new objects of Statement class or the PreparedStatement class every time a query need to be run.The update query function serves as a multi use method, as all the queries of the form update, modify, drop, etc. can be accessed using this one single method. The class also has two other methods defined within itself namely CONNECT and CLOSE. The connect() method is used to establish a connection with the mySQL database using the DriverManager.getConnection() method predefined in the Connection class. 
The select method has the return type ResultSet which is responsible for handling the output generated by the select query. Moreover every query has a chance of throwing SQLExceptions which have been caught in the respective method definitions using try catch blocks.
The close method is used for closing the connection established above between the program and the database.
DATABASE:
The database has 5 tables in all namely, studentTable, AdminTable, TeacherTable, Logs and CoursesTable. The student table and teacher table are responsible for holding the student and teacher general details repectively. The logs table hold the student attendance and marks for the different courses. While the Courses Table is responsible for holding the names and ids of all the courses offered by the school.
Table Skeletons:
AdminTable:
AdID(P)	AdPass

StudentTable:
StudentID(P)	StudentPass	Name	dOB	gender	contact	HolidaysTaken	standard	section	CoursesTaken

TeacherTable:
TeacherID(P)	TeacherPass	Name	dOB	gender	contact	HolidaysTaken	CoursesTaught	Salary

Logs table:
StudentID(P)	*CourseID 1*Attendance	*CourseID1*Marks	*CourseID2*Attendance	*CourseID2*Marks

Courses Table:
CourseID(P)	Course Name


Code Flow
A LoginGUI object is created inside main function of SchoolManagement class, which implements components of Login user interface. There are two Text fields which the first one accepts loginID and checks if the password written in the other one matches using Database and Opens a new Frame, format depending on whether Student/Admin/Teacher logged in, otherwise a window pop-up with a message Incorrect loginID or Password.
If Id is A000 and password matches, Admin’s GUI open up (components implemented in AdminGUI class)  with ability to ‘Add Student’, ‘Add Teacher’, ‘Add Course’, Modify Teacher and Student details, Change his/her password. All these tasks are performed when respective buttons are clicked, then action is performed defined in an overridden method actionPerformed().
-	Clicking on ‘Add Students’ button opens up a new GUI (components implemented inside AddStudentGUI class). This GUI is like a form where after submitting all entered TextFields , Database stores them in a new Student column created in student table and also in student-course table (where grades and attendance are stored).
-	Similarly on clicking ‘Add Teacher’, a form like GUI opens where details ofnew teacher is input by admin. 
If Id starts with ‘S’ is valid and password matches, Student’s GUI open up (components implemented in StudentGUI class)  and the student can see his/her details, course grades and attendance, can apply for a holiday and check if holiday can be granted if holidays taken is not exceeded. The student can also change password and courses of his choices.
If Id starts with ‘S’ is valid and password matches, Student’s GUI open up (components implemented in StudentGUI class)  and the student can see his/her details, course grades and attendance, can apply for a holiday and check if holiday can be granted if holidays taken is not exceeded.

LIMITATIONS :
1.	The code is not well commented, this might create problems while referring the code in future for modification and maintenance purposes. Variable names vary slightly with classes, i.e. a similar variable might be called by some other name in another class. This was due to a lack of establishment of variable naming convention between the two team members.
2.	Most of the data is referred to using the mySQL database only. While this helps in creating a centralized database for managing the data ( and saves a lot in  the amount of memory needed to be allocated to the program), and also allows us to refer to it from any class, it also makes the code a bit difficult to read. The data is fetched using mySQL queries which makes the code lengthy, instead of the generally used technique of referring through class objects.
3.	Learning about java GUI and integrating the project with mySQL was a new task to the team. Thus it was not possible to make the GUI and the database as visually pleasing as might have been possible for a programmer with much greater experience in the field.
4.	As an installation wizard is not provided, the user would have to manually install the require mySQL version and its connector and add the connector as an external .jar in the reference libraries folder of the project.
5.	mySQL version 5.1.33 has been used for maintaining the database instead of the latest version 8. While this does not pose any technical problems/drawbacks as such, but the user/installing wizard (if ever provided in a future version) would have to specifically download the past version and its corresponding connector.

DESIGN PATTERNS:
A.	Encapsulation
All methods and data members defined in the parent class USER have their access specifier set as private. Hence they are not visible to the outside world, including its child classes namely TEACHER and STUDENT classes. Thus encapsulation is achieved. Moreover getter and setter functions have been provided for the the private data members of the above mentioned class to achieve encapsulation.
B.	Loose Coupling
One of the most key aspects of a Java project is loose coupling. The loose coupling in Java shows how to achieve loose coupling in Java projects or programs. The TEACHER and the STUDENT classes have objects of classes Attendance, Holidays, Grades in them and these objects are completely oblivious to the working of each other, yet they are functioning together. Hence Loose Coupling is achieved. The factory pattern of our program has helped us in achieving loose coupling.
C.	Classes should be open for extension and closed for modification
The term open for extension means that we are able to change how the module functions. While closed for modification refers to that extending the behaviour of the module does not result in a change in its source code.
The User abstract class has been extended by its child classes yet its contents and its base structure cannot be modified by the children classes.


D.	Abstraction.
Abstraction is achieved in our program with the help of the abstract class USER. The program does not depend on absolute concrete classes and extends the abstract class. Hence the child classes have a basic blueprint available over which specific components of the child classes are added. The factory design pattern of our program is also a part of the abstraction achieved in the program. 

FUTURE IMPROVEMENTS:
•	The database can be expanded further to cover the different details of the stakeholders.
•	The GUI can be made better using more features of Java AWT, Swing and can be beautified further using other libraries.
•	More features like exam implementation, fee payment portal, provisions for providing scholarships, finance management for the school can be added to the system.
•	The privacy of the program can be improved, right now the database can be accessed by unwarranted persons by directly opening the mySQL application.
•	APIs can be used for supplying OTPs to the users while logging in.
















UML Diagrams:
UML CLASS DIAGRAM:








	


UML USE CASE DIAGRAM:
Please refer the pdf.



Find Source Code, Video and Diagrams at :
GDrive : https://drive.google.com/drive/folders/1AfskNU1nYXqKtepwr5muc6q8Q84P_GpY
GitHub: https://github.com/chibuh/SchoolManagementSystemJava












