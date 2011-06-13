package beans.utilities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CountryBean extends ArrayList implements Serializable {

    public CountryBean(){

        Locale[] locales = Locale.getAvailableLocales();
        
        for (Locale locale : locales){

            String countryName = locale.getDisplayCountry();
            if (countryName.length() > 0){
                add(new OptionItem(countryName,countryName));
            }
        }        
        Collections.sort(this);
        add(0,new OptionItem("","Select Country"));

    }

    public class OptionItem implements Comparable {

    private String id;
    private String label;

    public String getId() { return this.id; }
    public String getLabel() { return this.label; }

    public OptionItem( String id, String label )
    {
        this.id = id;
        this.label = label;
    }

    public int compareTo(Object o) {
        OptionItem oi = (OptionItem)o;
        return id.compareTo((oi.getId()));
    }

}
}