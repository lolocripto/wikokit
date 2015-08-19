

# Introduction #

[D2RQ](http://www4.wiwiss.fu-berlin.de/bizer/d2rq/) platform enables applications to access a RDF-view on a non-RDF database via the SPARQL Protocol.

# Setup #

Download the latest parsed Wiktionary database (e.g. file `enwikt20101030_parsed.7z` in the section Downloads).
Upload it to your local MySQL database:
```
mysql> CREATE DATABASE enwikt20101030_parsed;
mysql> USE enwikt20101030_parsed;
mysql> SOURCE c:\enwikt20101030_parsed.sql
```

Create MySQL user and provide access to the parsed Wiktionary database:
```
mysql> CREATE USER rdfmapper;
mysql> GRANT SELECT ON enwikt20101030_parsed.* TO rdfmapper@'%';
mysql> FLUSH PRIVILEGES;
```

Download and install [D2RQ](http://www4.wiwiss.fu-berlin.de/bizer/d2rq/).

Generate `.n3` mapping file by the scheme of MySQL database:
```
cd C:\w\d2r-server-0.7\
generate-mapping -o mapping-wikt_parsed.n3 -u rdfmapper jdbc:mysql://localhost/enwikt20101030_parsed
```

You can find an example of .n3 file in the section Downloads (file `mapping-enwikt20101030_parsed_en.7z`).

Change the following lines in the `.n3` mapping file:
  * delete lines with `index_ith.lat.foreign_has_definition` (_outdated_);
  * change `vocab` to `wikpa` (WIktionary PArsed database):
```
%s/vocab/wikpa/g
```
  * add lines:
```
map:Configuration a d2rq:Configuration;
    d2rq:resultSizeLimit 33;
    .
```

Run server:
```
cd C:\w\d2r-server-0.7\
d2r-server mapping-wikt_parsed.n3
```

Open web browser:
```
http://localhost:2020
```

## Online SPARQL endpoint ##

You can check your SPARQL requests in the following server: [http://cais.iias.spb.su:2020/snorql](http://cais.iias.spb.su:2020/snorql)

E.g. the following SPARQL request prints ID of English language in the database:
```
SELECT ?langIdDest WHERE {
  ?langDest wikpa:lang_code "en";
            wikpa:lang_id ?langIdDest.
}
```
Run [this link](http://cais.iias.spb.su:2020/snorql/?query=SELECT+%3FlangIdDest+WHERE+{%0D%0A++%3FlangDest+wikpa%3Alang_code+%22en%22%3B%0D%0A++++++++++++wikpa%3Alang_id+%3FlangIdDest.%0D%0A}). If you have "communication failure" error, then try click the button "Go".

You can test SPARQL examples presented below.

## WikPaSPARQL - Java project with SPARQL examples ##

The small Java project was developed in Eclipse. This project contains SPARQL requests addressed to Wiktionary parsed database.
Download [WikPaSPARQL\_20110618.7z](http://code.google.com/p/wikokit/downloads/detail?name=WikPaSPARQL_20110618.7z&can=2&q=).

# SPARQL examples #

## Get definition (meaning) by word and language ##

Let's find all definitions for the English word "dog":

Open URL: http://localhost:2020/snorql/

Paste SPARQL request:
```
SELECT ?langId ?pageId ?langPosId ?meaningId ?wikiTextIdDef ?definition
WHERE {
    ?lang wikpa:lang_code "en";
          wikpa:lang_id ?langId.

    ?page wikpa:page_page_title "dog";
          wikpa:page_id ?pageId.

    ?lang_pos wikpa:lang_pos_page_id ?pageId;
              wikpa:lang_pos_lang_id ?langId;
              wikpa:lang_pos_id ?langPosId.

    ?meaning wikpa:meaning_id ?meaningId;
             wikpa:meaning_lang_pos_id ?langPosId;
             wikpa:meaning_wiki_text_id ?wikiTextIdDef.

    ?wiki_text wikpa:wiki_text_id ?wikiTextIdDef;
             wikpa:wiki_text_text ?definition.
}
```

You can access these data from Java code by using [Jena](http://en.wikipedia.org/wiki/Jena_%28framework%29):
```
package wikpasparql;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import de.fuberlin.wiwiss.d2rq.ModelD2RQ;

public class SPARQLExample {

	public static void main(String[] args) {
        ModelD2RQ m = new ModelD2RQ("file:mapping-wikt_parsed.n3");
		
        String sparql = 
            "PREFIX wikpa: <http://localhost:2020/wikpa/resource/>" +
            "SELECT ?langId ?pageId ?wikiText WHERE {" +
            "   ?lang wikpa:lang_code \"en\"; " +
            "         wikpa:lang_id ?langId. " +
            
            "   ?page wikpa:page_page_title \"dog\"; " +
            "         wikpa:page_id ?pageId." +
            
            "   ?lang_pos wikpa:lang_pos_page_id ?pageId; " +
            "         wikpa:lang_pos_lang_id ?langId; " +
            "         wikpa:lang_pos_id ?langPosId. " +

            "   ?meaning wikpa:meaning_lang_pos_id ?langPosId; " +
            "         wikpa:meaning_wiki_text_id ?wikiTextId. " +

            "   ?wiki_text wikpa:wiki_text_id ?wikiTextId; " +
            "         wikpa:wiki_text_text ?wikiText. " +
		    "}";
		
		Query q = QueryFactory.create(sparql); 
		ResultSet rs = QueryExecutionFactory.create(q, m).execSelect();
		while (rs.hasNext()) {
			QuerySolution row = rs.nextSolution();
			System.out.println("langID: " + row.getLiteral("langId").getString());
			System.out.println("pageId: " + row.getLiteral("pageId").getString());
			System.out.println("wikiText: " + row.getLiteral("wikiText").getString());
		}
	}
}
```

## Get list of synonyms ##

There are two ways to find synonyms in the database of the parsed English Wiktionary:
  * _direct way_ - from entry to words, which are listed in the section _Synonyms_;
  * _reverse way_ - from synonyms to entries, i.e. a list of entries which contain this word as a synonym in the section _Synonyms_;

### 1. From entry to synonyms ###

Let's get a synonym for the Swedish (**sv** code) word [ordbok](http://en.wiktionary.org/wiki/ordbok#Synonyms).

SPARQL request:
```
SELECT ?langId ?pageId ?langPosId ?meaningId ?relationTypeId ?wikiTextIdRel ?relationWord
WHERE {
    ?lang wikpa:lang_code "sv";
          wikpa:lang_id ?langId.

    ?page wikpa:page_page_title "ordbok";
          wikpa:page_id ?pageId.

    ?lang_pos wikpa:lang_pos_page_id ?pageId;
              wikpa:lang_pos_lang_id ?langId;
              wikpa:lang_pos_id ?langPosId.

    ?meaning wikpa:meaning_id ?meaningId;
             wikpa:meaning_lang_pos_id ?langPosId.

    ?relation_type wikpa:relation_type_name "synonyms";
                   wikpa:relation_type_id ?relationTypeId.

    ?relation wikpa:relation_meaning_id ?meaningId;
              wikpa:relation_relation_type_id ?relationTypeId;
              wikpa:relation_wiki_text_id ?wikiTextIdRel.

    ?wiki_text wikpa:wiki_text_id ?wikiTextIdRel;
              wikpa:wiki_text_text ?relationWord.
}
```

### 2. From synonym to entries ###

Let's get a list of entries which contain a word (e.g. English phrase [computer language](http://en.wiktionary.org/wiki/computer_language)) in the section Synonyms.

SPARQL request:
```
SELECT ?langId ?pageId ?langPosId ?meaningId ?relationTypeId ?wikiTextIdRel ?entry
WHERE {
    ?lang wikpa:lang_code "en";
          wikpa:lang_id ?langId.

    ?page wikpa:page_page_title ?entry;
          wikpa:page_id ?pageId.

    ?lang_pos wikpa:lang_pos_page_id ?pageId;
              wikpa:lang_pos_lang_id ?langId;
              wikpa:lang_pos_id ?langPosId.

    ?meaning wikpa:meaning_id ?meaningId;
             wikpa:meaning_lang_pos_id ?langPosId.

    ?relation_type wikpa:relation_type_name "synonyms";
                   wikpa:relation_type_id ?relationTypeId.

    ?relation wikpa:relation_meaning_id ?meaningId;
              wikpa:relation_relation_type_id ?relationTypeId;
              wikpa:relation_wiki_text_id ?wikiTextIdRel.

    ?wiki_text wikpa:wiki_text_id ?wikiTextIdRel;
              wikpa:wiki_text_text "computer language".
}
```
Result:
```
entry=language
entry=programming language
```

## Translate word from one language to another ##

There are three ways to find translation in the database of the parsed English Wiktionary:
  * English entries:
    * _direct translation_ - from English word to words in other languages, which are listed in the section _Translation_ of the entry;
    * _reverse translation_ - from the non-English word in the section _Translation_ to the English title (header) of the entry;
  * non-English entries:
    * from the non-English word (entry title) to the definition in English of this entry;

Let's find translation of the English (**en** code) phrase [rain cats and dogs](http://en.wiktionary.org/wiki/rain_cats_and_dogs) into all languages by the first way (_direct translation_).

SPARQL request:
```
SELECT ?langId ?pageId ?langPosId ?meaningId ?translationId ?translationEntryId ?wikiTextIdTrans ?translationWord
WHERE {
    ?lang wikpa:lang_code "en";
          wikpa:lang_id ?langId.

    ?page wikpa:page_page_title "rain cats and dogs";
          wikpa:page_id ?pageId.

    ?lang_pos wikpa:lang_pos_page_id ?pageId;
              wikpa:lang_pos_lang_id ?langId;
              wikpa:lang_pos_id ?langPosId.

    ?meaning wikpa:meaning_id ?meaningId;
             wikpa:meaning_lang_pos_id ?langPosId.

    ?translation wikpa:translation_id ?translationId;
                 wikpa:translation_lang_pos_id ?langPosId;
                 wikpa:translation_meaning_id ?meaningId.

    ?translation_entry wikpa:translation_entry_id ?translationEntryId;
                       wikpa:translation_entry_translation_id ?translationId;
                       wikpa:translation_entry_wiki_text_id ?wikiTextIdTrans.

    ?wiki_text wikpa:wiki_text_id ?wikiTextIdTrans;
              wikpa:wiki_text_text ?translationWord.
}
```

Let's find translation of the English (**en** code) word [dog](http://en.wiktionary.org/wiki/dog) into French by the first way (_direct translation_).

```
SELECT ?langIdDest ?langIdSource ?pageId ?langPosId ?meaningId ?translationId ?translationEntryId ?wikiTextIdTrans ?translationWord
WHERE {
    ?langDest wikpa:lang_code "en";
          wikpa:lang_id ?langIdDest.

    ?page wikpa:page_page_title "book";
          wikpa:page_id ?pageId.

    ?lang_pos wikpa:lang_pos_page_id ?pageId;
              wikpa:lang_pos_lang_id ?langIdDest;
              wikpa:lang_pos_id ?langPosId.

    ?meaning wikpa:meaning_id ?meaningId;
             wikpa:meaning_lang_pos_id ?langPosId.

    ?translation wikpa:translation_id ?translationId;
                 wikpa:translation_lang_pos_id ?langPosId;
                 wikpa:translation_meaning_id ?meaningId.

    ?langSource wikpa:lang_code "fr";
          wikpa:lang_id ?langIdSource.

    ?translation_entry wikpa:translation_entry_id ?translationEntryId;
                       wikpa:translation_entry_translation_id ?translationId;
                       wikpa:translation_entry_lang_id ?langIdSource;
                       wikpa:translation_entry_wiki_text_id ?wikiTextIdTrans.

    ?wiki_text wikpa:wiki_text_id ?wikiTextIdTrans;
              wikpa:wiki_text_text ?translationWord.
}
```

Let's find translations of the French (**fr** code) word
[livre](http://en.wiktionary.org/wiki/livre) into English by the second way (_reverse translation_).

As I understand, the system founds translations in the data extracted from translation sections of the following Wiktionary pages: [pound](http://en.wiktionary.org/wiki/pound) and [book](http://en.wiktionary.org/wiki/book).

SPARQL request:
```
SELECT ?langIdDest ?englishWord ?langIdSource ?pageId ?langPosId ?meaningId ?translationId ?translationEntryId ?wikiTextIdTrans ?translationWord
WHERE {
    ?langDest wikpa:lang_code "en";
          wikpa:lang_id ?langIdDest.

    ?page wikpa:page_page_title ?englishWord;
          wikpa:page_id ?pageId.

    ?lang_pos wikpa:lang_pos_page_id ?pageId;
              wikpa:lang_pos_lang_id ?langIdDest;
              wikpa:lang_pos_id ?langPosId.

    ?meaning wikpa:meaning_id ?meaningId;
             wikpa:meaning_lang_pos_id ?langPosId.

    ?translation wikpa:translation_id ?translationId;
                 wikpa:translation_lang_pos_id ?langPosId;
                 wikpa:translation_meaning_id ?meaningId.

    ?langSource wikpa:lang_code "fr";
          wikpa:lang_id ?langIdSource.

    ?translation_entry wikpa:translation_entry_id ?translationEntryId;
                       wikpa:translation_entry_translation_id ?translationId;
                       wikpa:translation_entry_lang_id ?langIdSource;
                       wikpa:translation_entry_wiki_text_id ?wikiTextIdTrans.

    ?wiki_text wikpa:wiki_text_id ?wikiTextIdTrans;
              wikpa:wiki_text_text "livre".
}
```

# Problems and question #

  * How to track and stop very long queries?

# See also #
  * [SQLExamples](SQLExamples.md)