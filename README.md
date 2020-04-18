# Movie-Recommendation-System

#### A simple movie recommendation system using Java and CSS
This is a capstone project for coursera [Java programming course](https://www.coursera.org/learn/java-programming-recommender) by Duke University. Users can access a webpage with a list of recommended movies from top rating to low after finishing their first rating.

#### Demo: [http://www.dukelearntoprogram.com/capstone/recommender.php?id=aViEswXTACJP7p]
> This project is based on OOP and dynamic dispatching such as using methods of special objects and implementing interface.

## Screenshot
#### Rating page
![](https://github.com/tablu666/Movie-Recommendation-System/blob/master/screenshot/screenshot1.png)
#### Movie recommendation page
![](https://github.com/tablu666/Movie-Recommendation-System/blob/master/screenshot/screenshot2.png)

## Algorithm
- Computing dot product between ratings of two users to get weight.
Rating scale: 1 to 10 (0 means user has not rated).
User id: 001 rating list = [2,0,8,9];
User id: 002 rating list = [4,8,2,0];
Weight value = (2-5)*(4-5) + (8-5)*(2-5) = -6.
- As you can see, since there is a huge difference between 8 and 2, the weight is small and negative.
- Calculating similarity to get closer users.
Similarity: sum of every movie (rating * weight) value.
The bigger, the more similar.

## Tools & Source
- BlueJ environment
- org.apache.commons.csv package
- edu.duke package
- CSV files (movie and rating lists)

## Learning process
- Breaking a big question into small parts.
- Working by hand and knowing what to do.
- Creating objects that have special fields and methods.
- Coding step by step.
- Testing and debugging.
