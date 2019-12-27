package com.netteans.common.structure;

/**
 * @author netteans
 */
public class NewBee extends Tree {
    NewBee newed;
    NewBee bee;

    public NewBee getNewed() {
        return newed;
    }

    public void setNewed(NewBee newed) {
        this.newed = newed;
    }

    public NewBee getBee() {
        return bee;
    }

    public void setBee(NewBee bee) {
        this.bee = bee;
    }
}
