---
layout: page
title: User Guide
---

## Welcome to CampusCompanion
**_Organising your university life is just a few keystroke away!_**

CampusCompanion is a **desktop application** built for **NUS Computer Science students** to manage the tracking of 
assignments and internships.

Here's an **overview** of how CampusCompanion can help you streamline the most important tasks that you will 
encounter in your university life. 
- Store and edit information about assignments and internships
- Track the status of these assignments and internships
- Overview of the tasks for the upcoming week and month

## Table of Contents
- [About this guide](#about-this-guide)
  - [New Users](#new-users)
  - [Experienced Users](#experienced-users)
- [Getting started](#getting-started)
- [Glossary](#glossary)
- [Understanding the Graphical User Interface(GUI)](#understanding-the-graphical-user-interface--gui-)
- [Command Format](#command-format)
- [Tutorial (for new users)](#campuscompanion-tutorial--for-new-users-)
- [Features](#features)
  - [Assignment Features](#assignment-features)
    - [Adding an assignment](#adding-an-assignment)
    - [Editing an assignment](#editing-an-assignment)
    - [Deleting an assignment](#deleting-an-assignment)
    - [Marking an assignment as complete/incomplete](#marking-an-assignment-as-completeincomplete)
    - [Listing and filtering assignments](#listing-and-filtering-assignments)
    - [Sorting assignments by deadline](#sorting-an-assignment-by-deadline)
    - [Finding an assignment by keywords](#finding-an-assignment-by-keywords)
  - [Internship Features](#internship-features)
- [Command Summary](#command-summary)
- [Parameter Summary](#parameter-summary)
  - [Assignment Parameters](#assignment-parameters)
  - [Internship Parameters](#internship-parameters)
- [FAQ](#faq)

--------------------------------------------------------------------------------------------------------------------

## About this guide

### New Users

If you are new here, and need help with getting started with our application, visit our 
[Getting Started](#getting-started) guide to onboard onto CampusCompanion smoothly!

After setting up the application, if you are looking for a step-by-step walk-through on the various features 
that CampusCompanion has, [click here](#campuscompanion-tutorial--for-new-users-) for a tutorial of CampusCompanion.

If you are looking to understand the different parts of the Graphical User Interface, [click here](#understanding-the-graphical-user-interface--gui-)
for a comprehensive look at our GUI. 

If you want a detailed look into each of the features that CampusCompanion has, visit our [features section](#features).

For any other queries that you might have, visit the [FAQ section](#faq) to find the answers to your queries!

### Experienced users

If you have some experience with our application, and would like an overview of the keywords, 
visit our [command summary page](#command-summary) or [parameter summary page](#parameter-summary).

If you want a detailed look into each of the features that CampusCompanion has, visit our [features section](#features).

--------------------------------------------------------------------------------------------------------------------

## Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `campuscompanion.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your CampusCompanion.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar campuscompanion.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Campus Companion.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Glossary
### Definitions 
Here are some descriptions of the words we use throughout the User Guide:


| Term | Definition |
|------|------------|
| xxx  | xxxx       |

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Understanding the Graphical User Interface (GUI)

### Quick Orientation

PUT A LABELLED PICTURE!!!

Here is a quick summary of each GUI component within CampusCompanion

[Scroll back to Table of Contents](#table-of-contents)

### Notes about the GUI

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Command format

| Format                                                                                 | Explanation                                                                  | Examples                                                                                            |
|----------------------------------------------------------------------------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| Words in `UPPER_CASE`                                                                  | These are parameter values that are supplied by the user                     | `add-a n/NAME...` can be used as `add-a n/CS2103T TP...`                                            |
| Items in square brackets                                                               | These are optional parameters (can be left empty by user)                    | `add-a n/NAME ... [t/TAG]` can be used as `add-a n/CS2103T TP t/milestone2` or `add-a n/CS2103T TP` |
| Items with `…` after them                                                              | These are parameters that can be used multiple times (or omitted completely) | `add-a ... [t/TAG]…` can be used as `add-a ... t/groupProject t/milestone2` or `add-a ...`          |
| Parameters can be in any order.                                                        | N/A                                                                          | `add-a n/NAME e/DEADLINE ...` is equivalent to `add-a e/DEADLINE n/NAME`                            |
| Extraneous parameters for commands that do not take in parameters will not be allowed. | N/A                                                                          | `list-a 123` will lead to an error message because the command should be `list-a`                   |

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## CampusCompanion Tutorial (for new users)

This is a tutorial for new CampusCompanion users. This tutorial will provide you step-by-step instructions for how to use each
command for both assignments and internships. 

1. Launch CampusCompanion. You may refer to the instructions [here](#getting-started).

To view all our features, you may visit out [features section](#features).

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Features

## Assignment Features

### Adding an assignment

[Scroll back to Table of Contents](#table-of-contents)

### Editing an assignment

[Scroll back to Table of Contents](#table-of-contents)

### Deleting an assignment

[Scroll back to Table of Contents](#table-of-contents)

### Marking an assignment as complete/incomplete

[Scroll back to Table of Contents](#table-of-contents)

### Listing and Filtering assignments

[Scroll back to Table of Contents](#table-of-contents)

### Sorting an assignment by deadline 

[Scroll back to Table of Contents](#table-of-contents)

### Finding an assignment by keywords

[Scroll back to Table of Contents](#table-of-contents)

## Internship Features

### Adding an internship : [coming soon]

### Delete an internship : [coming soon]

### Edit an internship : [coming soon]

### List all internships : [coming soon]

### Clearing all entries : `clear`

Clears all entries from the campus companion.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CampusCompanion data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CampusCompanion data are saved automatically as a JSON file `[JAR file location]/data/campuscompanion.json`. Advanced users are welcome to update data directly by editing that data file.



### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------
## Command Summary

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Parameter Summary

### Assignment Parameters

[Scroll back to Table of Contents](#table-of-contents)

### Internship Parameters

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Campus Companion home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later 
switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the 
`preferences.json` file created by the application before running the application again.
