# Introduction #

If you have some issues with the character encoding in your application (e.g. you see characters replaced with '?'), then this page may be usefull for you, I hope.

# Database encoding #

The first task is to correctly upload the dump into MySQL. (The zero task is to download parsed Wiktionary database from this site.)

I prefer several commands in windows command-line cmd.exe:
```
mysql$ CREATE DATABASE enwikt20100824_parsed;
mysql$ USE enwikt20100824_parsed
mysql$ SOURCE path_to_file.sql
```

See, e.g.: [File\_wikt\_parsed\_empty\_sql](http://code.google.com/p/wikokit/wiki/File_wikt_parsed_empty_sql)

My connection parameters (from Java source code):
```
"enwikt20111008_parsed?useUnicode=false&characterEncoding=ISO8859_1&autoReconnect=true&useUnbufferedInput=false";
```

There are the following parameters of the parsed Wiktionary database in MySQL:
```
mysql> SHOW VARIABLES LIKE 'character_set%';
```

| Variable\_name            | Value  |
|:--------------------------|:-------|
| character\_set\_client     | latin1 |
| character\_set\_connection | latin1 |
| character\_set\_database   | latin1 |
| character\_set\_filesystem | binary |
| character\_set\_results    | latin1 |
| character\_set\_server     | latin1 |
| character\_set\_system     | utf8   |

# Application level #

As I understand, all text information (in Wikipedia and Wiktionary databases) is stored in the binary format.
MySQL thinks (see table above) that the data are stored in the `latin1` format.

So in Java code I am using the following function to decode text from
binary (bytes) to UTF8:

```
str_sql.append("SELECT text FROM wiki_text WHERE id=");
str_sql.append(id);
ResultSet rs = s.executeQuery (str_sql.toString());

String text = bytesToUTF8(rs.getBytes("text"));

...

public static String bytesToUTF8(byte[] bytes) {
       return bytesTo(bytes, "UTF8");
}

public static String bytesTo(byte[] bytes, String encode) {
       try {
           return new String(bytes, encode);
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           return EMPTY_STRING;
       }
   }
```

I hope it will help to find the solution using your programming language.

# Mac and Sequel #

_From the letter of the user, who successfully solved encoding problems._

It was used the Mac MySQL frontend Sequel (similar to
MySQLWorkbench, at least for the basic features).

The following variables were used (actually, _character\_set\_client_ and _character\_set\_database_ were "binary" also, but somehow they changed to utf8):

  * character\_set\_client     utf8
  * character\_set\_connection    binary
  * character\_set\_database    utf8
  * character\_set\_filesystem   binary
  * character\_set\_results    latin1
  * character\_set\_server     binary
  * character\_set\_system    utf8

According to this [article](http://www.bluetwanger.de/blog/2006/11/20/mysql-and-utf-8-no-more-question-marks/) the variable "character\_set\_server" only determines the **default** encoding of new databases.

Since a non-default encoding was chosen (during the creation of a database in order to load the Wiktionary parsed database dump file in), the above setting of `character_set_server` should be irrelevant.

It was created a database with encoding "cp1252 West European (latin1)".
Then the dump file with encoding "Western (ISO Latin 1)" was imported to the MySQL database.

# Notes #
## SQLite database ##

If you prefer work with SQLite instead of MySQL, then install `wiwordik` application via Java Web Start.

After installation you can find the parsed Wiktionary database in
SQLite format on your computer in the folder:
C:\Documents and Settings\YOUR USER NAME\.wiwordik\

# Links #
  * Markus Bertheau. [MySQL and UTF-8 — no more question marks!](http://www.bluetwanger.de/blog/2006/11/20/mysql-and-utf-8-no-more-question-marks/)