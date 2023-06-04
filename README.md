# Clean-Schemas

Välkommen till vår kodsamling! För att göra det enklare för granskarna att hitta koden och köra versionen av den har vi skapat denna README-fil.

För att få tillgång till koden kan du antingen ladda ner den från https://github.com/HampusHillborg/Clean-Schemas eller använda en git-kommandorad för att klona koden direkt från vår GitHub-repository. När du har koden på din dator, öppna projektet i din valda IDE.

För att köra koden måste du först se till att alla nödvändiga beroenden är installerade. 
1. Se till att du har den senaste versionen av PostgreSQL JDBC-driver på din dator och lägg till den i projektets library.
2. Se till att du har laddat ner dom-2.3.0-jaxb-1.0.6.jar och lagt till denna i projektets library.
3. Lägg till en LogData.java i mappen "Database" med inloggningsdata till databasen.
4. Se till så att rad 32 i klassen NutritionAPI.java har korrekt content root path till filen "livsmedelsverket" i mappen "Files".

När allt är klart, öppna projektet i din IDE och kör applikationen från Main-klassen. Detta bör starta GUI:et där du kan skapa och hantera profiler, beräkna BMR och TDEE, lägga till livsmedel och måltider, skapa veckoplaner och hantera dina profilinställningar.

Om du stöter på problem med att köra koden eller har frågor, tveka inte att kontakta oss. Vi är glada att hjälpa till och se till att du får en smidig upplevelse av vår kodsamling.
