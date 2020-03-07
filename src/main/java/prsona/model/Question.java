package prsona.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="QUESTION")
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String text;
	private String resourceURL;
	private QuestionType type;
	@ManyToOne
	@JsonIgnore
	private Quiz quiz;
	@OneToMany(mappedBy="question")
	private List<Answer> answers;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
	
	public QuestionType getType() {
		return type;
	}

	public void setQuestionType(QuestionType type) {
		this.type = type;
	}
}
