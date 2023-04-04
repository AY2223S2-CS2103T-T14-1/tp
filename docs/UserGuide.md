---
layout: page
title: User Guide
---

Artistic addressbook (ArB) is a **desktop app for artists, meant to help with managing clients and project information**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ArB can get your contact and project management tasks done faster than traditional GUI apps.

## **Table of Contents**
{:toc}
* [Quick Start](#quick-start)
* [Detailed Setup Guide](#detailed-setup)
* [Command Summary](#command-summary)
* [Command Syntax](#syntax)
* [Prefixes](#prefixes)
* [Features](#features)
* [General Features](#general-features)
* [Client Commands](#client-commands)
* [Project Commands](#project-commands)
* [Tag Commands](#tag-commands)
* [FAQ](#faq)
--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have [Java `11`](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or above installed in your Computer.

1. Download the latest `arb.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-1/tp/releases).

1. Copy the file to a new folder you want to use as the _home folder_ for your ArB

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar arb.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. The app may contain some sample data if being opened for the first time.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list-client` : Lists all clients.

   * `list-project`: Lists all projects. 

   * `add-client name/John Doe phone/98765432 email/johnd@example.com` : Adds a client named `John Doe` to the ArB.

   * `delete-client 3` : Deletes the 3rd client shown in the current client list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

## **Detailed Setup**

[To be added]

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                        | Alias | Format, Examples                                                                                                                                                      |
|-------------------------------|-------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **List Clients**              | `lc`  | `list-client`                                                                                                                                                         |
| **Add a Client**              | `ac`  | `add-client <name/NAME> [email/EMAIL] [phone/PHONE_NUMBER] [tag/TAG]*​` <br> e.g., `add-client name/Bob phone/12345678 email/bob@gmail.com tag/friend`                |
| **Edit a Client**             | `ec`  | `edit-client <index> [name/NAME] [email/EMAIL] [phone/PHONE] [tag/TAG]*​` <br> e.g.,`edit-client 3 name/Alice Risa phone/1234 tag/classmate`                          |
| **Delete a Client**           | `dc`  | `delete-client <index>`<br> e.g., `delete-client 1`                                                                                                                   |
| **Clearing the Client List**  | `cc`  | `clear-client`                                                                                                                                                        |
| **Finding a Client**          | `fc`  | `find-client [name/NAME]* [tag/TAG]*` <br> e.g., `find-client name/bob name/alice tag/friend`                                                                         |
| **Sorting all Clients**       | `sc`  | `sort-client`                                                                                                                                                         |
| **List Projects**             | `lp`  | `list-project`                                                                                                                                                        |
| **Add a Project**             | `ap`  | `add-project <name/NAME> [deadline/DEADLINE] [price/PRICE] [tag/TAG]*` <br> e.g., `add-project name/Background Commission deadline/2023-05-05 price/400 tag/painting` |
| **Edit a Project**            | `ep`  | `edit-project <index> [name/NAME] [deadline/DEADLINE] [price/PRICE] [tag/TAG]*` <br> e.g., `edit-project 2 name/The Starry Night`                                     |
| **Delete a Project**          | `dp`  | `delete-project <index>`<br> e.g., `delete-project 1`                                                                                                                 |
| **Mark a Project**            | `mp`  | `mark <index>` <br> e.g., `mark 3`                                                                                                                                    |
| **Unmark a Project**          | `up`  | `unmark <index>` <br> e.g., `unmark 3`                                                                                                                                |
| **Clearing the Project List** | `cp`  | `clear-project`                                                                                                                                                       |
| **Finding a Project**         | `fp`  | `find-project [name/NAME]* [start/START] [end/END] [tag/TAG]* [status/STATUS]` <br> e.g., `find-project name/sky start/yesterday tag/painting`                        |
| **Sorting all Project**       | `sp`  | `sort-project option/Option` <br> e.g., `sort-project option/name`                                                                                                    |

--------------------------------------------------------------------------------------------------------------------

## **Syntax**

`[ACTION VERB] [options]`

`<Required argument> [optional argument]`

An asterisk `*` after any argument indicates that it can be entered a variable number of times. E.g. `[tag/TAG]*`

All commands are case-insensitive.

## **Prefixes**

| Prefix      | Short form | Description                                        |
|-------------|------------|----------------------------------------------------|
| `name/`     | `n/`       | Name of client/project                             |
| `email/`    | `e/`       | Valid email address                                |
| `phone/`    | `p/`       | Valid phone number                                 |
| `deadline/` | `d/`       | Valid deadline for project                         |
| `price/`    | `pr/`      | Price of project (0 or 2 decimals)                 |
| `tag/`      | `t/`       | Alphanumeric tag                                   |
| `client/`   | `c/`       | Keywords to search for client to link to a project |
| `option/`   | `o/`       | Valid option to sort projects with                 |
| `start/`    | `s/`       | Valid start of timeframe for finding projects      |
| `end/`      | `e/`       | Valid end of timeframe for finding projects        |
| `status/`   | `st/`      | Valid status for finding projects                  |

<br>

--------------------------------------------------------------------------------------------------------------------

## **Features**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add-client name/NAME`, `NAME` is a parameter which can be used as `add-client name/John Doe`.

* Items in square brackets are optional.<br>
  e.g `name/NAME [tag/TAG]` can be used as `name/John Doe tag/friend` or as `name/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/friend`, `tag/friend tag/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name/NAME phone/PHONE_NUMBER`, `phone/PHONE_NUMBER name/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `phone/12341234 phone/56785678`, only `phone/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as [help](#viewing-help-help), [list-client](#list-all-clients-list-client), [list-project](#listing-all-projects--list-project), [list-tag](#listing-all-tags--list-tag), [exit](#exiting-the-program--exit), [clear-client](#clearing-the-client-list--clear-client), [clear-project](#clear) and [sort-client](#sorting-all-clients--sort-client)) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Extraneous parameters for commands that take in only one parameter (such as [delete-client](#deleting-a-client--delete-client)) will be ignored.<br>
  e.g. if the command specifices `delete-client 1 abc` it will be interpreted as `delete-client 1`.

* Most commands and parameters have shorter aliases that can be used the same way, such as [list-project](#listing-all-projects--list-project) having the alias `lp` and `name/` having the alias `n/`.<br>
  e.g. specifying `list-project` is the same as specifying `lp` and specifying `add-project name/John Doe` is the same as specifying `add-project n/John Doe`.

</div>

## General features

### Viewing help
#### Format: `help`

Shows a message explaining how to access the help page (this guide).

![help message](images/helpMessage.png)

### Exiting the program
#### Format: `exit`

Exits the program.

### Saving the data

ArB's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ArB's data is saved as a JSON file `[JAR file location]/data/arb.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ArB will discard all data and start with an empty data file at the next run.
</div>

## Client commands

The available client-related commands are:
* [List all clients](#listing-all-clients)
* [Add a client](#adding-a-client)
* [Edit a client](#editing-a-client)
* [Delete a client](#deleting-a-client)
* [Clear client list](#clearing-the-client-list)
* [Find clients](#finding-clients)
* [Sort clients](#sorting-all-clients)


### Listing all clients
#### Format: `list-client`

Short form: `lc`

Lists out all clients and shows the client list.

### Adding a client
#### Format: `add-client <name/NAME> [email/EMAIL] [phone/PHONE] [tag/TAG]*`

Short form: `ac <n/NAME> [e/EMAIL] [p/PHONE] [t/TAG]*`

Adds a client to the application with the supplied details. The details that can be supplied are the name, email address and phone number of the client, along with any number of tags to be added.

Only the name of the client is compulsory. 

The email address and phone number must be in a valid format. E.g. `XXX@gmail.com` or ```XXX@yahoo.com``` for emails and `12345678` for phone numbers.

Note:
* each tag to be added needs a separate `tag/TAG` flag.
* Empty prefixes for optional fields will be ignored.

Examples:
* `add-client name/Bob phone/12345678 email/bob@gmail.com tag/friend tag/default`
* `add-client name/Alice`
* `add-client name/Clary phone/87654321 email/clary@gmail.com`

### Editing a client
#### Format: `edit-client <index> [name/NAME] [email/EMAIL] [phone/PHONE] [tag/TAG]*`

Short form: `ec <index> [n/NAME] [e/EMAIL] [p/PHONE] [t/TAG]`

Edits the client at the given index of the client list, changing only the given field(s). Any fields that are mentioned but left empty will be deleted (apart from the name).

Fields that can be changed:
* Name
* Email address
* Phone number
* Tags

Note: 
* Using an empty `tag/` flag removes all tags of the client. This cannot be used with any non-empty `tag/` flags e.g. `edit-client 1 tag/ tag/friend` is not valid.
* At least one field to edit must be provided.

Examples:
*  `edit-client 1 email/new@email.com` Edits the email address of the 1st client to be `new@email.com`.
*  `edit-client 3 name/Alice Risa phone/1234 tag/` Edits the name of the 3rd client to `Alice Risa` and phone number to `1234`. Removes any tags.

### Deleting a client
#### Format: `delete-client <index>`

Short form: `dc <index>`

Deletes the client at the specified index of the client list.

Note:
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command cannot be undone. A deleted client cannot be restored.
</div>

Example:
*  `list-client` followed by `delete-client 1` deletes the first client in the list (if there is one).

### Clearing the client list
#### Format: `clear-client`

Short form: `cc`

Deletes all clients in the client list.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command cannot be undone. All deleted clients cannot be restored.
</div>

### Finding clients
#### Format: `find-client [name/NAME]* [tag/tag]*`

Short form: `fc [n/NAME] [t/TAG]*`

Finds a client based on the details provided. Details that can be supplied are the names and tags.

Note: 
* The matching with supplied names and tags are case-insensitive.
* Names and tags can either be separated by spaces or prefixes. E.g. `name/alice bob` is the same as `name/alice name/bob`
* Invalid names and tags will be ignored

Examples:
* `find-client name/bob tag/friend` will find any client whose name contains the word `bob` and is tagged with `friend`.
* `find-client name/bob name/alice tag/friend tag/husband` will find any client whose name contains either `bob` or `alice`, and is tagged with either `friend` or `husband`.

### Sorting all clients
#### Format: `sort-client`

Short form: `sc`

Sorts all clients that exist in the ArB by name in ascending order.

## Project commands

The available project-related commands are:
* [List all projects](#listing-all-projects)
* [Add a project](#adding-a-project)
* [Edit a project](#editing-a-project)
* [Delete a project](#deleting-a-project)
* [Mark a project as done](#marking-a-project-as-done)
* [Mark a project as not done](#marking-a-project-as-not-done)
* [Clear project list](#clearing-the-project-list)
* [Find projects](#finding-projects)
* [Sort projects](#sorting-all-projects)
* [Link a project to a client](#linking-a-project-to-a-client)

### Listing all projects
#### Format: `list-project`

Short form: `lp`

Lists out all projects and shows the project list.

### Adding a project
#### Format: `add-project <name/NAME> [deadline/DEADLINE] [price/PRICE] [tag/TAG]* [client/CLIENT]*`

Short form: `ap <n/NAME> [d/DEADLINE] [p/PRICE] [t/TAG]* [c/CLIENT]*`

Adds a project to the application with the supplied details. The details that can be supplied are the name, deadline, price, tags and linked client of the project.

Only the name of the project is compulsory. 

Deadlines can either be in natural language, such as `tomorrow` or in recognisable formats like `3pm 2023-03-03`.

Prices must be in a recognisable price format, such as `3.08` or `5`.

Clients can be linked by entering individual keywords that are part of the clients name. For example, if you wish to link the project to the client with the name `Alice Wheeler`, you can input `alice` or `wheeler`. Further steps to link to a client can be found [here](#linking-a-project-to-a-client).

Note: 
* Each tag to be added needs a separate `tag/TAG` flag.
* Client name keywords can be separated by either spaces or a prefix e.g. `client/alice client/wheeler` is the same as `client/alice wheeler`.
* Invalid client name keywords will be ignored.
* Empty prefixes for optional fields will be ignored.

Examples:
* `add-project name/Background Commission deadline/2023-05-05 price/500 tag/painting client/alice client/wheeler` Adds a project with the name Background Commision, a deadline of 5th May 2023, a price of $500, is tagged painting; and links this project to a client whose name contains any of the keywords `alice` or `wheeler`.
* `add-project name/Oil Painting`
* `ap n/Background Commission d/2023-05-05 pr/500 t/painting c/alice c/wheeler`

### Editing a project
#### Format: `edit-project <index> [name/NAME] [deadline/DEADLINE] [price/PRICE] [client/CLIENT]`

Short form: `ep <index> [n/NAME] [d/DEADLINE] [p/PRICE] [c/CLIENT]`

Edits the project at the given index of the client list, changing only the given field(s). Any fields that are mentioned but left empty will be deleted (apart from the name).

Fields that can be changed:
* Name
* Deadline
* Price
* Tags
* Linked client

Note:
* Using an empty `tag/` flag removes all tags of the project. This cannot be used with any non-empty `tag/` flags e.g. `edit-project 1 tag/ tag/painting` is not valid.
* Using an empty `client/` flag removes the linked client of the project. This cannot be used with any non-empty `client/` flags e.g. `edit-project 1 client/ client/alice` is not valid.
* Client name keywords can be separated by spaces or prefixes. E.g. `name/alice bob` is the same as `name/alice name/bob`
* Invalid client name keywords will be ignored.
* At least one field to edit must be provided.

The steps to link to a client can be found [here](#linking-a-project-to-a-client).

Example:
*  `edit-project 2 name/The Starry Night tag/` Edits the name of the 2nd project in the list to be `The Starry Night` and removes all tags.
* `edit-project 2 client/alice` Links the 2nd project in the list to a client whose name contains the keyword `alice`.
* `ep 2 n/The Starry Night pr/500`

### Deleting a project
#### Format: `delete-project <index>`

Short form: `dp <index>`

Deletes the project at the specified index of the project list.

Notes:
* The index refers to the index number shown in the displayed project list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
*  `list-project` followed by `delete-project 1` deletes the first project in the list (if there is one).

### Marking a project as done
#### Format: `mark <index>`

Short form: `mp <index>`

Marks the project at the specified index as done.

Notes:
* The index refers to the index number shown in the displayed list of projects.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list-project` followed by `mark 2` marks the 2nd project in the list of projects as done.

### Marking a project as not done
#### Format: `unmark <index>`

Short form: `up <index>`

"Un-marks" the project at the specified index, indicating that it is not done.

Notes:
* The index refers to the index number shown in the displayed list of projects.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list-project` followed by `unmark 2` indcates that the 2nd project in the list of projects is not done.

### Clearing the project list
#### Format: `clear-project`

Short form: `cp`

Deletes all projects in the project list.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command cannot be undone. All deleted projects cannot be restored.
</div>

### Finding projects
#### Format: `find-project [name/NAME]* [start/START] [end/END] [price/PRICE] [status/STATUS] [tag/TAG]* [client/CLIENT]*`

Short form: `fp [n/NAME]* [s/START] [e/END] [p/PRICE] [st/STATUS] [t/TAG]* [c/CLIENT]*`

Finds a project based on details provided. Details that can be supplied are the name, the start and end of the timeframe the deadline of the project should fall into, price, tags, the client the project is linked to, and the status of the project.

Note: 
* The matching with supplied names and tags are case-insensitive.
* Project names, tags and linked client names can either be separated by spaces or prefixes. E.g. `name/alice bob` is the same as `name/alice name/bob`
* Invalid project names, tags and linked client names will be ignored
* Status must be specified as either `not done`/`nd` or `done`/`d`. Overdue projects are included in "not done".

Examples:
* `find-project name/sculpture client/alice tag/personal start/yesterday end/tomorrow price/500 status/done` will find any project whose name contains `sculpture`, is linked to a client whose name contains `alice`, is tagged `personal`, has a price of $500, is done and has a deadline that falls between yesterday and tomorrow.

### Sorting all projects
#### Format: `sort-project <option/Option>`

Short form: `sp <o/OPTION>`

Sorts all projects that exist in the ArB. Projects can be sorted via the given options in ascending order:
* Name
* Deadline
* Price

Note:
* Option matching is case-insensitive

Examples: 
* `sort-project option/name`
* `sort-project option/n`
* `sort-project option/deadline`
* `sort-project option/d`
* `sort-project option/price`
* `sort-project option/pr`

### Linking a Project to a Client

This is only applicable if the client parameter has been specified when [adding a project](#adding-a-project-add-project) or [editing a project](#editing-a-project--edit-project).

ArB will display a list of clients that match the provided keywords. Entering an index specifies the client in the list to link to a project. Entering `0` will cancel the linking operation, but the previously added or edited project will remain.

Examples:
* `1` links the 1st client in the shown list of clients
* `0` cancels the linking operation and returns to the project list

## Tag commands

### Listing all tags
#### Format: `list-tag`

Short form: `lt`

Lists all tags that exist in the ArB. These include tags added to both clients and projects. The list shows how many clients and how many projects a particular tag is used with.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous artistic addressbook home folder.
