# Breakout Reinvented

For their project the students of Applied Computer Sciences at Howest needed to develop their own version of Breakout.
PLEASE NOTE: Additional Analysis + technical writing + business case is available upon request. This repository only contains code. 

## UML Classdiagram
The classdiagram has been added to the root of this project, this was too big to put into the readme.md

### W3 validator overview
Provides an overview of all the validated html pages with result, in this project.
## Database Create statement
This has been added to the root of the project

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

MySQL, an IDE of your choice and the java JDK 1.8 are needed.

### Installing

Download the ZIP to a folder and extract it.
Make sure to have a java_backend and a web_frontend folder, we will run these seperately.

Creating the MySQL Database.

```
Copy the contents from the Database create statement provided in this root of the Project, open your MySQL editor and run it as a query. 
```

Check your UID, URL (&port) and PWD in the MySQLConnection class in the be.ti.howest.howeststone.data.util package. 

```
public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/stone?useSSL=false&characterEncoding=utf8";
    private static final String UID = "root";
    private static final String PWD = "root";   //change these details for your connection with db! (granted all privileges to root user in MySQL)
```

```
Open the java backend folder with the 'project from existing sources' option in your IDE. The gradle project will now begin to download all of the dependencies.
```

Wait until Gradle has finished downloading the dependencies.

```
Now run the Server class in the API package. 
```

Launching the Frontend webpages.

```
Open the html files from your IDE with Chrome or Firefox. 
```

You can now play the game from the menu webpage.

## Authors

* **Fredrik Verriest** - *Project lead* - [Fredrik.Verriest](https://172.21.22.52/fredrik.verriest)
* **Henri Huyghe** - *Senior Developer, dev lead* -
* **Michael Coudeville** - *Senior developer*-
* **Brecht Willems** - *Junior developer* -

## Acknowledgments

* First of all we would like to thank our coach Louis 'Nero' Vanbiervliet for his input and support during the various meetings, secondly we would also like to thank Mattias De Wael, Koen Cornelis, Jill Vandendriessche and Kurt Sys for their input during the input sessions and code reviews.
* Inspiration
* Various resources used from other websites with permission to do so from coaches Louis Vanbiervliet, Koen Cornelis and Mattias De Wael:
* [Neverwinter](http://www.arcgames.com/en/games/neverwinter)
* [HearthstoneAPI](http://hearthstoneapi.com/)
* [Inkscape](https://inkscape.org/nl/)
* [Ashes of Creation](https://www.ashesofcreation.com/)
* [World of Warcraft](https://worldofwarcraft.com/en-us/)
* [Gwent](https://www.playgwent.com/en/support/category/hot-issues)
* [Hearthstone](https://playhearthstone.com/en-us/)
* [Gimp](https://www.gimp.org/)




##Commands
Status local git: git status

Add file to the commit: git add <file>

Commit your changes: git commit -m 'message about the commit goes here'

Add branch: git checkout -b <Name of branch>

Switch branch: git checkout <Name of branch>

Merge branch: git merge <Name of branch you want to merge with branch you are on> -m 'message about the merge goes here'

Remote repo add: git remote add origin <link naar repo>

Remote repo pull: git pull <remote repo (origin)> <branch> 

Remote repo push: git push -u <remote repo (origin)> <branch> 