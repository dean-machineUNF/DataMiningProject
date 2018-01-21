#requires twitteR r Package

#Retreives Sarcasm Twitter Data
#####################################
library(twitteR)

api_key <- "DWiD3ocMhgJsY6Xg5D5d9WCVf"
 
api_secret <- "8MVrAt5E9GtDhLrAjSTtV0QNBm5501GFWHroRgG1M9uDJzPGb3"
 
access_token <- "905505620102701056-cf8Ku89bEWGZO26MHVjxlWwQN8eSojr"
 
access_token_secret <- "sMrrrAfOQA10PS3QxuWqOxIHchlBpwTbF7EAc9AeKv5Wc"
 
setup_twitter_oauth(api_key,api_secret,access_token,access_token_secret)

results <- searchTwitter("#sarcasm", n=10000)

tweetData <- twListToDF(results)
######################################

results <- searchTwitter("#sarcasm", n=10000, since='2017-09-23')



#Not Finished 
######################################
library(twitteR)

api_key <- "DWiD3ocMhgJsY6Xg5D5d9WCVf"
 
api_secret <- "8MVrAt5E9GtDhLrAjSTtV0QNBm5501GFWHroRgG1M9uDJzPGb3"
 
access_token <- "905505620102701056-cf8Ku89bEWGZO26MHVjxlWwQN8eSojr"
 
access_token_secret <- "sMrrrAfOQA10PS3QxuWqOxIHchlBpwTbF7EAc9AeKv5Wc"
 
setup_twitter_oauth(api_key,api_secret,access_token,access_token_secret)

results <- searchTwitter("'-sarcasm'", n=10000)

tweetData <- twListToDF(results)
