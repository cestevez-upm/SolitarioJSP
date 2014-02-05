package upm.miw.solitaire.controllers.beans;

public class Bean {
    private String errors = "";

    public String getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !this.errors.equals("");
    }

    public void addFieldError(String fieldError) {
        if (!this.hasErrors())
            this.errors = fieldError;
        else
            this.errors += "; " + fieldError;
    }
}
