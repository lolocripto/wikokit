This step should be done before the parsing of the Wiktionary database (see [Getting started with Wiktionary parser](GettingStartedWiktionaryParser.md)).

# MySQL #

```
mysql$ CREATE DATABASE enwikt20100824_parsed;
mysql$ USE enwikt20100824_parsed
mysql$ SOURCE D:\all\projects\java\synonyms\wikokit\wikt_parser\doc\wikt_parsed_empty.sql
mysql$ GRANT SELECT ON enwikt20100824.* TO javawiki@'%';
mysql$ GRANT ALL ON enwikt20100824_parsed.* TO javawiki@'%';
mysql$ FLUSH PRIVILEGES;
```

If MySQL whines and prints error message that access is denied then you can try use "localhost" instead of "%":
```
mysql$ GRANT SELECT ON enwikt20100824.* TO javawiki@'localhost';
```

You can list the privileges that are granted to a MySQL user account ('javawiki'):
```
mysql$ SHOW GRANTS FOR javawiki;
```

# Details #

  * Edit `wikt_parsed_empty.mwb` file in [MySQL Workbench](MySQLWorkbench.md), export it to `wikt_parsed_empty.sql`
  * Make substitution in `wikt_parsed_empty.sql` in VIM by RE:
```
%s/`mydb`\.//g
```
  * Create DB in MySQL, e.g. `ruwikt20090122_parsed`
  * `USE ruwikt20090122_parsed`
  * `SOURCE wikt_parsed_empty.sql`

# Machine-readable database schema #

The structure (tables and relations) of the Wiktionary parsed database (database layout):

![http://wikokit.googlecode.com/svn/trunk/wikt_parser/doc/screenshots/wikt_parsed_empty_with_foreign_keys.png](http://wikokit.googlecode.com/svn/trunk/wikt_parser/doc/screenshots/wikt_parsed_empty_with_foreign_keys.png)

Set of tables related to quotations (fragment of the Wiktionary parsed database):

![http://wikokit.googlecode.com/svn/trunk/wiki/wiwordik.attach/db_scheme/quote_tables.png](http://wikokit.googlecode.com/svn/trunk/wiki/wiwordik.attach/db_scheme/quote_tables.png)

# Misc #

## Index ##

CREATE TABLE test (id INT, INDEX(id));

ALTER TABLE `relation` DROP INDEX `idx_software_key` ,
ADD INDEX `idx_software_key` ( `software_key` ( 3 ) );

# See also #
  * [MySQLWorkbench](MySQLWorkbench.md) - How to create empty SQL file for the Wiktionary parsed database.

# Previous step #
  * [Import Wiktionary database into local MySQL database](MySQL_import.md)

# Next step #
  * [Setup NetBeans environment for parsing, run parser](SetupNetBeansForParsing.md)