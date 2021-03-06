package prsona.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ANSWER")
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String text;
	@ManyToOne
	@JsonIgnore
	private Question question;
	@OneToMany(mappedBy="answer", cascade = CascadeType.ALL)
	private List<Weight> weights;
	
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
	
	public Question getQuestion() {
		return question;
	}
	
	public List<Weight> getWeights() {
		return weights;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
}
