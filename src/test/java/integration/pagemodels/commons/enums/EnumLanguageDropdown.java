package integration.pagemodels.commons.enums;

/**
 * Created by R.Shayda on 05.02.2016.
 */
public enum EnumLanguageDropdown {

    ENGLISH("English"), OTHER("Other"), SELECT_ONE("selectOne");


    private final String languageOption;

    private EnumLanguageDropdown(String inputLabel) {
        this.languageOption = inputLabel;

    }

    public String getLanguageOption() {
        return this.languageOption;
    }
}
