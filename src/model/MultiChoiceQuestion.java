package model;


import java.util.List;

public class MultiChoiceQuestion extends A_Question {

	private static final long serialVersionUID = 3874357661646318776L; // auto-generated by serializable
	private final int MAX_ANSWERS = 10;
	private MySet<Answer> answers = new MySet<Answer>();
	private int numOfAnswers = 0;
	private int correctAnswers = 0;

	public MultiChoiceQuestion() {
		this.type = E_QuestionType.eMULTI_CHOICE;
	}

	public MultiChoiceQuestion(String quest) throws Exception {
		super(quest.trim());
		addAnswer("There is more than one correct answer", false,10); // default answer #1
		addAnswer("All answers are incorrect", false,11); // default answer #2
		this.type = E_QuestionType.eMULTI_CHOICE;
	}

	public MultiChoiceQuestion(MultiChoiceQuestion question) throws Exception {
		this(question.quest);
		Answer temp;
		for (int i = 2; i < question.numOfAnswers; i++) {
			temp = new Answer(question.answers.get(i));
			this.addAnswer(temp.getAnswer(), temp.getIsTrue());
		}
		determineDefaultQuestions();
		this.type = E_QuestionType.eMULTI_CHOICE;
		this.serialNum = question.serialNum;
	}
	public boolean addAnswer(String answer, boolean isTrue, int id) throws Exception { // adds answer to the
		if (numOfAnswers == MAX_ANSWERS) // checks if answers[] array is full
			return false;
		Answer temp = new Answer(answer.trim(), isTrue,id);
		for (int i = 0; i < answers.size(); i++) {
			if (temp.equals(answers.get(i))) { // checks if the answer is already in the array
				return false;
			}
		}
		answers.add(temp);
		numOfAnswers++;
		if (isTrue) {
			correctAnswers++;

		}
		if (numOfAnswers > 2) {
			determineDefaultQuestions();
		}
		return true;
	}
	
	public boolean addAnswer(String answer, boolean isTrue) throws Exception { // adds answer to the
		if (numOfAnswers == MAX_ANSWERS) // checks if answers[] array is full
			return false;
		Answer temp = new Answer(answer.trim(), isTrue);
		for (int i = 0; i < answers.size(); i++) {
			if (temp.equals(answers.get(i))) { // checks if the answer is already in the array
				return false;
			}
		}
		answers.add(temp);
		numOfAnswers++;
		if (isTrue) {
			correctAnswers++;

		}
		if (numOfAnswers > 2) {
			determineDefaultQuestions();
		}
		return true;
	}

	public int getMAX_ANSWERS() {
		return MAX_ANSWERS;
	}

	public MySet<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> list) {
		answers = new MySet<>();
		answers.addAll(list);
		// numOfAnswers = answers.size();
		determineDefaultQuestions();
	}

	public int getNumOfAnswers() {
		return numOfAnswers;
	}

	public void setNumOfAnswers(int num) {
		this.numOfAnswers = num;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int num) {
		this.correctAnswers = num;
	}

	public boolean isValid() {
		return (numOfAnswers >= 6);
	}

	/* randomly removes answers from array of a copied question */
	public void pickRandomAnswers() {
		int random;
		while (numOfAnswers > 6) {
			random = 2 + (int) (Math.random() * (numOfAnswers - 2));
			removeAnswer(random);
		}
	}

	@Override
	public boolean removeAnswer(int answerIndex) throws IndexOutOfBoundsException { // removes answer by index
		if (answerIndex < 2 || answerIndex > 9)
			return false;
		if (answers.get(answerIndex).getIsTrue()) {
			correctAnswers--;
		}
		answers.remove(answerIndex);
		numOfAnswers--;
		determineDefaultQuestions();
		return true;
	}

	/* change an answer for the question */
	public boolean changeAnswer(String answer, boolean isTrue, int answerIndex) throws IndexOutOfBoundsException {
		boolean oldAnswerIsTrue = answers.get(answerIndex).getIsTrue();
		if (answers.get(answerIndex).setAnswer(answer.trim()) && answers.get(answerIndex).setIsTrue(isTrue)) {
			if (!isTrue && oldAnswerIsTrue) {
				correctAnswers--;
			} else if (isTrue && !oldAnswerIsTrue) {
				correctAnswers++;
			}
			determineDefaultQuestions();
			return true;
		}
		return false;
	}

	public void determineDefaultQuestions() throws IndexOutOfBoundsException {
		if (answers.get(1) != null) { // prevent exception in constructor while adding default answers
			if (correctAnswers == 0 && numOfAnswers > 2) {
				answers.get(1).setIsTrue(true);
				answers.get(0).setIsTrue(false);
			} else if (correctAnswers == 1 || numOfAnswers == 2) {
				answers.get(1).setIsTrue(false);
				answers.get(0).setIsTrue(false);
			} else {
				answers.get(1).setIsTrue(false);
				answers.get(0).setIsTrue(true);
			}
		}
	}

	public String toStringForTest() throws IndexOutOfBoundsException {
		StringBuffer sb = new StringBuffer();
		sb.append("Question: " + quest + "\n");
		for (int i = 0; i < numOfAnswers; i++) {
			sb.append("\t" + (i + 1) + "." + answers.get(i).getAnswer() + "\n");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) // defines Open or multi choice question
			return false;
		MultiChoiceQuestion other = (MultiChoiceQuestion) obj;
		return (quest.compareToIgnoreCase(other.quest) == 0);
	}

	@Override
	public String toString() throws IndexOutOfBoundsException {
		StringBuffer sb = new StringBuffer();
		sb.append("Question: " + quest + "\n");
		for (int i = 0; i < numOfAnswers; i++) {
			sb.append("\t" + (i + 1) + "." + answers.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	@Override
	public boolean setAnswerLength() {
		answerLength = 0;
		for (int i = 0; i < answers.size(); i++) {
			answerLength += answers.get(i).getLength();
		}
		return true;
	}

	public Answer getLastAnswer() {
		return answers.get(numOfAnswers - 1);
	}

}
