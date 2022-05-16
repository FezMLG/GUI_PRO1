package com.fezmlg.ui;

public class UIMenuOption {
    private String description;
    private Runnable runnable;
    private boolean returnAfterAction;

    public UIMenuOption(String description, Runnable runnable, boolean returnAfterAction) {
        this.description = description;
        this.runnable = runnable;
        this.returnAfterAction = returnAfterAction;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public boolean isReturnAfterAction() {
        return returnAfterAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void setReturnAfterAction(boolean returnAfterAction) {
        this.returnAfterAction = returnAfterAction;
    }
}
