USE test_generator;

INSERT INTO question_table VALUES
(1000,"What is largest mammal","eMULTI_CHOICE"),
(1001,"The first person to walk on the moon","eOPEN_QUESTION"),
(1002,"A newton is a unit of what?","eOPEN_QUESTION"),
(1003,"Who is Harry Potter?","eMULTI_CHOICE"),
(1004," What color is the octopus blood?","eMULTI_CHOICE"),
(1005,"What were Carrot, Goat, and Onion in WWII?","eMULTI_CHOICE");

INSERT INTO multichoice_table VALUES
(1000, 6, 1),
(1003, 6 , 1 ),
(1004, 6 , 1),
(1005, 6, 1);

INSERT INTO openquestion_table VALUES
(1001),
(1002);

INSERT INTO answer_table VALUES
(10, "There is more than one correct answer"),
(11,"All answers are incorrect"),
(12, "Whale"),
(13, "Girrafe"),
(14, "Lion"),
(15, "Deer"),
(16, "Neil Armstrong"),
(17, "Force"),
(18, "A Wizard"),
(19, "Football player"),
(20, "Soccer player"),
(21,"Software engineer"),
(22, "Yellow"),
(23,"Black"),
(24,"Light blue"),
(25,"Green"),
(26,"Vegetables"),
(27,"Explosives"),
(28,"Vehicles"),
(29,"Soldiers");

INSERT INTO multichoice_answer_table VALUES
(1000, 10, 0),
(1000, 11, 0),
(1000, 12, 1),
(1000, 13, 0),
(1000, 14, 0),
(1000, 15, 0),
(1003, 10, 0 ),
(1003, 11, 0 ),
(1003, 18 , 1 ),
(1003, 19, 0 ),
(1003, 20 , 0 ),
(1003, 21 , 0 ),
(1004, 10 , 0),
(1004, 11 , 0),
(1004, 22 , 0),
(1004, 23 , 0),
(1004, 24 , 1),
(1004, 25 , 0),
(1005, 10, 0),
(1005, 11, 0),
(1005, 26, 0),
(1005, 27, 1),
(1005, 28, 0),
(1005, 29, 0);

INSERT INTO openquestion_answer_table VALUES
(1001,16,1),
(1002,17,1);

INSERT INTO test_table VALUES
(10,3,3),
(11,4,4);

INSERT INTO test_question_table VALUES
(10,1000),
(10,1003),
(10,1005),
(11,1001),
(11,1004),
(11,1003),
(11,1002);


