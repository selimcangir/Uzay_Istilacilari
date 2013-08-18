package com.selimcangir.uzayistilacilari;

import org.andengine.util.adt.pool.GenericPool;

public class MermiHavuzu extends GenericPool {

	public static MermiHavuzu instance;
	 
    public static MermiHavuzu sharedMermiHavuzu() {
        if (instance == null)
            instance = new MermiHavuzu();
        return instance;
    }
 
    private MermiHavuzu() {
        super();
    }
 
    @Override
    protected Mermi onAllocatePoolItem() {
        return new Mermi();
    }
 
    protected void onHandleRecycleItem(final Mermi m) {
        m.mermiSekli.clearEntityModifiers();
        m.mermiSekli.clearUpdateHandlers();
        m.mermiSekli.setVisible(false);
        m.mermiSekli.detachSelf();
    }

}
