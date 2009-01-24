/*
 * WordSim353.java - "The WordSimilarity-353 Test Collection"
 * was taken from (thanks to Evgeniy Gabrilovich):
 * http://www.cs.technion.ac.il/~gabr/resources/data/wordsim353/wordsim353.html
 *
 * Copyright (c) 2005-2007 Andrew Krizhanovsky /aka at mail.iias.spb.su/
 * Distributed under GNU Public License.
 */

package wikipedia.experiment;

import java.util.List;
import java.util.ArrayList;


/** 
 * The WordSimilarity-353 Test Collection
 */
public class WordSim353 {
    private static final int _len = 353;
    
    public static boolean initialized = false;
    public static final List<WordSim> data = new ArrayList<WordSim> (_len);
    
    /** Creates a new instance of WordSim353 */
    public WordSim353() {
        if(!initialized) {
            initialized = true;
            
            data.add(new WordSim("love","sex",6.77f));
            data.add(new WordSim("tiger","cat",7.35f));
            data.add(new WordSim("tiger","tiger",10.00f));
            data.add(new WordSim("book","paper",7.46f));
            data.add(new WordSim("computer","keyboard",7.62f));
            data.add(new WordSim("computer","internet",7.58f));
            data.add(new WordSim("plane","car",5.77f));
            data.add(new WordSim("train","car",6.31f));
            data.add(new WordSim("telephone","communication",7.50f));
            data.add(new WordSim("television","radio",6.77f));
            data.add(new WordSim("media","radio",7.42f));
            data.add(new WordSim("drug","abuse",6.85f));
            data.add(new WordSim("bread","butter",6.19f));
            data.add(new WordSim("cucumber","potato",5.92f));
            data.add(new WordSim("doctor","nurse",7.00f));
            data.add(new WordSim("professor","doctor",6.62f));
            data.add(new WordSim("student","professor",6.81f));
            data.add(new WordSim("smart","student",4.62f));
            data.add(new WordSim("smart","stupid",5.81f));
            data.add(new WordSim("company","stock",7.08f));
            data.add(new WordSim("stock","market",8.08f));
            data.add(new WordSim("stock","phone",1.62f));
            data.add(new WordSim("stock","CD",1.31f));
            data.add(new WordSim("stock","jaguar",0.92f));
            data.add(new WordSim("stock","egg",1.81f));
            data.add(new WordSim("fertility","egg",6.69f));
            data.add(new WordSim("stock","live",3.73f));
            data.add(new WordSim("stock","life",0.92f));
            data.add(new WordSim("book","library",7.46f));
            data.add(new WordSim("bank","money",8.12f));
            data.add(new WordSim("wood","forest",7.73f));
            data.add(new WordSim("money","cash",9.15f));
            data.add(new WordSim("professor","cucumber",0.31f));
            data.add(new WordSim("king","cabbage",0.23f));
            data.add(new WordSim("king","queen",8.58f));
            data.add(new WordSim("king","rook",5.92f));
            data.add(new WordSim("bishop","rabbi",6.69f));
            data.add(new WordSim("Jerusalem","Israel",8.46f));
            data.add(new WordSim("Jerusalem","Palestinian",7.65f));
            data.add(new WordSim("holy","sex",1.62f));
            data.add(new WordSim("fuck","sex",9.44f));
            data.add(new WordSim("Maradona","football",8.62f));
            data.add(new WordSim("football","soccer",9.03f));
            data.add(new WordSim("football","basketball",6.81f));
            data.add(new WordSim("football","tennis",6.63f));
            data.add(new WordSim("tennis","racket",7.56f));
            data.add(new WordSim("Arafat","peace",6.73f));
            data.add(new WordSim("Arafat","terror",7.65f));
            data.add(new WordSim("Arafat","Jackson",2.50f));
            data.add(new WordSim("law","lawyer",8.38f));
            data.add(new WordSim("movie","star",7.38f));
            data.add(new WordSim("movie","popcorn",6.19f));
            data.add(new WordSim("movie","critic",6.73f));
            data.add(new WordSim("movie","theater",7.92f));
            data.add(new WordSim("physics","proton",8.12f));
            data.add(new WordSim("physics","chemistry",7.35f));
            data.add(new WordSim("space","chemistry",4.88f));
            data.add(new WordSim("alcohol","chemistry",5.54f));
            data.add(new WordSim("vodka","gin",8.46f));
            data.add(new WordSim("vodka","brandy",8.13f));
            data.add(new WordSim("drink","car",3.04f));
            data.add(new WordSim("drink","ear",1.31f));
            data.add(new WordSim("drink","mouth",5.96f));
            data.add(new WordSim("drink","eat",6.87f));
            data.add(new WordSim("baby","mother",7.85f));
            data.add(new WordSim("drink","mother",2.65f));
            data.add(new WordSim("car","automobile",8.94f));
            data.add(new WordSim("gem","jewel",8.96f));
            data.add(new WordSim("journey","voyage",9.29f));
            data.add(new WordSim("boy","lad",8.83f));
            data.add(new WordSim("coast","shore",9.10f));
            data.add(new WordSim("asylum","madhouse",8.87f));
            data.add(new WordSim("magician","wizard",9.02f));
            data.add(new WordSim("midday","noon",9.29f));
            data.add(new WordSim("furnace","stove",8.79f));
            data.add(new WordSim("food","fruit",7.52f));
            data.add(new WordSim("bird","cock",7.10f));
            data.add(new WordSim("bird","crane",7.38f));
            data.add(new WordSim("tool","implement",6.46f));
            data.add(new WordSim("brother","monk",6.27f));
            data.add(new WordSim("crane","implement",2.69f));
            data.add(new WordSim("lad","brother",4.46f));
            data.add(new WordSim("journey","car",5.85f));
            data.add(new WordSim("monk","oracle",5.00f));
            data.add(new WordSim("cemetery","woodland",2.08f));
            data.add(new WordSim("food","rooster",4.42f));
            data.add(new WordSim("coast","hill",4.38f));
            data.add(new WordSim("forest","graveyard",1.85f));
            data.add(new WordSim("shore","woodland",3.08f));
            data.add(new WordSim("monk","slave",0.92f));
            data.add(new WordSim("coast","forest",3.15f));
            data.add(new WordSim("lad","wizard",0.92f));
            data.add(new WordSim("chord","smile",0.54f));
            data.add(new WordSim("glass","magician",2.08f));
            data.add(new WordSim("noon","string",0.54f));
            data.add(new WordSim("rooster","voyage",0.62f));
            data.add(new WordSim("money","dollar",8.42f));
            data.add(new WordSim("money","cash",9.08f));
            data.add(new WordSim("money","currency",9.04f));
            data.add(new WordSim("money","wealth",8.27f));
            data.add(new WordSim("money","property",7.57f));
            data.add(new WordSim("money","possession",7.29f));
            data.add(new WordSim("money","bank",8.50f));
            data.add(new WordSim("money","deposit",7.73f));
            data.add(new WordSim("money","withdrawal",6.88f));
            data.add(new WordSim("money","laundering",5.65f));
            data.add(new WordSim("money","operation",3.31f));
            data.add(new WordSim("tiger","jaguar",8.00f));
            data.add(new WordSim("tiger","feline",8.00f));
            data.add(new WordSim("tiger","carnivore",7.08f));
            data.add(new WordSim("tiger","mammal",6.85f));
            data.add(new WordSim("tiger","animal",7.00f));
            data.add(new WordSim("tiger","organism",4.77f));
            data.add(new WordSim("tiger","fauna",5.62f));
            data.add(new WordSim("tiger","zoo",5.87f));
            data.add(new WordSim("psychology","psychiatry",8.08f));
            data.add(new WordSim("psychology","anxiety",7.00f));
            data.add(new WordSim("psychology","fear",6.85f));
            data.add(new WordSim("psychology","depression",7.42f));
            data.add(new WordSim("psychology","clinic",6.58f));
            data.add(new WordSim("psychology","doctor",6.42f));
            data.add(new WordSim("psychology","Freud",8.21f));
            data.add(new WordSim("psychology","mind",7.69f));
            data.add(new WordSim("psychology","health",7.23f));
            data.add(new WordSim("psychology","science",6.71f));
            data.add(new WordSim("psychology","discipline",5.58f));
            data.add(new WordSim("psychology","cognition",7.48f));
            data.add(new WordSim("planet","star",8.45f));
            data.add(new WordSim("planet","constellation",8.06f));
            data.add(new WordSim("planet","moon",8.08f));
            data.add(new WordSim("planet","sun",8.02f));
            data.add(new WordSim("planet","galaxy",8.11f));
            data.add(new WordSim("planet","space",7.92f));
            data.add(new WordSim("planet","astronomer",7.94f));
            data.add(new WordSim("precedent","example",5.85f));
            data.add(new WordSim("precedent","information",3.85f));
            data.add(new WordSim("precedent","cognition",2.81f));
            data.add(new WordSim("precedent","law",6.65f));
            data.add(new WordSim("precedent","collection",2.50f));
            data.add(new WordSim("precedent","group",1.77f));
            data.add(new WordSim("precedent","antecedent",6.04f));
            data.add(new WordSim("cup","coffee",6.58f));
            data.add(new WordSim("cup","tableware",6.85f));
            data.add(new WordSim("cup","article",2.40f));
            data.add(new WordSim("cup","artifact",2.92f));
            data.add(new WordSim("cup","object",3.69f));
            data.add(new WordSim("cup","entity",2.15f));
            data.add(new WordSim("cup","drink",7.25f));
            data.add(new WordSim("cup","food",5.00f));
            data.add(new WordSim("cup","substance",1.92f));
            data.add(new WordSim("cup","liquid",5.90f));
            data.add(new WordSim("jaguar","cat",7.42f));
            data.add(new WordSim("jaguar","car",7.27f));
            data.add(new WordSim("energy","secretary",1.81f));
            data.add(new WordSim("secretary","senate",5.06f));
            data.add(new WordSim("energy","laboratory",5.09f));
            data.add(new WordSim("computer","laboratory",6.78f));
            data.add(new WordSim("weapon","secret",6.06f));
            data.add(new WordSim("FBI","fingerprint",6.94f));
            data.add(new WordSim("FBI","investigation",8.31f));
            data.add(new WordSim("investigation","effort",4.59f));
            data.add(new WordSim("Mars","water",2.94f));
            data.add(new WordSim("Mars","scientist",5.63f));
            data.add(new WordSim("news","report",8.16f));
            data.add(new WordSim("canyon","landscape",7.53f));
            data.add(new WordSim("image","surface",4.56f));
            data.add(new WordSim("discovery","space",6.34f));
            data.add(new WordSim("water","seepage",6.56f));
            data.add(new WordSim("sign","recess",2.38f));
            data.add(new WordSim("Wednesday","news",2.22f));
            data.add(new WordSim("mile","kilometer",8.66f));
            data.add(new WordSim("computer","news",4.47f));
            data.add(new WordSim("territory","surface",5.34f));
            data.add(new WordSim("atmosphere","landscape",3.69f));
            data.add(new WordSim("president","medal",3.00f));
            data.add(new WordSim("war","troops",8.13f));
            data.add(new WordSim("record","number",6.31f));
            data.add(new WordSim("skin","eye",6.22f));
            data.add(new WordSim("Japanese","American",6.50f));
            data.add(new WordSim("theater","history",3.91f));
            data.add(new WordSim("volunteer","motto",2.56f));
            data.add(new WordSim("prejudice","recognition",3.00f));
            data.add(new WordSim("decoration","valor",5.63f));
            data.add(new WordSim("century","year",7.59f));
            data.add(new WordSim("century","nation",3.16f));
            data.add(new WordSim("delay","racism",1.19f));
            data.add(new WordSim("delay","news",3.31f));
            data.add(new WordSim("minister","party",6.63f));
            data.add(new WordSim("peace","plan",4.75f));
            data.add(new WordSim("minority","peace",3.69f));
            data.add(new WordSim("attempt","peace",4.25f));
            data.add(new WordSim("government","crisis",6.56f));
            data.add(new WordSim("deployment","departure",4.25f));
            data.add(new WordSim("deployment","withdrawal",5.88f));
            data.add(new WordSim("energy","crisis",5.94f));
            data.add(new WordSim("announcement","news",7.56f));
            data.add(new WordSim("announcement","effort",2.75f));
            data.add(new WordSim("stroke","hospital",7.03f));
            data.add(new WordSim("disability","death",5.47f));
            data.add(new WordSim("victim","emergency",6.47f));
            data.add(new WordSim("treatment","recovery",7.91f));
            data.add(new WordSim("journal","association",4.97f));
            data.add(new WordSim("doctor","personnel",5.00f));
            data.add(new WordSim("doctor","liability",5.19f));
            data.add(new WordSim("liability","insurance",7.03f));
            data.add(new WordSim("school","center",3.44f));
            data.add(new WordSim("reason","hypertension",2.31f));
            data.add(new WordSim("reason","criterion",5.91f));
            data.add(new WordSim("hundred","percent",7.38f));
            data.add(new WordSim("Harvard","Yale",8.13f));
            data.add(new WordSim("hospital","infrastructure",4.63f));
            data.add(new WordSim("death","row",5.25f));
            data.add(new WordSim("death","inmate",5.03f));
            data.add(new WordSim("lawyer","evidence",6.69f));
            data.add(new WordSim("life","death",7.88f));
            data.add(new WordSim("life","term",4.50f));
            data.add(new WordSim("word","similarity",4.75f));
            data.add(new WordSim("board","recommendation",4.47f));
            data.add(new WordSim("governor","interview",3.25f));
            data.add(new WordSim("OPEC","country",5.63f));
            data.add(new WordSim("peace","atmosphere",3.69f));
            data.add(new WordSim("peace","insurance",2.94f));
            data.add(new WordSim("territory","kilometer",5.28f));
            data.add(new WordSim("travel","activity",5.00f));
            data.add(new WordSim("competition","price",6.44f));
            data.add(new WordSim("consumer","confidence",4.13f));
            data.add(new WordSim("consumer","energy",4.75f));
            data.add(new WordSim("problem","airport",2.38f));
            data.add(new WordSim("car","flight",4.94f));
            data.add(new WordSim("credit","card",8.06f));
            data.add(new WordSim("credit","information",5.31f));
            data.add(new WordSim("hotel","reservation",8.03f));
            data.add(new WordSim("grocery","money",5.94f));
            data.add(new WordSim("registration","arrangement",6.00f));
            data.add(new WordSim("arrangement","accommodation",5.41f));
            data.add(new WordSim("month","hotel",1.81f));
            data.add(new WordSim("type","kind",8.97f));
            data.add(new WordSim("arrival","hotel",6.00f));
            data.add(new WordSim("bed","closet",6.72f));
            data.add(new WordSim("closet","clothes",8.00f));
            data.add(new WordSim("situation","conclusion",4.81f));
            data.add(new WordSim("situation","isolation",3.88f));
            data.add(new WordSim("impartiality","interest",5.16f));
            data.add(new WordSim("direction","combination",2.25f));
            data.add(new WordSim("street","place",6.44f));
            data.add(new WordSim("street","avenue",8.88f));
            data.add(new WordSim("street","block",6.88f));
            data.add(new WordSim("street","children",4.94f));
            data.add(new WordSim("listing","proximity",2.56f));
            data.add(new WordSim("listing","category",6.38f));
            data.add(new WordSim("cell","phone",7.81f));
            data.add(new WordSim("production","hike",1.75f));
            data.add(new WordSim("benchmark","index",4.25f));
            data.add(new WordSim("media","trading",3.88f));
            data.add(new WordSim("media","gain",2.88f));
            data.add(new WordSim("dividend","payment",7.63f));
            data.add(new WordSim("dividend","calculation",6.48f));
            data.add(new WordSim("calculation","computation",8.44f));
            data.add(new WordSim("currency","market",7.50f));
            data.add(new WordSim("OPEC","oil",8.59f));
            data.add(new WordSim("oil","stock",6.34f));
            data.add(new WordSim("announcement","production",3.38f));
            data.add(new WordSim("announcement","warning",6.00f));
            data.add(new WordSim("profit","warning",3.88f));
            data.add(new WordSim("profit","loss",7.63f));
            data.add(new WordSim("dollar","yen",7.78f));
            data.add(new WordSim("dollar","buck",9.22f));
            data.add(new WordSim("dollar","profit",7.38f));
            data.add(new WordSim("dollar","loss",6.09f));
            data.add(new WordSim("computer","software",8.50f));
            data.add(new WordSim("network","hardware",8.31f));
            data.add(new WordSim("phone","equipment",7.13f));
            data.add(new WordSim("equipment","maker",5.91f));
            data.add(new WordSim("luxury","car",6.47f));
            data.add(new WordSim("five","month",3.38f));
            data.add(new WordSim("report","gain",3.63f));
            data.add(new WordSim("investor","earning",7.13f));
            data.add(new WordSim("liquid","water",7.89f));
            data.add(new WordSim("baseball","season",5.97f));
            data.add(new WordSim("game","victory",7.03f));
            data.add(new WordSim("game","team",7.69f));
            data.add(new WordSim("marathon","sprint",7.47f));
            data.add(new WordSim("game","series",6.19f));
            data.add(new WordSim("game","defeat",6.97f));
            data.add(new WordSim("seven","series",3.56f));
            data.add(new WordSim("seafood","sea",7.47f));
            data.add(new WordSim("seafood","food",8.34f));
            data.add(new WordSim("seafood","lobster",8.70f));
            data.add(new WordSim("lobster","food",7.81f));
            data.add(new WordSim("lobster","wine",5.70f));
            data.add(new WordSim("food","preparation",6.22f));
            data.add(new WordSim("video","archive",6.34f));
            data.add(new WordSim("start","year",4.06f));
            data.add(new WordSim("start","match",4.47f));
            data.add(new WordSim("game","round",5.97f));
            data.add(new WordSim("boxing","round",7.61f));
            data.add(new WordSim("championship","tournament",8.36f));
            data.add(new WordSim("fighting","defeating",7.41f));
            data.add(new WordSim("line","insurance",2.69f));
            data.add(new WordSim("day","summer",3.94f));
            data.add(new WordSim("summer","drought",7.16f));
            data.add(new WordSim("summer","nature",5.63f));
            data.add(new WordSim("day","dawn",7.53f));
            data.add(new WordSim("nature","environment",8.31f));
            data.add(new WordSim("environment","ecology",8.81f));
            data.add(new WordSim("nature","man",6.25f));
            data.add(new WordSim("man","woman",8.30f));
            data.add(new WordSim("man","governor",5.25f));
            data.add(new WordSim("murder","manslaughter",8.53f));
            data.add(new WordSim("soap","opera",7.94f));
            data.add(new WordSim("opera","performance",6.88f));
            data.add(new WordSim("life","lesson",5.94f));
            data.add(new WordSim("focus","life",4.06f));
            data.add(new WordSim("production","crew",6.25f));
            data.add(new WordSim("television","film",7.72f));
            data.add(new WordSim("lover","quarrel",6.19f));
            data.add(new WordSim("viewer","serial",2.97f));
            data.add(new WordSim("possibility","girl",1.94f));
            data.add(new WordSim("population","development",3.75f));
            data.add(new WordSim("morality","importance",3.31f));
            data.add(new WordSim("morality","marriage",3.69f));
            data.add(new WordSim("Mexico","Brazil",7.44f));
            data.add(new WordSim("gender","equality",6.41f));
            data.add(new WordSim("change","attitude",5.44f));
            data.add(new WordSim("family","planning",6.25f));
            data.add(new WordSim("opera","industry",2.63f));
            data.add(new WordSim("sugar","approach",0.88f));
            data.add(new WordSim("practice","institution",3.19f));
            data.add(new WordSim("ministry","culture",4.69f));
            data.add(new WordSim("problem","challenge",6.75f));
            data.add(new WordSim("size","prominence",5.31f));
            data.add(new WordSim("country","citizen",7.31f));
            data.add(new WordSim("planet","people",5.75f));
            data.add(new WordSim("development","issue",3.97f));
            data.add(new WordSim("experience","music",3.47f));
            data.add(new WordSim("music","project",3.63f));
            data.add(new WordSim("glass","metal",5.56f));
            data.add(new WordSim("aluminum","metal",7.83f));
            data.add(new WordSim("chance","credibility",3.88f));
            data.add(new WordSim("exhibit","memorabilia",5.31f));
            data.add(new WordSim("concert","virtuoso",6.81f));
            data.add(new WordSim("rock","jazz",7.59f));
            data.add(new WordSim("museum","theater",7.19f));
            data.add(new WordSim("observation","architecture",4.38f));
            data.add(new WordSim("space","world",6.53f));
            data.add(new WordSim("preservation","world",6.19f));
            data.add(new WordSim("admission","ticket",7.69f));
            data.add(new WordSim("shower","thunderstorm",6.31f));
            data.add(new WordSim("shower","flood",6.03f));
            data.add(new WordSim("weather","forecast",8.34f));
            data.add(new WordSim("disaster","area",6.25f));
            data.add(new WordSim("governor","office",6.34f));
            data.add(new WordSim("architecture","century",3.78f));

        }
    }
  
    
}
