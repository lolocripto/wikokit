/* WPOSRu.java - corresponds to a POS level of Russian Wiktionary word.
 * 
 * Copyright (c) 2008 Andrew Krizhanovsky <andrew.krizhanovsky at gmail.com>
 * Distributed under GNU General Public License.
 */

package wikt.multi.ru;

import wikt.util.POSText;
import wikt.util.LangText;
//import wikt.constant.POSType;
import wikt.constant.POS;
//import wikt.multi.ru.POSTypeRu;

import wikipedia.util.StringUtilRegular;

import wikipedia.language.LanguageType;
import wikipedia.text.WikiParser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.List;
import java.util.ArrayList;

/** Splits text to fragments related to different parts of speech (POS).
 * POS is basically a level 2 header in Russian Wiktionary, e.g. for "roast":
 * ==roast I==
 * ...
 * ==roast II==
 * 
 * (and a level 3 in English Wiktionary: ===Verb===)
 * 
 * See http://ru.wiktionary.org/wiki/%D0%92%D0%B8%D0%BA%D0%B8%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8C:%D0%A7%D0%B0%D1%81%D1%82%D0%B8_%D1%80%D0%B5%D1%87%D0%B8
 *     http://ru.wiktionary.org/wiki/Викисловарь:Части речи
 *
 * See http://ru.wiktionary.org/wiki/Викисловарь:Правила оформления статей
 */
public class WPOSRu {

    private final static POSText[] NULL_POS_TEXT_ARRAY = new POSText[0];
    
    /** start of the POS block, 
     * current: ==word I==
     * old: == Существительное I ==
     * it can absent...
     */
    private final static Pattern ptrn_2nd_level = Pattern.compile(  // Vim: ^==\s*\([^=]\+\)\s*==\s*\Z
            //"(?m)^\\s*==");
            "(?m)^==\\s*([^=]+?)\\s*==\\s*");
            // "\\A==\\s*([^=]+)\\s*==\\s*\\Z");
    
    /** Gets first two letter after ==Морфологические и синтаксические свойства==
     * e.g. "{{" or "Су"ществительное, or "Гл"агол...
     */
    private final static Pattern ptrn_morpho_then_2letters = Pattern.compile(
            "===\\s*Морфологические и синтаксические свойства\\s*===\\s*\\n\\s*(..)");
            //"===\\s*Морфологические и синтаксические свойства\\s*==="); +
            //"\\A===\\s*Морфологические и синтаксические свойства\\s*===\\s*\\n\\s*(..)");
    
    /** page_title - word which are described in this article 'text'
     * @param lt .text will be parsed and splitted, 
     *           .lang is not using now, may be in future...
     * 
     * 1) Split the following text to "lead I" and "leat II"
     * 2) Extracts part of speech "гл" from "lead II"
     * <PRE>
     * == lead I == 
     * English text1 
     * == lead II== 
     * ===Морфологические и синтаксические свойства===" 
     * {{гл en reg|lead}}";</PRE>
     * 
     * todo isPOSHeader() (remove acce'nt -> accent) or guessPOS
     */
    public static POSText[] splitToPOSSections (
            String      page_title,
            LangText    lt)
    {
        String  pos_title = "";
        if(null == lt.text || 0 == lt.text.length()) {
            return NULL_POS_TEXT_ARRAY;
        }
        
        Matcher m = ptrn_2nd_level.matcher(lt.text.toString());
        boolean b_next = m.find();
        
        if(!b_next) {   // there is only one ==Second level header== in this language in this word (e.g. "== lead I ==" only)
            POSText[] pos_section_alone = new POSText[1];
            pos_section_alone[0] = guessPOS(lt.text);
            return pos_section_alone;
        }
                                                                // there are more than one POS in this language in this word
        List<POSText> pos_sections = new ArrayList<POSText>();  // result will be stored to
        StringBuffer current_pos_section = new StringBuffer();
        
        int start, end; // "<start> == Verb I == ... <end> == Verb II ==" position of POS block in the lt.text
        
        start = 0;
        pos_title = WikiParser.removeAcuteAccent(new StringBuffer(m.group(1)), LanguageType.ru).toString();
        b_next = m.find();
        if(b_next) { 
            end = m.start();
        } else {
            end = 0;    // there is only one POS block, e.g. ==Verb I==, it is a little strange ...
        }
        
        while(b_next) {
            current_pos_section.append(lt.text.substring(start, end));
            
            POS p = guessPOSWith2ndLevelHeader(page_title, pos_title, current_pos_section);
            if(null != p) { // OK. It's POS header, though it's possible that p=unknown :(
                POSText pt = new POSText(p, current_pos_section.toString());
                current_pos_section.setLength(0);
                pos_sections.add(pt);
            
            } else {
                // null, if this is another 2nd level header, e.g. Bibliography or References
                current_pos_section.append(""); // +??? this Bibliography text
                // todo ...
            }
            
            // variant I:  \1==page_title+"I", "II", ... "VIII"
            // variant II: \1==Verb|Noun|... (In Russian)
            pos_title = WikiParser.removeAcuteAccent(new StringBuffer(m.group(1)), LanguageType.ru).toString();
            
            b_next = m.find();
            if(b_next) {
                start = end;
                end = m.start();
            }
        }
        
        current_pos_section.append(lt.text.substring(end)); // last POS section
        
        POS p = guessPOSWith2ndLevelHeader(page_title, pos_title, current_pos_section);
        if(null != p) { // OK. It's last POS header, though it's possible that p=unknown :(
            POSText pt = new POSText(p, current_pos_section.toString());
            current_pos_section.setLength(0);
            pos_sections.add(pt);
        }
        
        return (POSText[])pos_sections.toArray(NULL_POS_TEXT_ARRAY);
    }
    

