# COMP1010 S1 2024 Assignment 2

## Worth 20% of your final marks

## Due date: Sunday 19th May

## Previewing .md files

Use the preview icon (with the magnifying glass) towards top right of VS Code window to see the preview of these .md files. Alternatively, use Ctrl-Shift-V (Cmd instead of Ctrl on Mac).

## Relevant Teaching Content

This assignment covers classes and objects, ArrayLists, recursion and recursive data structures. And, of course, everything before is assumed knowledge, so that's implicitly assessed too. Weeks 3 to 9 lectures and weeks 4 to 10 practicals are relevant.

## Group and individual component

The assignment is divided into two parts:

1. Submission worth 10%: This is a group component. You can form groups of **up to** 4 students using this [group self-selection page](https://ilearn.mq.edu.au/mod/groupselect/view.php?id=8173066). You can create groups. Group names MUST begin with "Assignment 2 Group " and then have an acceptable suffix. Group name NOT beginnning with "Assignment 2 Group " will be looked down upon and will get a 0.1 out of 10 mark penalty, small enough to not cause you heartache, but big enough so you follow the specs in future. Some examples "Assignment 2 Group Power Rangers" or "Assignment 2 Group Darth Vader". Any inappropriate group name will be reported to the university as unwanted behaviour (coz no one should have to deal with such behaviour). 

	Once you join a group, you cannot leave, so think carefully before joining a group. If, for some reason, you really need to leave a group, send me an email at gaurav.gupta@mq.edu.au and cc all other group members, politely explain the difference of opinions or lack of compatible schedule or whatever the reason is, and I will remove you from the group. 

	To stop random students from joining your group, you can set passwords that you can then share with others you wish to join your group. 

	Everyone must have a group to submit. If you wish to do the assignment individually (on your own), create a group and set a strong password so no one can join. 

	Students who don't have a group by Sunday 12th May, will be put into a group on their own and will need to submit individually. Only if you have already been working with other student(s) and all students involved are willing for you to join their group, one of the existing group members must send an email to me at gaurav.gupta@mq.edu.au, outlining the contributions of the member to be added, and we will make a decision whether there's sufficient evidence that you have already been working together or not.

	While you can divide the functions amongst group members, all group members should collaboratively discuss all solutions, and must be confident that they can solve every single function on their own, as this will be assessed in the second part of the assignment.

2. Live coding component (40-minute iLearn quiz during weeks 13 to 16, in self-enrolled sessions): This is similar to module exams and will contain questions analogous to the ones in the assignment. 

	For example, the assignment contains a function, that when passed an ArrayList of Card objects, returns true if all items of an ArrayList belong to the same suit, false otherwise. In the live coding, we may give you a function, that when passed an ArrayList of Songs, returns true if all Songs are by the same artist "Tay Tay", false otherwise.

	Please note that a practice quiz will not be supplied for part 2 since the assignment functions themselves are exemplars of the kind of questions that will be asked. Also note that while the domain in live coding might be different, the fundamental problems will be analogous. Again, please be aware that live coding can have something like a recursive list of **Circle** objects (instead of **Hand** object, as in the assignment).

## Domain

The assignment is based on playing cards. It's not an issue if you've never playing any card-based game before. The only things you really need to know are:

1. There are 4 suits: Clubs, Diamonds, Hearts and Spades
2. There are 13 ranks for every suit: Two to Ten - with obvious values, Jack (value 11), Queen (value 12), King (value 13) and Ace (value 14, but can be 1 as well)
3. Ace can be treated as value 14 or 1, based on your convenience. 
4. A collection of `N` cards is said to be in *sequence* if,
	1. `N >= 3`, and, 
	2. if the smallest rank in the card is `R` (Ace can be treated as 1 or 14, but not both), then the collection also contains 1 card **exactly** with rank `R+1`, `R+2`, ..., `R+N-1`.
5. A collection is said to have *all same suit* if it has at least 3 cards, and every single card in the collection is of the same suit.

## Stage-wise implementation

There are 5 stages in this assignment.

1. Stage 1: Classes and Objects
	1. `Rank.java`: 
	2. `Suit.java`
	3. `Card.java`	
2. Stages 2, 3: Classes and Objects, ArrayLists
	1. `Deck.java`
	2. `Hand.java` (This is the biggest component of the assignment) 
3. Stages 4, 5: Recursive Data Structures
	4. `HandNodeA.java`
	5. `HandNodeB.java` (HD-level)

Suggested timelines to stay on top of the assignment

1. Stage 1: May 3th
2. Stage 2: May 7th
3. Stage 3: May 12th
4. Stage 4: May 16th
5. Stage 5: May 19th

## Late submissions

Please refer to the unit guide on late submission policy. Late submissions are accepted up to 7 days after the due date. After that, you need special consideration approval to be able to make a submission.

## Importing libraries and other restrictions

- For stage 1, you don't really need to use any class. Importing and/or using any library will result in a zero for stage 1.
- For stages 2 and 3, you can use ArrayList class. Importing and/or using any library besides `ArrayList` will result in a zero for that stage.
- For stages 4 and 5, given the purpose is to implement recursive data structures, you cannot use arrays or arraylists. Any use of arrays or arraylists will automatically disqualify that part of your submission. Importing and/or using any library will result in a zero for relevant stage.

While we encourage students to research on their own, we are conscious of the fact that "picking up" code off the internet is not beneficial for your long-term development. With this in mind, concepts that have not been covered in COMP1000/COMP1010, such as HashMaps, streams, lambdas, regular expressions, are not allowed in this assignment.

## Doubts about Javadoc

When in doubt about javadoc, take a look at the test cases. Most of the time they should clarify things for you. If still in doubt, feel free to ask generic questions about the assignment on General Discussion Forums. Please prefix the subject of such posts with "Assignment 2: " so we can easily filter them.

## Automarker availability

We will make every possible attempt to deploy the automarker on Wednesday 1st May 21:00. Hence, the submissions have been opened at that time.

## Helper functions and arrays

You can also create and use as many helper functions as you want. 

Given stage 1 is very basic classes, stages 2 and 3 operate on ArrayLists, and stages 4 and 5 use recursive data structures, use of arrays is not needed nor allowed in assignment 2.

## Dependencies

While we have tried to minimize this, there are scenarios were one or more functions must be finished successfully before starting another function. For example, in `Hand.java`, unless `hasCard` function passes, you cannot start working on `removeRandomCard` or `swap` functions.

## Stage 4 and 5 - recursion or iteration

We want you solve stages 4 and 5 recursively to help you get better for the live coding component and practical exam. There WILL be questions based on stage 4 in the live coding assessment that require recursive solutions. 

The automarker will cap the marks for stage 4 at 35 if loops are used anywhere (even in one function) in class `HandNodeA.java`.
Similarly, the automarker will cap the marks for stage 5 at 30 if loops are used anywhere (even in one function) in class `HandNodeB.java`. 

# Clients

There are clients provided for stages 2 to 5 so you can see your classes at work. Stages 4 and 5 have a simple game where the adjacent players (assuming sitting in a circle, hence, cyclic) swap cards until one of them has a perfect sequence and/or same-suit hand. You can imagine having both is pretty improbable, and you'll almost always hit either one of them, before hitting both at the same time.

Feel free to add more clients of your own, even add functions to given classes if you'd like to extend the functionality and send me the updated files by email if you would like me to take a look, and then, with your permission, share with the rest of the cohort as an exhibition!

## Code Style

There is no code style component in this assignment. But you should still follow the code style guide and develop good programming habits like proper variable-naming and indentation, small functions, delegation and commenting. Also, have cloud backups in case your computer acts up.

## Re-submitting assignments after the deadline

It is possible that you do not look at the feedback in time, and realize that you were docked points for using a library in one of the stages. You will be penalized 5% (per day or part of) of the maximum marks for the assignment if you submit any file(s) after the deadline.
So, please think carefully, coz it's not worth re-submitting a file that will earn you less than or equal to 0.5 out of 10 extra marks on the first day after deadline, a file that will earn you less than or equal to 1 out of 10 extra marks on the second day after deadline, and so on.

## Submission

Only one member per group should make a submission. Please setup a communication channel (Discord/ Forum/ Whatsapp/ ...) to talk to each other regularly.

The files that you must submit are:

1. `Rank.java`
2. `Suit.java`
3. `Card.java`	
4. `Deck.java`
5. `Hand.java`
6. `HandNodeA.java`
7. `HandNodeB.java`

They must be named exactly as listed above (case-sensitive), otherwise it will raise a Compilation Error and therefore be rejected by the automarker. 

The automarker sets up a fresh project and populates it with the files you submit. If you don't submit one or more files, it uses the corresponding template file(s). So if you submit `Hand.java` but fail to submit `Card.java`, we will use the template `Card.java` which will result in most of the functions from `Hand.java` not passing either.

It's the group's responsibility, and not ours, to make a valid submission. We suggest one group member making the submission, and others checking the feedback in about an hour from the submission, to make sure it is all good.

## Academic Integrity

This is a group assignment and the work that you submit should have been done by your group and not and not other groups and/or using Generative AI. Breaches of academic honesty are reported to the University Disciplinary Committee.

## Computing Drop-in Centre

Remember to go to the Computing Drop-in Centre for guidance with the content related to the assessment. So, while they won't directly help you with the assignment, they can help you with any of the content at https://softwaretechnologymq.github.io/Strings or any of the problems on the codingbat website linked on the lecture notes page.

## Test bugs/mistakes

As much as we like to make sure that our tests are correct, it is possible that there might be some bugs. Students who find bugs can report it by emailing me with subject "COMP1010 Assignment 2 - I (think I) found a bug!". You will be given 20 house points if there is a test bug that has not yet been reported.

## Any other questions

Please feel free to ask generic questions about the assignment on General Discussion Forums.
Please prefix the subject of such posts with "Assignment 2: " so we can easily filter them.

All the best!
COMP1010 Teaching Team