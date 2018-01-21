
library(tm) #required for removeWords() function 

clean_text = function(x)
{
x = gsub("RT", "", x) # remove Retweet
x = gsub("@\\w+", "", x) # remove at(@)
x = gsub("[[:punct:]]", "", x) # remove punctuation
x = gsub("[[:digit:]]", "", x) # remove numbers/Digits
x = gsub("http\\w+", "", x)  # remove links http
x = gsub("^ ", "", x)  # remove blank spaces at the beginning
x = gsub(" $", "", x) # remove blank spaces at the end
try.error = function(z) #To convert the text in lowercase
{
y = NA
try_error = tryCatch(tolower(z), error=function(e) e)
if (!inherits(try_error, "error"))
y = tolower(z)
return(y)
}
x = sapply(x, try.error)
x = removeWords(x, "sarcasm")
return(x)
}

#Loop to run Clean Text Function
for(x in 1:nrow(twitter_data)){

	twitter_data$text[x] <- clean_text(twitter_data$text[x])
}

#Remove Empty Tweets
temp <- ""
for(i in 1:1000){
		
	twitter_data <- twitter_data[!(twitter_data$text == temp), ]
	
	temp <- paste(temp," ", sep="")
}

#Removes Duplicated Rows
twitter_data <- twitter_data[!duplicated(twitter_data$text), ]

#Loop to remove the word sarcasm from tweets requires tm library
for(x in 1:nrow(twitter_data)){ 

	twitter_data$text[x] <- removeWords(twitter_data$text[x], "sarcasm")
}

************************************************************************************

corpus <- Corpus(VectorSource(All_Sarcasm_Data$text))

term_doc_mat <- TermDocumentMatrix(corpus)

************************************************************************************

All_Sarcasm_Data <- All_Sarcasm_Data[!(All_Sarcasm_Data$text %in% '#sarcasm'), ]

************************************************************************************
library(stringr)

test2 <- str_split(test$text, " ")
test_data <- data.frame(do.call(rbind, test2), test$label)

eval(parse(text=paste("results$","newcol", x, " <- NA", sep="")))

for(x in 1:nrow(twitter_data)){

	temp_data <- str_split(twitter_data$text[x], " ")
	temp_data <- data.frame(do.call(rbind,temp_data))
	
	if(x == 1){
		temp_length <- ncol(temp_data)
	}else if(ncol(temp_data) > temp_length){
		temp_length <- ncol(temp_data)
	}

}

library(stringr)

results <- data.frame(matrix(NA, ncol = 141, nrow = 1))
new_row <- data.frame(matrix(NA, ncol = 141, nrow = 1))

for(x in 1:nrow(twitter_data)){
	
	temp_data <- str_split(twitter_data$text[x], " ")
	temp_data <- data.frame(do.call(rbind,temp_data))

	k <- ncol(temp_data) + 1
	
	if(ncol(temp_data) > 1 & ncol(temp_data) < 142){
	
		for(i in 1:ncol(results)){
		
			if(ncol(temp_data) == ncol(results)){
				break
			}else {
				eval(parse(text=paste("temp_data$","X", k, " <- NA", sep="")))

				k <- k+1
			}
		}
		results <- rbind(results, temp_data)
		
		results$X141[nrow(results)] <- twitter_data$Label[x]
	}
}

*****************************************************************

for( i in 1:(ncol(twitter_data_ready)-1)){
	
	eval(parse(text=paste("twitter_data_ready$","X", i, " <- as.character(twitter_data_ready$X", i ,")", sep="")))

}







