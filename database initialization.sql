
CREATE SCHEMA TEST_GENERATOR;
USE TEST_GENERATOR;

CREATE TABLE question_table
(qid INT NOT NULL ,
questStr VARCHAR(100) NOT NULL,
qType VARCHAR(20) NOT NULL,
CONSTRAINT pk_question PRIMARY KEY (qid),
CONSTRAINT chck_qid CHECK(qid>=1000)
);

CREATE TABLE multiChoice_table
(qid INT NOT NULL,
numOfAnswers INT NOT NULL,
numOfCorrectAnswers INT NOT NULL,
CONSTRAINT check_numOfAnswers CHECK(numOfAnswers >= 2),
CONSTRAINT pk_multiChoice PRIMARY KEY (qid),
CONSTRAINT fk_multiChoice FOREIGN KEY(qid)
			REFERENCES question_table(qid)
			ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE openQuestion_table
(qid INT NOT NULL,
CONSTRAINT pk_Open PRIMARY KEY (qid),
CONSTRAINT fk_Open FOREIGN KEY(qid)
			REFERENCES question_table(qid)
			ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE test_table
(tid INT NOT NULL,
desiredNumOfQuestions INT NOT NULL,
numOfQuestions INT NOT NULL,
CONSTRAINT desiredQuestions CHECK(desiredNumOfQuestions >= 1),
CONSTRAINT numOfQuestions CHECK(numOfQuestions >=1),
CONSTRAINT pk_test PRIMARY KEY (tid),
CONSTRAINT chck_tid CHECK(tid>=10)
);

CREATE TABLE answer_table
(aid INT NOT NULL,
answerStr VARCHAR(200) NOT NULL,
CONSTRAINT pk_answer PRIMARY KEY(aid),
CONSTRAINT chck_aid CHECK(aid>=10)
);

CREATE TABLE openQuestion_answer_table 
(qid INT NOT NULL,
aid INT NOT NULL,
isTrue BOOLEAN DEFAULT TRUE,
CONSTRAINT  pk_openQuestion_answer PRIMARY KEY(qid),
CONSTRAINT fk_openQuestion_answer_qid FOREIGN KEY(qid)
			REFERENCES openQuestion_table(qid)
            ON DELETE CASCADE,
CONSTRAINT fk_openQuestion_answer_aid FOREIGN KEY(aid)
			REFERENCES answer_table(aid)
            ON DELETE CASCADE
);

CREATE TABLE multiChoice_answer_table
(qid INT NOT NULL,
aid INT NOT NULL,
isTrue BOOLEAN NOT NULL,
CONSTRAINT  pk_multiChoice_answer PRIMARY KEY(qid,aid),
CONSTRAINT fk_multiChoice_answer_qid FOREIGN KEY(qid)
			REFERENCES multiChoice_table(qid)
            ON DELETE CASCADE,
CONSTRAINT fk_multiChoice_answer_aid FOREIGN KEY(aid)
			REFERENCES answer_table(aid)
            ON DELETE CASCADE
);


CREATE TABLE test_question_table
(tid INT NOT NULL,
qid INT NOT NULL,
CONSTRAINT pk_test_question PRIMARY KEY (tid,qid),
CONSTRAINT fk_test_question_tid FOREIGN KEY (tid)
			REFERENCES test_table(tid)
            ON DELETE CASCADE,
CONSTRAINT fk_test_question_qid FOREIGN KEY (qid)
			REFERENCES question_table(qid)
            ON DELETE CASCADE
);

