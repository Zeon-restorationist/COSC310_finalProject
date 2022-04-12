import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class bingTranslate {

    public bingTranslate(){
        Translate.setClientId("translatorICHIGO");
        Translate.setClientSecret("YPCXGm]~xG3?NX&^_{cn`#AwmyQd9:fFJtHq");
    }
    public static String translate(String s) throws Exception{
        String t = "";
        String englishTranslation = Translate.execute(s, Language.INDONESIAN);
        t = englishTranslation;
        return t;
    }












}

