import java.util.ArrayList;

public class WordInfo {
	
	// Number of occurrences
	private Integer occurenceCount;  
	// List of occurrence locations
	private ArrayList<Occurrence> occurrenceList;

	// Constructor
	public WordInfo(Integer occurenceCount, Integer lineNum, Integer wordNum) {
		this.occurenceCount = occurenceCount;
		this.occurrenceList = new ArrayList<Occurrence>();
		this.addOccurrence(lineNum, wordNum);
	}

	// Increment the number of occurrences
	public void incrementOccurenceCount() {
		this.occurenceCount += 1;
	}

	// Add an occurrence to the occurrence list
	public void addOccurrence(Integer lineNum, Integer wordNum) {
		Occurrence newOccurence = new Occurrence(lineNum, wordNum);
		occurrenceList.add(newOccurence);
	}

	// Retrieve the occurrence list
	public ArrayList<Occurrence> getOccurrenceList() {
		return this.occurrenceList;
	}

	// Return the occurrence count as a string
	public String toString() {
		return occurenceCount.toString();
	}

}