## About this Competition

The goal of this competition was to create a console-based application for helping Ontario Skills manage their
competitions. The overall goal was to create a integrated storage file system. We needed to generate a database or text file to store contestant data.

The file must include the following:
- ID: A generated incrementing 0 padded 8 digit number. For example, “123” should be padded as
“00000123”. “12345” should be padded as “00012345”.
- First Name: The contestant’s first name, mandatory.
- Last Name: The contestant’s last name, mandatory
- Email Address: The contestants email address.
- School District: The list of available school districts should be read in from a file containing the following options: Algoma, Avon, Bluewater, Niagara, Durham, Erie, Halton, Hamilton, Hastings, Toronto, Simco, Hamilton, Waterloo, Upper Canada.
- Birthday: Date of the contestant’s birth. Format dd/mm/yyyy.
- Competition : What event the contestant is competing in. (example: Coding, IT Software, Networking)
- Score: The score the contestant received (assume it is a percent value).

## Folder Structure

The repository is setup like this:

- `src`: the folder containing my project files
- `categories.txt`: the file specifying what categories are acceptable inputs
- `districts.txt`: the file specifying what categories are acceptable inputs

> If you want to customize the file contents, my program ensures that the only fields inside `categories` and `districts` will be the only ones allowed for students.

## How did I do?

I got 4th in the province. I also placed 1st in my region, however the program was done on a school-provided computer.
