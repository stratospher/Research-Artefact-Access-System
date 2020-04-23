# Research-Artefact-Access-System

## Functional Requirements:

* Only those researchers who have been permitted by superiors must be able to access the

  system. This is to be verified from the database based on column ‘AdvisorApproved’ in the

  corresponding researcher table

* Researchers should be able to view previously developed artefacts \(such as reports,

  published papers etc\) by year or by topic.

* Researchers should be able to open and read the contents of the corresponding file by

  providing file information \(such as file no\).

* A researcher should be able to update his/her research information relevant to his work and

  upload files.

* Superiors of a particular researcher should be able to view his/her subordinates research

  updates

* Details regarding research must be updated in corresponding background tables including

  relevant files

  **Implementation Requirements: \#\#**

  DB Design:Use MySQL database to store tables containing user level information, research level

  information and file level information. Relevant columns in research data table can be decided by

  developer.

  Screens:

* [x] Screen 1 : Text Boxes for username and password and submit button to be provided.
* [x] Screen 2: To view information for Superior.
  * This screen should provide a list of corresponding subordinates.
  * Once a researcher’s name is selected the new screen should populate the corresponding

    researchers work information from database and also option to view any corresponding

    files using ‘View File’ button
* [x] Screen 3: This should be visible only for researchers.
  * If a researcher is approved to view others work then this screen should have facility to

    select some work based on year/ search by Topic.

  * Update button to be provided to redirect to screen 3
* [x] Screen 4:
* Provision to enter individual research data and upload files.
* Provision to view existing research data.
* The file uploaded to be maintained at a disk location

