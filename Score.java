import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import enigma.console.TextAttributes;

public class Score {


	//double linked list for high score table
	private DoubleLinkedList highscores;
	
	//score 
	private int score = 0;
	
	//multi linked list to give score to player.
	private MultiLinkedList aminoacids;
	
	private int counter;
	//counter for controlling codons.
	
	
	//string array to keep gave points.
	String[] AllPoints = new String[34];
	
	//allPoints index counter
	private int index = 0; 
	
	
	//constructor.
	public Score() 
	{
		score=0;
		highscores = new DoubleLinkedList();
		//initial counter.
		counter = 3;
	}
	//constructor end.
	public void initScore() {
		AllPoints = new String[34];
		score=0;
		counter = 3;
	}
	
	
	//reading aminoacids from txt.
	public void readAminoacids() throws NumberFormatException, IOException {
		
		//all aminoacids from text.
		aminoacids = new MultiLinkedList();
		
		//file reader.
		FileReader fileReader = new FileReader("aminoacids.txt");
		//
		String line;
		//
		BufferedReader br = new BufferedReader(fileReader);
		
		//all lines.
		while ((line = br.readLine()) != null) {
			
			//delete all spaces.
			line=line.trim();
			
			//split by ","
		    String [] split = line.split(",",-1);
		    
		    //new aminoacid name.
		    aminoacids.addProtein(split[0], split[1]);
		    //
		    
		    for (int i = 2; i < split.length; i++) 
		    {
		    	//split again (to "AAT" and "30").
				String [] extraSplitted = split[i].split("-");
				//new codon and point.
				aminoacids.addCodon(split[1], extraSplitted[0], Integer.parseInt(extraSplitted[1]));
			}
		    //for end.
		    
		    //line = br.readLine();
		    //new line.
		}
		br.close();
		//close file.
	}
	//read end.
	
	
	//giving point from txt.
	public void givePoint(SingleLinkedList list) {
		
		//deleting all point to fill again. 
		for (int i = 0; i < AllPoints.length; i++) {
			AllPoints[i] = "";
		}
		//for end.
		//initial index
		index=0;
		
		
		//initial codon.
		String codon = "";
		
		//temp snake.
		SingleLinkedList tempsnake = new SingleLinkedList();
		//temp node
		Node temp = list.getHead();
		//copying.
		while(temp!=null)
		{
			tempsnake.add(temp.getData(), temp.getCoordinateX(), temp.getCoordinateY());
			temp = temp.getLink();
		}
		//while end.
		temp = tempsnake.getHead();
		// Yeni baþtan okuduðu için score çok fazla artýyordu, onu düzelttim burada
		// Sürekli codon puanlarýný baþtan ekliyor
		score = (tempsnake.size() - 3) * 5;
		while(temp != null)
		{
			codon = "";
	    for(int i = 0; i<3;i++){
			codon=codon+temp.getData();
			temp = temp.getLink();
			}
		
	//    comparing and giving point.
	     Node temp1 = aminoacids.getHead();
	     while(temp1!= null)
			{
				Node temp2 = temp1.getRight();
				//column
				while(temp2 != null)
				{
					if(codon.equalsIgnoreCase((String)temp2.getCodName()))
					{
						//increase score.
						score += temp2.getPoint();
						if(index==AllPoints.length) {
							for (int i = 0; i < AllPoints.length; i++) {
								AllPoints[i]=null;
							}
							index=0;
						}
						AllPoints[index] = (temp2.getCodName()+ " - " + temp2.getPoint());
						index++;
						
					}
					//if end.
					temp2 = temp2.getNextCod();
				}
				//while end.
				temp1 = temp1.getDown();
			}
		}
		//while end.
	        
		counter = 0;
	}
	//give point end.
	
	
	//printing points.
	public void printingCodons(enigma.console.Console console) {
		int i = 4;
		int a = 62;
		console.setTextAttributes(new TextAttributes(new Color(255,255,255)));
	    console.getTextWindow().setCursorPosition(a, i);
	    
	    for (int j = 0; j < AllPoints.length; j++) {
	    	if(a==71 && i==21) {
	    		i=4;
	    		a=62;
	    		j=0;
	    	}
	    	if(j>=17) {
	    		if(j==17) {
	    		a += 9;
	    		i = 4;}
	    		console.getTextWindow().setCursorPosition(a, i);
	    		if(AllPoints[j]!=null)
		    		System.out.print(AllPoints[j]);
	    		i++;
	    	}
	    	else {
	    		if(AllPoints[j]!=null)
		    		System.out.print(AllPoints[j]);
				i++;
				console.getTextWindow().setCursorPosition(a, i);
	    	}
	    	
		}
	}
	//print end.
	
	
	
	// Takes an input for player name and adds to high scores(DLL) with score current round
	public void addHighScores(int score) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter your name?");

        //controls already exist names
        boolean flag;
        String name;

        name = sc.nextLine();
        //controlling name is existed or not
        flag = highscores.isNameExist(name);
        if(flag == true) {
            //taking score of existed palyer.
            if(highscores.ScoreExist(name)<score) 
            {
                Node temp = highscores.getHead();
                while(temp != null) {
                    if(temp.getName().equalsIgnoreCase(name))
                    {
                        temp.setScore(score);
                        break;
                    }
                    temp = temp.getNext();
                }
                
                ////////refresh the highscores linked list
                DoubleLinkedList temporary = new DoubleLinkedList();
                temp = highscores.getHead();
                while(temp != null) {
                	temporary.add(temp.getName(), temp.getScore());
                	temp = temp.getNext();
                }
                highscores.setHead(null);
                temp = temporary.getHead();
                while(temp != null) {
                	highscores.add(temp.getName(), temp.getScore());
                	temp = temp.getNext();
                }
                }
            }
        



        //input name is unique

        //adds to highscores
        else
        highscores.add(name, score);


    }
	//add high score end.
	
	
	
	// all time high score.
	public void allTimeHighScores() throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("HighScoreTable.txt"));
		String sCurrentLine;
		try {
			while((sCurrentLine = in.readLine()) != null) {
				
				String[] splitArray = new String[2];
				splitArray = sCurrentLine.split(";");
				highscores.add((String)splitArray[0],  Integer.parseInt(splitArray[1]));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		in.close();

	}
	//allTimeHighScores end.
	
	
	
	
	///GET-SET
	public DoubleLinkedList getHighscores() {
		return highscores;
	}

	public void setHighscores(DoubleLinkedList highscores) {
		this.highscores = highscores;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public MultiLinkedList getAminoacids() {
		return aminoacids;
	}

	public void setAminoacids(MultiLinkedList aminoacids) {
		this.aminoacids = aminoacids;
	}

	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	} 	
	
}
