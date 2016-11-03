package integration.pagemodels.commons.enums;


public enum EnumInputLabels{
    FIRST_NAME("First Name"), LAST_NAME("Last Name"), COMPANY_NAME("Company Name"), WORK_PHONE("Work Phone"),
    WEBSITE("Website"), LANGUAGE("Language"), MONTHLY_PPC_SPEND("Monthly PPC Spend"), YOUR_AGENCYS_NAME("Your Agency's Name"),
    YOUR_AGENCYS_WEBSITE("Your Agency's Website"), TOTAL_MONTHLY_PPC_SPEND_YOU_MANAGE("Total Monthly PPC Spend you Manage"),
    CREATE_A_WORDSTREAM_PASSWORD("Create a WordStream password"), VERIFY_WORDSTREAM_PASSWORD("Verify WordStream password");


    private final String formLabels;

    private EnumInputLabels(String inputLabel) {
        this.formLabels = inputLabel;

    }

    public String getLabel() {
        return this.formLabels;
    }

}
