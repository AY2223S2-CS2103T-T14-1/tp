---
layout: page
title: User Guide
---

Artistic addressbook (ArB) is a **desktop app for artists, meant to help with managing clients and project information**, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ArB can get your contact and project management tasks done faster than traditional GUI apps.

## **Table of Contents**
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `arb.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ArB

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar arb.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. The app may contain some sample data if being opened for the first time.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list-client` : Lists all clients.

   * `add-client name/John Doe phone/98765432 email/johnd@example.com` : Adds a client named `John Doe` to the ArB.

   * `delete-client 3` : Deletes the 3rd client shown in the current client list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## **Syntax**

`[ACTION VERB] [options]`

`<Required argument> [optional argument]`

An asterisk `*` after any argument indicates that it can be entered a variable number of times.

All commands are case insensitive.

## **Prefixes**

1. `name/n` -> name: name
2. `email/e` -> email: valid email
3. `phone/p` -> phone: valid phone
4. `deadline/d` -> deadline: valid deadline
5. `price/pr` -> price: valid price
6. `tag/t` -> tag: valid tag
7. `client/c` -> client: keywords to search for clients to link to a project
8. `option/o` -> option: option to sort the project list with
9. `start/s` -> start: valid start of a timeframe
10. `end/e` -> end: valid end of a timeframe
11. `status/st` -> status: valid status

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

