MockDatingWebsite
=================

Repo that contains my implementation of a simple/mock dating site written in java

SET UP
This code was compiled on Eclipse using Java 1.8 SE, as well as using external libraries such assqljdbc4 jar 
file and  mysql-connector-java-5.1.32-bin jar file in order to have the database connected and running. You would also 
have to make sure that Apache and MySQL are running, I do this by using EXAMPP. I use localhost/phpMyAdmin/ for my 
database host where I store users in (databased is called people) and table I get it from is called members.

INTRODUCTION:
Once the program starts, the user has 4 options: (1)Sign_in, (2)Create New Account, (3)View All Members, and (4)Quit. 
The user must select the options by entering the corresponding value on the keyboard, the menu will repeat if value is 
incorrect or after View All Members task is completed. Enter only integer values or program will crash since it does 
not yet handle non-integer input. 


FIRST SET OF OPTIONS
Option #1: SIGN_IN
When you pick the SIGN_IN option (by pressuring and entering 1 on keyboard), you will be first be prompted to enter 
the username. If the username is not in database, the initial options will display again. If you would like to sign 
in as one of the users from the database you can select VIEW_ALL_MEMBERS(3) which will display all of the users in 
database. Enter their username and then once you are prompted for password, just enter the string “password”. Once you 
had signed in, you will be prompted with second options, please visit SECOND SET OF OPTIONS section to read up on the 
2nd options. 


Option #2: CREATE_ACCOUNT
Once you pick the CREATE_ACCOUNT option (pressing and entering 2 on keyboard), you will be prompted to enter your 
username, password, and first name. WARNING! PLEASE DO NOT ENTER AN ACTUAL PERSONAL PASSWORD, ENTER A SIMPLE ONE 
THAT YOU NEVER USE INSTEAD. Initial design of this program is simplicity, and data-base can be connected to without a 
password. Once the username, password, and name is set, you will be prompted to pick your attributes from the displayed 
attribute options. For example, you will be asked to select your education attribute (level of education you 
had achieved); The selection would be: 

(1)HIGH_SCHOOL, (2)SOME_COLLEGE, (3)COLLEGE_GRADUATE, (4)DOCTOR

If you are a college graduate then you would enter 3 and press enter, your attribute for education would be recorded as 
college graduate, doctor if you had selected 4 instead. This is a pretty much similar interface for all attribute 
selections. If you had selected the wrong value for the attribute, you will be prompted to enter the value over and 
over until one of the correct attributes is selected.
Once your username, password, name, and attributes had been filled out. Your input will be saved on a database 
called people, in table called members on a host website localhost/phpMyAdmin/ . Input will not be saved until all 
of the proper data is entered. Once the account had been successfully created, you will be presented with the second 
options, and you will be able to sign in next time.

Option #3 VIEW_ALL_MEMBERS
Fairly simple option, basically shows all members of the site, or all of the members that have been recorded on 
a database. It displays their name, username, and attributes. Sort of like browsing on a website, only much similar 
(and slightly less functional).

Option #4 QUIT
Simply enter 4 and program will terminate.

SECOND SET OF OPTIONS
This set of options will appear once you had either signed in or successfully created an account. Next set of 
options are: (1)SEARCH, (2)VIEW_ALL_MEMBERS, (3)VIEW_CONTACTS, (4)ADD_CONTACT, (5)REMOVE_CONTACT, (6)QUIT
List of options will continue to re-appear as soon as the tasks/options are completed, unless user presses 6 (quit) 
or inputs wrong data that I hadn’t account for yet. 

Option #2 (VIEW_ALL_MEMBERS) and Option #6 (QUIT) are same ones from the first set of options previously mentioned, 
only difference is that their corresponding numbers have changed. We will focus on the newer options:

Option #1 SEARCH
This option will allow you to search for members based on the criteria that you will specify during this process. 
Once you select this button you will be asked to enter your criteria based age, height, weight, and 5 enumerated 
attributes: eye color, hair color, ethnicity, education, and zodiac. 

For age, height, and weight criteria, process is going to be almost exactly the same. You will be prompted to either 
enter -1 to select default criteria or any other value to begin entering lower bound and upper bound criteria for 
such age/height/weight. If you selected -1, basically you will omit the attribute as a criteria that you want to 
constrain the search with. For example if you entered -1 for your age range, then anyone within any age group will 
still be in the running for your search result. However if you had chosen to manually select you range for 
age/height/weight by pressing any value besides -1, then you will be prompted to enter lower bound and upper bound 
for the attribute to limit your search results. If you had chosen to manually enter your range for age/height/weight 
(pressed anything besides -1) then you must enter proper lower bound and upper bound limits or you will be prompted 
over and over until the input is reasonable.

Once you had entered/selected the criteria for age/height/weight. You will enter criteria based on 5 enumerated 
attributes, eye color, hair color, ethnicity, education, and zodiac. For each attribute criteria, you will be 
displayed with options and their corresponding values. 
For example when you will be prompted to select your eye 
color criteria it will look like:
“(1)GREEN (2)BROWN (3)BLUE (4)HAZEL Select attribute values that correspond with the keyboard for the EyeColor 
criteria  search criteria or press anything else to leave it as default”

GREEN, BROWN, BLUE, AND HAZEL are the attribute values you had select from. When you are initially prompted to 
select criteria based on this type of attribute; you can either select a proper attribute 
(by entering corresponding value) or use default selection (select them all) by entering a value outside the 
selection. For example if you enter 8, that means that for your eye color criteria you are looking for anything 
that has green, brown, blue, and hazel eyes. 

If you choose to enter values manually (on your first prompt you entered a value that corresponds with 
one of the options), then you can either continue entering more values for your criteria or enter a 
value that is outside the selection or enter a duplicate value to finish your criteria selection.

For example if you were prompted to enter criteria for the eye color, you could enter 2, 4, and 6. 
Meaning that you selected Brown and Hazel and that should be it for the eye color criteria. You can manually 
select all the available values, but once all the values are selected or you entered a duplicate value, your 
criteria selection will be complete. 

Once your search criteria is complete, the program will find the members that match the criteria. 
It will eliminate anyone outside the age/height/weight range, as well as eliminate anyone that is not 
compatible based on combination of sexuality and gender, and eliminate anyone that did not fall into at 
least of the selected enumerated criteria. 
For example if in your criteria you had selected:
Eye Color: Green
Hair Color: Brown
Ethnicity: African American
Education: College Graduate
Zodiac: Scorpio 

Then Wendy who has blue eyes, red hair, Caucasian ethnicity, who just graduated high school, and 
has zodiac of Taurus, will be eliminated from the search results because she did not fit in any of the enumerated 
criteria. 

Members in the search results will be ordered (in descending order) based how much they fit the 
selected criteria. Jacob who for example only fits 1 enumerated criteria by having brown eyes, will be ordered 
bellow Drew who fits 3 enumerated criteria by having brown eyes, black hair, and Virgo zodiac attributes. 

Option #3 VIEW_CONTACTS
Fairly simple task, it allows you to view your contacts that you can add in SECOND SET OF OPTIONS.

Option #4 ADD_CONTACT
Allows you to add member to your username to contact list by inputting their correct username. 
You may want to use this options once you find matches that satisfy your search criteria. And later you can 
always view their usernames (for now) by using VIEW_CONTACTS option that is until the program terminates. Contacts 
are not yet, but later will be stored on a database. 

Option #5 REMOVE_CONTACT
Allows user to remove one of their contacts from their contact list by specifying their username. 

