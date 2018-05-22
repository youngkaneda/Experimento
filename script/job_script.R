dados  <- read.csv("exp.csv",header=T, sep = ";");
sets <- dados[dados$collection=='java.util.Set' & dados$methods>0,];
lists <- dados[dados$collection=='java.util.List' & dados$methods>0,];
maps <- dados[dados$collection=='java.util.Map' & dados$methods>0,];


pdf('Q1 - Set x List x Map - jext.pdf')
op <- par(mfrow=c(2,2))
boxplot(sets$adicao,sets$remocao,sets$busca,sets$acesso, sets$edicao,
        names = c("a", "r", "b","ac", "e"),
        col = "yellow",
        main = "java.util.Set",
        #xlab = "Distribui??o",
        ylab = "Quantidade");


boxplot(lists$adicao,lists$remocao,lists$busca,lists$acesso, lists$edicao,
        names = c("a", "r", "b","ac", "e"),
        col = "orange",
        main = "java.util.List",
        #xlab = "Distribui??o",
        ylab = "Quantidade");

boxplot(maps$adicao,maps$remocao,maps$busca,maps$acesso, maps$edicao,
        names = c("a", "r", "b","ac", "e"),
        col = "green",
        main = "java.util.Map",
        #xlab = "Distribui??o",
        ylab = "Quantidade");

legend(0, 0, c("java.util.Set", "java.util.List", "java.util.Map"),fill = c("yellow", "orange", "green"));
par(op)
dev.off()
