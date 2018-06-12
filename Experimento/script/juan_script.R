library(reshape2)
library(ggplot2)

setwd("/media/kuuhaku/linux/ifpb/periodo6/tcc1/Experimento/estudo-de-caso/")

getValuesArray <- function(projectFileName, collectionClassName) {
    dados  <- read.csv(projectFileName ,header=T, sep = ";")
    collection <- dados[dados$collection==collectionClassName & dados$methods>0,]
    
    print(collection)
    
    arrAD <- collection$adicao
    arrRM <- collection$remocao
    arrBS <- collection$busca
    arrAC <- collection$acesso
    arrED <- collection$edicao
    
    return <- c(sum(arrAD), sum(arrRM), sum(arrBS), sum(arrAC), sum(arrED))
}

axionArr = getValuesArray("axion.csv", "java.util.Map")
jextArr = getValuesArray("jext.csv", "java.util.Map")
freecsArr = getValuesArray("freecs.csv", "java.util.Map")
jgraphtArr = getValuesArray("jgrapht.csv", "java.util.Map")

df <- data.frame(Method = c("adição", "remoção", "busca","acesso", "edição"),
                 Jext = jextArr,
                 Axion = axionArr,
                 Freecs = freecsArr,
                 JGrapht = jgraphtArr)

data.m <- melt(df, id.vars='Method')

myPlot <- ggplot(data.m, aes(Method, value)) + geom_bar(aes(fill = variable), 
  width = 0.4, position = position_dodge(width=0.5), stat="identity") +  
  theme(legend.position="right") +
  xlab("Method") + ylab("Count") + ggtitle("java.util.Map") + labs(fill = "Projects")

print(myPlot)

ggsave(filename = "map2.pdf", plot = myPlot)