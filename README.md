Information Retrieval 

## S2-17_SSZG537

  
  
  
  
  
  

Saurabh Araiyer 2017HT12225

  
  
  
  
  
  
  
  
  
  
  

[Doc Link](https://docs.google.com/document/d/1IgflMkNYBBK7-EYilS8U4E1pQpw9fE1Rnu0ykfkG5IA/edit?usp=sharing)

  

High level Overview:

  

This document is divided into following segments:

1. Pre-Processing input files 
2. Stemming 
3. Indexing algorithm 
4. Stop Words Filter 
5. Search Algorithm 
6. Demo steps and documentation 
7. Output 
8. Code 
  
  

Pre-Processing input files:

  

Following constraints are assumed:

1. The file provided is in docx format. I am using [apache.poi](https://poi.apache.org/) libraries to extract text from the files. 
2. Content of the file is in small case. If capitals are there then tokens are handled differently. 
  

After extracting the text from docx files, the document is split into tokens assuming space as delimiter.

  

Stemming:

Based on the program configuration, the input tokens are stemmed on the basis of porter’s algorithm

  

Indexing:

The algorithm indexes token, documentId and position of the token document. The code would be using an in-memory index for the sake of assignment. 

  

I am using the following structure to store indices:

With the given dataset, index of word strong looks like:

{
"strong": {
    "id": "strong",
    "docInfos": [
      {
        "docId": "Doc3",
        "positions": [
          228
        ]
      },
      {
        "docId": "Doc4",
        "positions": [
          107
        ]
      }
    ]
  }
}


  

Multiple occurrences would be added to Json List:

{
"anesthesia": {
    "id": "anesthesia",
    "docInfos": [
      {
        "docId": "Doc2",
        "positions": [
          16,
          1,
          226,
          132,
          422,
          409,
          75,
          395,
          397,
          159
        ]
      }
    ]
  }
}
  

For the sake of this assignment, and readability the generated indices would be shared in [JSON](https://www.json.org/) format.

Stop Words Filter:

Based on the list provided at [https://www.ranks.nl/stopwords](https://www.ranks.nl/stopwords), Elements from index are evicted after generation. The list can be updated in configuration file.

  

Search Algorithm:

1. After index is generated, the input is stemmed and stop words are removed 
2. After removal of stop words, search is run, it provides following output: 

For query string with OR based search “n alumni lane library ways to give find a person about us mission to develop and apply innovative techniques for efficient quantitative analysis and display of medical imaging data through interdisciplinary collaboration goals education to train physicians and technologists locally and”


Found Words : 
educ Doc6 Doc1 Doc3 Doc2 
imag Doc6 Doc1 
train Doc6 Doc1 Doc3 Doc2 
local Doc1 
appli Doc1 Doc3 
goal Doc1 Doc5 
lane Doc1 Doc2 Doc5 
person Doc1 Doc3 Doc2 Doc5 
develop Doc1 Doc3 Doc4 
find Doc6 Doc1 Doc2 Doc5 
us Doc6 Doc1 Doc3 Doc2 Doc5 Doc4 
data Doc1 
technologist Doc1 
displai Doc1 
medic Doc1 Doc2 Doc5 
interdisciplinari Doc1 
collabor Doc1 Doc3 
effici Doc1 
librari Doc6 Doc1 Doc2 Doc5 
analysi Doc1 
mission Doc1 
physician Doc1 Doc2 
alumni Doc6 Doc1 Doc2 Doc5 
techniqu Doc1 
wai Doc1 Doc2 Doc5 
give Doc1 Doc2 Doc5 Doc4 
quantit Doc1 
innov Doc1 Doc4 
Document Frequency:
Frequency: 1.0
Frequency: 0.42857142857142855
Frequency: 0.25
Frequency: 0.14285714285714285
Frequency: 0.35714285714285715
Frequency: 0.25
Stop Words: through a and of about for to 

In case only stop words or no matching document is found, following message would be shown:

Query Type:
1. OR
2. AND
3. Print Index
4.Exit
Please enter selection (1 or 2 or 3 or 4) : 
1
Enter search String
and
No matching document found or only stopwords are queried forStop Words: and 
  

For and based search:

Query Type:
1. OR
2. AND
3. Print Index
4.Exit
Please enter selection (1 or 2 or 3 or 4) : 
2
Enter search String
alumni lane library ways to give find a person about us mission to develop and apply innovative techniques for efficient quantitative analysis and display of medical imaging data through interdisciplinary collaboration goals education to train physicians and technologists locally and
Document Frequency:
Frequency: 1.0
Stop Words: 


Demo steps and documentation:

  

Note: I have tested the code in Unix/Linux environment.

  

For running the executable, we would need java 8 installed on system.

And to run, following command needs to be entered:

java -cp "Jar file path" ir.assignment.App "docx file path"

For example, in my system the jar file is in directory “/Users/saurabh.araiyer/repo/2017HT12225/target/” and docx files are in directory “/Users/saurabh.araiyer/repo/2017HT12225/src/main/java/ir/resources/”

Command used to run:

java -cp "/Users/saurabh.araiyer/repo/2017HT12225/target/2017HT12225-1.0-SNAPSHOT-jar-with-dependencies.jar" ir.assignment.App "repo/2017HT12225/src/main/java/ir/resources/"

[Ref Video](https://youtu.be/PzafNZrZhOE)

In order to build the code, we need to have [maven](http://www.baeldung.com/install-maven-on-windows-linux-mac). After Maven and Java 8 are installed, we need to run the following command in sources directory:

mvn clean compile assembly:single

This would be creating the jar 2017HT12225-1.0-SNAPSHOT-jar-with-dependencies.jar for further runs.

  

Shared Jar link: [https://drive.google.com/open?id=1Fu-sUEpLOGRbdL16lTbQvfQZrjRJ9lgs](https://drive.google.com/open?id=1Fu-sUEpLOGRbdL16lTbQvfQZrjRJ9lgs)

Index Gist: https://gist.github.com/saurabhsar/755ebf98203e91193db4a6a619c11ee3

Code and index are attached