    /** The POS should be extracted from the texts, e.g.<PRE>
     * noun:
     * ===Морфологические и синтаксические свойства===
     * {{сущ en|слоги=lead|lead|leads}}
     * 
     * verb:
     * ===Морфологические и синтаксические свойства===
     * {{гл ru 4b-ся
     * {{гл ru 8b/b^
     * {{гл ru 5c'^-т
     * 
     * adjective:
     * ===Морфологические и синтаксические свойства===
     * {{прил ru 1*a
     * 
     * adverb:
     * ===Морфологические и синтаксические свойства===
     * 
     * {{adv ru|слоги={{по-слогам|ра|но|ва́|то}}|или=предикатив|или-кат=предикативы|}}
     * 
     * {{adv-ru|
     * Наречие, неизменяемое.
     * 
     * Old formatting 
     * 
     *  ===Морфологические и синтаксические свойства===
     *  {{СущМужНеодуш1c(1)
     *  {{СущЖенНеодуш8a
     *  Существительное, ...
     * 
     * {{прил ia}}
     * 
     * {{парадигма-рус  // old formatting (>500, < 1000 pages)
     * |шаблон=Гл11b/c
     * {{Гл1a</PRE>
     */
    public static POSText guessPOS (StringBuffer text)
    {
        POS p_type = POS.unknown;
        
        if(null == text || 0 == text.length()) {
            return new POSText(p_type, text.toString());
        }
        
        Matcher m = ptrn_morpho_then_2letters.matcher(text.toString());
        boolean b = m.find();
        if(b) {
            String two_letters = m.group(1);
            
            if(two_letters.equalsIgnoreCase("{{")) {
                // if \1=="{{" then get first letters till space
                // substring started after the symbol "{{"
                String pos_name = StringUtilRegular.getLettersTillSpace(text.substring(m.end()));
                if(POSTypeRu.has(pos_name)) {
                    p_type = POSTypeRu.get(pos_name);
                } else {
                    // old template of POS with hyphen, e.g. "{{adv-ru|}} instead of {{adv ru|}}
                    pos_name = StringUtilRegular.getLettersTillHyphen(text.substring(m.end()));
                    if(POSTypeRu.has(pos_name)) {
                        p_type = POSTypeRu.get(pos_name);
                    }
                }
            } else {
                // else get two_letters + the first Word
                // todo 
                // ....
            }
        }
        
        return new POSText(p_type, text.toString());
    }
    
    
    /** The POS should be extracted from the text.
     * @return POS, e.g. POS.verb for "== Verb =="
     * @return POS.unknown if text contains "== Word I ==" or "== Word II =="
     * (without additional POS data, e.g. "verb").
     * @return else null 
     * 
     * Example of text.<PRE>
     * noun:
     * == bar II ==
     * ===Морфологические и синтаксические свойства===
     * {{сущ en|nom-sg=bar|слоги=bar}}
     * 
     * adjective:
     * == round I ==
     * ===Морфологические и синтаксические свойства===
     * {{прил en|round|слоги=round}}
     * 
     * adverb (old style for "fast"):
     * ==Наречие==
     * {{нар en|fast}}
     * 
     * adverb (very old style for DE "fast")
     * <b>fast</b>
     * Наречие
     * ==Произношение==
     * {{transcription|fɑst}}
     * ==Значение==
     * [[почти]]
     * </PRE>
     * 
     * @param page_title    word, name of the article, e.g. "lead"
     * @param pos_title     extracted 2nd level title, e.g. "lead I", "lead II", or "Adverb" (old style)
     */
    public static POS guessPOSWith2ndLevelHeader (String page_title,String pos_title, StringBuffer text)
    {
        POSText pt = guessPOS (text);
        
        if(POS.unknown != pt.getPOSType() || null == text || 0 == text.length()) {
            return pt.getPOSType();
        }
        
        // compare pos_title with POSType
        if( POSTypeRu.has(pos_title)) {
            return POSTypeRu.get(pos_title);
        }
        
        // get first word without number, e.g. ==Verb I== -> "Verb"
        String pos_name = StringUtilRegular.getLettersTillSpace(pos_title);
        if( POSTypeRu.has(pos_name)) {
            return POSTypeRu.get(pos_name);
        }
        
        if(page_title.equalsIgnoreCase(pos_name)) { // It's POS because, e.g. "round I" == "round" + "I", but it's unknown POS
            return POS.unknown;
        }
        
        // takes one by one second-level-headers (first word, e.g. Noun for "Noun I", compare with POS
        /*Matcher m = ptrn_2nd_level.matcher(text.toString());
        
        while(m.find()) {   
           String s = m.group(1);   // header 2nd level
            if( POSTypeRu.has(s)) {
                return POSTypeRu.get(s);
            }
        }*/
        
        return null;
    }
        
}