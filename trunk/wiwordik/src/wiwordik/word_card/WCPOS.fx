/* WCLangPOS.fx - A part of word card corresponds to a language-POS part
 * of a page (entry) in Wiktionary.
 *
 * Copyright (c) 2009 Andrew Krizhanovsky <andrew.krizhanovsky at gmail.com>
 * Distributed under GNU General Public License.
 */

package wiwordik.word_card;

import wikt.sql.*;
import wikipedia.sql.Connect;
import wikipedia.language.LanguageType;
import wikt.constant.POS;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.Group;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.*;

/** Part of speech (POS) part of word card (WC) of a Wiktionary page.
 *
 * @see wikt.word.WPOS
 */
public class WCPOS {
    def DEBUG : Boolean = false;
    
    /** Part of speech. */
    var pos_type : POS;

    /** Title of the POS card. */
    var POS_header_value : String = "POS";


    /** (1) Meaning consists of Definitions + Quotations. */
    var meaning : WCMeaning[];    // private WMeaning[] meaning;

    var meaning_group : VBox = VBox { spacing: 14 };


    public var group: VBox = VBox {
        spacing: 5
        content: [
            Text {
                content: bind POS_header_value
                font: Font {  size: 14  }
                fill: Color.BROWN
            }
            
            meaning_group]
    };


    /** Creates a language part of card (parts of wiki pages),
     * builds visual block with this language.
    **/
    public function create(conn : Connect,
                            //_tpage : TPage,
                            //_lang : LanguageType,
                            _lang_pos : TLangPOS
                           ) {

        //if(null != _tpage and null != _lang_pos) {
        if(null != _lang_pos) {

            var _pos : POS = _lang_pos.getPOS().getPOS();

            POS_header_value = "{_pos.toString()}";
            if (DEBUG)
                POS_header_value = "{_pos.toString()}; lang_pos.id = {_lang_pos.getID()}";
            
            def mm : TMeaning[] = TMeaning.get(conn, _lang_pos);
            for(m in mm) {

                def _meaning : WCMeaning = new WCMeaning();
                _meaning.create(conn, m, sizeof mm);

                insert _meaning into meaning;                       // logic
                insert _meaning.group into meaning_group.content;   // visual
            }


            // System.out.println("WCLanguage.create(). language_name_value = {language_name_value}");

            /*def definitions : String[] = WTMeaning.getDefinitionsByLangPOS(conn, lang_pos);

            def synonyms : String[] = WTRelation.getForEachMeaningByPageLang(conn, lang_pos, Relation.synonymy);
            //def synonyms : String[] = ["", "synonyms 2"];

            for (j in [0.. sizeof definitions-1]) {
                s = s.concat("  {j+1}. {definitions[j]}\n");

                if(sizeof synonyms > j and synonyms[j].length() > 0)
                    //s = s.concat("    Syn.: {synonyms[j]}\n");
                    s = s.concat("    Syn.: {synonyms[j]}\n");
                            //fill: Color.rgb (0xec, 0xed, 0xee)
                    // todo: Text {fill: Color.BLUE; content: synonyms[j]}
            }*/
        }
    }

}
