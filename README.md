README for SE310 HW:


<br/>This Project is all about making a custom survery and test builder which can be used to take these as well. Originally, this project was distributed into 4 parts.<br/>
So, documentation about the Survey and Tests are provided as following:
Part1:https://www.cs.drexel.edu/~umpeysak/Classes/SoftDesignCS350/CS350Homework1Part1.html
<br/> Part2: https://www.cs.drexel.edu/~umpeysak/Classes/SoftDesignCS350/CS350Homework1Part2.html
<br/> Part3: https://www.cs.drexel.edu/~umpeysak/Classes/SoftDesignCS350/CS350Homework1Part3.html
<br/> Part 4: https://www.cs.drexel.edu/~umpeysak/Classes/SoftDesignCS350/CS350Homework1Part4.html

<br/>File Input / Output method used: Serialization

<br/>For Part-4: 
	<br/>Ranking question is added, which is extending the MCQ question type
	<br/>I/O is abstracted out in Input and Output class

<br/>Please read the following guidelines:

<br/>1) Please be careful about the captialization everywhere. Program will not going to fail, but results would different than the expectations.
<br/>2) To allow multiple responses for all the quesitons, user can input in console till he/she entered a blank. Also, for MCQ, program does not allow user 
	to enter more than the choices. 
	<br/>For example:
	<br/>Which one/s are countries ?
	1) France
	2) Australia
	3) Paris
	4) Canada
	<br/>Please enter your response:
	4
<br/>	2
<br/>	1

<br/>	What is an object ?
	<br/>Please enter your response:
	<br/>object is a set of responsibilities
	<br/>object is an instance of a class

<br/>3) Multiple correct answers are also allowed for short-asnwer questions.
<br/>4) For the invalid input, user would be granted to input again until input matches the guidelines.
<br/>5) I have attached the generated survey 1 and test 1 with each containing the all kind of questions, with three set of responses.
<br/>6) I have covered all the cases that I could think of where program could have failed due to an input error 
	so, program is fairly tested so, should not fail in any case of input.

