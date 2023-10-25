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
    - [Marking an assignment as complete](#marking-an-assignment-as-complete)
    - [Marking an assignment as incomplete](#marking-an-assignment-as-incomplete)
    - [Listing and filtering assignments](#listing-and-filtering-assignments)
    - [Finding an assignment by keywords](#finding-an-assignment-by-keywords)
  - [Internship Features](#internship-features)
- [Command Summary](#command-summary)
  - [Assignment Commands](#assignment-commands)
  - [Internship Commands](#internship-commands)
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

   * `list-a` : Lists all assignments.

   * `list-i` : Lists all internships.

   * `add-a n/CS2100 Assignment 1 e/2023-10-29` : Adds an assignment named `CS2100 Assignment 1` 
with a deadline of `29 Oct 2023 23:59` to the Campus Companion.

   * `delete-a 3` : Deletes the assignment with index 3 in the current list.

   * `mark-a 1` : Marks the assignment with index 1 in the current list.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Glossary
### Definitions 
Here are some descriptions of the words we use throughout the User Guide:

| Term               | Definition                                                                                                                                                                                                          |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Command            | An input from the user that tells CampusCompanion to perform an action (eg. ADD an assignment, DELETE an assignment).                                                                                               |
| Parameter          | Parameters are the details you would include about the assignment/internship (eg. Name, Description). A command may include 1 or more parameters.                                                                   |
| Required Parameter | These are parameters that **must** be included in the command, otherwise the command will fail, and an error message will be displayed to you.                                                                      |
| Optional Parameter | These are parameters that can be omitted from the command with no errors. Such parameters provides you the flexibility of capturing or omitting additional, less important information.                             |
| GUI                | Graphical User Interface (GUI) represents the visual display of CampusCompanion which you are looking at and interacting with.                                                                                      |
| GUI component      | The GUI is made up of many GUI components such as the calendar component, the list component etc. For more information on specific GUI components, [click here](#understanding-the-graphical-user-interface--gui-). |
| CLI                | Command Line Interface (CLI) represents a text-based user interface to interact with the application.                                                                                                               |


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
2. Let's try **adding an assignment** to CampusCompanion. Enter the command
`add-a n/CC tutorial 1 e/2023-11-11`
![NewUserTutAdd1.png](images%2FNewUserTutAdd1.png)
3. Add a few more assignments. Try out filling optional parameters as well, etc
`add-a n/CC optional 1 e/2023-11-11 16:00 d/try this out! s/complete p/2023-11-10`
   - Note: On adding assignments, they will automatically be sorted by their deadlines.
   The assignment due earliest will be first in the assignments list, which can be view using `list-a`.
![NewUserTutAddMoreAssgn.png](images%2FNewUserTutAddMoreAssgn.png)
4. Let us try editing the description of `CC tutorial 1` to say `nice descriptive description!`.
Input `list-a` to show the assignments list, then look for `CC tutorial 1`. In the example above, it is in index 2,
so we do `edit-a i/2 d/nice descriptive description!`
![NewUserTutEdit.png](images%2FNewUserTutEdit.png)
5. We are done with `CC tutorial 1`! Let's now mark it as complete. First, let's try using `find-a` to filter and narrow
down the list. We can do `find-a tutorial` to filter the assignments list to show only assignments that
contain `tutorial`.
![NewUserTutFind.png](images%2FNewUserTutFind.png)
Then, we use the index shown in the list (in this case, 1) and do `mark-a 1` to mark the assignment as completed.
![NewUserTutMark.png](images%2FNewUserTutMark.png)
6. To see all our assignments again, let's do `list-a`.
![NewUserTutList.png](images%2FNewUserTutList.png)
7. Lastly, let's delete our `CC tutorial 1` as we no longer want to track it.
With reference the picture above, we will do `delete-a 2`.
![NewUserTutDelete.png](images%2FNewUserTutDelete.png)
8. Congratulations! You are now ready to use CampusCompanion.
To view all our features, you may visit out [features section](#features).

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Features

## Assignment Features

### Adding an assignment

```add-a n/NAME e/YYYY-MM-DD HH:mm [d/DESCRIPTION] [s/STATUS] [p/YYYY-MM-DD HH:mm]```

To view assignment parameter information, click [here](#assignment-parameters)

**Purpose:**
To add an assignment into CampusCompanion.

**Examples:** 
- `add-a n/CS2100 Assignment 1 e/2023-10-29 13:00`
- `add-a n/CS2103T v1.3 e/2023-10-28 18:00 d/Work on add function for task s/incomplete p/2023-10-27 16:00`

**When you might use it:** 
- When you first receive news of an assignment or deadline, and you want to keep track of it.
- When you already have existing assignments not written in CampusCompanion, and want to organise them all in one place.

[Scroll back to Table of Contents](#table-of-contents)

### Editing an assignment

```put command first```

To view assignment parameter information, click [here](#assignment-parameters)

**Purpose:**

<div markdown="block" class="alert alert-danger">

**ALERT**
Put alerts here, if any
</div>

<div markdown="block" class="alert alert-info">

**Note**
Put notes here
</div>

**Examples:** show some examples with the parameters

**When you might use it:** give some use cases

[Scroll back to Table of Contents](#table-of-contents)

### Deleting an assignment

```delete-a INDEX```

To view assignment parameter information, click [here](#assignment-parameters)

**Purpose:** To delete the specified assignment from CampusCompanion

<div markdown="block" class="alert alert-danger">

**ALERT**
This command cannot be undone. If you remove the wrong assignment, you will have to add it back using ``add-a``.

</div>


<div markdown="block" class="alert alert-info">

**Note:**

- The index refers to the index number in the displayed assignment list. If you want to find out the index of the 
assignment you want to delete, you can use ``list-a`` to view all the current assignment you have.

- The first assignment in the list may not be index 1. Please refer to [parameter summary](#parameter-summary) 
if you want to understand what is the index number.

</div>

**Examples:**
- ``list-a`` to list all the assignments, followed by ``delete-a 1`` to delete assignment with **index** 1.
- ``find-a CS2103T`` to find the assignment you want to delete which has "CS2103T" in the name. Assuming this assignment is at **index** 1,
use ``delete-a 1`` to delete it.

**When you might use it:**
- If the assignment is completed and submitted, and you have no need to continue tracking it. 
- If the assignment is wrongly added.
- If certain details of the assignment was wrongly added, and these details are non-editable (e.g. name of assignment, deadline). 
For information on which details can be edited, [refer here](#editing-an-assignment).

[Scroll back to Table of Contents](#table-of-contents)

### Marking an assignment as complete

```mark-a INDEX```

To view assignment parameter information, click [here](#assignment-parameters)

**Purpose:**
To mark a specific assignment in CampusCompanion as complete.
<div markdown="block" class="alert alert-info">

**Note:**
- The index refers to the index number in the displayed assignments list. If you want to find out the index of the
  assignment you want to mark, you can use ``list-a`` to view all the current assignments you have.
- The first assignment in the list may not be index 1. Please refer to [parameter summary](#parameter-summary)
  if you want to understand what is the index number.
-  If an assignment is wrongly marked as complete, you can unmark it with `unmark-a`.
</div>

**Examples:**
- `list-a` to list all the assignments, then `mark-a 1` to mark
the assignment with index 1 as complete.
- `find-a MIH1101` to find assignments with MIH1101 in their name.
Assuming the assignment to mark is at index 1, you can use `mark-a 1` to mark it.

**When you might use it:**
- When you complete an assignment, and you want to mark it.
- When you wrongly unmark a completed assignment, and want to mark it again.

### Marking an assignment as incomplete

```unmark-a INDEX```

To view assignment parameter information, click [here](#assignment-parameters)

**Purpose:**
To mark a specific assignment in CampusCompanion as incomplete.
<div markdown="block" class="alert alert-info">

**Note:**
- The index refers to the index number in the displayed assignments list. If you want to find out the index of the
  assignment you want to unmark, you can use ``list-a`` to view all the current assignments you have.
- The first assignment in the list may not be index 1. Please refer to [parameter summary](#parameter-summary)
  if you want to understand what is the index number.
- If a completed assignment is wrongly marked as incomplete, you can mark it again with `mark-a`.
</div>

**Examples:**
- `list-a` to list all the assignments, then `unmark-a 1` to mark
  the assignment with index 1 as incomplete, assuming it was complete previously.
- `find-a MIH1101` to find assignments with MIH1101 in their name.
  Assuming the assignment to unmark is at index 1 and is complete, 
you can use `unmark-a 1` to mark it as incomplete.

**When you might use it:**
- When you wrongly mark an assignment as complete, and you want to unmark it.
- When you added an assignment with a complete status mistakenly, and want to unmark it.
- When you initially completed an assignment and marked it, but realise you have changes to make 
regarding the assignment and want to continue working on it.

[Scroll back to Table of Contents](#table-of-contents)

### Listing and Filtering assignments

```list-a [s/YYYY-MM-DD [HH:mm]] [e/YYYY-MM-DD [HH:mm]]```

**Purpose:** To list all the assignments with deadline between the given start date and end date. 
This command also changes the view to assignments tab (if the view is not currently at the assignments tab).

<div markdown="block" class="alert alert-info">

**Note:**

- _Start date_ must be after the _current date_. Otherwise, an error message will be shown. 

- _End date_ must be after _start date_. Otherwise, an error message will be shown.

- Both _start date_ and _end date_ are optional parameters. The timing specified within the _start date_ and _end date_ are also optional.
  - If no timing is specified for _start date_, the timing will default to 00:00.
  - If no timing is specified for _end date_, the timing will default to 23:59.

</div>

**Examples:**

| Combination                                                                                                           | Example                                          | What is displayed to you                                                          |
|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|-----------------------------------------------------------------------------------|
| **None** present                                                                                                      | ``list-a``                                       | **All** assignments that are saved in CampusCompanion.                            |
| **Only** _start date with time_ present                                                                               | ``list-a s/2023-12-10 18:00``                    | **All** assignments with deadline **later** than or equal 10 Dec 2023 18:00       |
| **Only** _start date without time_ present                                                                            | ``list-a s/2023-12-10``                          | **All** assignments with deadline **later** than or equal 10 Dec 2023 00:00       |
| **Only** _end date with time_ present                                                                                 | ``list-a e/2023-12-10 18:00``                    | **All** assignments with deadline **earlier** than or equal to 10 Dec 2023 18:00  |
| **Only** _end date without time_ present                                                                              | ``list-a e/2023-12-10``                          | **All** assignments with deadline **earlier** than or equal to 10 Dec 2023 23:59  |
| **Both** _start date_ and _end date_ present (Example not shown for this combination without timing, but it is valid) | ``list-a s/2023-12-10 18:00 e/2023-12-17 18:00`` | **All** assignments with deadline between 10 Dec 2023 18:00 and 17 Dec 2023 18:00 |

**When you might use it**:
- If you want to see all your current assignments.
- If you want to filter your current assignments by date, and view the assignments only within a specified time frame.

[Scroll back to Table of Contents](#table-of-contents)

### Finding an assignment by keywords

```put command first```

**Purpose:**

<div markdown="block" class="alert alert-danger">

**ALERT**
Put alerts here, if any
</div>

<div markdown="block" class="alert alert-info">

**Note:**
Put notes here
</div>

**Examples:** show some examples with the parameters

**When you might use it:** give some use cases

[Scroll back to Table of Contents](#table-of-contents)

## Internship Features

### Adding an internship [coming soon]

[Scroll back to Table of Contents](#table-of-contents)

### Delete an internship [coming soon]

[Scroll back to Table of Contents](#table-of-contents)

### Edit an internship [coming soon]

[Scroll back to Table of Contents](#table-of-contents)

### List all internships [coming soon]

[Scroll back to Table of Contents](#table-of-contents)

## General Commands

### Exiting the program

```exit```

**Purpose:** Exits the program.

[Scroll back to Table of Contents](#table-of-contents)

### Viewing help

```help```

**Purpose:** Shows a message explaining how to access the help page, which is the CampusCompanion User Guide. 

**When you might use it**
- If you have forgotten what is the structure for a certain command and its parameters.
- If you keep receiving error messages after entering commands, and are unsure why. 
- If you are unsure of what are the commands that are available to you in CampusCompanion. 

[Scroll back to Table of Contents](#table-of-contents)

## Data Related Features

### Saving the data

CampusCompanion data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Scroll back to Table of Contents](#table-of-contents)

### Editing the data file

CampusCompanion data are saved automatically as a JSON file `[JAR file location]/data/campuscompanion.json`. 

Advanced users are welcome to update data directly by editing that data file. After editing that file, 
when you re-run the application, the new data will automatically be shown. 

<div markdown="block" class="alert alert-info">

**Note:**
We do not recommend editing the file directly, because if the data is entered in the wrong format, it would corrupt the
application, and the application may not be able to load subsequently.

</div>


[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Command Summary

This section provides a summary of the **commands**. 

<div markdown="block" class="alert alert-info">

**Note:**
Each command may require 1 or more compulsory parameters.
Please refer to [parameter summary](#parameter-summary) section, for more information on each parameter,
or [feature](#features) section, for detailed information about each feature.

</div>


### Assignment Commands

| Command  | What it does                                                                                                                                                   |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| add-a    | Adds an assignment into CampusCompanion                                                                                                                        |
| edit-a   | Edits the existing information of the specified assignment                                                                                                     |
| delete-a | Deletes the specified assignment from CampusCompanion                                                                                                          |
| mark-a   | Changes the status of the specified assignment to completed                                                                                                    |
| unmark-a | Changes the status of the specified assignment to incomplete                                                                                                   |
| list-a   | Changes the view to assignments tab (if the view is not already at the assignments tab), and lists the assignments with deadline within a specified time frame |
| find-a   | Changes the view to assignments tab (if the view is not already at the assignments tab), and lists the assignments with name matching the given keyword        |

[Scroll back to Table of Contents](#table-of-contents)

### Internship Commands

(coming soon)

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Parameter Summary

### Assignment Parameters

The information below specifies parameter description, constraints and usage through valid and invalid examples.

| Parameter                             | Description                                            | Constraints                                                                                                                             | Valid Examples                          | Invalid Examples          |
|---------------------------------------|--------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------|---------------------------|
| n/                                    | Name of the assignment                                 | NA                                                                                                                                      | CS2100 Assignment, Tutorial 2           | NA                        |
| e/                                    | Deadline of the assignment                             | Has to be in YYYY-MM-DD HH:mm format, HH:mm is optional, and set as 23:59 by default<br/> Date inputted **MUST** be after current date. | 2023-12-12, 2023-12-10 15:00            | 12-12-2023, 30/12/2023    |
| d/                                    | Description of the assignment (optional parameter)     | NA                                                                                                                                      | Complete UML diagram, Finish question 2 | NA                        |
| s/                                    | Status (optional parameter)                            | Either `complete` or `incomplete`                                                                                                       | complete, incomplete                    | done, finished, completed |
| p/                                    | Planned finish date of assignment (optional parameter) | Has to be in YYYY-MM-DD HH:mm format, HH:mm is optional, and set as 23:59 by default<br/> Date inputted **MUST** be after current date. | 2023-12-12, 2023-12-10 15:00            | 12-12-2023, 30/12/2023    |
| INDEX<sup>*</sup> or i/ (in `edit-a`) | Index in assignments list                              | Positive integer less than or equal to 2147483649                                                                                       | 1, 2147483647                           | -1, 2147483649            |

[Scroll back to Table of Contents](#table-of-contents)

### Internship Parameters

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Campus Companion home folder.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later 
switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the 
`preferences.json` file created by the application before running the application again.

[Scroll back to Table of Contents](#table-of-contents)
