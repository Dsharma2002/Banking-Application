# My Personal Project- CPSC 210 UBC 2021

## Personal Banking Application

This application is designed for the use of:
- Keeping a check on **balance** and **recent transactions** for a *customer*.
This application also keeps a check on the money a *customer* **deposits** or **withdraws** from his account. 
- A particular area of interest in users as they can check how does the **interest** add to their 
current balance and how much money they might have **accumulated after years**.
- This application can be used by *anyone*, but it will mostly be used by individuals 
who want to keep a regular check on their finances.

This project is of interest to me as this plays on conditions on when a user can deposit and withdraw money to his 
account and when he can not. This idea ensures that an invalid amount has not been processed.
It is a healthy practice to keep a check on self finance and make informed decisions regarding when to spend
and when not to spend. These conditions can be implemented in an error free manner. This is the ideology
I have, and I hope to design this application in the best possible way.


User Stories:
- As a user, I want to be able to add multiple Transaction to Account.
- As a user, I want to be able to add money to my account.
- As a user, I want to be able to withdraw money from my account.
- As a user, I want to be able to check my remaining balance in my account.
- As a user, I want to be able to check my most recent transaction.
- As a user, I want to be able to view my account balance after years.
of interest.
- As a user, I want to be able to save my transactions to file, particularly my balance.
- As a user, I want to be able to load my transactions from file, particularly my balance.


## Phase 4: Task 2
This is a Logger of how steps were carried out by the application on user commands.

Wed Nov 24 20:56:39 PST 2021
Added money to account!

Wed Nov 24 20:56:41 PST 2021
adds transaction to this workroom

Wed Nov 24 20:56:41 PST 2021
returns in workroom as a JSON array

Wed Nov 24 20:56:46 PST 2021
Withdrew money from account!

Wed Nov 24 20:56:46 PST 2021
returns list of transactions in this workroom

Wed Nov 24 20:56:46 PST 2021
returns list of transactions in this workroom

Wed Nov 24 20:56:47 PST 2021
adds transaction to this workroom

Wed Nov 24 20:56:47 PST 2021
returns in workroom as a JSON array

Wed Nov 24 20:56:53 PST 2021
adds transaction to this workroom

Wed Nov 24 20:56:53 PST 2021
returns in workroom as a JSON array

## Phase 4: Task 3
- If given a chance the following would be implemented for making it clean and reusable.

If I had more time to work on my project, I would have attempted the following
refactoring to improve my design: 
- I could have used Singleton Design pattern where my Transaction class had only one 
instance and this single object could be used by all other classes. This would have 
decreased coupling in my system and Account and AccountGUI classes could have 
used that particular transaction object.
- My AccountGUI has buttons and text fields which perform particular tasks. 
I could have extracted them and put them in separate classes in ui package.
This would give my project better readability and structure.
- I would insert exception handling to check my system and try to make it 
robust.

