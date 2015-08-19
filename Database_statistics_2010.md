# English Wiktionary, dump 20100106 #

Source database: ruwikt20090122, see [Database dump progress](http://download.wikimedia.org/backup-index.html)

Total pages in source database: 1 543 585

... todo link

## Parsing ##

### enwikt20100106\_parsed ###

Time ...2 months?

## Parsed database ##

See [database layout](http://wikokit.googlecode.com/svn/trunk/wikt_parser/doc/screenshots/wikt_parsed_20090122_2.png).

There are the following results (name of table, size of table, and my comment).

| **Table** | **Size1** | **Size2** | **Table description** | **Comment** |
|:----------|:----------|:----------|:----------------------|:------------|
| page      | 724,779   |           |                       | Should be > 1 543 585 pages in source database, due to red links (i.e. wikification) |
| relation  | 91,413    |           | number of semantic relations, e.g. synonyms, antonyms, etc. |             |
| lang\_pos | 652,305   |           | number of pairs: language & part of speech, one Wiktionary page can contain several such pairs. |             |
| wiki\_text | 976,026   |           | number of meaning definitions + number of semantic relations phrases (divided by comma, semicolon) + number of wikified translations |             |
| wiki\_text\_words | 1,507,797 |           | number of wikified words (in meaning definitions + in semantic relations + in translations) |             |
| meaning   | 863,105   |           | number of meanings, one word can have several meanings |             |
| inflection | 83,144    |           | number of unique inflectional wordforms | It is extracted from wikified word definitions |
| translation | 53,689    |           | number of translation section boxes (in best case: one translation box corresponds to one meaning) | in practice: 35 009 < 177 158 |
| translation\_entry | 355,157   |           | number of different translations (pairs of translations) |             |

**Size1** is a statistics of the database enwikt20100106\_parsed when only 1/3 of the enwikt20100106 was parsed, and **Size2** - total size of enwikt20100106\_parsed.

## See also ##
  * [SQLWiktParsedPhantasmagoria](SQLWiktParsedPhantasmagoria.md)