* Extraneous parameters for commands that do not take in parameters (such as [help](#viewing-help-help), [list-client](#list-all-clients-list-client), [list-project](#listing-all-projects--list-project), [list-tag](#listing-all-tags--list-tag), [exit](#exiting-the-program--exit), [clear-client](#clear), [clear-project](#clear) and [sort-client](#sorting-all-clients--sort-client)) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Most commands and parameters have shorter aliases that can be used the same way, such as [list-project](#listing-all-projects--list-project) having the alias `lp` and `name/` having the alias `n/`.<br>
  e.g. specifying `list-project` is the same as specifying `lp` and specifying `add-project name/John Doe` is the same as specifying `add-project n/John Doe`.

</div>

## General commands

### Viewing help: `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ArB's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ArB's data is saved as a JSON file `[JAR file location]/data/arb.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ArB will discard all data and start with an empty data file at the next run.
</div>

## Client commands

### Listing all clients: `list-client`
#### Alias: `lc`

Lists out all clients and shows the client list.

Format: `list-client`

### Adding a client: `add-client`
#### Alias: `ac`

Adds a client to the application with the supplied details. The details that can be supplied are the name, email address and phone number of the client, along with any number of tags to be added.

Only the name of the client is compulsory. 

The email address and phone number must be in a valid format. E.g. `XXX@gmail.com` or ```XXX@yahoo.com``` for emails and `12345678` for phone numbers.

Format: `add-client <name/NAME> [email/EMAIL] [phone/PHONE_NUMBER] [tag/TAG]*`

Note: each tag to be added needs a separate `tag/TAG` flag.

Examples:
* `add-client name/Bob phone/12345678 email/bob@gmail.com tag/friend tag/default`
* `add-client name/Alice`
* `add-client name/Clary phone/87654321 email/clary@gmail.com`

### Editing a client : `edit-client`
#### Alias: `ec`

Edits the client at the given index of the client list, changing only the given field(s). Any fields that are mentioned but left empty will be deleted (apart from the name).

Fields that can be changed:
* Name
* Email address
* Phone number
* Tags

Note: 
* Using an empty `tag/` flag removes all tags of the client. This cannot be used with any non-empty `tag/` flags e.g. `edit-client 1 tag/ tag/friend` is not valid.
* At least one field to edit must be provided.

Format: `edit-client <index> [name/NAME] [email/EMAIL] [phone/PHONE] [tag/TAG]*`

Examples:
*  `edit-client 1 email/new@email.com` Edits the email address of the 1st client to be `new@email.com`.
*  `edit-client 3 name/Alice Risa phone/1234 tag/` Edits the name of the 3rd client to `Alice Risa` and phone number to `1234`. Removes any tags.

### Deleting a client : `delete-client`
#### Alias: `dc`

Deletes the client at the specified index of the client list.

Notes:
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Format: `delete-client <index>`

Example:
*  `list-client` followed by `delete-client 1` deletes the first client in the list (if there is one).

### Finding a client : `find-client`
#### Alias: `fc`

Finds a client based on the details provided. Details that can be supplied are the names and tags.

Note: 
* The matching with supplied names and tags are case-insensitive.

Format: `find-client [name/NAME]* [tag/tag]*`

Examples:
* `find-client name/bob tag/friend` will find any client whose name contains the word `bob` and is tagged with `friend`.
* `find-client name/bob name/alice tag/friend tag/husband` will find any client whose name contains either `bob` or `alice`, and is tagged with either `friend` or `husband`.

### Sorting all clients : `sort-client`
#### Alias: `sc`

Sorts all clients that exist in the ArB by name in ascending order.

Format: `sort-client`

## Project commands

### Listing all projects : `list-project`
#### Alias: `lp`

Lists out all projects and shows the project list.

Format: `list-project`

### Adding a project: `add-project`
#### Alias: `ap`

Adds a project to the application with the supplied details. The details that can be supplied are the name, deadline, price, tags and linked client of the project.

Only the name of the project is compulsory. 

Deadlines can either be in natural language, such as `tomorrow` or in recognisable formats like `3pm 2023-03-03`.

Prices must be in a recognisable price format, such as `3.08` or `5`.

Clients can be linked by entering individual keywords that are part of the clients name. For example, if you wish to link the project to the client with the name `Alice Wheeler`, you can input `alice` or `wheeler`. Further steps to link to a client can be found [here](#linking-a-project-to-a-client).

Format: `add-project <name/NAME> [deadline/DEADLINE] [price/PRICE] [tag/TAG]* [client/CLIENT]*`

Note: 
* Each tag to be added needs a separate `tag/TAG` flag.
* Each client name keyword needs a separate `client/CLIENT` flag.

Examples:
* `add-project name/Background Commission deadline/2023-05-05 price/500 tag/painting client/alice client/wheeler` Adds a project with the name Background Commision, a deadline of 5th May 2023, a price of $500, is tagged painting; and links this project to a client whose name contains any of the keywords `alice` or `wheeler`.
* `add-project name/Oil Painting`
* `ap n/Background Commission d/2023-05-05 pr/500 t/painting c/alice c/wheeler`

### Editing a project : `edit-project`
#### Alias: `ep`

Edits the project at the given index of the client list, changing only the given field(s). Any fields that are mentioned but left empty will be deleted (apart from the name).

Fields that can be changed:
* Name
* Deadline
* Price
* Tags
* Linked client

Note:
* Using an empty `tag/` flag removes all tags of the project. This cannot be used with any non-empty `tag/` flags e.g. `edit-project 1 tag/ tag/painting` is not valid.
* At least one field to edit must be provided.

The steps to link to a client can be found [here](#linking-a-project-to-a-client).

Format: `edit-project <index> [name/NAME] [deadline/DEADLINE] [price/PRICE] [client/CLIENT]`

Example:
*  `edit-project 2 name/The Starry Night tag/` Edits the name of the 2nd project in the list to be `The Starry Night` and removes all tags.
* `edit-project 2 client/alice` Links the 2nd project in the list to a client whose name contains the keyword `alice`.
* `ep 2 n/The Starry Night pr/500`

### Marking a project as done : `mark`
#### Alias: `mp`
Marks the project at the specified index as done.

Format: `mark <index>`

Notes:
* The index refers to the index number shown in the displayed list of projects.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list-project` followed by `mark 2` marks the 2nd project in the list of projects as done.

### Unmarking a project as done : `unmark`
#### Alias: `up`

Unmarks the project at the specified index, indicating that it is not done.

Format: `unmark <index>`

Notes:
* The index refers to the index number shown in the displayed list of projects.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list-project` followed by `unmark 2` indcates that the 2nd project in the list of projects is not done.

### Deleting a project : `delete-project`
#### Alias: dp

Deletes the project at the specified index of the project list.

Notes:
* The index refers to the index number shown in the displayed project list.
* The index **must be a positive integer** 1, 2, 3, …​

Format: `delete-project <index>`

Example:
*  `list-project` followed by `delete-project 1` deletes the first project in the list (if there is one).

### Finding a project: `find-project`
#### Alias: fp

Finds a project based on details provided. Details that can be supplied are the name, the start and end of the timeframe the deadline of the project should fall into, price, tags, the client the project is linked to, and the status of the project.

Note: 
* The matching with supplied names and tags are case-insensitive.
* Status must be specified as either `not done` or `done`.

Format: `find-project [name/NAME]* [start/START] [end/END] [price/PRICE] [status/STATUS] [tag/TAG]* [client/CLIENT]*`

Examples:
* `find-project name/sculpture client/alice tag/personal start/yesterday end/tomorrow price/500 status/done` will find any project whose name contains `sculpture`, is linked to a client whose name contains `alice`, is tagged `personal`, has a price of $500, is done and has a deadline that falls between yesterday and tomorrow.

### Sorting all projects : `sort-project`
#### Alias: `sp`

Sorts all projects that exist in the ArB. Projects can be sorted via the given options in ascending order:
* Name
* Deadline
* Price

Format: `sort-project <option/Option>`

Examples: 
* `sort-project option/name`

### Linking a Project to a Client

This is only applicable if the client parameter has been specified when [adding a project](#adding-a-project-add-project) or [editing a project](#editing-a-project--edit-project).

ArB will display a list of clients that match the provided keywords. Entering an index specifies the client in the list to link to a project.

Examples:
* `1` links the 1st client in the shown list of clients

## Tag commands

### Listing all tags : `list-tag`
#### Alias: `lt`

Lists all tags that exist in the ArB. These include tags added to both clients and projects. The list shows how many clients and how many projects a particular tag is used with.

Format: `list-tag`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous artistic addressbook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples                                                                                                              |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------|
| **List Clients**    | `list-client` |
| **Add a Client**     | `add-client <name/NAME> [email/EMAIL] [phone/PHONE_NUMBER] [tag/TAG]*​` <br> e.g., `add-client name/Bob phone/12345678 email/bob@gmail.com tag/friend` |
| **Edit a Client**    | `edit-client <index> [name/NAME] [email/EMAIL] [phone/PHONE] [tag/TAG]*​` <br> e.g.,`edit-client 3 name/Alice Risa phone/1234 tag/classmate` |
| **Delete a Client**  | `delete-client <index>`<br> e.g., `delete-client 1`|
| **Finding a Client** | `find-client [name/NAME]* [tag/TAG]*` <br> e.g., `find-client name/bob name/alice tag/friend` |
| **Sorting all Clients** | `sort-client` |
| **List Projects**   | `list-project` |
| **Add a Project**    | `add-project <name/NAME> [deadline/DEADLINE] [price/PRICE] [tag/TAG]*` <br> e.g., `add-project name/Background Commission deadline/2023-05-05 price/400 tag/painting` |
| **Edit a Project**   | `edit-project <index> [name/NAME] [deadline/DEADLINE] [price/PRICE] [tag/TAG]*` <br> e.g., `edit-project 2 name/The Starry Night` |
| **Mark a Project**   | `mark <index>` <br> e.g., `mark 3` |
| **Unmark a Project** | `unmark <index>` <br> e.g., `unmark 3` |
| **Delete a Project** | `delete-project <index>`<br> e.g., `delete-project 1` |
| **Finding a Project** | `find-project [name/NAME]* [start/START] [end/END] [tag/TAG]* [status/STATUS]` <br> e.g., `find-project name/sky start/yesterday tag/painting` |
| **Sorting all Project**   | `sort-project option/Option` <br> e.g., `sort-project option/name` |

