package FinalProject_Template;

import java.util.ArrayList;

public class Twitter {
	
	//ADD YOUR CODE BELOW HERE
	private MyHashTable<String,ArrayList<Tweet>> twitter;//key=the post day(in format YYYY-MM-DD)
	private MyHashTable<String,ArrayList<Tweet>> Author;//key=author name
	private ArrayList<String> stopWords=new ArrayList<>();
	//ADD CODE ABOVE HERE 
	
	// O(n+m) where n is the number of tweets, and m the number of stopWords
	public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {
		//ADD YOUR CODE BELOW HERE
		twitter=new MyHashTable<>(10);
		Author=new MyHashTable<>(10);
		for(Tweet t:tweets){
			addTweet(t);
		}


		for(String s:stopWords) {
			this.stopWords.add(s.toLowerCase());
		}
		//ADD CODE ABOVE HERE 
	}
	
	
    /**
     * Add Tweet t to this Twitter
     * O(1)
     */
	public void addTweet(Tweet t) {
		//ADD CODE BELOW HERE
		if(Author.get(t.getAuthor())==null) {
			ArrayList<Tweet> init=new ArrayList<>();
			init.add(t);
			Author.put(t.getAuthor(),init);
		}
		else {
			ArrayList<Tweet> anAuthor=Author.get(t.getAuthor());
			anAuthor.add(0,t);
//			if(t.getDateAndTime().compareTo(anAuthor.get(0).getAuthor())>=0)
//				anAuthor.add(0, t);
//			else
//				anAuthor.add(t);
		}

		if(twitter.get(t.getDateAndTime().substring(0,10))==null) {
			ArrayList<Tweet> init=new ArrayList<>();
			init.add(t);
			twitter.put(t.getDateAndTime().substring(0,10),init);
		}
		else
			twitter.get(t.getDateAndTime().substring(0,10)).add(t);

		//ADD CODE ABOVE HERE 
	}
	

    /**
     * Search this Twitter for the latest Tweet of a given author.
     * If there are no tweets from the given author, then the 
     * method returns null. 
     * O(1)  
     */
    public Tweet latestTweetByAuthor(String author) {
        //ADD CODE BELOW HERE
		ArrayList<Tweet> tweet=Author.get(author);
		if(tweet==null)
			return null;
		String date=tweet.get(0).getDateAndTime();
		Tweet back=tweet.get(0);
		for(Tweet t:tweet){
			if(date.compareTo(t.getDateAndTime())<=0) {
				date=t.getDateAndTime();
				back=t;
			}
		}
    	return back;
    	
        //ADD CODE ABOVE HERE 
    }


    /**
     * Search this Twitter for Tweets by `date' and return an 
     * ArrayList of all such Tweets. If there are no tweets on 
     * the given date, then the method returns null.
     * O(1)
     */
    public ArrayList<Tweet> tweetsByDate(String date) {
        //ADD CODE BELOW HERE
    	
    	return twitter.get(date);
    	
        //ADD CODE ABOVE HERE
    }
    
	/**
	 * Returns an ArrayList of words (that are not stop words!) that
	 * appear in the tweets. The words should be ordered from most 
	 * frequent to least frequent by counting in how many tweet messages
	 * the words appear. Note that if a word appears more than once
	 * in the same tweet, it should be counted only once. 
	 */
    public ArrayList<String> trendingTopics() {
        //ADD CODE BELOW HERE
		ArrayList<String> words=new ArrayList<>();
		MyHashTable<String,Integer> wordmap=new MyHashTable<>(10);

    	for(HashPair<String,ArrayList<Tweet>> entry:twitter){
    		for(Tweet t:entry.getValue()){
				ArrayList<String> wordList=getWords(t.getMessage().toLowerCase());
				wordList.removeAll(stopWords);
				MyHashTable<Integer,String> unique=new MyHashTable<>(10);
				for(int i=0;i<wordList.size();i++){
					unique.put(i,wordList.get(i));
				}
				words.addAll(unique.values());
			}
		}

    	for(int i=0;i<words.size();i++){
    		String temp_word=words.get(i);
    		if(wordmap.get(temp_word)==null)
    			wordmap.put(temp_word,1);
    		else
    			wordmap.put(temp_word,wordmap.get(temp_word)+1);
		}

    	return wordmap.fastSort(wordmap);
    	
        //ADD CODE ABOVE HERE    	
    }
    
    
    
    /**
     * An helper method you can use to obtain an ArrayList of words from a 
     * String, separating them based on apostrophes and space characters. 
     * All character that are not letters from the English alphabet are ignored. 
     */
    private static ArrayList<String> getWords(String msg) {
    	msg = msg.replace('\'', ' ');
    	String[] words = msg.split(" ");
    	ArrayList<String> wordsList = new ArrayList<String>(words.length);
    	for (int i=0; i<words.length; i++) {
    		String w = "";
    		for (int j=0; j< words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
    				w += c;
    			
    		}
    		wordsList.add(w);
    	}
    	return wordsList;
    }

    

}
